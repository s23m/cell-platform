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
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.api;

import org.s23m.cell.Set;
import org.s23m.cell.platform.impl.F_GraphQueries;

public class GraphQueries {

	/**
	 * QUERIES
	 */

		/**
		 * Given a connected subgraph, produce an ordered set of vertices
		 * in dependency order based on visibility arrows.
		 *
		 * See http://en.wikipedia.org/wiki/Topological_sorting
		 */
		public static Set topologicalVisibilitySort(final Set connectedSubgraph) {
			return F_GraphQueries.topologicalVisibilitySort(connectedSubgraph);
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
			return F_GraphQueries.partitionModelByVisibilityArrows(model);
		}


		public static final Set filterConnectedComponents(final Set graph) {
			return F_GraphQueries.filterConnectedComponents(graph);
		}
}