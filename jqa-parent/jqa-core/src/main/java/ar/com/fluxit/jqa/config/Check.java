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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package ar.com.fluxit.jqa.config;

import ar.com.fluxit.jqa.predicate.Predicate;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class Check {
	/**
	 * Regla que determina los targets
	 */
	private Predicate filterRule;
	/**
	 * Regla que se aplica a los target
	 */
	private Predicate checkRule;

	/**
	 * Nombre del Check
	 */
	private String name;

	public Predicate getCheckRule() {
		return checkRule;
	}

	public Predicate getFilterRule() {
		return filterRule;
	}

	public String getName() {
		return name;
	}

	public void setCheckRule(Predicate rule) {
		checkRule = rule;
	}

	public void setFilterRule(Predicate targetRule) {
		filterRule = targetRule;
	}

	public void setName(String name) {
		this.name = name;
	}

}
