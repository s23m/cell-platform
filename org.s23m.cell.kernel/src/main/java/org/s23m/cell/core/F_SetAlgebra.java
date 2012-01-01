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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.impl.SemanticDomainCode;

public final class F_SetAlgebra {

	public static Set union(final Set set1, final Set set2) {
		final Set result = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
		final Set a = transformToOrderedSet(set1);
		final Set b = transformToOrderedSet(set2);
		for (final Set element : a) {
			((OrderedSet) result).add(element);
		}
		for (final Set element : b) {
			if (!result.containsRepresentation(element)) {
				((OrderedSet) result).add(element);
			}
		}
		return result;
	}

	public static Set unionOfconnectingLinks(final Set set1, final Set set2) {
		return set1.unionOfconnectingLinks(set2);
	}

	public static Set intersection(final Set set1, final Set set2) {
		final Set result = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
		final Set a = transformToOrderedSet(set1);
		final Set b = transformToOrderedSet(set2);
		for (final Set element : a) {
			if (b.containsRepresentation(element)) {
				((OrderedSet) result).add(element);
			}
		}
		return result;
	}

	public static Set complement(final Set set1, final Set set2) {
		final Set a = transformToOrderedSet(set1);
		final Set b = transformToOrderedSet(set2);
		final Set intersection = intersection(a, b);
		final Set result = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
		for (final Set element : a) {
			if (!intersection.containsRepresentation(element)) {
				((OrderedSet) result).add(element);
			}
		}
		return result;
	}

	public static Set isElementOf(final Set element, final Set set) {
		if (SemanticDomain.semanticIdentity.isSuperSetOf(element.category()).isEqualTo(GmodelSemanticDomains.is_TRUE)
				&& (SemanticDomain.semanticIdentity.isSuperSetOf(set.category()).isEqualTo(GmodelSemanticDomains.is_TRUE))) 	{
			return SemanticDomainCode.isElementOf(element.container(), element, set);
		} else {
			if (transformToOrderedSet(set).containsRepresentation(element)) {
				return GmodelSemanticDomains.is_TRUE;
			} else {
				return GmodelSemanticDomains.is_FALSE;
			}
		}
	}

	public static Set transformToOrderedSetOfSemanticIdentities(final Set set) {
		final Set result = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
		for (final Set element : set) {
			if (!result.containsSemanticMatch(element)) {
				((OrderedSet) result).add(element.semanticIdentity());
			}
		}
		return result;
	}
	// TODO unify with implementation of SemanticDomainCode.isElementOf(final Set semanticDomain, final Set element, final Set set)
	private static Set transformSemanticIdentitySetToOrderedSet(final Set set) {
		final Set result = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
		final Set elementLinks1 = set.container().filter(SemanticDomain.elements_to_semanticIdentitySet);
		for (final Set link : elementLinks1) {
			if (link.to().isEqualTo(set)) {
				((OrderedSet) result).add(link.from());
			}
		}
		final Set elementLinks2 = set.container().filter(SemanticDomain.elements_to_disjunctSemanticIdentitySet);
		for (final Set link : elementLinks2) {
			if (link.to().isEqualTo(set)) {
				((OrderedSet) result).add(link.from());
			}
		}
		return result;
	}

	private static Set transformToOrderedSet(final Set set) {
		if(set.flavor().isEqualTo(GmodelSemanticDomains.orderedSet)) {
			return set;
		} else {
			if (SemanticDomain.semanticIdentity.isSuperSetOf(set.category()).isEqualTo(GmodelSemanticDomains.is_TRUE)) {
				return transformSemanticIdentitySetToOrderedSet(set);
			} else {
				return set.filterInstances();
			}
		}
	}

}
