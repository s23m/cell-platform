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
import org.s23m.cell.api.SetAlgebra;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.formulaProcessors.FormulaEvaluator;

public class Contains extends FormulaEvaluator {

	protected Contains(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		super(subFormula, terms, variables, constants, values);
	}

	public static FormulaEvaluator create(final Set subFormula, final Set terms, final Set variables, final Set constants, final Set values) {
		return new Contains(subFormula, terms, variables, constants, values);
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

		switch (arity) {
        case BINARY:
        	if ((2 == terms.size()) ) {
            	if (terms.extractFirst().containsSemanticMatch(terms.extractSecond())) {
            		return S23MSemanticDomains.is_TRUE;
            	} else {
            		return S23MSemanticDomains.is_FALSE;
            	}
        	}
        	return this.subFormula;
        default:
    		return this.subFormula;
		}
	}
}
