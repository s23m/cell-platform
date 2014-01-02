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
package org.s23m.cell.communication.xml.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class ConnectedComponentExample {

	public static void main(String[] args) {
		DirectedGraph graph = createGraph();

		System.out.println("Input:\n" + graph);

		List<DirectedGraph> result = new ArrayList<DirectedGraph>();

		// all vertices are initially unvisited
		Set<Vertex> unvisitedVertices = new HashSet<Vertex>();
		for (Vertex v : graph.getVertices()) {
			unvisitedVertices.add(v);
		}

		while (!unvisitedVertices.isEmpty()) {
			DirectedGraph connectedComponent = new DirectedGraph();
			// pick the next available unvisited vertex
			Vertex root = unvisitedVertices.iterator().next();
			unvisitedVertices.remove(root);
			connectedComponent.addVertex(root);

			Queue<Vertex> queue = new LinkedList<Vertex>();
			queue.add(root);

			while (!queue.isEmpty()) {
				Vertex currentVertex = queue.remove();
				// iterate through adjacent neighbours (via outgoing and incoming edges)
				for (Edge outgoingEdge : currentVertex.outgoingEdges) {
					Vertex neighbor = outgoingEdge.to;
					if (unvisitedVertices.contains(neighbor)) {
						queue.add(neighbor);
						unvisitedVertices.remove(neighbor);
						connectedComponent.addVertex(neighbor);
					}
				}
				for (Edge incomingEdge : currentVertex.incomingEdges) {
					Vertex neighbor = incomingEdge.from;
					if (unvisitedVertices.contains(neighbor)) {
						queue.add(neighbor);
						unvisitedVertices.remove(neighbor);
						connectedComponent.addVertex(neighbor);
					}
				}
				
			}
			result.add(connectedComponent);
		}

		// print result
		System.out.println("Number of connected components: " + result.size());

		System.out.println("Output:\n" + result);
	}

	private static DirectedGraph createGraph() {
		Vertex one = new Vertex("1");
		Vertex two = new Vertex("2");
		Vertex three = new Vertex("3");
		Vertex four = new Vertex("4");
		Vertex five = new Vertex("5");
		Vertex six = new Vertex("6");
		Vertex seven = new Vertex("7");
		Vertex eight = new Vertex("8");

		one.addEdge(five).addEdge(six);
		four.addEdge(five);
		three.addEdge(six).addEdge(eight);
		five.addEdge(two).addEdge(seven).addEdge(eight);
		six.addEdge(seven).addEdge(eight);

		Vertex nine = new Vertex("9");
		Vertex ten = new Vertex("10");
		nine.addEdge(ten);
		
		Set<Vertex> vertices = ImmutableSet.of(
				one, two, three, four, five, six, seven, eight,
				nine, ten
		);

		return new DirectedGraph(vertices);
	}
}
