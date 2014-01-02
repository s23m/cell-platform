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
package org.s23m.cell.platform.formulaProcessors.informationqualitylogic;

import org.s23m.cell.Set;
import org.s23m.cell.api.InformationQualityLogic;
import org.s23m.cell.api.SetAlgebra;
import org.s23m.cell.platform.api.FormulaEvaluation;
import org.s23m.cell.platform.formulaProcessors.FormulaEvaluator;

public class Or extends FormulaEvaluator {

	protected Or(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		super(subFormula, terms, variables, constants, values);
	}

	public static FormulaEvaluator create(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		return new Or(subFormula, terms, variables, constants, values);
	}

	@Override
	public Set evaluate() {
		Set termsForImmediateEvaluation = SetAlgebra.anEmptySet();
		Set termsForRecursiveEvaluation = SetAlgebra.anEmptySet();

		for (final Set term : this.terms) {
			if (constants.containsRepresentation(term)) {
				termsForRecursiveEvaluation = termsForRecursiveEvaluation.union(term.wrapInOrderedSet());
			} else {
				termsForImmediateEvaluation = termsForImmediateEvaluation.union(term.wrapInOrderedSet());
			}
		}

    	if (termsForImmediateEvaluation.size() == terms.size()) {
    		return InformationQualityLogic.or(this.terms);
    	} else {
        	final Set evaluatedFunction = InformationQualityLogic.or(termsForImmediateEvaluation);
        	termsForRecursiveEvaluation.union(evaluatedFunction.wrapInOrderedSet());
        	return FormulaEvaluation.addSubFormula(subFormula.container(), subFormula.category(), this.generateSemanticIdentityForEvaluationResult(), termsForRecursiveEvaluation, variables, constants);
    	}
	}
}
