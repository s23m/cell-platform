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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.semanticextensions.testscripts;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.Transaction;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.semanticextensions.outershells.SemanticExtensionsDomain;
import org.s23m.cell.testbench.KernelTestSequence;
import org.s23m.cell.testbench.TestSequence;

public class Test {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		org.s23m.cell.semanticextensions.G.bootTemplate();
		int kernelComplexity = identityFactory.kernelComplexity();
		int inMemoryComplexity = identityFactory.inMemoryComplexity();
		int inMemorySetCount = org.s23m.cell.api.Query.inMemorySets().size();
		int changedSetsCount = org.s23m.cell.api.Query.changedSets().size();
		org.s23m.cell.testbench.KernelTestSequence.run();
		Transaction.commitChangedSets();
		changedSetsCount = org.s23m.cell.api.Query.changedSets().size();

		org.s23m.cell.G.goLiveWithGmodelEditor();
		TestSequence.run();
		Transaction.commitChangedSets();
		changedSetsCount = org.s23m.cell.api.Query.changedSets().size();

		org.s23m.cell.testbench.TestSequence.run();
		Transaction.commitChangedSets();
		changedSetsCount = org.s23m.cell.api.Query.changedSets().size();

		org.s23m.cell.testbench.TestSequence.run();
		Transaction.commitChangedSets();
		changedSetsCount = org.s23m.cell.api.Query.changedSets().size();
		// extensive visualizedGraph test with icon assignment
		final Set gv = createGraphVisualization(KernelTestSequence.testDomain);
		for (final Set si : KernelTestSequence.testDomain.filterPolymorphic(SemanticDomain.semanticIdentity)) {
			addIcon(si, "here goes the icon file");
		}
		Transaction.commitChangedSets();
		changedSetsCount = org.s23m.cell.api.Query.changedSets().size();

		final Set testDomain = org.s23m.cell.api.Instantiation.addSemanticDomain("test domain 3", "test domains 3", GmodelSemanticDomains.finiteSets);
		final Set testInstance =  Root.models.addAbstract(coreGraphs.vertex, testDomain);
		final Set vizSet = RepositoryStructure.graphVisualizations;
		final Set idSet = org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet("container visualizedGraph", "container artifactVisualizations", testDomain);
		final Set viz3 = vizSet.addConcrete(Visualization.graphVisualization, idSet);
		idSet.assignNewName("new art viz");

		/* decommission tests */
		final Set viz2 = vizSet.addConcrete(Visualization.graphVisualization, idSet);
		viz2.decommission();
		final java.util.UUID some_uuid = java.util.UUID.randomUUID();
		final Identity id = org.s23m.cell.api.serializerinterface.Reconstitution.reconstituteIdentity("some", "some", some_uuid, some_uuid);
		org.s23m.cell.api.serializerinterface.Reconstitution.instantiateConcrete(SemanticDomain.disjunctSemanticIdentitySet, id);
		// TODO fix up: org.s23m.cell.core.F_Query.getSetFromLocalMemory(id);
		Transaction.commitChangedSets();

		final Set d1 = org.s23m.cell.testbench.TestSequence.crm_aviz.decommission();
		Transaction.commitChangedSets();
		final Set d2 = org.s23m.cell.testbench.TestSequence.order.decommission();
		Transaction.commitChangedSets();
		final Set d3 = org.s23m.cell.testbench.TestSequence.product_to_price.decommission();
		Transaction.commitChangedSets();

		changedSetsCount = org.s23m.cell.api.Query.changedSets().size();

		final Set g = coreGraphs.graph;
		final Set kerDef = g.filter(GmodelSemanticDomains.kernelDefect);
		final Set semErr = g.filter(GmodelSemanticDomains.semanticErr);
		final Set v = coreGraphs.vertex;
		final Set l = coreGraphs.link;
		final Set e = coreGraphs.edge;
		final Set viz = coreGraphs.visibility;
		final Set s = coreGraphs.superSetReference;
		kernelComplexity = identityFactory.kernelComplexity();
		inMemoryComplexity = identityFactory.inMemoryComplexity();
		inMemorySetCount = org.s23m.cell.api.Query.inMemorySets().size();
		final Set root = Root.root;
		final Set g0 = g;



	}

	public static Set createGraphVisualization(final Set semanticDomain) {
		final Set gv = RepositoryStructure.graphVisualizations.addConcrete(Visualization.graphVisualization, semanticDomain);
		final Set v = gv.addConcrete(Visualization.visualizedGraph, semanticDomain);
		final Set details = gv.addConcrete(Visualization.details,Visualization.details);
		final Set structure = gv.addConcrete(Visualization.structure,Visualization.structure);
		final Set reuse = gv.addConcrete(Visualization.reuse,Visualization.reuse);
		final Set visibilities = gv.addConcrete(Visualization.visibilities,Visualization.visibilities);
		return gv;
	}

	public static void addIcon(final Set semanticIdentity, final String iconFile) {
		final Set semanticDomain = semanticIdentity.container();
		// ensure existence of graph visualizedGraph
		Set graphVisualization = GmodelSemanticDomains.is_UNKNOWN;;
		for (final Set gv : RepositoryStructure.graphVisualizations.filterPolymorphic(Visualization.graphVisualization)) {
			for (final Set v_to_vg : gv.filterPolymorphic(Visualization.visualizedGraph_to_graph)) {
				if (v_to_vg.to().isEqualTo(semanticDomain)) {
					graphVisualization = gv;
				}
			}
		}
		if (graphVisualization.isEqualTo(GmodelSemanticDomains.is_UNKNOWN)) {
			graphVisualization = createGraphVisualization(semanticDomain);
		}
		// ensure existence of the default symbol (the icon)
		Set icon = GmodelSemanticDomains.is_UNKNOWN;
		for (final Set s_to_si : graphVisualization.filterPolymorphic(Visualization.symbol_to_semantic_identity)) {
			if (s_to_si.to().isEqualTo(semanticIdentity)
					&& s_to_si.fromEdgeEnd().isEqualTo(SemanticExtensionsDomain.theDefault) ) {
				icon = s_to_si.from();
			}
		}
		// a few tests of semanticIdentity()
		Visualization.details.semanticIdentity();

		if (icon.isEqualTo(GmodelSemanticDomains.is_UNKNOWN)) {
			icon = graphVisualization.addConcrete(Visualization.symbol, SemanticExtensionsDomain.icon);
			Instantiation.link(Visualization.symbol_to_semantic_identity,
				GmodelSemanticDomains.anonymous,
				SemanticExtensionsDomain.theDefault,
				icon,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_TRUE,
				semanticIdentity,
				semanticIdentity,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_NOTAPPLICABLE, GmodelSemanticDomains.isContainer_FALSE
				);
		}
		// use the iconFile as payload
		icon.identity().setPayload(iconFile);
	}
}
