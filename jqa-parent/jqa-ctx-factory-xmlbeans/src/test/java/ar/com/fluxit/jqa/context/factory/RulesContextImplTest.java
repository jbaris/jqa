/*******************************************************************************
 * Copyright (c) 2013 Flux IT.
 * 
 * This file is part of JQA (http://github.com/fluxitsoft/jqa).
 * 
 * JQA is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 * 
 * JQA is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with JQA. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package ar.com.fluxit.jqa.context.factory;

import java.net.URL;

import junit.framework.TestCase;
import ar.com.fluxit.jqa.bce.BCERepositoryLocator;
import ar.com.fluxit.jqa.bce.Type;
import ar.com.fluxit.jqa.context.RulesContext;
import ar.com.fluxit.jqa.context.factory.exception.RulesContextFactoryException;
import ar.com.fluxit.jqa.predicate.ContextProvidedPredicate;
import ar.com.fluxit.jqa.predicate.lang.AbstractionPredicate;
import ar.com.fluxit.jqa.predicate.lang.AbstractionPredicate.AbstractionType;
import ar.com.fluxit.jqa.predicate.lang.AllocationPredicate;
import ar.com.fluxit.jqa.predicate.lang.NamingPredicate;
import ar.com.fluxit.jqa.predicate.lang.ThrowingPredicate;
import ar.com.fluxit.jqa.predicate.lang.TypingPredicate;
import ar.com.fluxit.jqa.predicate.lang.UsagePredicate;
import ar.com.fluxit.jqa.predicate.logic.AndPredicate;
import ar.com.fluxit.jqa.predicate.logic.FalsePredicate;
import ar.com.fluxit.jqa.predicate.logic.NotPredicate;
import ar.com.fluxit.jqa.predicate.logic.OrPredicate;
import ar.com.fluxit.jqa.predicate.logic.TruePredicate;
import ar.com.fluxit.jqa.predicate.logic.XorPredicate;
import ar.com.fluxit.jqa.rule.Rule;
import ar.com.fluxit.jqa.rule.RuleSet;

/**
 * TODO javadoc
 * 
 * @author Juan Ignacio Barisich
 */
public class RulesContextImplTest extends TestCase {

	private RulesContext rulesContext;

	private Rule getRule(String ruleName) {
		for (final RuleSet ruleSet : this.rulesContext.getRuleSets()) {
			for (final Rule rule : ruleSet.getRules()) {
				if (rule.getName().equals(ruleName)) {
					return rule;
				}
			}
		}
		throw new IllegalArgumentException("Can not fin rule with name " + ruleName);
	}

	@Override
	protected void setUp() throws Exception {
		final RulesContextFactory rulesContextFactory = RulesContextFactoryLocator.getRulesContextFactory();
		final URL resource = RulesContextImplTest.class.getResource("/sample_rulescontext.xml");
		this.rulesContext = rulesContextFactory.getRulesContext(resource.getPath());
	}

	@Override
	protected void tearDown() throws Exception {
		this.rulesContext = null;
	}

