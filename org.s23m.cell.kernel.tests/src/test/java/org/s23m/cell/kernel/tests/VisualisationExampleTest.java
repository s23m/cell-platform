package org.s23m.cell.kernel.tests;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;

public class VisualisationExampleTest extends GmodelTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set crm_aviz = InstantiationSequences.crm_aviz;
		final Set testDomain = InstantiationSequences.testDomain;
		final Set crm = InstantiationSequences.crm;
		final Set crm_product = InstantiationSequences.crm_product;

		final Set crm_viz = crm_aviz.addConcrete(Visualization.visualizedGraph,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph", "crm schema graphVisualizations", testDomain));

		final Set crm_viz_details = crm_aviz.addConcrete(Visualization.details,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | details", "crm schema visualizedGraph | details", testDomain));

		final Set crm_viz_structure = crm_aviz.addConcrete(Visualization.structure,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure", "crm schema visualizedGraph | structures", testDomain));

		final Set crm_viz_reuse = crm_aviz.addConcrete(Visualization.reuse,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | reuse", "crm schema visualizedGraph | reuses", testDomain));

		final Set crm_viz_visibilities = crm_aviz.addConcrete(Visualization.visibilities,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | visibilities", "crm schema visualizedGraph | visibilities", testDomain));

		// add diagram information
		final Set crm_viz_structure_diag = crm_aviz.addConcrete(Visualization.diagram,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1", "crm schema visualizedGraph | structure diag 1", testDomain));
		Instantiation.link(Visualization.visualizedAspect_to_diagram,
				GmodelSemanticDomains.anonymous, crm_viz_structure,
				crm_viz_structure,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag,
				crm_viz_structure_diag,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);

		// add representation information
		final Set crm_viz_structure_diag_product = crm_aviz.addConcrete(Visualization.representation,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product", "crm schema visualizedGraph | structure diag 1 | product", testDomain));
		Instantiation.link(Visualization.diagram_to_representation,
				GmodelSemanticDomains.anonymous, crm_viz_structure_diag,
				crm_viz_structure_diag,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);
		// add icon to representation
		final Set crm_viz_structure_diag_product_icon = crm_aviz.addConcrete(Visualization.symbol,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product icon", "crm schema visualizedGraph | structure diag 1 | product icon", testDomain));
		crm_viz_structure_diag_product_icon.identity().setPayload("here goes the content of the icon file");
		Instantiation.link(Visualization.symbol_to_semantic_identity,
				GmodelSemanticDomains.anonymous, crm_viz_structure_diag_product_icon,
				crm_viz_structure_diag_product_icon,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product.semanticIdentity(),
				crm_viz_structure_diag_product.semanticIdentity(),
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);
		// add coordinates to representation
		final Set crm_viz_structure_diag_product_x = crm_aviz.addConcrete(Visualization.x,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product x", "crm schema visualizedGraph | structure diag 1 | product x", testDomain));
		crm_viz_structure_diag_product_x.identity().setPayload("57");
		Instantiation.link(Visualization.representation_to_x,
				GmodelSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product_x,
				crm_viz_structure_diag_product_x,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);
		final Set crm_viz_structure_diag_product_y = crm_aviz.addConcrete(Visualization.y,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product y", "crm schema visualizedGraph | structure diag 1 | product y", testDomain));
		crm_viz_structure_diag_product_y.identity().setPayload("4");
		Instantiation.link(Visualization.representation_to_y,
				GmodelSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product_y,
				crm_viz_structure_diag_product_y,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);
		final Set crm_viz_structure_diag_product_z = crm_aviz.addConcrete(Visualization.z,
						Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product z", "crm schema visualizedGraph | structure diag 1 | product z", testDomain));

				crm_viz_structure_diag_product_z.identity().setPayload("0");
		Instantiation.link(Visualization.representation_to_z,
				GmodelSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
						GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_TRUE,
						crm_viz_structure_diag_product_z,
						crm_viz_structure_diag_product_z,
						GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);

		Instantiation.link(Visualization.visualizedGraph_to_graph,
				GmodelSemanticDomains.anonymous, crm_viz,
						crm_viz,
						GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE,
						crm,
						crm,
						GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);
		Instantiation.link(Visualization.representation_to_representedInstance,
				GmodelSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE,
				crm_product,
				crm_product,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
		);
	}

}
