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
package ar.com.fluxit.jqa.rule;

import java.util.Collection;

import ar.com.fluxit.jqa.bce.JavaClass;
import ar.com.fluxit.jqa.bce.RepositoryLocator;
import ar.com.fluxit.jqa.context.CheckingContext;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class ThrowingRule extends FilteredRule {

	public ThrowingRule() {
		super();
	}

	public ThrowingRule(Rule filterRule) {
		super(filterRule);
	}

	@Override
	public boolean check(JavaClass clazz, CheckingContext checkingContext) {
		final Collection<JavaClass> throwedClasses = RepositoryLocator
				.getRepository().getThrows(clazz);
		for (final JavaClass throwedClass : throwedClasses) {
			if (getFilterRule().check(throwedClass, checkingContext)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Rule getFilterRule() {
		return filterRule;
	}

	@Override
	public void setFilterRule(Rule filterRule) {
		this.filterRule = filterRule;
	}

}