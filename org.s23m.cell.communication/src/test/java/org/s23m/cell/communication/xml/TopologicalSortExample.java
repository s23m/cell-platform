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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/*
 * Based on http://en.wikipedia.org/wiki/Topological_sort
 */
public class TopologicalSortExample {

	static class Vertex {
		public final String name;
		public final HashSet<Edge> incomingEdges;
		public final HashSet<Edge> outgoingEdges;

		public Vertex(String name) {
			this.name = name;
			incomingEdges = new HashSet<Edge>();
			outgoingEdges = new HashSet<Edge>();
		}

		public Vertex addEdge(Vertex node) {
			Edge e = new Edge(this, node);
			outgoingEdges.add(e);
			node.incomingEdges.add(e);
			return this;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	static class Edge {
		public final Vertex from;
		public final Vertex to;

		public Edge(Vertex from, Vertex to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Edge)) {
				return false;
			}
			Edge e = (Edge) obj;
			return e.from == from && e.to == to;
		}
	}

	public static void main(String[] args) {
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

		Vertex[] allNodes = { seven, five, three, eleven, eight, two, nine, ten };
		// L <- Empty list that will contain the sorted elements
		ArrayList<Vertex> L = new ArrayList<Vertex>();

		// S <- Set of all nodes with no incoming edges
		HashSet<Vertex> S = new HashSet<Vertex>();
		for (Vertex n : allNodes) {
			if (n.incomingEdges.isEmpty()) {
				S.add(n);
			}
		}

		// while S is non-empty do
		while (!S.isEmpty()) {
			// remove a node n from S
			Vertex n = S.iterator().next();
			S.remove(n);

			// insert n into L
			L.add(n);

			// for each node m with an edge e from n to m do
			for (Iterator<Edge> it = n.outgoingEdges.iterator(); it.hasNext();) {
				// remove edge e from the graph
				Edge e = it.next();
				Vertex m = e.to;
				it.remove();// Remove edge from n
				m.incomingEdges.remove(e);// Remove edge from m

				// if m has no other incoming edges then insert m into S
				if (m.incomingEdges.isEmpty()) {
					S.add(m);
				}
			}
		}
		// Check to see if all edges are removed
		boolean cycle = false;
		for (Vertex n : allNodes) {
			if (!n.incomingEdges.isEmpty()) {
				cycle = true;
				break;
			}
		}
		if (cycle) {
			System.out.println("Cycle present, topological sort not possible");
		} else {
			System.out.println("Topological Sort: "
					+ Arrays.toString(L.toArray()));
		}
	}
}
