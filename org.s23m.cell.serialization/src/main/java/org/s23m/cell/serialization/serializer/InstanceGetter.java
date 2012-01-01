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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.util.UUID;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.serializerinterface.Reconstitution;

public class InstanceGetter {

	/**
	 * Returns the in-memory instance with the UUID
	 * @param uuid
	 * @return
	 * @throws IllegalStateException
	 */
	public static Set getInstanceByUUID(final UUID uuid) throws IllegalStateException {
		final Set setFromMemory = Reconstitution.getSetFromLocalMemory(Reconstitution.reconstituteIdentity("", "",
				uuid, uuid));
		if (isNonMemoryResidentInstance(setFromMemory)) {
			throw new IllegalStateException("Non-existent instance");
		} else {
			return setFromMemory;
		}
	}

	/**
	 * Returns true of the instance does not exist in memory
	 * @param set
	 * @return
	 */
	public static boolean isNonMemoryResidentInstance(final Set set) {
		// NOTE : the test based on names may fail, the only correct test is for representational equivalence via isEqualToRepresentation()
		//return set.identity().name().equals((GmodelSemanticDomains.semanticErr_ThisSetIsNotAvailableInMemory.identity().name()));
		return org.s23m.cell.api.serializerinterface.Reconstitution.getSetFromLocalMemory(set.identity()).isEqualToRepresentation(GmodelSemanticDomains.semanticErr_ThisSetIsNotAvailableInMemory) ;
	}

	/**
	 * Returns true if the given instance has a modifiable name
	 * @param set
	 * @return
	 */
	public static boolean hasModifiableName(final Set set) {
		if (
				set.category().isEqualTo(SemanticDomain.semanticIdentity)
				|| set.category().isEqualTo(SemanticDomain.semanticIdentitySet)
				|| set.category().isEqualTo(SemanticDomain.disjunctSemanticIdentitySet)
				//|| set.category().isEqualTo(SemanticDomain.abstractSemanticRole)
				|| set.category().isEqualTo(SemanticDomain.semanticRole)
				|| set.category().isEqualTo(SemanticDomain.variantDisjunctSemanticIdentitySet)
		) {
			return true;
		} else {
			return false;
		}
	}

}
