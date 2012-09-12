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

package org.s23m.cell.core;

import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.api.models.SemanticDomain.disjunctSemanticIdentitySet;
import static org.s23m.cell.api.models.SemanticDomain.elements_to_disjunctSemanticIdentitySet;
import static org.s23m.cell.api.models.SemanticDomain.semanticIdentitySet;
import static org.s23m.cell.api.models.SemanticDomain.variantDisjunctSemanticIdentitySet;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.SemanticDomain;

public final class F_Query {

	public static Set findSet(final String uniqueRepresentationReference) {
		return Query.inMemorySets().extractUniqueMatch(uniqueRepresentationReference);
	}

	public static Set findDependentArrows(final Set referencedSet) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.createAnAnonymousIdentity());
		for (final Set instance : inMemorySets()) {
			for (final Set arrow : referencedSet.unionOfconnectingArrows(instance)) {
				result.add(arrow);
			}
		}
		return result;
	}

	public static Set findDependentInstances(final Set category) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.createAnAnonymousIdentity());
		for (final Set instance : inMemorySets()) {
			if (instance.category().isEqualToRepresentation(category)) {
				result.add(instance);
			}
		}
		return result;
	}

	public static Set findDependentSets(final Set set) {
		// relevant for DECOMMISSION_SEMANTICS
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.createAnAnonymousIdentity());
		// test for child instances in the containment tree
		for (final Set instance : set.filterInstances()) {
			result.add(instance);
		}
		for (final Set instance : inMemorySets()) {
			// test for instances that are linked with the set
			for (final Set arrow : set.unionOfconnectingArrows(instance)) {
				result.add(arrow);
			}
			// test for instances that depend on a category
			if (instance.category().isEqualToRepresentation(set)) {
				result.add(instance);
			}
			// test for instances that depend on a semantic identity
			if (SemanticDomain.semanticIdentity.isSuperSetOf(set.category()).isEqualTo(coreSets.is_TRUE)
					&& (instance.isEqualTo(set))
					&& (!instance.isEqualToRepresentation(set))) {
				result.add(instance);
			}
		}
		return result;
	}

	public static Set inMemorySets() {
		return Graph.inMemorySets;
	}

	public static Set inMemorySemanticIdentities() {
		return Graph.inMemorySemanticIdentities;
	}

	public static Set changedSets() {
		return Graph.changedSets;
	}

	public static Vertex vertex() {
		return Vertex.vertex;
	}

	public static OrderedSet orderedSet() {
		return OrderedSet.orderedSet;
	}

	public static Arrow arrow() {
		return Arrow.arrow;
	}

	public static SuperSetReference superSetReference() {
		return SuperSetReference.superSetReference;
	}

	public static Visibility visibility() {
		return Visibility.visibility;
	}

	public static Set graph() {
		return Graph.graph;
	}

	public static EdgeEnd edgeEnd() {
		return EdgeEnd.edgeEnd;
	}

	public static Edge edge() {
		return Edge.edge;
	}

	public static Set getSetFromLocalMemory(final Identity identity) {
		final Set r =  F_Query.inMemorySemanticIdentities().extractUniqueMatch(identity);
		if (r.is_NOTAPPLICABLE()) {
			return F_Instantiation.raiseError(identityFactory.semanticErr_ThisSetIsNotAvailableInMemory(), coreSets.semanticErr);
		} else {
			return r;
		}
	}

	public static Set runtimeErrors() {
		return Query.graph.filter(coreSets.semanticErr).union(Query.graph.filter(coreSets.kernelDefect));
	}

	public static Set elementsOfSemanticIdentitySet(final Set semanticDomain, final Set set) {
		if ((semanticDomain.category().isEqualTo(SemanticDomain.semanticdomain)  && (set.category().isEqualTo(disjunctSemanticIdentitySet)
				|| set.category().isEqualTo(variantDisjunctSemanticIdentitySet)
				|| set.category().isEqualTo(semanticIdentitySet)
				))) {
			final Set elementLinks = semanticDomain.filter(SemanticDomain.elements_to_semanticIdentitySet);
			final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.createAnAnonymousIdentity());

			for (final Set element : elementLinks) {
				if (element.to().isEqualTo(set)) {
					result.add(element.from());
				}
			}
			final Set elementLinks2 = semanticDomain.filter(elements_to_disjunctSemanticIdentitySet);
			for (final Set element : elementLinks2) {
				if (element.to().isEqualTo(set)) {
					result.add(element.from());
				}
			}
			return result;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	public static Set testDeserialisationPrerequisites(final Set set) {
		if (set.container().isEqualTo(Root.root)) {
			return set.container().visibleInstancesForSubGraph(set);
		} else {
			final OrderedSet prerequisites = ((OrderedSet) testDeserialisationPrerequisites(set.container()));
			for (final Set visibleSet : set.container().visibleInstancesForSubGraph(set)) {
				prerequisites.add(visibleSet);
			}
			return prerequisites;
		}
	}
}

