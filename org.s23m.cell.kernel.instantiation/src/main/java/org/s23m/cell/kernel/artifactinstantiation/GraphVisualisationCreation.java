package org.s23m.cell.kernel.artifactinstantiation;

import static org.s23m.cell.api.Instantiation.arrow;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;

public class GraphVisualisationCreation extends AbstractInstantiationSequence {

	@Override
	protected void executeInstantiationSequence() {
		final Set semanticDomain = S23MSemanticDomains.cellKernel;

		final Set gv = RepositoryStructure.graphVisualizations.addConcrete(Visualization.graphVisualization, semanticDomain);
		final Set v = gv.addConcrete(Visualization.visualizedGraph, semanticDomain);

		final Set vg_to_semanticDomain = arrow(Visualization.visualizedGraph_to_graph,
				Visualization.visualizedGraph_to_graph,
				Visualization.visualizedGraph,
				v,
			    S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			    S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			    S23MSemanticDomains.isNavigable_NOTAPPLICABLE,
			    S23MSemanticDomains.isContainer_FALSE,
				semanticDomain,
				semanticDomain,
			    S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			    S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			    S23MSemanticDomains.isNavigable_NOTAPPLICABLE,
			    S23MSemanticDomains.isContainer_FALSE);

		final Set details = gv.addConcrete(Visualization.details,Visualization.details);
		final Set structure = gv.addConcrete(Visualization.structure,Visualization.structure);
		final Set reuse = gv.addConcrete(Visualization.reuse,Visualization.reuse);
		final Set visibilities = gv.addConcrete(Visualization.visibilities,Visualization.visibilities);
	}

}
