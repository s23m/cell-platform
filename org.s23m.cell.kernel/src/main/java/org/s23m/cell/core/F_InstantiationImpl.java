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

package org.s23m.cell.core;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;

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
	public static Set createResultSet() {
		return new OrderedSet(identityFactory.createIdentity(F_Instantiation.identityFactory.aTransientResultSet().name(), F_Instantiation.identityFactory.aTransientResultSet().name(), Instantiation.indexIsNotAvailable));
		//return new OrderedSet(F_Instantiation.identityFactory.createAnAnonymousIdentity());
		//return new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
	}

	public static Set createFunction(final Identity semanticIdentity, final Set parameter, final Set category) {
		final OrderedSet os = new OrderedSet(semanticIdentity, category);
		os.add(parameter);
		return os;
	}
	public static Set declareFunction(final Identity semanticIdentity, final Set category, final Set parameters) {
		final OrderedSet os = new OrderedSet(semanticIdentity, category);
		for (final Set parameter : parameters) {
			os.add(parameter);
		}
		return os;
	}
	protected static Vertex createSemanticIdentityVertex() {
		return new Vertex(Graph.graph, identityFactory.createIdentityReification(), Vertex.vertex);
	}

	public static Vertex raiseError(final Identity semanticIdentity, final Set category) {
		recordError(semanticIdentity, category);
		return new Vertex((Graph) coreGraphs.graph, semanticIdentity, category);
	}

	@SuppressWarnings("unused")
	private static void recordError(final Identity semanticIdentity, final Set category) {
		// TODO improve error handling
		final StringWriter sw = new StringWriter();
		new Throwable().printStackTrace(new PrintWriter(sw));
		System.err.println(sw.toString());

		// Errors are attached to the S23M Graph container.
		// Currently errors are not stored in a persistent log.
		// Persistent logging is a matter of reading & serialising the error vertices contained in the S23M Graph
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
			final Identity edgeIdentity) {
		if (edgeIdentity.isEqualTo(identityFactory.anonymous())) {
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
					edgeIdentity);
		}
	}
	protected static Edge createEdge(final Identity firstSemanticIdentity,final Set firstValue,
			final Identity seconSemanticIdentity, final Set secondValue) {
		return new Edge(firstSemanticIdentity, firstValue, seconSemanticIdentity, secondValue);
	}
	protected static SuperSetReference createSuperSetReference(final Set specialization, final Set generalization, final Set category) {
		return new SuperSetReference(specialization, generalization, category);
	}
	//protected static Arrow createInstanceListIterator() {
	//	return new Arrow(identityFactory.createAnonymousIdentity());
	//}
	//protected static Arrow createInstanceIterator() {
	//	return new Arrow(identityFactory.createAnonymousIdentity());
	//}
}
