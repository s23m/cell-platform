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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.formulaProcessors;

import org.s23m.cell.Set;
import org.s23m.cell.platform.api.models.CellPlatformAgent;
import org.s23m.cell.platform.api.models.CellPlatformDomain;

public class FormulaEvaluator {

	protected final Set subFormula;
	protected final Set terms;
	protected final Set variables;
	protected final Set constants;
	protected final Set values;
	protected final Arity arity;

	protected enum Arity {
	    ZERO, UNARY, BINARY, NARY
	}

	protected FormulaEvaluator(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		this.subFormula = subFormula;
		this.terms = terms;
		this.variables = variables;
		this.constants = constants;
		this.values = values;
		final int numberOfTerms = terms.size();
		if (numberOfTerms == 1) {
			arity = Arity.UNARY;
		} else if (numberOfTerms == 2) {
			arity = Arity.BINARY;
		} else if (numberOfTerms > 2) {
			arity = Arity.NARY;
		} else {
			arity = Arity.ZERO;
		}
	}

	public static FormulaEvaluator create(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		return new FormulaEvaluator(subFormula, terms, variables, constants, values);
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
				, org.s23m.cell.platform.api.Instantiation.toSemanticDomain(CellPlatformAgent.development));
	}
}
