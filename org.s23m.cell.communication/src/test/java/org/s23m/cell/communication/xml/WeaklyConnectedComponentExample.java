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
package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class WeaklyConnectedComponentExample {
	
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
            DirectedGraph weaklyConnectedComponent = new DirectedGraph();
            // pick the next available unvisited vertex
            Vertex root = unvisitedVertices.iterator().next();
            unvisitedVertices.remove(root);
            weaklyConnectedComponent.addVertex(root);

            Queue<Vertex> queue = new LinkedList<Vertex>();
            queue.add(root);

            while (!queue.isEmpty()) {
            	Vertex currentVertex = queue.remove();
            	// iterate through reachable neighbours (via outgoing edges)
                Set<Edge> neighbours = currentVertex.outgoingEdges;

                for (Edge outgoingEdge : neighbours) {
                    Vertex neighbor = outgoingEdge.to;
                    if (unvisitedVertices.contains(neighbor)) {
                        queue.add(neighbor);
                        unvisitedVertices.remove(neighbor);
                        weaklyConnectedComponent.addVertex(neighbor);
                    }
                }
            }
            result.add(weaklyConnectedComponent);
        }

        // print result
        System.out.println("Number of weakly connected components: " + result.size());
        System.out.println("Output:\n" + result);
	}

	private static DirectedGraph createGraph() {
		Vertex seven = new Vertex("7");
		Vertex five = new Vertex("5");
		Vertex three = new Vertex("3");
		Vertex eleven = new Vertex("11");
		Vertex eight = new Vertex("8");
		Vertex two = new Vertex("2");
		Vertex nine = new Vertex("9");
		Vertex ten = new Vertex("10");
		seven.addEdge(eleven).addEdge(eight);
		five.addEdge(eleven);
		three.addEdge(eight).addEdge(ten);
		eleven.addEdge(two).addEdge(nine).addEdge(ten);
		eight.addEdge(nine).addEdge(ten);
		
		Set<Vertex> vertices = ImmutableSet.of(
			seven,
			five,
			three,
			eleven,
			eight,
			two,
			nine,
			ten 	
		);
		
		return new DirectedGraph(vertices);
	}
}
