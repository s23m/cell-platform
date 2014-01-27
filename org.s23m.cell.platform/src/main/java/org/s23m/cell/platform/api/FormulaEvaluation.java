/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.api;

import static org.s23m.cell.S23MKernel.coreSets;

import java.util.List;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.core.F_InstantiationImpl;
import org.s23m.cell.core.OrderedSet;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.models.CellEngineering;
import org.s23m.cell.platform.api.models.CellPlatformAgent;
import org.s23m.cell.platform.api.models.CellPlatformDomain;
import org.s23m.cell.platform.api.models.Formula;
import org.s23m.cell.platform.api.models.LogicalFormula;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.And;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.Contains;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.Empty;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.Equals;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.EqualsToRepresentation;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.Not;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.Or;
import org.s23m.cell.platform.formulaProcessors.informationqualitylogic.Xor;

public class FormulaEvaluation {

	protected enum Arity {
	    UNARY, BINARY, NARY, CONSTANT, VARIABLE
	}

	public static final Set implementedFunctionSymbols = implementedFunctionSymbols();

	private static final Set implementedFunctionSymbols() {
		final OrderedSet implemented = (OrderedSet) F_InstantiationImpl.createResultSet();
		// n-ary functions
		implemented.addElement(LogicalFormula.and);
		implemented.addElement(LogicalFormula.or);
		implemented.addElement(LogicalFormula.xor);
		implemented.addElement(LogicalFormula.equal);
		implemented.addElement(LogicalFormula.equalToRepresentation);

		// binary functions
		implemented.addElement(LogicalFormula.contains);

		// unary functions
		implemented.addElement(LogicalFormula.not);
		implemented.addElement(LogicalFormula.empty);

		return implemented;
	}

	public static final Set evaluate(final Set formula, final Set values) {
		final Arity arity;
		final Set terms;
		final Set subFormula;
		final Set variables;
		final Set constants;

		if (CellEngineering.formula.isSuperSetOf(formula.category()).is_TRUE()) {
			// fetch the proper subformula that constitutes the root of the formula
			 subFormula = formula.extractUniqueMatch(CellPlatformDomain.literal);
		} else {
			subFormula = formula;
		}

		if (Formula.unaryFunction.isSuperSetOf(subFormula.category()).is_TRUE()) {
			arity = Arity.UNARY;
		} else if (Formula.binaryFunction.isSuperSetOf(subFormula.category()).is_TRUE()) {
			arity = Arity.BINARY;
		} else if (Formula.naryFunction.isSuperSetOf(subFormula.category()).is_TRUE()) {
			arity = Arity.NARY;
		} else if (Formula.naryFunction.isSuperSetOf(subFormula.category()).is_TRUE()) {
			arity = Arity.VARIABLE;
		} else {
			arity = Arity.CONSTANT;
		}
		switch (arity) {
			case UNARY:
				terms = subFormula.container().filterByLinkedFrom(subFormula).filter(Formula.unaryFunction_to_term).filterTo();
				break;
			case BINARY:
				terms = subFormula.container().filterByLinkedFrom(subFormula).filter(Formula.binaryFunction_to_terms).filterTo();
				break;
			case NARY:
				terms = subFormula.container().filterByLinkedFrom(subFormula).filter(Formula.naryFunction_to_terms).filterTo();
				break;
			default:
				terms = S23MSemanticDomains.is_NOTAPPLICABLE;
				break;
		}
		variables = formula.container().filter(Formula.variable).filterTo();
		constants = formula.container().filter(Formula.constant).filterTo();
		return FormulaEvaluation.evaluateSubFormula(subFormula, arity,  terms,  variables,  constants, values);
		// TODO construct new formula from new terms

	}

	public static final Set replaceTerms(final Set terms, final Set variables, final Set values) {
		OrderedSet oldTerms = (OrderedSet) terms;
		if (S23MPlatform.coreSets.orderedSet.isSuperSetOf(terms).is_TRUE()) {
			for (final Set value : values) {
				if (variables.containsRepresentation(value.category())) {
					final OrderedSet newTerms = (OrderedSet) F_InstantiationImpl.createResultSet();
					for (final Set term : oldTerms) {
						if (term.isEqualTo(value.category())) {
							newTerms.addElement(value);
						} else {
							newTerms.addElement(term);
						}
					}
					oldTerms = newTerms;
				}
			}
		}
		return oldTerms;
	}

	public static final Set evaluateEmbeddedFunctions(final Set terms, final Set variables, final Set values) {
		final OrderedSet newTerms = (OrderedSet) F_InstantiationImpl.createResultSet();
		if (S23MPlatform.coreSets.orderedSet.isSuperSetOf(terms).is_TRUE()) {
			for (final Set term : terms) {
				if (Formula.unaryFunction.isSuperSetOf(term.category()).is_TRUE()
						|| Formula.binaryFunction.isSuperSetOf(term.category()).is_TRUE()
						|| Formula.naryFunction.isSuperSetOf(term.category()).is_TRUE()) {
					newTerms.addElement(FormulaEvaluation.evaluate(term, values));
				} else {
					newTerms.addElement(term);
				}
			}
		}
		return newTerms;
	}

