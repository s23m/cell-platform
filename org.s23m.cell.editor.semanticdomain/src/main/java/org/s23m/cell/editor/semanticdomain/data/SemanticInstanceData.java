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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.data;

import java.io.Serializable;
import java.util.Vector;

import org.s23m.cell.Set;

@SuppressWarnings("serial")
public class SemanticInstanceData implements Serializable {

	public static final String NAME = "name";
	public static final String PLURAL_NAME = "pluralName";
	public static final String SEMANTIC_DOMAIN = "semanticDomain";
	public static final String SEMANTIC_DOMAIN_NAME = "semanticDomainName";
	public static final String REFERENCED_SEMANTIC_IDENTITY = "abstractSemanticRole";
	public static final String REFERENCED_SEMANTIC_IDENTITY_NAME = "referencedSemanticIdentityName";

	private String name;
	private String pluralName;

	private final Set semanticDomain;
	private final String semanticDomainName;
	private Set referencedSemanticIdentity = null;
	private String  referencedSemanticIdentityName = "";

	public SemanticInstanceData(final String name, final String pluralName, final Set semanticDomain) {
		this.name = name;
		this.pluralName = pluralName;
		this.semanticDomain = semanticDomain;
		this.semanticDomainName = semanticDomain.identity().name();
	}

	public SemanticInstanceData(final String name, final String pluralName, final Set semanticDomain,
			final Set referencedSemanticIdentity) {
		this(name, pluralName, semanticDomain);
		this.referencedSemanticIdentity = referencedSemanticIdentity;
		this.referencedSemanticIdentityName = referencedSemanticIdentity.identity().name();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public String getSemanticDomainName() {
		return semanticDomainName;
	}

	public void setPluralName(final String pluralName) {
		this.pluralName = pluralName;
	}

	public Set getSemanticDomain() {
		return semanticDomain;
	}

	public Set getReferencedSemanticIdentity() {
		return referencedSemanticIdentity;
	}

	public void setReferencedSemanticIdentity(final Set referencedSemanticIdentity) {
		this.referencedSemanticIdentity = referencedSemanticIdentity;
		this.referencedSemanticIdentityName = referencedSemanticIdentity.identity().name();
	}

	public String getReferencedSemanticIdentityName() {
		return referencedSemanticIdentityName;
	}

	public void setReferencedSemanticIdentityName(
			final String referencedSemanticIdentityName) {
		this.referencedSemanticIdentityName = referencedSemanticIdentityName;
	}

	public static Vector<String> getDisplayedInstances() {
		final Vector<String> order = new Vector<String>();
		order.add(NAME);
		order.add(PLURAL_NAME);
		order.add(SEMANTIC_DOMAIN_NAME);
		return order;
	}

	public static Vector<String> getDisplayedInstancesForSemanticRole() {
		final Vector<String> order = new Vector<String>();
		order.add(NAME);
		order.add(PLURAL_NAME);
		order.add(SEMANTIC_DOMAIN_NAME);
		order.add(REFERENCED_SEMANTIC_IDENTITY_NAME);
		return order;
	}

}
