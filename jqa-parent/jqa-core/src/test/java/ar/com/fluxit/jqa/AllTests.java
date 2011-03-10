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
package ar.com.fluxit.jqa;

import junit.framework.Test;
import junit.framework.TestSuite;
import ar.com.fluxit.jqa.rule.AbstractionRuleTest;
import ar.com.fluxit.jqa.rule.AllocationRuleTest;
import ar.com.fluxit.jqa.rule.AndRuleTest;
import ar.com.fluxit.jqa.rule.InterfaceRuleTest;
import ar.com.fluxit.jqa.rule.NamingRuleTest;
import ar.com.fluxit.jqa.rule.NotRuleTest;
import ar.com.fluxit.jqa.rule.OrRuleTest;
import ar.com.fluxit.jqa.rule.ThrowingRuleTest;
import ar.com.fluxit.jqa.rule.TypingRuleTest;
import ar.com.fluxit.jqa.rule.UsageRuleTest;
import ar.com.fluxit.jqa.rule.XorRuleTest;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class AllTests {

	public static Test suite() {
		final TestSuite suite = new TestSuite(AllTests.class.getName());
		// $JUnit-BEGIN$
		suite.addTestSuite(JQACheckerTest.class);
		suite.addTestSuite(UsageRuleTest.class);
		suite.addTestSuite(XorRuleTest.class);
		suite.addTestSuite(OrRuleTest.class);
		suite.addTestSuite(TypingRuleTest.class);
		suite.addTestSuite(NamingRuleTest.class);
		suite.addTestSuite(NotRuleTest.class);
		suite.addTestSuite(AndRuleTest.class);
		suite.addTestSuite(AbstractionRuleTest.class);
		suite.addTestSuite(InterfaceRuleTest.class);
		suite.addTestSuite(AllocationRuleTest.class);
		suite.addTestSuite(ThrowingRuleTest.class);		
		// $JUnit-END$
		return suite;
	}

}
