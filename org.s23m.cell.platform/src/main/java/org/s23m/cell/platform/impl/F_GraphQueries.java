package org.s23m.cell.platform.impl;

import static org.s23m.cell.S23MKernel.coreSets;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.SetAlgebra;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.Instantiation;


public  class F_GraphQueries {

	/**
	 * Given a connected subgraph, produce an ordered set of vertices
	 * in dependency order based on visibility arrows.
	 *
	 * See http://en.wikipedia.org/wiki/Topological_sorting
	 */
	public static Set topologicalVertexSort(final Set connectedSubgraph) {
		if (connectedSubgraph.category().isEqualTo(Query.orderedSet)) {
			// error
			return Instantiation.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance, coreSets.semanticErr);
		} else {
			// L <- Empty list that will contain the sorted elements
			Set L = SetAlgebra.anEmptySet();

			final Set allArrows = connectedSubgraph.filterArrows();
			final Set allVertices = connectedSubgraph.filterProperClass(Query.vertex);

			final Set verticesWithIncomingArrows = allArrows.filterTo();

			// S <- Set of all vertices with no remaining incoming arrows
			final Set nextLevelVertices = allVertices.complement(verticesWithIncomingArrows);

			final List<Set> listOfNextLevelVertices = nextLevelVertices.asList();

			Set remainingArrows = allArrows;

			L = L.union(nextLevelVertices);

			while (!listOfNextLevelVertices.isEmpty()) {
				// remove a vertex v from listOfNextLevelVertices
				final Set v = listOfNextLevelVertices.remove(listOfNextLevelVertices.size() - 1);

				// for each vertex w with an arrow  from v to w do
				for (final Set arrow : remainingArrows.filterByLinkedFrom(v)) {
					// remove arrow  from the graph
					remainingArrows = remainingArrows.complement(arrow);
					final Set w = arrow.to();
					// if w has no other incoming arrow then insert w into S
					if (remainingArrows.filterByLinkedTo(w).isEmpty()) {
						listOfNextLevelVertices.add(w);
						L = SetAlgebra.addElementToOrderedSet(L, v);						
					}
				}
			}
			
			// check to see if all edges are removed
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
			return L;
		}
	}

	public static Set topologicalVertexSortFlawed(final Set connectedSubgraph) {
		if (connectedSubgraph.category().isEqualTo(Query.orderedSet)) {
			// error
			return Instantiation.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance, coreSets.semanticErr);
		} else {
			// L <- Empty list that will contain the sorted elements
			Set L = SetAlgebra.anEmptySet();

			final Set allVisibilities = connectedSubgraph.filterProperClass(Query.visibility);
			final Set allVertices = connectedSubgraph.filterProperClass(Query.vertex);

			final Set verticesWithIncomingVisibilityArrows = allVisibilities.filterTo();

			// S <- Set of all vertices with no incoming visibility arrows
			final Set S = allVertices.complement(verticesWithIncomingVisibilityArrows);

			final List<Set> listOfS = S.asList();

			Set remainingVisibilities = allVisibilities;

			// while S is non-empty do
			while (!listOfS.isEmpty()) {
				// remove a vertex v from S
				final Set v = listOfS.remove(listOfS.size() - 1);

				// insert v into L
				L = SetAlgebra.addElementToOrderedSet(L, v);
				// TODO use union operation

				// for each vertex w with an arrow  from v to w do
				for (final Set arrow : remainingVisibilities.filterByLinkedFrom(v)) {
					// remove arrow  from the graph
					remainingVisibilities = remainingVisibilities.complement(arrow);
					final Set w = arrow.to();
					// if w has no other incoming arrow then insert w into S
					if (remainingVisibilities.filterByLinkedTo(w).isEmpty()) {
						listOfS.add(w);
					}
				}
			}

			// check to see if all edges are removed
			boolean cycle = false;
			for (final Set v : allVertices) {
				if (!remainingVisibilities.filterByLinkedTo(v).isEmpty()) {
					cycle = true;
					break;
				}
			}
			if (cycle) {
				// Cycle present, topological sort not possible
				return Instantiation.raiseError(coreSets.semanticErr_CycleOfVisibilities, coreSets.semanticErr);
			} //else {System.out.println("Topological Sort: "+ Arrays.toString(L.toArray()));}
			return L;
		}
	}

	/**
	 * Partitions a model into independent parts, based on its visibility arrows.
	 *
	 * This is equivalent to determining the set of weakly connected components in
	 * a directed graph.
	 *
	 * See http://mathworld.wolfram.com/WeaklyConnectedComponent.html
	 *
	 * 1. filter all the vertices that don't have any visibilities attached
	 *  (the content of these vertices can be serialised first). These are
	 *  the "independent vertices" iVertices
	 *  [look at edge-ends]
	 *
	 * 2. If the model is called M, define C as the complement of iVertices in M
	 *
	 * 3. Analyse C and partition C into as many disconnected subgraphs of
	 *  vertices (connected via visibilities) as possible. The result will be
	 *  subsets C1, .... Cn, with n >= 1
	 *
	 * 4. Each Ci above is a directed acyclic graph consisting of vertices and
	 *  visibilities. We need to serialise the root first and then the next level down
	 *  from the root.
	 *
	 */
	public static Set partitionModelByVisibilityArrows(final Set model) {
		final Set allEdges = model.filterArrows();
		final Set allVertices = model.filter(Query.vertex);

		final Set nodesIncidentToVisibilityEdges = allVertices.filterByLinkedToVia(Query.visibility);
		final Set independentVertices = allVertices.complement(nodesIncidentToVisibilityEdges);

		return null;
	}


	public static final Set filterConnectedComponents(final Set graph) {
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
				// iterate through adjacent neighbours (via outgoing and incoming edges)
				//for (final Set outgoingEdge : currentVertex.outgoingEdges) {
				for (final Set outgoingEdge : currentVertex.container().filterArrows().filterByLinkedFrom(currentVertex)) {
					final Set neighbor = outgoingEdge.to();
					if (unvisitedVertices.contains(neighbor)) {
						queue.add(neighbor);
						unvisitedVertices.remove(neighbor);
						SetAlgebra.addElementToOrderedSet(connectedComponent, neighbor);
					}
				}
				for (final Set incomingEdge : currentVertex.container().filterArrows().filterByLinkedTo(currentVertex)) {
					final Set neighbor = incomingEdge.from();
					if (unvisitedVertices.contains(neighbor)) {
						queue.add(neighbor);
						unvisitedVertices.remove(neighbor);
						SetAlgebra.addElementToOrderedSet(connectedComponent, neighbor);
					}
				}

			}
			SetAlgebra.addElementToOrderedSet(result, connectedComponent);

		}
		return result;
	}
}
