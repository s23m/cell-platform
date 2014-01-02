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

class Vertex {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((incomingEdges == null) ? 0 : incomingEdges.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result	+ ((outgoingEdges == null) ? 0 : outgoingEdges.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (incomingEdges == null) {
			if (other.incomingEdges != null)
				return false;
		} else if (!incomingEdges.equals(other.incomingEdges))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (outgoingEdges == null) {
			if (other.outgoingEdges != null)
				return false;
		} else if (!outgoingEdges.equals(other.outgoingEdges))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}