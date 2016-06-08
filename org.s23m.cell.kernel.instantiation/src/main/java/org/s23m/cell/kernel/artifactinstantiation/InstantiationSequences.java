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

package org.s23m.cell.kernel.artifactinstantiation;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Instantiation.addSemanticDomain;
import static org.s23m.cell.api.Instantiation.arrow;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.EnterpriseArchitecture;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;

/**
 * A lazily-initialised singleton
 */
public class InstantiationSequences {

	private static InstantiationSequences instance;

	// just for the fun of it we create an instance of an edge
	public final Set testDomain = addSemanticDomain("test domain", "test domains", S23MSemanticDomains.sandboxSemanticDomains);

	public final Set v1 = Instantiation.arrow(coreGraphs.visibility, Root.sandbox, testDomain);

	public final Set whoToWho = arrow(coreGraphs.edge, addDisjunctSemanticIdentitySet("who to who", "set of who to who", testDomain),
//			F_SemanticStateOfInMemoryModel.addAnonymousDisjunctSemanticIdentitySet(testDomain), EnterpriseArchitecture.who,
			EnterpriseArchitecture.who, EnterpriseArchitecture.who,
			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE,
//			F_SemanticStateOfInMemoryModel.addAnonymousDisjunctSemanticIdentitySet(testDomain), EnterpriseArchitecture.who,
			EnterpriseArchitecture.who, EnterpriseArchitecture.who,
			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);

	public final Set acmeEA = RepositoryStructure.domainengineering.addConcrete(EnterpriseArchitecture.enterpriseArchitectureGraph,
			addDisjunctSemanticIdentitySet("ACME Enterprise Architecture", "set of ACME Enterprise Architecture", testDomain));

	public final Set acmeMelbourne = RepositoryStructure.applicationengineering.addConcrete(acmeEA,
			addDisjunctSemanticIdentitySet("ACME Melbourne Enterprise Architecture", "set of ACME Melbourne Enterprise Architecture", testDomain));

	public final Set entityrelationshipschema = RepositoryStructure.domainengineering.addConcrete(coreGraphs.vertex,
			addDisjunctSemanticIdentitySet("entity relationship schema", "entity relationship schemas", testDomain));
	public final Set hierarchicalerschema = RepositoryStructure.domainengineering.addConcrete(coreGraphs.vertex,
			addDisjunctSemanticIdentitySet("hierarchical entity relationship schema", "hierarchical entity relationship schemas", testDomain));

	public final Set crm = RepositoryStructure.applicationengineering.addConcrete(entityrelationshipschema,
			addDisjunctSemanticIdentitySet("customer relationship management", "customer relationship management", testDomain));
	public final Set entity = entityrelationshipschema.addConcrete(coreGraphs.vertex, addDisjunctSemanticIdentitySet("entity", "entities" , testDomain));

	public final Set crm_product = crm.addConcrete(entity,
			addDisjunctSemanticIdentitySet("product", "products" , testDomain));

	public Set product_to_price;
	public Set order;


	public final Set crm_aviz = RepositoryStructure.graphVisualizations.addConcrete(Visualization.graphVisualization,
			addDisjunctSemanticIdentitySet("crm schema container visualizedGraph", "crm schema container cellVisualizations", testDomain));

	public Set crm_viz_structure_diag_product;

	private InstantiationSequences() {
	}

	public static InstantiationSequences getInstance() {
		if (instance == null) {
			instance = new InstantiationSequences();
		}
		return instance;
	}


