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

import junit.framework.TestCase;
import ar.com.fluxit.jqa.bce.JavaClass;
import ar.com.fluxit.jqa.bce.RepositoryLocator;
import ar.com.fluxit.jqa.context.CheckingContext;
import ar.com.fluxit.jqa.mock.ClassA;
import ar.com.fluxit.jqa.mock.ExceptionA;
import ar.com.fluxit.jqa.mock.MockCheckingContext;
import ar.com.fluxit.jqa.mock.throwing.ClassThatDoesntThrowsExceptionA;
import ar.com.fluxit.jqa.mock.throwing.ClassThatThrowsExceptionAOnConstructor;
import ar.com.fluxit.jqa.mock.throwing.ClassThatThrowsExceptionAOnMethod;
import ar.com.fluxit.jqa.mock.throwing.ClassThatThrowsExceptionAOnStaticMethod;
import ar.com.fluxit.jqa.mock.throwing.ClassThatThrowsUncheckedExceptionOnConstructor;
import ar.com.fluxit.jqa.mock.throwing.ClassThatThrowsUncheckedExceptionOnMethod;
import ar.com.fluxit.jqa.mock.throwing.ClassThatThrowsUncheckedExceptionOnStaticMethod;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class ThrowingRuleTest extends TestCase {

	private CheckingContext checkingContext;

	private CheckingContext getCheckingContext() {
		return checkingContext;
	}

	@Override
	protected void setUp() throws Exception {
		checkingContext = new MockCheckingContext();
	}

	@Override
	protected void tearDown() throws Exception {
		checkingContext = null;
	}

	public final void testCheck() throws ClassNotFoundException {
		final String filterRuleParentClass = ExceptionA.class.getName();
		// No exceptions
		testMatches(filterRuleParentClass, ClassA.class, false);
		testMatches(filterRuleParentClass,
				ClassThatDoesntThrowsExceptionA.class, false);
		// Checked exceptions
		testMatches(filterRuleParentClass,
				ClassThatThrowsExceptionAOnConstructor.class, true);
		testMatches(filterRuleParentClass,
				ClassThatThrowsExceptionAOnMethod.class, true);
		testMatches(filterRuleParentClass,
				ClassThatThrowsExceptionAOnStaticMethod.class, true);
		// Unchecked exceptions
		final String runtimeExceptionClassName = RuntimeException.class
				.getName();
		testMatches(runtimeExceptionClassName,
				ClassThatThrowsUncheckedExceptionOnMethod.class, true);
		testMatches(runtimeExceptionClassName,
				ClassThatThrowsUncheckedExceptionOnConstructor.class, true);
		testMatches(runtimeExceptionClassName,
				ClassThatThrowsUncheckedExceptionOnStaticMethod.class, true);
	}

	private void testMatches(String filterRuleParentClass,
			Class<?> usageRuleclass, boolean matches)
			throws ClassNotFoundException {
		final Rule filterRule = new TypingRule(filterRuleParentClass);
		final JavaClass clazz = RepositoryLocator.getRepository().lookupClass(
				usageRuleclass);
		assertEquals(matches,
				new ThrowingRule(filterRule).check(clazz, getCheckingContext()));
	}

}