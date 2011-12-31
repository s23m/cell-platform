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
public class InstantiationData implements Serializable {

	public static final String METAINSTANCE_NAME = "metaElement";
	public static final String METAINSTANCE = "metaInstance";
	public static final String NAME = "name";
	public static final String PLURAL_NAME = "pluralName";
	public static final String IS_ABSTRACT = "abstract";
	public static final String CONTAINER = "container";
	public static final String IDENTITY = "semanticIdentity";

	private String metaElement;
	private final String container;
	private boolean isAbstract;
	private String name;
	private String pluralName;
	private Set metaInstance;
	private Set identity;

	public static Vector<String> getDisplayOrder() {
		final Vector<String> order = new Vector<String>();
		order.add(METAINSTANCE_NAME);
		order.add(CONTAINER);
		order.add(NAME);
		order.add(PLURAL_NAME);
		order.add(IS_ABSTRACT);
		return order;
	}

	public InstantiationData(final Set metaInstance, final String container, final boolean isAbstract,
			final String name, final String pluralName) {
		this.metaElement = metaInstance.identity().name();
		this.metaInstance = metaInstance;
		this.container = container;
		this.isAbstract = isAbstract;
		this.name = name;
		this.pluralName = pluralName;
	}

	public String getContainer() {
		return container;
	}

	public String getMetaElement() {
		return metaElement;
	}

	public Set getMetaInstance() {
		return metaInstance;
	}

	public String getName() {
		return name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(final boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public void setMetaInstance(final Set instance) {
		this.metaInstance = instance;
		this.metaElement = metaInstance.identity().name();
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPluralName(final String pluralName) {
		this.pluralName = pluralName;
	}

	public void setIdentity(final Set identity) {
		this.identity = identity;
		this.name = identity.identity().name();
		this.pluralName = identity.identity().pluralName();
	}

	public Set getIdentity() {
		return identity;
	}

}