	public void visualizationExample() {
		final Set crm_viz = crm_aviz.addConcrete(Visualization.visualizedGraph,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph", "crm schema cellVisualizations", testDomain));

		final Set crm_viz_details = crm_aviz.addConcrete(Visualization.details,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | details", "crm schema visualizedGraph | details", testDomain));

		final Set crm_viz_structure = crm_aviz.addConcrete(Visualization.structure,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure", "crm schema visualizedGraph | structures", testDomain));

		final Set crm_viz_reuse = crm_aviz.addConcrete(Visualization.reuse,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | reuse", "crm schema visualizedGraph | reuses", testDomain));

		final Set crm_viz_visibilities = crm_aviz.addConcrete(Visualization.visibilities,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | visibilities", "crm schema visualizedGraph | visibilities", testDomain));

		// addPrivilege diagram information
		final Set crm_viz_structure_diag = crm_aviz.addConcrete(Visualization.diagram,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1", "crm schema visualizedGraph | structure diag 1", testDomain));
		arrow(Visualization.visualizedAspect_to_diagram,
				S23MSemanticDomains.anonymous, crm_viz_structure,
				crm_viz_structure,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag,
				crm_viz_structure_diag,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);

		// addPrivilege representation information
		crm_viz_structure_diag_product = crm_aviz.addConcrete(Visualization.representation,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product", "crm schema visualizedGraph | structure diag 1 | product", testDomain));
		arrow(Visualization.diagram_to_representation,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag,
				crm_viz_structure_diag,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);
		// addPrivilege icon to representation
		final Set crm_viz_structure_diag_product_icon = crm_aviz.addConcrete(Visualization.symbol,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product icon", "crm schema visualizedGraph | structure diag 1 | product icon", testDomain));
		crm_viz_structure_diag_product_icon.identity().setPayload("here goes the content of the icon file");
		arrow(Visualization.symbol_to_semantic_identity,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product_icon,
				crm_viz_structure_diag_product_icon,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product.semanticIdentity(),
				crm_viz_structure_diag_product.semanticIdentity(),
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);
		// addPrivilege coordinates to representation
		final Set crm_viz_structure_diag_product_x = crm_aviz.addConcrete(Visualization.x,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product x", "crm schema visualizedGraph | structure diag 1 | product x", testDomain));
		crm_viz_structure_diag_product_x.identity().setPayload("57");
		arrow(Visualization.representation_to_x,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product_x,
				crm_viz_structure_diag_product_x,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);
		final Set crm_viz_structure_diag_product_y = crm_aviz.addConcrete(Visualization.y,
				addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product y", "crm schema visualizedGraph | structure diag 1 | product y", testDomain));
		crm_viz_structure_diag_product_y.identity().setPayload("4");
		arrow(Visualization.representation_to_y,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				crm_viz_structure_diag_product_y,
				crm_viz_structure_diag_product_y,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);
		final Set crm_viz_structure_diag_product_z = crm_aviz.addConcrete(Visualization.z,
						addDisjunctSemanticIdentitySet("crm schema visualizedGraph | structure diag 1 | product z", "crm schema visualizedGraph | structure diag 1 | product z", testDomain));
				crm_viz_structure_diag_product_z.identity().setPayload("0");
		arrow(Visualization.representation_to_z,
				S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
						crm_viz_structure_diag_product_z,
						crm_viz_structure_diag_product_z,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
						);
		/* *** exclude as this causes cardinality violation if script is run more than once
		F_SemanticStateOfInMemoryModel.link(Visualization.visualizedGraph_to_graph,
				F_SemanticStateOfInMemoryModel.S23MSemanticDomains.anonymous, crm_viz,
						crm_viz,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE,
						crm,
						crm,
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
						);

		F_SemanticStateOfInMemoryModel.link(Visualization.representation_to_representedInstance,
				F_SemanticStateOfInMemoryModel.S23MSemanticDomains.anonymous, crm_viz_structure_diag_product,
				crm_viz_structure_diag_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE,
				crm_product,
				crm_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);
		*** exclude as this causes cardinality violation if script is run more than once */

	}

	public Set createGraphVisualization(final Set semanticDomain) {
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
		return gv;
	}

	public void addIcon(final Set semanticIdentity, final String iconFile) {
		final Set semanticDomain = semanticIdentity.container();

		Set graphVisualization = S23MSemanticDomains.is_UNKNOWN;;
		for (final Set gv : RepositoryStructure.graphVisualizations.filterPolymorphic(Visualization.graphVisualization)) {
			for (final Set v_to_vg : gv.filterPolymorphic(Visualization.visualizedGraph_to_graph)) {
				if (v_to_vg.to().isEqualTo(semanticDomain)) {
					graphVisualization = gv;
				}
			}
		}
		if (graphVisualization.isEqualTo(S23MSemanticDomains.is_UNKNOWN)) {
			graphVisualization = createGraphVisualization(semanticDomain);
		}


	// addPrivilege icon to representation
		for (final Set s_to_si : graphVisualization.filterPolymorphic(Visualization.symbol_to_semantic_identity)) {
			if (s_to_si.to().isEqualTo(semanticIdentity)
					&& s_to_si.toEdgeEnd().isEqualTo(semanticIdentity) ) {
				final Set symbol = s_to_si.from();
			}
		}


		final Set icon = graphVisualization.addConcrete(Visualization.symbol, Visualization.symbol);
		icon.identity().setPayload(iconFile);
		arrow(Visualization.symbol_to_semantic_identity,
				S23MSemanticDomains.anonymous,
				//cellplatformDomain.theDefault,
				icon,
				icon,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				semanticIdentity,
				semanticIdentity,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);

	}

}
