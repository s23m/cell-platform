package org.s23m.cell.kernel.tests;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.Visualization;

public class VisualisationExampleTest extends S23MTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set crm_aviz = instantiationSequences.crm_aviz;
		final Set testDomain = instantiationSequences.testDomain;
		final Set crm = instantiationSequences.crm;
		final Set crm_product = instantiationSequences.crm_product;

		final Set crm_viz = crm_aviz.addConcrete(Visualization.visualizedGraph,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph", "crm schema cellVisualizations", testDomain));

		final Set crm_viz_details = crm_aviz.addConcrete(Visualization.details,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | details", "crm schema visualizedGraph | details", testDomain));

		final Set crm_viz_structure = crm_aviz.addConcrete(Visualization.structure,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure", "crm schema visualizedGraph | structures", testDomain));

		final Set crm_viz_reuse = crm_aviz.addConcrete(Visualization.reuse,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | reuse", "crm schema visualizedGraph | reuses", testDomain));

		final Set crm_viz_visibilities = crm_aviz.addConcrete(Visualization.visibilities,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | visibilities", "crm schema visualizedGraph | visibilities", testDomain));

		// addPrivilege diagram information
		final Set crm_viz_structure_diag = crm_aviz.addConcrete(Visualization.diagram,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1", "crm schema visualizedGraph | structure diag 1", testDomain));
		Instantiation.arrow(Visualization.visualizedAspect_to_diagram,
				S23MSemanticDomains.anonymous, crm_viz_structure,
				crm_viz_structure,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag,
				crm_viz_structure_diag,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);

		// addPrivilege representation information
		final Set crm_viz_structure_diag_product = crm_aviz.addConcrete(Visualization.representation,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product", "crm schema visualizedGraph | structure diag 1 | product", testDomain));
		Instantiation.arrow(Visualization.diagram_to_representation,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag,
				crm_viz_structure_diag,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);
		// addPrivilege icon to representation
		final Set crm_viz_structure_diag_product_icon = crm_aviz.addConcrete(Visualization.symbol,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product icon", "crm schema visualizedGraph | structure diag 1 | product icon", testDomain));
		crm_viz_structure_diag_product_icon.identity().setPayload("here goes the content of the icon file");
		Instantiation.arrow(Visualization.symbol_to_semantic_identity,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product_icon,
				crm_viz_structure_diag_product_icon,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product.semanticIdentity(),
				crm_viz_structure_diag_product.semanticIdentity(),
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);
		// addPrivilege coordinates to representation
		final Set crm_viz_structure_diag_product_x = crm_aviz.addConcrete(Visualization.x,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product x", "crm schema visualizedGraph | structure diag 1 | product x", testDomain));
		crm_viz_structure_diag_product_x.identity().setPayload("57");
		Instantiation.arrow(Visualization.representation_to_x,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product_x,
				crm_viz_structure_diag_product_x,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);
		final Set crm_viz_structure_diag_product_y = crm_aviz.addConcrete(Visualization.y,
				Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product y", "crm schema visualizedGraph | structure diag 1 | product y", testDomain));
		crm_viz_structure_diag_product_y.identity().setPayload("4");
		Instantiation.arrow(Visualization.representation_to_y,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product_y,
				crm_viz_structure_diag_product_y,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);
		final Set crm_viz_structure_diag_product_z = crm_aviz.addConcrete(Visualization.z,
						Instantiation.addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product z", "crm schema visualizedGraph | structure diag 1 | product z", testDomain));

				crm_viz_structure_diag_product_z.identity().setPayload("0");
		Instantiation.arrow(Visualization.representation_to_z,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
						crm_viz_structure_diag_product_z,
						crm_viz_structure_diag_product_z,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);

		Instantiation.arrow(Visualization.visualizedGraph_to_graph,
				S23MSemanticDomains.anonymous, crm_viz,
						crm_viz,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE,
						crm,
						crm,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);
		Instantiation.arrow(Visualization.representation_to_representedInstance,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE,
				crm_product,
				crm_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
		);
	}

}
