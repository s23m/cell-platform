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

package org.s23m.cell.impl;

import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.api.models.SemanticDomain.disjunctSemanticIdentitySet;
import static org.s23m.cell.api.models.SemanticDomain.elements_to_disjunctSemanticIdentitySet;
import static org.s23m.cell.api.models.SemanticDomain.elements_to_semanticIdentitySet;
import static org.s23m.cell.api.models.SemanticDomain.semanticIdentitySet;
import static org.s23m.cell.api.models.SemanticDomain.semanticRole;
import static org.s23m.cell.api.models.SemanticDomain.variantDisjunctSemanticIdentitySet;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.F_InstantiationImpl;

public class SemanticDomainCode {

	public static Set addElement(final Set set, final Set element){
		if (   set.category().isEqualTo(disjunctSemanticIdentitySet)
				|| set.category().isEqualTo(variantDisjunctSemanticIdentitySet)	) {
			return F_Instantiation.arrow(elements_to_disjunctSemanticIdentitySet,
					F_Instantiation.reuseSemanticIdentity(identityFactory.element()),
					element,
					coreSets.minCardinality_NOTAPPLICABLE,
					coreSets.maxCardinality_NOTAPPLICABLE,
					coreSets.isNavigable_TRUE,
					coreSets.isContainer_FALSE,
					F_Instantiation.reuseSemanticIdentity(identityFactory.set()),
					set,
					coreSets.minCardinality_NOTAPPLICABLE,
					coreSets.maxCardinality_NOTAPPLICABLE,
					coreSets.isNavigable_TRUE,
					coreSets.isContainer_FALSE
			);
		} else {
			if (   set.category().isEqualTo(semanticIdentitySet)) {
				return F_Instantiation.arrow(elements_to_semanticIdentitySet,
						F_Instantiation.reuseSemanticIdentity(identityFactory.element()),
						element,
						coreSets.minCardinality_NOTAPPLICABLE,
						coreSets.maxCardinality_NOTAPPLICABLE,
						coreSets.isNavigable_TRUE,
						coreSets.isContainer_FALSE,
						F_Instantiation.reuseSemanticIdentity(identityFactory.set()),
						set,
						coreSets.minCardinality_NOTAPPLICABLE,
						coreSets.maxCardinality_NOTAPPLICABLE,
						coreSets.isNavigable_TRUE,
						coreSets.isContainer_FALSE
				);
			} else {
				return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
			}
		}

	}

	public static Set removeElement(final Set set, final Set element){
		if (   set.category().isEqualTo(disjunctSemanticIdentitySet)
				|| set.category().isEqualTo(variantDisjunctSemanticIdentitySet)	) {
			final Set arrows = element.filter(elements_to_disjunctSemanticIdentitySet);
			// TODO rest of implementation DECOMMISSION_SEMANTICS
		}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsNotYetImplemented.identity(), coreSets.semanticErr);

	}
	// TODO unify with implementation of F_SetAlgebra.transformSemanticIdentitySetToOrderedSet(final Set set)
	public static Set isElementOf(final Set semanticDomain, final Set element, final Set set) {
		final Set setClass = transformSemanticRoleToEquivalenceClass(set);
		final Set elementClass = transformSemanticRoleToEquivalenceClass(element);
		final Set elementLinks = semanticDomain.filter(SemanticDomain.elements_to_semanticIdentitySet);
		for (final Set e : elementLinks) {
			if (e.from().isEqualTo(elementClass)
					&&  e.to().isEqualTo(setClass)) {
				return S23MSemanticDomains.is_TRUE;
			}
		}
		final Set elementLinks2 = semanticDomain.filter(elements_to_disjunctSemanticIdentitySet);
		for (final Set e : elementLinks2) {
			if (e.from().isEqualTo(elementClass)
					&&  e.to().isEqualTo(setClass)) {
				return S23MSemanticDomains.is_TRUE;
			}
		}
		return S23MSemanticDomains.is_FALSE;
	}

	public static Set addSemanticRole(final Set newSemanticRole, final Set equivalenceClass){
		if (  newSemanticRole.category().isSuperSetOf(semanticRole).isEqualTo(coreSets.is_TRUE)) {
			return linkSemanticRole(newSemanticRole, equivalenceClass);
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	public static Set linkSemanticRole(final Set semanticRole, final Set equivalenceClass) {
		return F_Instantiation.arrow(SemanticDomain.semanticRole_to_equivalenceClass,
				F_Instantiation.reuseSemanticIdentity(identityFactory.referencingSemanticRole()),
				semanticRole,
				coreSets.minCardinality_NOTAPPLICABLE,
				coreSets.maxCardinality_NOTAPPLICABLE,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				F_Instantiation.reuseSemanticIdentity(identityFactory.equivalenceClass()),
				equivalenceClass,
				coreSets.minCardinality_NOTAPPLICABLE,
				coreSets.maxCardinality_NOTAPPLICABLE,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE);
	}

	public static Set transformSemanticRoleToEquivalenceClass(final Set semanticRole){
		if (semanticRole.category().isEqualTo(SemanticDomain.disjunctSemanticIdentitySet)
				|| semanticRole.category().isEqualTo(SemanticDomain.semanticIdentitySet)
				|| semanticRole.category().isEqualTo(SemanticDomain.variantDisjunctSemanticIdentitySet)
		) {
			return semanticRole;
		}
		final Set resultSet = semanticRole.container().filter(SemanticDomain.semanticRole_to_equivalenceClass).filterByLinkedFrom(semanticRole).filterTo();
		if (resultSet.size() == 1) {
			if (resultSet.extractFirst().category().isEqualTo(SemanticDomain.semanticRole)) {
				return transformSemanticRoleToEquivalenceClass(resultSet.extractFirst());
			} else {
				return resultSet.extractFirst();
			}
		} else {
			return S23MSemanticDomains.is_NOTAPPLICABLE;
		}
	}
}
