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
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.data;

import java.io.Serializable;

import org.s23m.cell.G;
import org.s23m.cell.Set;

@SuppressWarnings("serial")
public class LinkDetailsData implements Serializable {

    public static final String TO_INSTANCE_PROPERTY = "toInstanceName";

	public static final String FROM_INSTANCE_PROPERTY = "fromInstanceName";

	public static final String NAME_PROPERTY = "name";

	private final String name;

	private final String fromInstanceName;

	private final String toInstanceName;

	public static final String[] DISPLAY_ORDER = new String[] {
		FROM_INSTANCE_PROPERTY,
		TO_INSTANCE_PROPERTY
	};

	public LinkDetailsData(final Set edge) {
		this.name = edge.identity().name();
		if (edge.flavor().isEqualTo(G.coreGraphs.visibility)) {
			this.fromInstanceName = edge.from().identity().name();
			this.toInstanceName = edge.to().identity().name();
		} else if (edge.flavor().isEqualTo(G.coreGraphs.superSetReference)) {
			this.fromInstanceName = edge.from().identity().name();
			this.toInstanceName = edge.to().identity().name();
		} else {
			throw new IllegalStateException("Unsupported type: " + edge.getClass().getSimpleName());
		}
	}

	public String getName() {
		return name;
	}

	public String getFromInstanceName() {
		return fromInstanceName;
	}

	public String getToInstanceName() {
		return toInstanceName;
	}

}
