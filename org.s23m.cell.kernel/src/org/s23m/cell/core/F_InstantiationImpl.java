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
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;

public final class F_InstantiationImpl {

	public static Set createOrderedPair(final Identity semanticIdentity, final Set category) {
		return new OrderedPair(semanticIdentity, category);
	}

	public static Set createOrderedPair(final Identity semanticIdentity) {
		return new OrderedPair(semanticIdentity, OrderedPair.orderedPair);
	}

	public static Set createFunction(final Identity semanticIdentity, final Set category) {
		return new OrderedSet(semanticIdentity, category);
	}
	public static Set createFunction(final Identity semanticIdentity, final Set parameter, final Set category) {
		final OrderedSet os = new OrderedSet(semanticIdentity, category);
		os.add(parameter);
		return os;
	}

	protected static Vertex createSemanticIdentityVertex() {
		return new Vertex(Graph.graph, identityFactory.createIdentityReification(), Vertex.vertex);
	}

	public static Vertex raiseError(final Identity semanticIdentity, final Set category) {
		// Errors are attached to the Gmodel Graph container.
		// Currently errors are not stored in a persistent log.
		// Persisent logging is a matter of reading & serialising the error vertices contained in the Gmodel Graph
		return new Vertex((Graph) coreGraphs.graph, semanticIdentity, category);
	}

	protected static EdgeEnd createEdgeEnd(final Set container, final Identity firstSemanticIdentity, final Set firstValue) {
		return new EdgeEnd(container, firstSemanticIdentity, firstValue);
	}
	protected static Edge createEdge(final Identity firstEdgeEndIdentity,
			final Set firstSet,
			final Set firstMinCardinality,
			final Set firstMaxCardinality,
			final Set firstIsNavigable,
			final Set firstIsContainer,
			final Identity secondEdgeEndIdentity,
			final Set secondSet,
			final Set secondMinCardinality,
			final Set secondMaxCardinality,
			final Set secondIsNavigable,
			final Set secondIsContainer,
			final Set category,
			final Identity edgeFlavoredIdentity) {
		if (edgeFlavoredIdentity.isEqualTo(identityFactory.anonymous())) {
			return new Edge(firstEdgeEndIdentity,
					firstSet,
					firstMinCardinality,
					firstMaxCardinality,
					firstIsNavigable,
					firstIsContainer,
					secondEdgeEndIdentity,
					secondSet,
					secondMinCardinality,
					secondMaxCardinality,
					secondIsNavigable,
					secondIsContainer,
					category);
		} else {
			return new Edge(firstEdgeEndIdentity,
					firstSet,
					firstMinCardinality,
					firstMaxCardinality,
					firstIsNavigable,
					firstIsContainer,
					secondEdgeEndIdentity,
					secondSet,
					secondMinCardinality,
					secondMaxCardinality,
					secondIsNavigable,
					secondIsContainer,
					category,
					edgeFlavoredIdentity);
		}
	}
	protected static Edge createEdge(final Identity firstSemanticIdentity,final Set firstValue,
			final Identity seconSemanticIdentity, final Set secondValue) {
		return new Edge(firstSemanticIdentity, firstValue, seconSemanticIdentity, secondValue);
	}
	protected static SuperSetReference createSuperSetReference(final Set specialization, final Set generalization, final Set category) {
		return new SuperSetReference(specialization, generalization, category);
	}
	protected static Link createInstanceListIterator() {
		return new Link(identityFactory.createAnonymousIdentity());
	}
	protected static Link createInstanceIterator() {
		return new Link(identityFactory.createAnonymousIdentity());
	}
}
