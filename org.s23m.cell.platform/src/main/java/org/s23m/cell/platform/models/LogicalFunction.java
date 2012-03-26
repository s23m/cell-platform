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


public class LogicalFunction {

	//private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.logicalExpression, CellEngineering.cellContent);
	private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.logicalExpression, CellEngineering.organization);

	//public static final Set logicalExpression = Root.cellengineering.addAbstract(CellContent.cell, CellPlatformDomain.logicalExpression);
	public static final Set logicalFunction = CellEngineering.logicalExpression.addAbstract(Organization.cell, CellPlatformDomain.logicalFunction);
	public static final Set unaryLogicalFunction = CellEngineering.logicalExpression.addAbstract(Organization.cell, CellPlatformDomain.unaryLogicalFunction);
	public static final Set binaryLogicalFunction = CellEngineering.logicalExpression.addAbstract(Organization.cell, CellPlatformDomain.binaryLogicalFunction);
	public static final Set naryLogicalFunction = CellEngineering.logicalExpression.addAbstract(Organization.cell, CellPlatformDomain.naryLogicalFunction);

	public static final Set not = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.not);
	public static final Set exist = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.exist);
	public static final Set empty = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.empty);

	public static final Set contains = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.contains);
	public static final Set smaller = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.smaller);
	public static final Set smallerEqual = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.smallerEqual);
	public static final Set greater = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.greater);
	public static final Set greaterEqual = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.greaterEqual);

	public static final Set equal = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.equal);
	public static final Set equalToRepresentation = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.equalToRepresentation);
	public static final Set and = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.and);
	public static final Set or = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.or);
	public static final Set xor = CellEngineering.logicalExpression.addConcrete(Organization.cell, CellPlatformDomain.xor);


	public static Set instantiateFeature() {

		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, and, naryLogicalFunction);
		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, and, logicalFunction);

		final Set binaryLogicalFunction_to_operands = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.binaryLogicalFunction_to_operands,
				binaryLogicalFunction,
				binaryLogicalFunction,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_TRUE,
				CellPlatformDomain.operand,
				coreGraphs.vertex,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_2,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		final Set and_to_operands = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.and_to_operands,
				and,
				and,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.operand,
				logicalFunction,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set or_to_operands = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.or_to_operands,
				or,
				or,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.operand,
				logicalFunction,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set xor_to_operands = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.xor_to_operands,
				xor,
				xor,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.operand,
				logicalFunction,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set not_to_operand = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.not_to_operand,
				not,
				not,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.operand,
				logicalFunction,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set exist_to_operand = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.exist_to_operand,
				exist,
				exist,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.operand,
				coreGraphs.vertex,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set empty_to_operand = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.empty_to_operand,
				empty,
				empty,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.operand,
				coreGraphs.vertex,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		Instantiation.arrow(coreGraphs.superSetReference, logicalFunction, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, unaryLogicalFunction, logicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, binaryLogicalFunction, logicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, naryLogicalFunction, logicalFunction);

		Instantiation.arrow(coreGraphs.superSetReference, not, unaryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, exist, unaryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, empty, unaryLogicalFunction);

		Instantiation.arrow(coreGraphs.superSetReference, contains, binaryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, smaller, binaryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, greater, binaryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, smallerEqual, binaryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, greaterEqual, binaryLogicalFunction);

		Instantiation.arrow(coreGraphs.superSetReference, equal, naryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, equalToRepresentation, naryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, and, naryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, or, naryLogicalFunction);
		Instantiation.arrow(coreGraphs.superSetReference, xor, naryLogicalFunction);

		return logicalFunction;
	}

}
