/*******************************************************************************
 * JQA (http://code.google.com/p/jqa-project/)
 * 
 * Copyright (c) 2011 Juan Ignacio Barisich.
 * 
 * JQA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * JQA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with JQA.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package ar.com.fluxit.jqa.context.factory.exception;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class RuleSetFactoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuleSetFactoryException() {
		super();
	}

	public RuleSetFactoryException(String message) {
		super(message);
	}

	public RuleSetFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuleSetFactoryException(Throwable cause) {
		super(cause);
	}

}
