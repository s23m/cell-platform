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
import org.s23m.cell.api.models.Root;


public class RepresentationStyleCategories {

	public static final Set representationStyleCategories = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.representationStyleCategories);

	public static final Set representationStyle = representationStyleCategories.addAbstract(Cell.cell, CellPlatformDomain.representationStyle);
	public static final Set vertexRepresentationStyle = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.vertexRepresentationStyle);
	public static final Set arrowRepresentationStyle = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.arrowRepresentationStyle);
	public static final Set color = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.color);
	public static final Set lineWidth = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.lineWidth);
	public static final Set lineStyle = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.lineStyle);
	public static final Set connectorSymbol = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.connectorSymbol);
	public static final Set symbolBoundaryShape = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.symbolBoundaryShape);
	public static final Set symbolImage = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.symbolImage);
	public static final Set symbolIcon = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.symbolIcon);
	public static final Set includesName = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.includesName);
	public static final Set includesIcon = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.includesIcon);
	public static final Set includesImage = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.includesImage);
	public static final Set isBold = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.isBold);
	public static final Set isInItalics = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.isInItalics);
	public static final Set imageSize = representationStyleCategories.addConcrete(Cell.cell, CellPlatformDomain.imageSize);

	public static final Set vertexRepresentationStyle_to_superset_representationStyle = Instantiation.arrow(coreGraphs.superSetReference, vertexRepresentationStyle, representationStyle);
	public static final Set arrowRepresentationStyle_to_superset_representationStyle = Instantiation.arrow(coreGraphs.superSetReference, arrowRepresentationStyle, representationStyle);


	public static Set instantiateFeature() {

		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, Artifact.artifact, representationStyleCategories);

		final Set representationStyle_to_color = Instantiation.arrow(TimeConsciousEdge.timeConsciousEdge,
				CellPlatformDomain.representationStyle_to_color,
				representationStyle,
				representationStyle,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				color,
				color,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set representationStyle_to_lineWidth = Instantiation.arrow(TimeConsciousEdge.timeConsciousEdge,
				CellPlatformDomain.representationStyle_to_lineWidth,
				representationStyle,
				representationStyle,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				lineWidth,
				lineWidth,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set arrowRepresentationStyle_to_sourceConnectorSymbol = Instantiation.arrow(TimeConsciousEdge.timeConsciousEdge,
				CellPlatformDomain.arrowRepresentationStyle_to_sourceConnectorSymbol,
				arrowRepresentationStyle,
				arrowRepresentationStyle,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.sourceConnectorSymbol,
				connectorSymbol,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set arrowRepresentationStyle_to_targetConnectorSymbol = Instantiation.arrow(TimeConsciousEdge.timeConsciousEdge,
				CellPlatformDomain.arrowRepresentationStyle_to_targetConnectorSymbol,
				arrowRepresentationStyle,
				arrowRepresentationStyle,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.targetConnectorSymbol,
				connectorSymbol,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, and, naryLogicalFunction);
		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, and, logicalFunction);





		return representationStyleCategories;
	}

}
