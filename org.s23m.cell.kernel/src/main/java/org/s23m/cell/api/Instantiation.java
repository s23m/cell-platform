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
package org.s23m.cell.api;

import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.serializerinterface.Reconstitution;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.Graph;
import org.s23m.cell.impl.SemanticDomainCode;

public class Instantiation {

	/**
	 * QUERIES
	 */
	// TODO this value must be made private, but is currently accessed by a test that is external to the kernel - the test needs to be reworked
	public static final int indexIsNotAvailable = org.s23m.cell.core.F_SemanticStateOfInMemoryModel.indexIsNotAvailable;

	public static Set addAnonymousDisjunctSemanticIdentitySet(final Set semanticDomain) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, identityFactory.createAnonymousIdentity(false));
	}

	public static Set addAnonymousSemanticRole(final Set semanticDomain, final Set referencedSemanticIdentity) {
		final Set result = ((Graph)semanticDomain).addConcrete(SemanticDomain.semanticRole, identityFactory.createAnonymousIdentity(false));
		SemanticDomainCode.addSemanticRole(result, referencedSemanticIdentity);
		return result;
	}

	public static Set addDisjunctSemanticIdentitySet(final String name, final String pluralName, final Set semanticDomain) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, identityFactory.createIdentity(name, pluralName, Instantiation.indexIsNotAvailable));
	}

	public static Set addSemanticDomain(final String name, final String pluralName, final Set semanticDomain) {
		return ((Graph)semanticDomain).addAbstract(SemanticDomain.semanticdomain, identityFactory.createIdentity(name, pluralName));
	}

	public static Set addSemanticIdentitySet(final String name, final String pluralName, final Set semanticDomain) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.semanticIdentitySet, identityFactory.createIdentity(name, pluralName, Instantiation.indexIsNotAvailable));
	}

	public static Set addSemanticRole(final String name, final String pluralName, final Set semanticDomain, final Set referencedSemanticIdentity) {
		return F_Instantiation.addSemanticRole(name, pluralName, semanticDomain, referencedSemanticIdentity);
	}

	public static Set instantiateAbstract(final Set category, final Set semanticIdentity) {
		return Reconstitution.instantiateAbstract(category, org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(semanticIdentity));
	}

	public static Set instantiateConcrete(final Set category, final Set semanticIdentity) {
		return Reconstitution.instantiateConcrete(category, org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(semanticIdentity));
	}
	public static Set link(final Set category, final Set fromInstance, final Set toInstance) {
		return org.s23m.cell.core.F_Instantiation.link(category, fromInstance, toInstance);
	}
	public static Set declareFunction(final Set semanticIdentity, final Set category, final Set parameters) {
		return org.s23m.cell.core.F_InstantiationImpl.declareFunction(semanticIdentity.identity(), category, parameters);
	}

	public static Set link(final Set category,
			final Set edgeFlavoredIdentity,
			final Set firstSemanticIdentity,
			final Set firstOrderedPair,
			final Set firstMinCardinality,
			final Set firstMaxCardinality,
			final Set firstIsNavigable,
			final Set firstIsContainer,
			final Set secondSemanticIdentity,
			final Set secondOrderedPair,
			final Set secondMinCardinality,
			final Set secondMaxCardinality,
			final Set secondIsNavigable,
			final Set secondIsContainer
			) {
		return org.s23m.cell.core.F_Instantiation.link(category,
				org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(edgeFlavoredIdentity),
				org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(firstSemanticIdentity),
				firstOrderedPair,
				firstMinCardinality,
				firstMaxCardinality,
				firstIsNavigable,
				firstIsContainer,
				org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(secondSemanticIdentity),
				secondOrderedPair,
				secondMinCardinality,
				secondMaxCardinality,
				secondIsNavigable,
				secondIsContainer
				);
	}

	public static Set linktoEquivalenceClass(final Set newSemanticRole, final Set equivalenceClass) {
		return F_Instantiation.linkToEquivalenceClass(newSemanticRole, equivalenceClass);
	}

	public static Set raiseError(final Set semanticIdentity, final Set category) {
		return org.s23m.cell.core.F_Instantiation.raiseError(semanticIdentity.identity(), category);
	}

}
