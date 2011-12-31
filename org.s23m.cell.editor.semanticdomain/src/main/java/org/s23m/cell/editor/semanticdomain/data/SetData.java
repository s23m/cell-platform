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
public class SetData implements Serializable, Comparable {

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	private Set set;
	private String name = "";
	private String pluralName = "";
	private String description = "";

	public SetData (final Set set) {
		this.set = set;
		if (set != null) {
			this.name = set.identity().name();
			this.pluralName = set.identity().pluralName();
			this.description = set.toString();
		}
	}

	public Set getSet() {
		return set;
	}

	public void setSet(final Set set) {
		this.set = set;
		this.name = set.identity().name();
		this.pluralName = set.identity().pluralName();
		this.description = set.toString();
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public static Vector<String> getDisplayedInstances() {
		final Vector<String> order = new Vector<String>();
		order.add(NAME);
		return order;
	}

	public static Vector<String> getFullyDisplayedInstances() {
		final Vector<String> order = new Vector<String>();
		order.add(DESCRIPTION);
		return order;
	}

	public int compareTo(final Object arg) {
		final SetData othData = (SetData) arg;
		final String s1 = getSet().identity().name();
		final String s2 = othData.getSet().identity().name();
		return s1.compareTo(s2);
	}

}
