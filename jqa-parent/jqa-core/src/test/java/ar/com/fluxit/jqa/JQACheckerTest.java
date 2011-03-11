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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.fluxit.jqa.mock.ClassA;
import ar.com.fluxit.jqa.predicate.Predicate;
import ar.com.fluxit.jqa.predicate.logic.FalsePredicate;
import ar.com.fluxit.jqa.predicate.logic.TruePredicate;
import ar.com.fluxit.jqa.result.CheckingResult;
import ar.com.fluxit.jqa.rule.MockRule;
import ar.com.fluxit.jqa.rule.MockRuleSet;
import ar.com.fluxit.jqa.rule.Rule;
import ar.com.fluxit.jqa.rule.RuleSet;
import ar.com.fluxit.jqa.util.FileUtils;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class JQACheckerTest extends TestCase {

	private Logger log;

	private Rule createRule(Predicate instance, Predicate instance2) {
		return new MockRule(instance, instance2);
	}

	private RuleSet createRuleSet(Predicate instance, Predicate instance2) {
		MockRuleSet result = new MockRuleSet();
		result.addRule(createRule(instance, instance2));
		return result;
	}

	private Collection<RuleSet> createRuleSets(Predicate predicate,
			Predicate predicate2) {
		Collection<RuleSet> result = new ArrayList<RuleSet>();
		result.add(createRuleSet(predicate, predicate2));
		return result;
	}

	public RuleSetChecker getChecker() {
		return RuleSetChecker.INSTANCE;
	}

	protected Logger getLog() {
		return log;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		log = LoggerFactory.getLogger(JQACheckerTest.class);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		log = null;
	}

	public final void testCheckRuleFail() {
		final Collection<File> classFiles = FileUtils.INSTANCE
				.getClassFiles(ClassA.class);
		Collection<RuleSet> configuration = createRuleSets(
				TruePredicate.INSTANCE, FalsePredicate.INSTANCE);
		final CheckingResult result = getChecker().check(classFiles,
				configuration, getLog());
		assertNotNull(result);
		assertNotNull(result.getDate());
		assertNotNull(result.getRuleExecutionsFailed());
		assertEquals(1, result.getRuleExecutionsFailed().size());
	}

	public final void testCheckRuleSuccess() {
		final Collection<File> classFiles = FileUtils.INSTANCE
				.getClassFiles(ClassA.class);
		Collection<RuleSet> configuration = createRuleSets(
				TruePredicate.INSTANCE, TruePredicate.INSTANCE);
		final CheckingResult result = getChecker().check(classFiles,
				configuration, getLog());
		assertNotNull(result);
		assertNotNull(result.getDate());
		assertNotNull(result.getRuleExecutionsFailed());
		assertTrue(result.getRuleExecutionsFailed().isEmpty());
	}

	public final void testCheckWithNoFiles() {
		final Collection<File> classFiles = new ArrayList<File>();
		Collection<RuleSet> configuration = createRuleSets(
				TruePredicate.INSTANCE, TruePredicate.INSTANCE);
		final CheckingResult result = getChecker().check(classFiles,
				configuration, getLog());
		assertNotNull(result);
		assertNotNull(result.getDate());
		assertNotNull(result.getRuleExecutionsFailed());
		assertTrue(result.getRuleExecutionsFailed().isEmpty());
	}

	public final void testFilterRuleFail() {
		final Collection<File> classFiles = FileUtils.INSTANCE
				.getClassFiles(ClassA.class);

		Collection<RuleSet> configuration = createRuleSets(
				TruePredicate.INSTANCE, FalsePredicate.INSTANCE);
		final CheckingResult result = getChecker().check(classFiles,
				configuration, getLog());
		assertNotNull(result);
		assertNotNull(result.getDate());
		assertNotNull(result.getRuleExecutionsFailed());
		assertEquals(1, result.getRuleExecutionsFailed().size());
	}

	public final void testFilterRuleSuccess() {
		final Collection<File> classFiles = FileUtils.INSTANCE
				.getClassFiles(ClassA.class);
		Collection<RuleSet> configuration = createRuleSets(
				TruePredicate.INSTANCE, TruePredicate.INSTANCE);
		final CheckingResult result = getChecker().check(classFiles,
				configuration, getLog());
		assertNotNull(result);
		assertNotNull(result.getDate());
		assertNotNull(result.getRuleExecutionsFailed());
		assertTrue(result.getRuleExecutionsFailed().isEmpty());
	}

}
