package org.s23m.cell.platform.formulaProcessors;

import org.s23m.cell.Set;
import org.s23m.cell.platform.models.CellPlatform;
import org.s23m.cell.platform.models.CellPlatformDomain;


public  class FormulaEvaluator {

	protected   Set subFormula;
	protected   Set terms;
	protected   Set variables;
	protected   Set constants;
	protected   Set values;
	protected   Arity arity;

	protected enum Arity {
	    ZERO, UNARY, BINARY, NARY
	}

	protected FormulaEvaluator(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		this.subFormula = subFormula;
		this.terms = terms;
		this.variables = variables;
		this.constants = constants;
		this.values = values;
		if (terms.size() == 1) {arity = Arity.UNARY;}
		else {
			if (terms.size() == 2) {arity = Arity.BINARY;}
			else {
				if (terms.size() > 2) {arity = Arity.NARY;}
				else {arity = Arity.ZERO;}
			}
		}
	}

	public static FormulaEvaluator create(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		 return  new FormulaEvaluator(subFormula, terms, variables, constants, values);
	}

	public Set evaluate() {
		switch (arity) {
        default:
    		return this.subFormula;
		}
	}

	protected Set generateSemanticIdentityForEvaluationResult() {

	return org.s23m.cell.platform.api.Instantiation.addDisjunctSemanticIdentitySet(
			CellPlatformDomain.evaluationOf.identity().name()
			+ CellPlatformDomain.openSquareBracket.identity().name()
			+ this.subFormula.identity().name()
			+ CellPlatformDomain.closeSquareBracket.identity().name()
			,
			CellPlatformDomain.evaluationOf.identity().name()
			+ CellPlatformDomain.openSquareBracket.identity().name()
			+ this.subFormula.identity().name()
			+ CellPlatformDomain.closeSquareBracket.identity().name()
			, org.s23m.cell.platform.api.Instantiation.toSemanticDomain(CellPlatform.development));
	}
}
