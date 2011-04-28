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
package ar.com.fluxit.jqa.predicate.lang;

import junit.framework.TestCase;
import ar.com.fluxit.jqa.bce.JavaClass;
import ar.com.fluxit.jqa.bce.RepositoryLocator;
import ar.com.fluxit.jqa.mock.ClassA;
import ar.com.fluxit.jqa.mock.allocation.ClassThatAllocatesClassA;
import ar.com.fluxit.jqa.mock.allocation.ClassThatStaticAllocatesClassA;
import ar.com.fluxit.jqa.mock.allocation.InnerClassThatAllocatesClassA;
import ar.com.fluxit.jqa.mock.allocation.StaticInnerClassThatAllocatesClassA;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class AllocationPredicateTest extends TestCase {

	public final void testCheck() throws ClassNotFoundException {
		final String filterPredicateParentClass = ClassA.class.getName();

		testMatches(filterPredicateParentClass, ClassA.class, false);
		testMatches(filterPredicateParentClass, ClassThatAllocatesClassA.class, true);
		testMatches(filterPredicateParentClass, InnerClassThatAllocatesClassA.B.class, true);
		testMatches(filterPredicateParentClass, StaticInnerClassThatAllocatesClassA.B.class, true);
		testMatches(filterPredicateParentClass, ClassThatStaticAllocatesClassA.class, true);
	}

	private void testMatches(String filterPredicateParentClass, Class<?> usagePredicateclass, boolean matches) throws ClassNotFoundException {
		final TypingPredicate filterPredicate = new TypingPredicate();
		filterPredicate.setParentClassName(filterPredicateParentClass);
		final JavaClass clazz = RepositoryLocator.getRepository().lookupClass(usagePredicateclass);
		final AllocationPredicate allocationPredicate = new AllocationPredicate();
		allocationPredicate.setFilterPredicate(filterPredicate);
		assertEquals(matches, allocationPredicate.evaluate(clazz, null));
	}

}
