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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.api;

import static org.s23m.cell.S23MKernel.coreGraphs;
import junit.framework.TestCase;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.Transaction;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.models.CellPlatformDomain;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

public class CellKernelTest extends TestCase {

	private static boolean agencyTestFoundationInitialised = false;

	@Override
	protected void setUp() throws Exception {
		S23MPlatform.boot();
		if (!agencyTestFoundationInitialised) {
			AgencyTestFoundation.instantiateFeature();
			agencyTestFoundationInitialised = true;
		}
	}

	public void testBasicTransactions() {
		TestSequence.run();
		Transaction.commitChangedSets();
		checkForRuntimeErrors();

		TestSequence.run();
		Transaction.commitChangedSets();
		checkForRuntimeErrors();

		TestSequence.run();
		Transaction.commitChangedSets();
		checkForRuntimeErrors();
	}

	public void testGraphVisualizations() {
		// extensive visualizedGraph test with icon assignment
		final Set gv = createGraphVisualization(KernelTestSequence.testDomain);
		for (final Set si : KernelTestSequence.testDomain.filterPolymorphic(SemanticDomain.semanticIdentity)) {
			addIcon(si, "here goes the icon file");
		}
		Transaction.commitChangedSets();

		final Set testDomain = org.s23m.cell.api.Instantiation.addSemanticDomain("test domain 3", "test domains 3", S23MSemanticDomains.sandboxSemanticDomains);
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

		checkForRuntimeErrors();
	}

	public void testDecommissioning() {
		final Set d1 = TestSequence.crm_aviz.decommission();
		Transaction.commitChangedSets();
		final Set d2 = TestSequence.order.decommission();
		Transaction.commitChangedSets();
		final Set d3 = TestSequence.product_to_price.decommission();
		Transaction.commitChangedSets();

		checkForRuntimeErrors();
	}

	private void checkForRuntimeErrors() {
		final Set runtimeErrors = Query.runtimeErrors();
		if (!runtimeErrors.isEmpty()) {
			final StringBuilder builder = new StringBuilder("The following runtime errors were encountered:\n");
			// TODO improve display of sets
			for (final Set set: runtimeErrors) {
				builder.append(set);
				builder.append("\n");
			}
			fail(builder.toString());
		}
	}

	private static Set createGraphVisualization(final Set semanticDomain) {
		final Set gv = RepositoryStructure.graphVisualizations.addConcrete(Visualization.graphVisualization, semanticDomain);
		final Set v = gv.addConcrete(Visualization.visualizedGraph, semanticDomain);
		final Set details = gv.addConcrete(Visualization.details,Visualization.details);
		final Set structure = gv.addConcrete(Visualization.structure,Visualization.structure);
		final Set reuse = gv.addConcrete(Visualization.reuse,Visualization.reuse);
		final Set visibilities = gv.addConcrete(Visualization.visibilities,Visualization.visibilities);
		return gv;
	}

	private static void addIcon(final Set semanticIdentity, final String iconFile) {
		final Set semanticDomain = semanticIdentity.container();
		// ensure existence of graph visualizedGraph
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
		// ensure existence of the default symbol (the icon)
		Set icon = S23MSemanticDomains.is_UNKNOWN;
		for (final Set s_to_si : graphVisualization.filterPolymorphic(Visualization.symbol_to_semantic_identity)) {
			if (s_to_si.to().isEqualTo(semanticIdentity)
					&& s_to_si.fromEdgeEnd().isEqualTo(CellPlatformDomain.theDefault) ) {
				icon = s_to_si.from();
			}
		}
		// a few tests of semanticIdentity()
		Visualization.details.semanticIdentity();

		if (icon.isEqualTo(S23MSemanticDomains.is_UNKNOWN)) {
			icon = graphVisualization.addConcrete(Visualization.symbol, CellPlatformDomain.icon);
			Instantiation.arrow(Visualization.symbol_to_semantic_identity,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.theDefault,
				icon,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_TRUE,
				semanticIdentity,
				semanticIdentity,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_NOTAPPLICABLE, S23MSemanticDomains.isContainer_FALSE
				);
		}
		// use the iconFile as payload
		icon.identity().setPayload(iconFile);
	}

}