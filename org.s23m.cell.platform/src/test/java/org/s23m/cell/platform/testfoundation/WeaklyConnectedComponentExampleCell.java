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
package org.s23m.cell.platform.testfoundation;


import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.api.SetAlgebra.addElementToOrderedSet;
import static org.s23m.cell.api.SetAlgebra.anEmptySet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.Instantiation;
import org.s23m.cell.platform.api.models.CellEngineering;
import org.s23m.cell.platform.api.models.Organization;


public class WeaklyConnectedComponentExampleCell {

	public static void main(final String[] args) {
		final Set graph = createGraph();

		System.out.println("Input:\n" + graph);

		final Set result = anEmptySet();

		// all vertices are initially unvisited
		final java.util.Set<Set> unvisitedVertices = new HashSet<Set>();
		for (final Set v : graph.filterProperClass(coreGraphs.vertex)) {
			unvisitedVertices.add(v);
		}

        while (!unvisitedVertices.isEmpty()) {
            final Set weaklyConnectedComponent = anEmptySet();
            // pick the next available unvisited vertex
            final Set root = unvisitedVertices.iterator().next();
            unvisitedVertices.remove(root);
            addElementToOrderedSet(weaklyConnectedComponent, root);

            final Queue<Set> queue = new LinkedList<Set>();
            queue.add(root);

            while (!queue.isEmpty()) {
            	final Set currentVertex = queue.remove();
            	// iterate through adjacent neighbours (via outgoing edges)
                final Set neighbours = currentVertex.container().filterArrows().filterByLinkedFrom(currentVertex);

                for (final Set outgoingEdge : neighbours) {
                    final Set neighbor = outgoingEdge.to();
                    if (unvisitedVertices.contains(neighbor)) {
                        queue.add(neighbor);
                        unvisitedVertices.remove(neighbor);
                        addElementToOrderedSet(weaklyConnectedComponent, neighbor);
                    }
                }
            }
            addElementToOrderedSet(result, weaklyConnectedComponent);
        }

        // print result
        System.out.println("Number of weakly connected components: " + result.size());

        System.out.println("Output:\n" + result);
	}

	private static Set createGraph() {
		S23MPlatform.boot();
		AgencyTestFoundation.instantiateFeature();

		final Set root = AgencyTestFoundation.test1.filter(CellEngineering.organization).extractFirst();
		final Set g = createVertex(root, "g");
		final Set one = createVertex(g, "1");
		final Set two = createVertex(g, "2");
		final Set three = createVertex(g, "3");
		final Set four = createVertex(g, "4");
		final Set five = createVertex(g, "5");
		final Set six = createVertex(g, "6");
		final Set seven = createVertex(g, "7");
		final Set eight = createVertex(g, "8");

		createEdge(one, five);
		createEdge(one, six);
		createEdge(four, five);
		createEdge(three, six);
		createEdge(three, eight);
		createEdge(five, two);
		createEdge(five, seven);
		createEdge(five, eight);
		createEdge(six, eight);

		return g;
	}

	private static void createEdge(final Set source, final Set target) {
		Instantiation.arrow(
			coreGraphs.edge,
			S23MSemanticDomains.anonymous,
			source,
			source,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			target,
			target,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
		);
	}

	private static Set createVertex(final Set set, final String label) {
		return set.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet(label, label, Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
	}
}
