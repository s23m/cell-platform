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

package org.s23m.cell.platform.api.models;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;


public class LogicalFormula {

	private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.logicalFormula, CellEngineering.organization);

	public static final Set not = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.not);
	public static final Set exist = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.exist);
	public static final Set empty = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.empty);

	public static final Set contains = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.contains);
	public static final Set smaller = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.smaller);
	public static final Set smallerEqual = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.smallerEqual);
	public static final Set greater = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.greater);
	public static final Set greaterEqual = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.greaterEqual);

	public static final Set equal = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.equal);
	public static final Set equalToRepresentation = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.equalToRepresentation);
	public static final Set and = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.and);
	public static final Set or = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.or);
	public static final Set xor = CellEngineering.logicalFormula.addConcrete(Organization.cell, CellPlatformDomain.xor);

	public static Set instantiateFeature() {

		Instantiation.arrow(coreGraphs.superSetReference, not, Formula.unaryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, exist, Formula.unaryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, empty, Formula.unaryFunction);

		Instantiation.arrow(coreGraphs.superSetReference, contains, Formula.binaryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, smaller, Formula.binaryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, greater, Formula.binaryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, smallerEqual, Formula.binaryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, greaterEqual, Formula.binaryFunction);

		Instantiation.arrow(coreGraphs.superSetReference, equal, Formula.naryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, equalToRepresentation, Formula.naryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, and, Formula.naryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, or, Formula.naryFunction);
		Instantiation.arrow(coreGraphs.superSetReference, xor, Formula.naryFunction);

		return CellEngineering.logicalFormula;
	}

}