	public void testGlobalPredicates() throws RulesContextFactoryException {
		assertNotNull(this.rulesContext);
		// AbstractionPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("AbstractionPredicateTest") instanceof AbstractionPredicate);
		assertTrue(((AbstractionPredicate) this.rulesContext.getGlobalPredicate("AbstractionPredicateTest")).getAbstractionType().equals(
				AbstractionType.CONCRETE));
		// AllocationPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("AllocationPredicateTest") instanceof AllocationPredicate);
		assertTrue(((AllocationPredicate) this.rulesContext.getGlobalPredicate("AllocationPredicateTest")).getFilterPredicate() instanceof TruePredicate);
		// AndPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("AndPredicateTest") instanceof AndPredicate);
		assertEquals(2, ((AndPredicate) this.rulesContext.getGlobalPredicate("AndPredicateTest")).getPredicates().length);
		assertTrue(((AndPredicate) this.rulesContext.getGlobalPredicate("AndPredicateTest")).getPredicates()[0] instanceof TruePredicate);
		assertTrue(((AndPredicate) this.rulesContext.getGlobalPredicate("AndPredicateTest")).getPredicates()[1] instanceof TruePredicate);
		// ContextProvidedPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("ContextProvidedPredicateTest") instanceof ContextProvidedPredicate);
		assertEquals("test", ((ContextProvidedPredicate) this.rulesContext.getGlobalPredicate("ContextProvidedPredicateTest")).getProvidedPredicateName());
		// FalsePredicate
		assertTrue(this.rulesContext.getGlobalPredicate("AlwaysFalsePredicate") instanceof FalsePredicate);
		// NamingPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("NamingPredicateTest") instanceof NamingPredicate);
		assertEquals("test", ((NamingPredicate) this.rulesContext.getGlobalPredicate("NamingPredicateTest")).getClassNamePattern());
		// NotPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("NotPredicateTest") instanceof NotPredicate);
		assertTrue(((NotPredicate) this.rulesContext.getGlobalPredicate("NotPredicateTest")).getPredicate() instanceof TruePredicate);
		// OrPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("OrPredicateTest") instanceof OrPredicate);
		assertEquals(2, ((OrPredicate) this.rulesContext.getGlobalPredicate("OrPredicateTest")).getPredicates().length);
		assertTrue(((OrPredicate) this.rulesContext.getGlobalPredicate("OrPredicateTest")).getPredicates()[0] instanceof TruePredicate);
		assertTrue(((OrPredicate) this.rulesContext.getGlobalPredicate("OrPredicateTest")).getPredicates()[1] instanceof TruePredicate);
		// ThrowingPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("ThrowingPredicateTest") instanceof ThrowingPredicate);
		assertTrue(((ThrowingPredicate) this.rulesContext.getGlobalPredicate("ThrowingPredicateTest")).getFilterPredicate() instanceof TruePredicate);
		// TruePredicate
		assertTrue(this.rulesContext.getGlobalPredicate("AlwaysTruePredicate") instanceof TruePredicate);
		// TypingPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("TypingPredicateTest") instanceof TypingPredicate);
		final TypingPredicate typingPredicate = (TypingPredicate) this.rulesContext.getGlobalPredicate("TypingPredicateTest");
		assertTrue(typingPredicate.getFilterPredicate() instanceof NamingPredicate);
		final NamingPredicate namingPredicate = (NamingPredicate) typingPredicate.getFilterPredicate();
		assertEquals("java.lang.Object", namingPredicate.getClassNamePattern());
		// UsagePredicate
		assertTrue(this.rulesContext.getGlobalPredicate("UsagePredicateTest") instanceof UsagePredicate);
		assertTrue(((UsagePredicate) this.rulesContext.getGlobalPredicate("UsagePredicateTest")).getFilterPredicate() instanceof TruePredicate);
		// XORPredicate
		assertTrue(this.rulesContext.getGlobalPredicate("XORPredicateTest") instanceof XorPredicate);
		assertEquals(2, ((XorPredicate) this.rulesContext.getGlobalPredicate("XORPredicateTest")).getPredicates().length);
		assertTrue(((XorPredicate) this.rulesContext.getGlobalPredicate("XORPredicateTest")).getPredicates()[0] instanceof TruePredicate);
		assertTrue(((XorPredicate) this.rulesContext.getGlobalPredicate("XORPredicateTest")).getPredicates()[1] instanceof TruePredicate);
	}

	public void testGlobalVariables() throws RulesContextFactoryException, ClassNotFoundException {
		assertNotNull(this.rulesContext);
		assertEquals("java.lang.String", this.rulesContext.getGlobalVariable("StringClass"));
		BCERepositoryLocator.init(null, "1.5", null);
		Type stringType = BCERepositoryLocator.getRepository().lookupType(String.class);
		assertTrue(this.rulesContext.getGlobalPredicate("VariablePredicate").evaluate(stringType, this.rulesContext));
		for (RuleSet rs : this.rulesContext.getRuleSets()) {
			if ("VariableRuleSet".equals(rs.getName())) {
				Rule rule = rs.getRules().iterator().next();
				assertTrue(rule.getFilterPredicate().evaluate(stringType, this.rulesContext));
				assertTrue(rule.getCheckPredicate().evaluate(stringType, this.rulesContext));
			}
		}
	}

	public void testInvalidRulesContextFile() throws RulesContextFactoryException {
		final RulesContextFactory rulesContextFactory = RulesContextFactoryLocator.getRulesContextFactory();
		final URL resource = RulesContextImplTest.class.getResource("/invalid_rulescontext.xml");
		try {
			rulesContextFactory.getRulesContext(resource.getPath());
			fail("Must throw an RulesContextFactoryException");
		} catch (final RulesContextFactoryException e) {
			// OK
		}
	}

	public void testRulePriority() throws RulesContextFactoryException {
		assertNotNull(this.rulesContext);
		final Rule rule = getRule("PriorityCheckRule");
		assertEquals(3, rule.getPriority());
	}

}
