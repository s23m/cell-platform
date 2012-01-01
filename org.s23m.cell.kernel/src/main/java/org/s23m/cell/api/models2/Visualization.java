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
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api.models2;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;

import org.s23m.cell.G;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.SemanticDomain;

/**
 * {@link Visualization} implements all semantics related to visualizing an container
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 * 
 * The semantics enforced in visualizedGraph provide the basis for modelling the of 1, 2, and 3 dimensional representations of Gmodel artifacts
 */
public final class Visualization {

	public static final Set graphVisualization = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("graph visualizedGraph", "graph visualizations", GmodelSemanticDomains.gmodel));

	public static final Set visualizedGraph = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("visualized graph", "visualized graphs", GmodelSemanticDomains.gmodel));
	private static final Set maxC = visualizedGraph.addToValues(GmodelSemanticDomains.maxCardinality_1);
	public static final Set visualizedAspect = graphVisualization.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("visualized aspect", "visualized aspects", GmodelSemanticDomains.gmodel));
	public static final Set structure = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("structure", "structures", GmodelSemanticDomains.gmodel));
	private static final Set maxC1 = structure.addToValues(GmodelSemanticDomains.maxCardinality_1);
	public static final Set reuse = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("reuse", "set of reuses", GmodelSemanticDomains.gmodel));
	private static final Set maxC2 = reuse.addToValues(GmodelSemanticDomains.maxCardinality_1);
	public static final Set visibilities = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("visibilities", "set of visibilities", GmodelSemanticDomains.gmodel));
	private static final Set maxC3 = visibilities.addToValues(GmodelSemanticDomains.maxCardinality_1);
	public static final Set details = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("details", "set of details", GmodelSemanticDomains.gmodel));
	private static final Set maxC4 = details.addToValues(GmodelSemanticDomains.maxCardinality_1);
	public static final Set diagram = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("diagram", "diagrams", GmodelSemanticDomains.gmodel));
	public static final Set representation = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("representation", "representations", GmodelSemanticDomains.gmodel));
	public static final Set symbol = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("symbol", "symbols", GmodelSemanticDomains.gmodel));
	public static final Set x = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("x", "set of x", GmodelSemanticDomains.gmodel));
	public static final Set y = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("y", "set of y", GmodelSemanticDomains.gmodel));
	public static final Set z = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("z", "set of z", GmodelSemanticDomains.gmodel));
	public static final Set width = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("width", "set of widths", GmodelSemanticDomains.gmodel));
	public static final Set height = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("height", "set of heights", GmodelSemanticDomains.gmodel));
	public static final Set depth = graphVisualization.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("depth", "set of depths", GmodelSemanticDomains.gmodel));

	public static final Set visualizedAspect_to_diagram = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("visualizedAspect_to_diagram", "visualizedAspect_to_diagram", GmodelSemanticDomains.gmodel),
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
	public static final Set diagram_to_representation = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("diagram_to_representation", "diagram_to_representation", GmodelSemanticDomains.gmodel),
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

	public static final Set representation_to_x = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_x", "representation_to_x", GmodelSemanticDomains.gmodel),
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
	public static final Set representation_to_y = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_y", "representation_to_y", GmodelSemanticDomains.gmodel),
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
	public static final Set representation_to_z = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_z", "representation_to_z", GmodelSemanticDomains.gmodel),
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
	public static final Set representation_to_width = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_width", "representation_to_width", GmodelSemanticDomains.gmodel),
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
	public static final Set representation_to_height = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_height", "representation_to_height", GmodelSemanticDomains.gmodel),
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
	public static final Set representation_to_depth = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_depth", "representation_to_depth", GmodelSemanticDomains.gmodel),
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
	public static final Set representation_to_representedInstance = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("representation_to_representedInstance", "representation_to_representedInstance", GmodelSemanticDomains.gmodel),
			representation,
			representation,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			Instantiation.addDisjunctSemanticIdentitySet("represented instance", "represented instances", GmodelSemanticDomains.gmodel),
			G.coreGraphs.graph,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set visualizedGraph_to_graph = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("visualizedGraph_to_graph", "visualizedGraph_to_graph", GmodelSemanticDomains.gmodel),
			visualizedGraph,
			visualizedGraph,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			G.coreGraphs.graph,
			G.coreGraphs.graph,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set symbol_to_semantic_identity = Instantiation.link(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("symbol_to_semantic_identity", "symbols_to_semantic_identities", GmodelSemanticDomains.gmodel),
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

		Instantiation.link(coreGraphs.superSetReference, graphVisualization, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, visualizedGraph, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, representation, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, details, visualizedAspect);
		Instantiation.link(coreGraphs.superSetReference, structure, visualizedAspect);
		Instantiation.link(coreGraphs.superSetReference, reuse, visualizedAspect);
		Instantiation.link(coreGraphs.superSetReference, visibilities, visualizedAspect);

		visualizedGraph.identity().makePartOfUniversalArtifactConcept();
		representation.identity().makePartOfUniversalArtifactConcept();
		symbol.identity().makePartOfUniversalArtifactConcept();

		return visualizedGraph;
	}

}
