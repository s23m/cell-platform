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
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.api.models.CellEngineering;
import org.s23m.cell.platform.api.models.Organization;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

// test-only org.s23m.cell.platform.api.GraphQueriesTest
public class GraphQueriesTest extends AgencyTestFoundationTestCase {

	private static Set visibilitiesGraph;
	private static Set oneComponentGraph;
	private static Set twoComponentGraph;
	private static Set sixComponentsGraph;
	private static Set eightComponentGraph;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		if (visibilitiesGraph == null) {
			final Set root = AgencyTestFoundation.test1.filter(CellEngineering.organization).extractFirst();

			visibilitiesGraph = createVisibilitiesGraph(root);
			oneComponentGraph = create1ComponentGraph(root);
			twoComponentGraph = create2ComponentGraph(root);
			sixComponentsGraph = create6ComponentGraph(root);
			eightComponentGraph = create8ComponentGraph(root);
		}
	}

	public void testSortConnectedComponentElementsInTopologicalOrder() {
		final Set topologicallySortedVertices = GraphQueries.sortConnectedComponentElementsInTopologicalOrder(visibilitiesGraph);
		assertFalse(topologicallySortedVertices.isEqualTo(coreSets.semanticErr_CycleOfVisibilities));
		assertFalse(topologicallySortedVertices.category().isEqualTo(coreSets.semanticErr));
	}

	public void testSortSetInTopologicalOrder() {
		final Set eightElements = GraphQueries.sortSetInTopologicalOrder(eightComponentGraph);
		final Set twoElements = GraphQueries.sortSetInTopologicalOrder(twoComponentGraph);
		final Set oneElement = GraphQueries.sortSetInTopologicalOrder(oneComponentGraph);
		final Set topologicallySortedVertices = GraphQueries.sortSetInTopologicalOrder(visibilitiesGraph);

		assertEquals(8, eightElements.size());
		assertEquals(15, twoElements.size());
		assertEquals(18, oneElement.size());

		assertEquals(18, topologicallySortedVertices.size());
	}

	public void testGroupByConnectedComponents() {
		final Set eightElements = GraphQueries.groupByConnectedComponents(eightComponentGraph);
		final Set sixElements = GraphQueries.groupByConnectedComponents(sixComponentsGraph);
		final Set twoElements = GraphQueries.groupByConnectedComponents(twoComponentGraph);
		final Set oneElement = GraphQueries.groupByConnectedComponents(oneComponentGraph);

		assertEquals(8, eightElements.size());
		assertEquals(6, sixElements.size());
		assertEquals(2, twoElements.size());
		assertEquals(1, oneElement.size());
	}

	private Set create8ComponentGraph(final Set root) {
		final Set graph = root.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("g", "g", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));

		for (int i = 0; i < 8; i++) {
			final String label = String.valueOf(i + 1);
			createVertex(graph, label);
		}

		return graph;
	}

	private Set create2ComponentGraph(final Set root) {
		final Set graph = root.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("g", "g", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));

		final Set one = createVertex(graph, "1");
		final Set two = createVertex(graph, "2");
		final Set three = createVertex(graph, "3");
		final Set four = createVertex(graph, "4");
		final Set five = createVertex(graph, "5");
		final Set six = createVertex(graph, "6");
		final Set seven = createVertex(graph, "7");
		final Set eight = createVertex(graph, "8");

		addEdge(one, five);

		addEdge(three, six);
		addEdge(three, eight);

		addEdge(four, five);

		addEdge(five, two);
		addEdge(five, seven);

		addEdge(six, eight);

		return graph;
	}

	private Set create1ComponentGraph(final Set root) {
		final Set graph = root.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("g", "g", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));

		final Set one = createVertex(graph, "1");
		final Set two = createVertex(graph, "2");
		final Set three = createVertex(graph, "3");
		final Set four = createVertex(graph, "4");
		final Set five = createVertex(graph, "5");
		final Set six = createVertex(graph, "6");
		final Set seven = createVertex(graph, "7");
		final Set eight = createVertex(graph, "8");

		addEdge(one, five);
		addEdge(one, six);

		addEdge(three, six);
		addEdge(three, eight);

		addEdge(four, five);

		addEdge(five, two);
		addEdge(five, seven);
		addEdge(five, eight);

		addEdge(six, seven);
		addEdge(six, eight);

		return graph;
	}

	@SuppressWarnings("unused")
	private Set create6ComponentGraph(final Set root) {
		final Set graph = root.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("g", "g", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));

		final Set one = createVertex(graph, "1");
		final Set two = createVertex(graph, "2");
		final Set three = createVertex(graph, "3");
		final Set four = createVertex(graph, "4");
		final Set five = createVertex(graph, "5");
		final Set six = createVertex(graph, "6");
		final Set seven = createVertex(graph, "7");
		final Set eight = createVertex(graph, "8");

		addEdge(one, two);
		addEdge(three, six);

		return graph;
	}

	private Set createVisibilitiesGraph(final Set root) {
		final Set graph = root.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("g", "g", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));

		final Set one = createVertex(graph, "1");
		final Set two = createVertex(graph, "2");
		final Set three = createVertex(graph, "3");
		final Set four = createVertex(graph, "4");
		final Set five = createVertex(graph, "5");
		final Set six = createVertex(graph, "6");
		final Set seven = createVertex(graph, "7");
		final Set eight = createVertex(graph, "8");

		addVisibility(one, five);
		addVisibility(one, six);

		addVisibility(three, six);
		addVisibility(three, eight);

		addVisibility(four, five);

		addVisibility(five, two);
		addVisibility(five, seven);
		addVisibility(five, eight);

		addVisibility(six, seven);
		addVisibility(six, eight);

		return graph;
	}

	private Set createVertex(final Set container, final String label) {
		return container.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet(label, label, Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
	}

	private void addEdge(final Set from, final Set to) {
		Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("test-edge", "test-edge", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)),
			from,
			from,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			to,
			to,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
		);
	}

	private void addVisibility(final Set from, final Set to) {
		Instantiation.arrow(coreGraphs.visibility, from, to);
	}
}