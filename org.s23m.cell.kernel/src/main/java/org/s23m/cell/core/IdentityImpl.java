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

import java.util.UUID;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;

public class IdentityImpl implements Identity {

	private static UUIDReservoirForKernel kernelIdentityReservoir;
	private UUID identifier;
	private UUID uniqueRepresentationReference;
	private String name;
	private String pluralName;
	private String codeName;
	private String pluralCodeName;
	private String payload;
	private boolean isPartOfUniversalCellConcept = false;
	private String newName;
	private String newPluralName;
	private String newCodeName;
	private String newPluralCodeName;


	public static void initialize(final UUIDReservoirForKernel reservoir) {
		kernelIdentityReservoir = reservoir;
	}

	public IdentityImpl(final String name, final String pluralName, final int nameRegistryIndex) {
		super();
		this.setName(name);
		this.setPluralName(pluralName);
		this.setCodeName(null);
		this.setPluralCodeName(null);
		this.newName = null;
		this.newPluralName = null;
		this.newCodeName = null;
		this.newPluralCodeName = null;

		if (F_SemanticStateOfInMemoryModel.cellEditorIsLive()) {
			this.setIdentifier(UUID.randomUUID());
			this.setUniqueRepresentationReference(identifier());
			if ((nameRegistryIndex == SemanticIdentityRegistry.anonymousInKernel.ordinal())
					|| (nameRegistryIndex == SemanticIdentityRegistry.anonymous.ordinal())	) {
				this.setPluralName(identifier().toString());
			}
		} else {
			if (nameRegistryIndex == SemanticIdentityRegistry.anonymousInKernel.ordinal()) {
				this.setIdentifier(kernelIdentityReservoir.getNextUUIDForAnonymousUse());
				this.setPluralName(identifier().toString());
			} else {
				if (nameRegistryIndex > F_SemanticStateOfInMemoryModel.indexIsNotAvailable) {
					if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
						this.setIdentifier(kernelIdentityReservoir.getExtendedJavaBootstrappingUUID(nameRegistryIndex));
					} else {
						this.setIdentifier(kernelIdentityReservoir.getOpenSourceCoreUUID(nameRegistryIndex));
					}
				} else {
					this.setIdentifier(kernelIdentityReservoir.getNextUUIDForAnonymousUse());
				}
			}
			this.setUniqueRepresentationReference(identifier());
		}
	}
	public IdentityImpl(final Identity semanticIdentity) {
		super();
		this.setName(semanticIdentity.name());
		this.setPluralName(semanticIdentity.pluralName());
		this.setCodeName(null);
		this.setPluralCodeName(null);
		this.newName = null;
		this.newPluralName = null;
		this.newCodeName = null;
		this.newPluralCodeName = null;
		if (F_SemanticStateOfInMemoryModel.cellEditorIsLive()) {
			this.setUniqueRepresentationReference(UUID.randomUUID());
		} else {
			this.setUniqueRepresentationReference(kernelIdentityReservoir.getNextUUIDForAnonymousUse());
		}
		this.setIdentifier(semanticIdentity.identifier());
	}
	public IdentityImpl(final String name, final String pluralName, final UUID identifier, final UUID uniqueRepresentationReference) {
		super();
		this.setIdentifier(identifier);
		this.setUniqueRepresentationReference(uniqueRepresentationReference);
		this.setName(name);
		this.setPluralName(pluralName);
		this.setCodeName(null);
		this.setPluralCodeName(null);
		this.newName = null;
		this.newPluralName = null;
		this.newCodeName = null;
		this.newPluralCodeName = null;
	}

	/* Implementation of semantics */

	public boolean isEqualToRepresentation(final Identity representation) {
		if (this.uniqueRepresentationReference().equals(representation.uniqueRepresentationReference())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEqualTo(final Identity semanticIdentity) {
		if (this.identifier().equals(semanticIdentity.identifier())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEqualTo(final Set property) {
		if (this.identifier().equals(property.identity().identifier())) {
			return true;
		} else {
			return false;
		}
	}
	public UUID identifier() {
		return identifier;
	}
	public UUID uniqueRepresentationReference() {
		return uniqueRepresentationReference;
	}

	public String name() {
		return name;
	}

	public String pluralName() {
		return pluralName;
	}

	public String technicalName() {
		// simplistic implementation that can be refined
		return name.replace(" ", "_").replace("-", "_").replace("?", "_").toLowerCase();
	}

	private void setIdentifier(final UUID id) {
		identifier = id;
	}
	private void setUniqueRepresentationReference(final UUID uniqueRepresentationReference) {
		this.uniqueRepresentationReference = uniqueRepresentationReference;
	}

	private void setName(final String name) {
		this.name = name;
	}

	private void setPluralName(final String pluralName) {
		this.pluralName = pluralName;
	}
	private void setCodeName(final String codeName) {
		this.codeName = codeName;
	}

	private void setPluralCodeName(final String pluralCodeName) {
		this.pluralCodeName = pluralCodeName;
	}

	public boolean isPartOfKernel() {
		return kernelIdentityReservoir.isPartOfKernel(this.uniqueRepresentationReference);
	}

	public String payload() {
		return this.payload;
	}

	public String setPayload(final String payload) {
		this.payload = payload;
		return null;
	}

	public boolean isPartOfUniversalCellConcept() {
		return isPartOfUniversalCellConcept;
	}

	public void makePartOfUniversalCellConcept() {
		this.isPartOfUniversalCellConcept = true;
	}
	public void assignNewName(final String newName) {
		this.newName = newName;
	}
	public void assignNewPluralName(final String newPluralName) {
		this.newPluralName = newPluralName;
	}
	public void assignNewCodeName(final String newCodeName) {
		this.newCodeName = newCodeName;
	}
	public void assignNewPluralCodeName(final String newPluralCodeName) {
		this.newPluralCodeName = newPluralCodeName;
	}

	public void commitNewName() {
		this.name = this.newName;
		this.codeName = this.newCodeName;
	}

	public void commitNewPluralName() {
		this.pluralName = this.newPluralName;
		this.pluralCodeName = this.newPluralCodeName;
	}

	public void rollbackNewName() {
		this.newName = this.name;
		this.newCodeName = this.codeName;
	}

	public void rollbackNewPluralName() {
		this.newPluralName = this.pluralName;
		this.newPluralCodeName = this.pluralCodeName;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "< " + name + ": " + identifier + " >";
	}

	public String codeName() {
		if (this.codeName == null) {
			return name;
		} else {
			return codeName;
		}
	}

	public String pluralCodeName() {
		if (this.pluralCodeName == null) {
			return pluralName;
		} else {
			return pluralCodeName;
		}
	}

}
