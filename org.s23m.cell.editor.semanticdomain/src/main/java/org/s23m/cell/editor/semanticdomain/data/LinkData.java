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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.data;

import java.io.Serializable;
import java.util.Vector;

import org.s23m.cell.Set;

@SuppressWarnings("serial")
public class LinkData implements Serializable {

	public static final String FROM_INSTANCE = "fromInstance";
	public static final String TO_INSTANCE = "toInstance";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";

	private final Set fromInstance;
	private Set toInstance;
	private final String source;
	private String target = "";

	public void setToInstance(final Set toInstance) {
		this.toInstance = toInstance;
		this.target = toInstance.identity().name();
	}

	public LinkData(final Set fromInstance) {
		this.fromInstance = fromInstance;
		this.source = fromInstance.identity().name();
	}

	public Set getFromInstance() {
		return fromInstance;
	}

	public Set getToInstance() {
		return toInstance;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}

	public static Vector<String> getDisplayedInstances() {
		final Vector<String> order = new Vector<String>();
		order.add(SOURCE);
		order.add(TARGET);
		return order;
	}


}
