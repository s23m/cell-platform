package org.s23m.cell.kernel.tests;

import static org.s23m.cell.api.Instantiation.link;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;

public class GraphVisualisationCreationTest extends GmodelTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set semanticDomain = GmodelSemanticDomains.gmodel;

		final Set gv = RepositoryStructure.graphVisualizations.addConcrete(Visualization.graphVisualization, semanticDomain);
		final Set v = gv.addConcrete(Visualization.visualizedGraph, semanticDomain);

		final Set vg_to_semanticDomain = link(Visualization.visualizedGraph_to_graph,
				Visualization.visualizedGraph_to_graph,
				Visualization.visualizedGraph,
				v,
			    GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
			    GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
			    GmodelSemanticDomains.isNavigable_NOTAPPLICABLE,
			    GmodelSemanticDomains.isContainer_FALSE,
				semanticDomain,
				semanticDomain,
			    GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
			    GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
			    GmodelSemanticDomains.isNavigable_NOTAPPLICABLE,
			    GmodelSemanticDomains.isContainer_FALSE);

		final Set details = gv.addConcrete(Visualization.details,Visualization.details);
		final Set structure = gv.addConcrete(Visualization.structure,Visualization.structure);
		final Set reuse = gv.addConcrete(Visualization.reuse,Visualization.reuse);
		final Set visibilities = gv.addConcrete(Visualization.visibilities,Visualization.visibilities);
	}

}
