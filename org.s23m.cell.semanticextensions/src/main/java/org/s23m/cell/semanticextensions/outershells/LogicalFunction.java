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
 * The Original Code is Gmodel.
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

package org.s23m.cell.semanticextensions.outershells;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class LogicalFunction {

	public static final Set logicalExpression = Root.universalartifactengineering.addAbstract(Artifact.artifact, SemanticExtensionsDomain.logicalExpression);
	public static final Set logicalFunction = logicalExpression.addAbstract(Artifact.artifact, SemanticExtensionsDomain.logicalFunction);
	public static final Set unaryLogicalFunction = logicalExpression.addAbstract(Artifact.artifact, SemanticExtensionsDomain.unaryLogicalFunction);
	public static final Set binaryLogicalFunction = logicalExpression.addAbstract(Artifact.artifact, SemanticExtensionsDomain.binaryLogicalFunction);
	public static final Set naryLogicalFunction = logicalExpression.addAbstract(Artifact.artifact, SemanticExtensionsDomain.naryLogicalFunction);

	public static final Set not = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.not);
	public static final Set exist = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.exist);
	public static final Set empty = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.empty);

	public static final Set contains = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.contains);
	public static final Set smaller = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.smaller);
	public static final Set smallerEqual = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.smallerEqual);
	public static final Set greater = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.greater);
	public static final Set greaterEqual = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.greaterEqual);

	public static final Set equal = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.equal);
	public static final Set equalToRepresentation = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.equalToRepresentation);
	public static final Set and = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.and);
	public static final Set or = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.or);
	public static final Set xor = logicalExpression.addConcrete(Artifact.artifact, SemanticExtensionsDomain.xor);


	public static Set instantiateFeature() {

		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, and, naryLogicalFunction);
		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, and, logicalFunction);

		final Set binaryLogicalFunction_to_operands = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.binaryLogicalFunction_to_operands,
				binaryLogicalFunction,
				binaryLogicalFunction,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_TRUE,
				SemanticExtensionsDomain.operand,
				coreGraphs.vertex,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_2,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		final Set and_to_operands = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.and_to_operands,
				and,
				and,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.operand,
				logicalFunction,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set or_to_operands = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.or_to_operands,
				or,
				or,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.operand,
				logicalFunction,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set xor_to_operands = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.xor_to_operands,
				xor,
				xor,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.operand,
				logicalFunction,
				coreSets.minCardinality_2,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set not_to_operand = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.not_to_operand,
				not,
				not,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.operand,
				logicalFunction,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set exist_to_operand = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.exist_to_operand,
				exist,
				exist,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.operand,
				coreGraphs.vertex,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set empty_to_operand = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.empty_to_operand,
				empty,
				empty,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.operand,
				coreGraphs.vertex,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		Instantiation.link(coreGraphs.superSetReference, logicalFunction, coreGraphs.vertex);

		    Instantiation.link(coreGraphs.superSetReference, unaryLogicalFunction, logicalFunction);
			Instantiation.link(coreGraphs.superSetReference, binaryLogicalFunction, logicalFunction);
			Instantiation.link(coreGraphs.superSetReference, naryLogicalFunction, logicalFunction);

			Instantiation.link(coreGraphs.superSetReference, not, unaryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, exist, unaryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, empty, unaryLogicalFunction);

			Instantiation.link(coreGraphs.superSetReference, contains, binaryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, smaller, binaryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, greater, binaryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, smallerEqual, binaryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, greaterEqual, binaryLogicalFunction);

			Instantiation.link(coreGraphs.superSetReference, equal, naryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, equalToRepresentation, naryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, and, naryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, or, naryLogicalFunction);
			Instantiation.link(coreGraphs.superSetReference, xor, naryLogicalFunction);


		return logicalFunction;
	}

}
