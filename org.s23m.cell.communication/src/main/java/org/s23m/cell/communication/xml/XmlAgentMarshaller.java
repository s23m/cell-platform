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

import org.s23m.cell.Set;
import org.s23m.cell.communication.SetMarshaller;
import org.s23m.cell.communication.SetMarshallingException;

public class XmlAgentMarshaller implements SetMarshaller<String> {

	/*
	 * Algorithms:
	 * 
	 * A) Partitioning into disconnected subgraphs [each one a connected di-graph].
	 * 
	 * 	An algorithm similar to this one http://en.wikipedia.org/wiki/Flood%5Ffill should do the job.
	 * 	Starting with one vertex V in model M, and building up an ordered set C1 of all the other vertices 
	 * 	which can be reached from the starting point, and of course including V in C1. Then applying the same
	 * 	technique (recursion) to the complement of C1 in M to calculate C2. Then, as long as there still is a
	 * 	non-empty complement, calculating further Ci.
	 * 
	 * B) Dependency analysis of a connected subgraph. Need the sequence of vertices
	 * in dependency order [http://en.wikipedia.org/wiki/Topological_sorting] 
	 * 
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
	 * 5. Actually there can be more than one root or top level elements, let's
	 *  call them T1 to Tm. Consider A, B, C, D with A -> C, B -> D and C -> D. 
	 *  These should be serialised in dependency order. One permitted order would be D, B, C, A.
	 *  
	 *  API usage:
	 *  - Need the Query API with filter and extract operations
	 *  
	 *  Test case:
	 *  - Agent model
	 *  
	 * Notes:
	 *  The 2 algorithms should be completely separate. The results in both cases are ordered sets.
	 *  Recommend to first articulate the algorithm in more abstract form, walk through examples, and then write code.
	 *  Still need to look into how to include edges, as edges can also be containers of further models.
	 *  
	 * Reverse sequence can be used for de-serialisation.
	 */
	@Override
	public String serialise(Set input) throws SetMarshallingException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Set deserialise(String input) throws SetMarshallingException {
		// TODO Auto-generated method stub
		return null;
	}

}
