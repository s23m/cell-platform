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

package org.s23m.cell.api.serializerinterface;

import java.util.UUID;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.F_Query;
import org.s23m.cell.core.Graph;



/**
 * {@link Reconstitution} complements the public org.s23m.cell.G API and is only for use in the context of deserialization
 */
public class Reconstitution {

	public static Set addAbstract(final Set instance, final Set category, final Identity identity) {
		return ((Graph)instance).addAbstract(category, identity);
	}

	public static Set addConcrete(final Set instance, final Set category, final Identity identity) {
		return ((Graph)instance).addConcrete(category, identity);
	}

	public static void completeS23MSemanticDomainInitialization() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.completeCellKernelSemanticDomainInitialization();
	}
	public static Set getSetFromLocalMemory(final Identity identity) {
		return org.s23m.cell.core.F_Query.getSetFromLocalMemory(identity);
	}
	public static Set instantiateAbstract(final Set category, final Identity semanticIdentity) {
		return F_Instantiation.instantiateAbstract(semanticIdentity, category);
	}

	public static Set instantiateConcrete(final Set category, final Identity semanticIdentity) {
		return F_Instantiation.instantiateConcrete(semanticIdentity, category);
	}

	public static Set instantiateSemanticDomain(final Identity semanticIdentity) {
		return F_Instantiation.instantiateSemanticDomain(semanticIdentity);
	}

	public static Set raiseError(final Identity semanticIdentity, final Set category) {
		return org.s23m.cell.core.F_Instantiation.raiseError(semanticIdentity, category);
	}

	public static Identity reconstituteIdentity(final String name, final String pluralName, final UUID identifier, final UUID uniqueRepresentationReference) {
		return org.s23m.cell.core.F_Instantiation.reconstituteIdentity(name, pluralName, identifier, uniqueRepresentationReference);
	}
	public static Set reconstituteArrow(final Identity category, final Identity edgeIdentity, final Identity fromInstance, final Identity toInstance) {
		return org.s23m.cell.core.F_Instantiation.reconstituteArrow(category, edgeIdentity, fromInstance, toInstance);
	}
	public static Set reconstituteArrow(final Identity category,
			final Identity edgeIdentity,
			final Identity firstSemanticIdentity,
			final Identity firstOrderedPair,
			final Identity firstMinCardinality,
			final Identity firstMaxCardinality,
			final Identity firstIsNavigable,
			final Identity firstIsContainer,
			final Identity secondSemanticIdentity,
			final Identity secondOrderedPair,
			final Identity secondMinCardinality,
			final Identity secondMaxCardinality,
			final Identity secondIsNavigable,
			final Identity secondIsContainer
	) {
		return org.s23m.cell.core.F_Instantiation.reconstituteArrow(category,
				edgeIdentity,
				firstSemanticIdentity,
				firstOrderedPair,
				firstMinCardinality,
				firstMaxCardinality,
				firstIsNavigable,
				firstIsContainer,
				secondSemanticIdentity,
				secondOrderedPair,
				secondMinCardinality,
				secondMaxCardinality,
				secondIsNavigable,
				secondIsContainer
		);
	}

	public static Set testDeserialisationPrerequisites(final Set set) {
		return F_Query.testDeserialisationPrerequisites(set);
	}
}
