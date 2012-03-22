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

package org.s23m.cell.api.models2;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;

/**
 * {@link Visualization} implements all semantics related to visualizing an container
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 * 
 * The semantics enforced in visualizedGraph provide the basis for modelling the of 1, 2, and 3 dimensional representations of S23M artifacts
 */
public final class Visualization {

	public static final Set graphVisualization = Root.cellengineering.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("graph visualization", "graph visualizations", S23MSemanticDomains.cellKernel));

	public static final Set visualizedGraph = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("visualized graph", "visualized graphs", S23MSemanticDomains.cellKernel));
	private static final Set maxC = visualizedGraph.addToValues(S23MSemanticDomains.maxCardinality_1);
	public static final Set visualizedAspect = graphVisualization.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("visualized aspect", "visualized aspects", S23MSemanticDomains.cellKernel));
	public static final Set structure = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("structure", "structures", S23MSemanticDomains.cellKernel));
	private static final Set maxC1 = structure.addToValues(S23MSemanticDomains.maxCardinality_1);
	public static final Set reuse = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("reuse", "set of reuses", S23MSemanticDomains.cellKernel));
	private static final Set maxC2 = reuse.addToValues(S23MSemanticDomains.maxCardinality_1);
	public static final Set visibilities = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("visibilities", "set of visibilities", S23MSemanticDomains.cellKernel));
	private static final Set maxC3 = visibilities.addToValues(S23MSemanticDomains.maxCardinality_1);
	public static final Set details = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("details", "set of details", S23MSemanticDomains.cellKernel));
	private static final Set maxC4 = details.addToValues(S23MSemanticDomains.maxCardinality_1);
	public static final Set diagram = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("diagram", "diagrams", S23MSemanticDomains.cellKernel));
	public static final Set representation = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("representation", "representations", S23MSemanticDomains.cellKernel));
	public static final Set symbol = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("symbol", "symbols", S23MSemanticDomains.cellKernel));
	public static final Set x = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("x", "set of x", S23MSemanticDomains.cellKernel));
	public static final Set y = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("y", "set of y", S23MSemanticDomains.cellKernel));
	public static final Set z = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("z", "set of z", S23MSemanticDomains.cellKernel));
	public static final Set width = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("width", "set of widths", S23MSemanticDomains.cellKernel));
	public static final Set height = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("height", "set of heights", S23MSemanticDomains.cellKernel));
	public static final Set depth = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("depth", "set of depths", S23MSemanticDomains.cellKernel));

	public static final Set visualizedAspect_to_diagram = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("visualizedAspect_to_diagram", "visualizedAspect_to_diagram", S23MSemanticDomains.cellKernel),
			visualizedAspect,
			visualizedAspect,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			diagram,
			diagram,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set diagram_to_representation = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("diagram_to_representation", "diagram_to_representation", S23MSemanticDomains.cellKernel),
			diagram,
			diagram,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			representation,
			representation,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);

	public static final Set representation_to_x = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_x", "representation_to_x", S23MSemanticDomains.cellKernel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			x,
			x,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set representation_to_y = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_y", "representation_to_y", S23MSemanticDomains.cellKernel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			y,
			y,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set representation_to_z = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_z", "representation_to_z", S23MSemanticDomains.cellKernel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			z,
			z,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set representation_to_width = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_width", "representation_to_width", S23MSemanticDomains.cellKernel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			width,
			width,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set representation_to_height = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_height", "representation_to_height", S23MSemanticDomains.cellKernel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			height,
			height,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set representation_to_depth = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_depth", "representation_to_depth", S23MSemanticDomains.cellKernel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			depth,
			depth,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set representation_to_representedInstance = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_representedInstance", "representation_to_representedInstance", S23MSemanticDomains.cellKernel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			Instantiation.addDisjunctSemanticIdentitySet("represented instance", "represented instances", S23MSemanticDomains.cellKernel),
			S23MKernel.coreGraphs.graph,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);
	public static final Set visualizedGraph_to_graph = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("visualizedGraph_to_graph", "visualizedGraph_to_graph", S23MSemanticDomains.cellKernel),
			visualizedGraph,
			visualizedGraph,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			S23MKernel.coreGraphs.graph,
			S23MKernel.coreGraphs.graph,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);

	public static final Set symbol_to_semantic_identity = Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("symbol_to_semantic_identity", "symbols_to_semantic_identities", S23MSemanticDomains.cellKernel),
			symbol,
			symbol,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			SemanticDomain.semanticIdentity,
			SemanticDomain.semanticIdentity,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
			);

	public static Set instantiateFeature() {

		Instantiation.arrow(coreGraphs.superSetReference, graphVisualization, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, visualizedGraph, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, representation, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, details, visualizedAspect);
		Instantiation.arrow(coreGraphs.superSetReference, structure, visualizedAspect);
		Instantiation.arrow(coreGraphs.superSetReference, reuse, visualizedAspect);
		Instantiation.arrow(coreGraphs.superSetReference, visibilities, visualizedAspect);

		visualizedGraph.identity().makePartOfUniversalCellConcept();
		representation.identity().makePartOfUniversalCellConcept();
		symbol.identity().makePartOfUniversalCellConcept();

		return visualizedGraph;
	}

}
