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

package org.s23m.cell.repository.domainclasses;

public class SemanticIndex {

	private String identity;
	private String instanceTypeName;
	private String metaElementId;
	private String name;
	private String container;
	private Document ownerDocument;
	private String pluralName;
	private String URR;
	private String UUID;

	@SuppressWarnings("unused")
	private SemanticIndex() {
		// needed for Hibernate
	}

	public SemanticIndex(final String UUID, final String identity, final String URR, final String container, final Document ownerDocument, final String name,
			final String pluralName, final String metaElementId, final String instanceTypeName) {
		this.UUID = UUID;
		this.identity = identity;
		this.URR = URR;
		this.container = container;
		setOwnerDocument(ownerDocument);
		this.name = name;
		this.pluralName = pluralName;
		this.metaElementId = metaElementId;
		this.instanceTypeName = instanceTypeName;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SemanticIndex other = (SemanticIndex) obj;
		if (UUID == null) {
			if (other.UUID != null) {
				return false;
			}
		} else if (!UUID.equals(other.UUID)) {
			return false;
		}
		return true;
	}

	public String getContainer() {
		return container;
	}

	public String getIdentity() {
		return identity;
	}

	public String getInstanceTypeName() {
		return instanceTypeName;
	}



	public String getMetaElementId() {
		return metaElementId;
	}

	public String getName() {
		return name;
	}

	public Document getOwnerDocument() {
		return ownerDocument;
	}

	public String getPluralName() {
		return pluralName;
	}

	public String getURR() {
		return URR;
	}

	public String getUUID() {
		return UUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UUID == null) ? 0 : UUID.hashCode());
		return result;
	}

	public void setContainer(final String container) {
		this.container = container;
	}


	public void setIdentity(final String identity) {
		this.identity = identity;
	}

	public void setInstanceTypeName(final String instanceTypeName) {
		this.instanceTypeName = instanceTypeName;
	}

	public void setMetaElementId(final String metaElementId) {
		this.metaElementId = metaElementId;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setOwnerDocument(final Document ownerDocument) {
		this.ownerDocument = ownerDocument;
	}

	public void setPluralName(final String pluralName) {
		this.pluralName = pluralName;
	}

	public void setURR(final String uRR) {
		URR = uRR;
	}

	public void setUUID(final String uUID) {
		UUID = uUID;
	}

}
