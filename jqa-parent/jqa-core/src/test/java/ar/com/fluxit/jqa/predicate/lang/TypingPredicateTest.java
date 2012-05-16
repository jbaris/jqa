/*******************************************************************************
 * JQA (http://github.com/jbaris/jqa)
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

import java.util.ArrayList;
import java.util.Vector;

import junit.framework.TestCase;
import ar.com.fluxit.jqa.bce.BCERepository;
import ar.com.fluxit.jqa.bce.BCERepositoryLocator;
import ar.com.fluxit.jqa.bce.Type;
import ar.com.fluxit.jqa.mock.ExceptionA;
import ar.com.fluxit.jqa.mock.typing.ClassThatExtendsClassThatImplementsInterfaceA;
import ar.com.fluxit.jqa.mock.typing.ClassThatImplementsInterfaceA;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class TypingPredicateTest extends TestCase {

	private TypingPredicate createTypingPredicate(String string) {
		final TypingPredicate result = new TypingPredicate();
		result.setFilterPredicate(new NamingPredicate(string));
		return result;
	}

	public final void testCheck() throws ClassNotFoundException {
		final BCERepository bCERepository = BCERepositoryLocator.getRepository();
		// Test class type
		testMatches(createTypingPredicate("java.lang.Number"), new Type[] { bCERepository.lookupType(Integer.class), bCERepository.lookupType(Long.class) },
				new Type[] { bCERepository.lookupType(String.class), bCERepository.lookupType(Exception.class) });
		// Test interface type
		testMatches(createTypingPredicate("java.util.List"), new Type[] { bCERepository.lookupType(ArrayList.class), bCERepository.lookupType(Vector.class) },
				new Type[] { bCERepository.lookupType(String.class), bCERepository.lookupType(Integer.class) });
		// Test inherited interfaces
		testMatches(createTypingPredicate("ar.com.fluxit.jqa.mock.InterfaceA"), new Type[] { bCERepository.lookupType(ClassThatImplementsInterfaceA.class),
				bCERepository.lookupType(ClassThatExtendsClassThatImplementsInterfaceA.class) }, new Type[] { bCERepository.lookupType(ExceptionA.class) });
	}

	private void testMatches(TypingPredicate predicate, Type[] positive, Type[] negative) {
		for (final Type element : positive) {
			assertTrue("\"" + predicate + "\" should match \"" + element + "\"", predicate.evaluate(element, null));
		}
		for (final Type element : negative) {
			assertTrue("\"" + predicate + "\" shouldn't match \"" + element + "\"", !predicate.evaluate(element, null));
		}
	}
}
