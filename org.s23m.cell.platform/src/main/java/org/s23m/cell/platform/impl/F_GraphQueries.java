package org.s23m.cell.platform.impl;

import static org.s23m.cell.S23MKernel.coreSets;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.SetAlgebra;
import org.s23m.cell.core.F_SetAlgebra;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.Instantiation;


public  class F_GraphQueries {

	/**
	 * Sorts any set of vertices and arrows into the topological order defined by dependencies
	 * between the elements
	 *
	 * See http://mathworld.wolfram.com/WeaklyConnectedComponent.html
	 *
	 * 1. Analyse the set of vertices and arrows and partition them into
	 *  connected subgraphs of maximal size. The resulting is a set of subgraphs
	 *  C1, .... Cn, with n >= 1
	 *
	 * 2. Each Ci above is a directed acyclic graph consisting of vertices and
	 *  arrows that gets sorted in topological order.
	 *
	 *  The ordered set returned is in a topological order that is suitable for serialisation.
	 *  Macro-level serialisation algorithms can use this order to serialise by proceeding in a
	 *  depth-first strategy into the artefact containment tree.
	 *
	 *  Note that vertices as well as arrows may act as containers of nested artefacts!
	 *  Hence the algorithm returns an ordered set of vertices and arrows in topological order.
	 *
	 */
	public static Set sortSetInTopologicalOrder(final Set set) {
		final Set result = SetAlgebra.anEmptySet();
		for (final Set connectedComponent : groupByConnectedComponents(set)) {
			for (final Set element : sortConnectedComponentElementsInTopologicalOrder(connectedComponent)) {
				SetAlgebra.addElementToOrderedSet(result, element);
			}
		}
		return result;
	}

	/**
	 * Given a connected subgraph, produce an ordered set of vertices
	 * in dependency order based on visibility arrows.
	 *
	 * See http://en.wikipedia.org/wiki/Topological_sorting
	 */
	public static Set sortConnectedComponentElementsInTopologicalOrder(final Set connectedSubgraph) {
			// result <- Empty set that will contain the sorted elements
			Set result = SetAlgebra.anEmptySet();

			final Set allArrows = connectedSubgraph.filterArrows();
			final Set allVertices = connectedSubgraph.filterProperClass(Query.vertex);

			final Set verticesWithOutboundArrows = allArrows.filterFrom();

			// nextLevelVertices <- Set of all vertices with no remaining outbound arrows
			final Set nextLevelVertices = allVertices.complement(verticesWithOutboundArrows);

			final List<Set> listOfNextLevelVertices = nextLevelVertices.asList();

			Set remainingArrows = allArrows;

			result = result.union(nextLevelVertices);

			while (!listOfNextLevelVertices.isEmpty()) {
				// remove a vertex v from listOfNextLevelVertices
				final Set v = listOfNextLevelVertices.remove(listOfNextLevelVertices.size() - 1);

				// for each vertex w with an arrow  from w to v do
				for (final Set arrow : remainingArrows.filterByLinkedTo(v)) {
					// remove arrow  from the graph
					remainingArrows = remainingArrows.complement(F_SetAlgebra.wrapInOrderedSet(arrow));
					final Set w = arrow.from();
					// if w has no other outgoing arrow then insert w into S
					if (remainingArrows.filterByLinkedFrom(w).isEmpty()) {
						listOfNextLevelVertices.add(w);
						result = SetAlgebra.addElementToOrderedSet(result, w);
					}
					result = SetAlgebra.addElementToOrderedSet(result, arrow);
					}
			}

			// check to see if all arrows are removed
			boolean cycle = false;
			for (final Set v : allVertices) {
				if (!remainingArrows.filterByLinkedTo(v).isEmpty()) {
					cycle = true;
					break;
				}
			}
			if (cycle) {
				// Cycle present, topological sort not possible
				return Instantiation.raiseError(coreSets.semanticErr_CycleOfVisibilities, coreSets.semanticErr);
			} //else {System.out.println("Topological Sort: "+ Arrays.toString(L.toArray()));}
			return result;
	}


	public static final Set groupByConnectedComponents(final Set graph) {
		final Set result = SetAlgebra.anEmptySet();

		// all vertices are initially unvisited
		final java.util.Set<Set> unvisitedVertices = new HashSet<Set>();
		for (final Set v : graph.filterProperClass(S23MPlatform.coreGraphs.vertex)) {
			unvisitedVertices.add(v);
		}

		while (!unvisitedVertices.isEmpty()) {
			final Set connectedComponent = SetAlgebra.anEmptySet();
			// pick the next available unvisited vertex
			final Set root = unvisitedVertices.iterator().next();
			unvisitedVertices.remove(root);
			SetAlgebra.addElementToOrderedSet(connectedComponent, root);

			final Queue<Set> queue = new LinkedList<Set>();
			queue.add(root);

			while (!queue.isEmpty()) {
				final Set currentVertex = queue.remove();
				// iterate through adjacent neighbours (via outgoing and incoming arrows)
				for (final Set outgoingArrow : currentVertex.container().filterArrows().filterByLinkedFrom(currentVertex)) {
					final Set neighbor = outgoingArrow.to();
					if (unvisitedVertices.contains(neighbor)) {
						queue.add(neighbor);
						unvisitedVertices.remove(neighbor);
						SetAlgebra.addElementToOrderedSet(connectedComponent, neighbor);
					}
					SetAlgebra.addElementToOrderedSet(connectedComponent, outgoingArrow);

				}
				for (final Set incomingArrow : currentVertex.container().filterArrows().filterByLinkedTo(currentVertex)) {
					final Set neighbor = incomingArrow.from();
					if (unvisitedVertices.contains(neighbor)) {
						queue.add(neighbor);
						unvisitedVertices.remove(neighbor);
						SetAlgebra.addElementToOrderedSet(connectedComponent, neighbor);
					}
					SetAlgebra.addElementToOrderedSet(connectedComponent, incomingArrow);
				}

			}
			SetAlgebra.addElementToOrderedSet(result, connectedComponent);

		}
		return result;
	}
}
