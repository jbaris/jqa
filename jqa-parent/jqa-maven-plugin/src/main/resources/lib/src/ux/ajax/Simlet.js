#-------------------------------------------------------------------------------
# Copyright (c) 2013 Flux IT.
# 
# This file is part of JQA (http://github.com/fluxitsoft/jqa).
# 
# JQA is free software: you can redistribute it and/or modify it 
# under the terms of the GNU Lesser General Public License as 
# published by the Free Software Foundation, either version 3 of 
# the License, or (at your option) any later version.
# 
# JQA is distributed in the hope that it will be useful, but WITHOUT 
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
# or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General 
# Public License for more details.
# 
# You should have received a copy of the GNU Lesser General Public 
# License along with JQA. If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------
/**
 * @author Don Griffin
 *
 * This is a base class for more advanced "simlets" (simulated servers). A simlet is asked
 * to provide a response given a {@link Ext.ux.ajax.SimXhr} instance.
 */
Ext.define('Ext.ux.ajax.Simlet', function () {
    var urlRegex = /([^?#]*)(#.*)?$/,
        dateRegex = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/,
        intRegex = /^[+-]?\d+$/,
        floatRegex = /^[+-]?\d+\.\d+$/;

    function parseParamValue (value) {
        var m;

        if (Ext.isDefined(value)) {
            value = decodeURIComponent(value);

            if (intRegex.test(value)) {
                value = parseInt(value, 10);
            } else if (floatRegex.test(value)) {
                value = parseFloat(value);
            } else if (!!(m = dateRegex.test(value))) {
                value = new Date(Date.UTC(+m[1], +m[2]-1, +m[3], +m[4], +m[5], +m[6]));
            }
        }

        return value;
    }

    return {
        alias: 'simlet.basic',

        isSimlet: true,

        responseProps: ['responseText', 'responseXML', 'status', 'statusText'],

        /**
         * @cfg {Number} responseText
         */

        /**
         * @cfg {Number} responseXML
         */

        /**
         * @cfg {Object} responseHeaders
         */

        /**
         * @cfg {Number} status
         */
        status: 200,

        /**
         * @cfg {String} statusText
         */
        statusText: 'OK',

        constructor: function (config) {
            Ext.apply(this, config);
        },

        doGet: function (ctx) {
            var me = this,
                ret = {};

            Ext.each(me.responseProps, function (prop) {
                if (prop in me) {
                    ret[prop] = me[prop];
                }
            });

            return ret;
        },

        doRedirect: function (ctx) {
            return false;
        },

        /**
         * Performs the action requested by the given XHR and returns an object to be applied
         * on to the XHR (containing `status`, `responseText`, etc.). For the most part,
         * this is delegated to `doMethod` methods on this class, such as `doGet`.
         *
         * @param {Ext.ux.ajax.SimXhr} xhr The simulated XMLHttpRequest instance.
         * @returns {Object} The response properties to add to the XMLHttpRequest.
         */
        exec: function (xhr) {
            var me = this,
                ret = {},
                method = 'do' + Ext.String.capitalize(xhr.method.toLowerCase()), // doGet
                fn = me[method];

            if (fn) {
                ret = fn.call(me, me.getCtx(xhr.method, xhr.url, xhr));
            } else {
                ret = { status: 405, statusText: 'Method Not Allowed' };
            }

            return ret;
        },

        getCtx: function (method, url, xhr) {
            return {
                method: method,
                params: this.parseQueryString(url),
                url: url,
                xhr: xhr
            };
        },

        openRequest: function (method, url, options, async) {
            var ctx = this.getCtx(method, url),
                redirect = this.doRedirect(ctx),
                xhr;

            if (redirect) {
                xhr = redirect;
            } else {
                xhr = new Ext.ux.ajax.SimXhr({
                    mgr: this.manager,
                    simlet: this,
                    options: options
                });
                xhr.open(method, url, async);
            }

            return xhr;
        },

        parseQueryString : function (str) {
            var m = urlRegex.exec(str),
                ret = {},
                key,
                value,
                i, n;

            if (m && m[1]) {
                var pair, parts = m[1].split('&');

                for (i = 0, n = parts.length; i < n; ++i) {
                    if ((pair = parts[i].split('='))[0]) {
                        key = decodeURIComponent(pair.shift());
                        value = parseParamValue((pair.length > 1) ? pair.join('=') : pair[0]);

                        if (!(key in ret)) {
                            ret[key] = value;
                        } else if (Ext.isArray(ret[key])) {
                            ret[key].push(value);
                        } else {
                            ret[key] = [ret[key], value];
                        }
                    }
                }
            }

            return ret;
        },

        redirect: function (method, url, params) {
            switch (arguments.length) {
                case 2:
                    if (typeof url == 'string') {
                        break;
                    }
                    params = url;
                    // fall...
                case 1:
                    url = method;
                    method = 'GET';
                    break;
            }

            if (params) {
                url = Ext.urlAppend(url, Ext.Object.toQueryString(params));
            }
            return this.manager.openRequest(method, url);
        }
    };
}());
