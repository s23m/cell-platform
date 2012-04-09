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

package org.s23m.cell.platform.models;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;


public class Formula  {

	private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.formula, CellEngineering.organization);

	public static final Set properSubFormula = CellEngineering.formula.addAbstract(Organization.cell, CellPlatformDomain.properSubFormula);
	//public static final Set literal = CellEngineering.formula.addAbstract(Organization.cell, CellPlatformDomain.literal);
	//private static final Set maxC = literal.addToValues(S23MSemanticDomains.maxCardinality_1);
	public static final Set unaryFunction = CellEngineering.formula.addAbstract(Organization.cell, CellPlatformDomain.unaryFunction);
	public static final Set binaryFunction = CellEngineering.formula.addAbstract(Organization.cell, CellPlatformDomain.binaryFunction);
	public static final Set naryFunction = CellEngineering.formula.addAbstract(Organization.cell, CellPlatformDomain.naryFunction);
	public static final Set variable = CellEngineering.formula.addAbstract(Organization.cell, CellPlatformDomain.variable);
	public static final Set constant = CellEngineering.formula.addAbstract(Organization.cell, CellPlatformDomain.constant);

	//private static final Set s0 = Instantiation.arrow(coreGraphs.superSetReference, literal, Organization.cell);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, properSubFormula, Organization.cell);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, unaryFunction, properSubFormula);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, binaryFunction, properSubFormula);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, naryFunction, properSubFormula);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, variable, properSubFormula);
	private static final Set s6 = Instantiation.arrow(coreGraphs.superSetReference, constant, properSubFormula);

	/*public static final Set literal_to_properSubFormula = Instantiation.arrow(TimeConsciousness.dynamicConnection,
			CellPlatformDomain.literal_to_properSubFormula,
			literal,
			literal,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.properSubFormula,
			properSubFormula,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	*/
	public static final Set unaryFunction_to_term = Instantiation.arrow(TimeConsciousness.dynamicConnection,
			CellPlatformDomain.unaryFunction_to_term,
			unaryFunction,
			unaryFunction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.term,
			properSubFormula,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set binaryFunction_to_terms = Instantiation.arrow(TimeConsciousness.dynamicConnection,
			CellPlatformDomain.binaryFunction_to_terms,
			binaryFunction,
			binaryFunction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.term,
			properSubFormula,
			coreSets.minCardinality_2,
			coreSets.maxCardinality_2,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set naryFunction_to_terms = Instantiation.arrow(TimeConsciousness.dynamicConnection,
			CellPlatformDomain.naryFunction_to_terms,
			naryFunction,
			naryFunction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.term,
			properSubFormula,
			coreSets.minCardinality_2,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set variable_to_representation = Instantiation.arrow(TimeConsciousness.dynamicConnection,
			CellPlatformDomain.variable_to_representation,
			variable,
			variable,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.representation,
			coreGraphs.vertex,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set constant_to_representation = Instantiation.arrow(TimeConsciousness.dynamicConnection,
			CellPlatformDomain.constant_to_representation,
			constant,
			constant,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.representation,
			coreGraphs.vertex,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static Set instantiateFeature() {

		return CellEngineering.formula;
	}

}
