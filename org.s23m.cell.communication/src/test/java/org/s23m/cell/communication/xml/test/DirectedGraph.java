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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DirectedGraph {

	private final Set<Vertex> vertices;
	
	public DirectedGraph() {
		this.vertices = new HashSet<Vertex>();
	}
	
	public DirectedGraph(Set<Vertex> vertices) {
		this.vertices = vertices;
	}
	
	public void addVertex(Vertex vertex) {
		this.vertices.add(vertex);
	}
	
	public Set<Edge> getEdges() {
		Set<Edge> result = new HashSet<Edge>();
		for (Vertex v : vertices) {
			result.addAll(v.incomingEdges);
			result.addAll(v.outgoingEdges);
		}
		return result;
	}
	
	public Set<Vertex> getVertices() {
		return vertices;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Directed Graph {\n");
		for (Vertex v : vertices) {
			builder.append("  ");
			builder.append(v.name);
			builder.append(" {");
			
			builder.append(" Outgoing edges [");
			Iterator<Edge> outgoingEdgeIterator = v.outgoingEdges.iterator();
			while (outgoingEdgeIterator.hasNext()) {
				Edge outgoingEdge = outgoingEdgeIterator.next();
				builder.append(" --> ");
				builder.append(outgoingEdge.to.name);
				if (outgoingEdgeIterator.hasNext()) {
					builder.append(",");
				}
			}
			builder.append("], ");
			
			builder.append(" Incoming edges [");
			Iterator<Edge> incomingEdgeIterator = v.incomingEdges.iterator();
			while (incomingEdgeIterator.hasNext()) {
				Edge incomingEdge = incomingEdgeIterator.next();
				builder.append(" <-- ");
				builder.append(incomingEdge.from.name);
				if (incomingEdgeIterator.hasNext()) {
					builder.append(",");
				}
			}
			builder.append("]");
			
			builder.append("}\n");	
		}
		builder.append("}");
		return builder.toString();
	}
}
