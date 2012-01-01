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


public class RepresentationStyleCategories {

	public static final Set representationStyleCategories = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.representationStyleCategories);

	public static final Set representationStyle = representationStyleCategories.addAbstract(Artifact.artifact, SemanticExtensionsDomain.representationStyle);
	public static final Set vertexRepresentationStyle = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.vertexRepresentationStyle);
	public static final Set linkRepresentationStyle = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.linkRepresentationStyle);
	public static final Set color = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.color);
	public static final Set lineWidth = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.lineWidth);
	public static final Set lineStyle = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.lineStyle);
	public static final Set connectorSymbol = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.connectorSymbol);
	public static final Set symbolBoundaryShape = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.symbolBoundaryShape);
	public static final Set symbolImage = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.symbolImage);
	public static final Set symbolIcon = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.symbolIcon);
	public static final Set includesName = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.includesName);
	public static final Set includesIcon = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.includesIcon);
	public static final Set includesImage = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.includesImage);
	public static final Set isBold = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.isBold);
	public static final Set isInItalics = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.isInItalics);
	public static final Set imageSize = representationStyleCategories.addConcrete(Artifact.artifact, SemanticExtensionsDomain.imageSize);

	public static final Set vertexRepresentationStyle_to_superset_representationStyle = Instantiation.link(coreGraphs.superSetReference, vertexRepresentationStyle, representationStyle);
	public static final Set linkRepresentationStyle_to_superset_representationStyle = Instantiation.link(coreGraphs.superSetReference, linkRepresentationStyle, representationStyle);


	public static Set instantiateFeature() {

		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, Artifact.artifact, representationStyleCategories);

		final Set representationStyle_to_color = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.representationStyle_to_color,
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
		final Set representationStyle_to_lineWidth = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.representationStyle_to_lineWidth,
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
		final Set linkRepresentationStyle_to_sourceConnectorSymbol = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.linkRepresentationStyle_to_sourceConnectorSymbol,
				linkRepresentationStyle,
				linkRepresentationStyle,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.sourceConnectorSymbol,
				connectorSymbol,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set linkRepresentationStyle_to_targetConnectorSymbol = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.linkRepresentationStyle_to_targetConnectorSymbol,
				linkRepresentationStyle,
				linkRepresentationStyle,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.targetConnectorSymbol,
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