	public static final Set evaluateSubFormula(final Set subFormula, final Arity arity, final Set oldTerms, final Set variables, final Set constants, final Set values) {
		OrderedSet terms = (OrderedSet) FormulaEvaluation.replaceTerms(oldTerms, variables, values);
		terms = (OrderedSet) FormulaEvaluation.evaluateEmbeddedFunctions(terms, variables, values);
		final List<Set> termsL = terms.asList();
		final List<Set> oldTermsL = oldTerms.asList();
		boolean modified = false;
		if (!(termsL.size() == oldTermsL.size())) {
			modified = true;
		} else {
			for (int i = 0 ; i < oldTermsL.size() ; i++ ) {
				if(!oldTermsL.get(i).identity().isEqualTo(termsL.get(i).identity())) {
					modified = true;
				}
			}
		}
		if (modified) {
			return FormulaEvaluation.evaluateSubFormula(subFormula, arity,  terms,  variables,  constants, values);
		} else {
			// delegate to the appropriate formula evaluators
			switch (arity) {
				case NARY:
					if (subFormula.category().isEqualTo(LogicalFormula.and)) {return And.create(subFormula, terms, variables, constants, values).evaluate();}
					if (subFormula.category().isEqualTo(LogicalFormula.or)) {return Or.create(subFormula, terms, variables, constants, values).evaluate();}
					if (subFormula.category().isEqualTo(LogicalFormula.xor)) {return Xor.create(subFormula, terms, variables, constants, values).evaluate();}
					if (subFormula.category().isEqualTo(LogicalFormula.equal)) {return Equals.create(subFormula, terms, variables, constants, values).evaluate();}
					if (subFormula.category().isEqualTo(LogicalFormula.equalToRepresentation)) {return EqualsToRepresentation.create(subFormula, terms, variables, constants, values).evaluate();}
		    		if (!modified) {return subFormula;}
		    		break;
				case BINARY:
					if (subFormula.category().isEqualTo(LogicalFormula.contains)) {return Contains.create(subFormula, terms, variables, constants, values).evaluate();}
		    		if (!modified) {return subFormula;}
		    		break;
				case UNARY:
					if (subFormula.category().isEqualTo(LogicalFormula.not)) {return Not.create(subFormula, terms, variables, constants, values).evaluate();}
					if (subFormula.category().isEqualTo(LogicalFormula.empty)) {return Empty.create(subFormula, terms, variables, constants, values).evaluate();}
		    		if (!modified) {return subFormula;}
		    		break;
				default:
		    		if (!modified) {return subFormula;}
			}
		}
		final Set si = org.s23m.cell.platform.api.Instantiation.addDisjunctSemanticIdentitySet(
				CellPlatformDomain.evaluationOf.identity().name()
				+ CellPlatformDomain.openSquareBracket.identity().name()
				+ subFormula.container().identity().name()
				+ CellPlatformDomain.closeSquareBracket.identity().name()
				,
				CellPlatformDomain.evaluationOf.identity().name()
				+ CellPlatformDomain.openSquareBracket.identity().name()
				+ subFormula.container().identity().name()
				+ CellPlatformDomain.closeSquareBracket.identity().name()
				, org.s23m.cell.platform.api.Instantiation.toSemanticDomain(CellPlatformAgent.development));

		return FormulaEvaluation.addSubFormula(subFormula.container(), subFormula.category(), si, terms, variables, constants);
	}

	public static final Set addSubFormula(final Set container, final Set properSubFormulaCategory, final Set properSubFormulaSemanticIdentity, final Set terms, final Set variables, final Set constants) {
		final Set subFormula = container.addConcrete(properSubFormulaCategory, properSubFormulaSemanticIdentity);
		for (final Set term : terms) {
			if (term.category().isEqualTo(Formula.unaryFunction)) {
				final Set unaryFunction_to_term = Instantiation.arrow(Formula.unaryFunction_to_term,
						CellPlatformDomain.unaryFunctionInstance_to_term,
						Formula.unaryFunction,
						subFormula,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						coreSets.isNavigable_FALSE,
						coreSets.isContainer_TRUE,
						CellPlatformDomain.term,
						term,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						coreSets.isNavigable_TRUE,
						coreSets.isContainer_FALSE
				);
			}
			if (term.category().isEqualTo(Formula.binaryFunction)) {
				final Set unaryFunction_to_term = Instantiation.arrow(Formula.binaryFunction_to_terms,
						CellPlatformDomain.binaryFunctionInstance_to_terms,
						Formula.binaryFunction,
						subFormula,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						coreSets.isNavigable_FALSE,
						coreSets.isContainer_TRUE,
						CellPlatformDomain.term,
						term,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						coreSets.isNavigable_TRUE,
						coreSets.isContainer_FALSE
				);
			}
			if (term.category().isEqualTo(Formula.naryFunction)) {
				final Set unaryFunction_to_term = Instantiation.arrow(Formula.naryFunction_to_terms,
						CellPlatformDomain.naryFunctionInstance_to_terms,
						Formula.naryFunction,
						subFormula,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						coreSets.isNavigable_FALSE,
						coreSets.isContainer_TRUE,
						CellPlatformDomain.term,
						term,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						S23MSemanticDomains.is_NOTAPPLICABLE,
						coreSets.isNavigable_TRUE,
						coreSets.isContainer_FALSE
				);
			}

		}
		for (final Set variable : variables) {
			final Set v = container.addConcrete(Formula.variable, variable);
		}
		for (final Set constant : constants) {
			final Set v = container.addConcrete(Formula.constant, constant);
		}
		return subFormula;
	}

}