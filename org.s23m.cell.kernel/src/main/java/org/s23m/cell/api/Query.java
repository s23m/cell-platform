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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.core.Arrow;
import org.s23m.cell.core.Edge;
import org.s23m.cell.core.EdgeEnd;
import org.s23m.cell.core.F_Query;
import org.s23m.cell.core.OrderedSet;
import org.s23m.cell.core.SuperSetReference;
import org.s23m.cell.core.Vertex;
import org.s23m.cell.core.Visibility;



/**
 * {@link Query} provides access to the Sets and Properties of the S23M kernel
 * that constitute the basic S23M vocabulary.
 * 
 * Additionally F_SemanticStateOfInMemoryModel enables the creation of arrows between Sets,
 * and automatically attaches the arrow to the appropriate container Set.
 * 
 * Note: F_SemanticStateOfInMemoryModel contains no implementation, it simply delegates to ArrowConstraints, IdentityFactory, CoreSets,
 * and KernelOrderedSets.
 * 
 * Extensions: S23M is designed to be extensible. All extensions that only involve a structural extension
 * of the meta model can be achieved by modelling the extension in S23M. Beyond such basic extensions,
 * S23M can be extended/modified by plugging in a different IdentityFactory and/or by writing a custom Shell.
 * Such extensions are created by creating a subclass of F_SemanticStateOfInMemoryModel that
 * 
 * 	(a) adds a method that references the appropriate SemanticIndentityFactory:
 * 
 * 		public static final CustomSemanticIdentityFactory customSemanticIdentityFactory = new CustomSemanticIdentityFactory();
 * 
 * 	and/or
 * 
 * 	(b) reference the appropriate custom Shell by overriding the raiseError and arrow methods in F_SemanticStateOfInMemoryModel and by delegating to ArrowConstraints
 * 		to invoke the raiseError and arrow methods in the kernel.
 * 
 * All extensions must use F_SemanticStateOfInMemoryModel's CoreSets and KernelOrderedSets.
 * 
 */
public class Query {

	public static Set vertex = vertex();

	public static Set orderedSet = orderedSet();

	public static Set arrow = arrow();

	public static Set superSetReference = superSetReference();
	public static Set visibility = visibility();
	public static Set graph = graph();
	public static Set edgeEnd = edgeEnd();

	public static Set edge = edge();

	public static Set changedSets() {
		return F_Query.changedSets();
	}

	private static Edge edge() {
		return F_Query.edge();
	}
	private static EdgeEnd edgeEnd() {
		return F_Query.edgeEnd();
	}

	public static Set find(final Set searchSpaceContainer, final Set searchSpaceDepth) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}
	public static Set find(final Set searchSpaceContainer, final Set searchSpaceDepth, final Set category) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}

	public static Set findByName(final String instanceNameFragment, final String categoryNameFragment, final String containerNameFragment) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}
	public static Set findDependentInstances(final Set category) {
		return F_Query.findDependentInstances(category);
	}

	public static Set findDependentArrows(final Set referencedSet) {
		return F_Query.findDependentArrows(referencedSet);
	}
	public static Set findDependentSets(final Set set) {
		// relevant for DECOMMISSION_SEMANTICS
		// TODO: implement additional repository based query to confirm that no dependent sets exit beyond the in-memory representation
		return F_Query.findDependentSets(set);
	}

	public static Set findFromSets(final Set toSet) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}
	public static Set findFromSets(final Set categoryOfArrow, final Set toSet) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}

	public static Set findFromSets(final Set categoryOfArrow, final Set toEdgeEnd, final Set toSet) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}

	public static Set findFromSets(final Set categoryOfArrow, final Set fromEdgeEnd, final Set toEdgeEnd, final Set toSet) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}

	public static Set findGraphVisualization(final Set set) {
		return findUniqueFromSet(Visualization.visualizedGraph_to_graph, set);
	}

	public static Set findSet(final String uniqueRepresentationReference) {
		return org.s23m.cell.core.F_Query.findSet(uniqueRepresentationReference);
	}
	public static Set findUniqueFromSet(final Set categoryOfArrow, final Set toSet) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}
	public static Set findUniqueFromSet(final Set categoryOfArrow, final Set toEdgeEnd, final Set toSet) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}
	public static Set findUniqueFromSet(final Set categoryOfArrow, final Set fromEdgeEnd, final Set toEdgeEnd, final Set toSet) {
		// TODO: implement
		return S23MSemanticDomains.isEmpty;
	}
	private static Set graph() {
		return F_Query.graph();
	}

	public static Set inMemorySets() {
		return F_Query.inMemorySets();
	}

	public static Set isLoadedInLocalMemory(final Set set) {
		if (Query.inMemorySets().containsRepresentation(set)) {
			return S23MKernel.coreSets.is_TRUE;
		} else {return S23MKernel.coreSets.is_FALSE;
		}
	}

	private static Arrow arrow() {
		return F_Query.arrow();
	}

	private static OrderedSet orderedSet() {
		return F_Query.orderedSet();
	}

	public static Set runtimeErrors() {
		return F_Query.runtimeErrors();
	}

	private static SuperSetReference superSetReference() {
		return F_Query.superSetReference();
	}
	private static Vertex vertex() {
		return F_Query.vertex();
	}
	private static Visibility visibility() {
		return F_Query.visibility();
	}

}
