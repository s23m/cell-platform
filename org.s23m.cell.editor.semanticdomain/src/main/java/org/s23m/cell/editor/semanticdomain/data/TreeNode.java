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

import org.s23m.cell.Set;

public class TreeNode {

	public static final Set NO_SET = null;

	private final String urr;
	private final Set set;

	public TreeNode(final String urr, final Set set) {
		this.urr = urr;
		this.set = set;
	}

	public String getName() {
		return set.localVisualRecognitionText();
	}

	public Set getSet() {
		return set;
	}

	public String getUrr() {
		return urr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((urr == null) ? 0 : urr.hashCode());
		return result;
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
		final TreeNode other = (TreeNode) obj;
		if (urr == null) {
			if (other.urr != null) {
				return false;
			}
		} else if (!urr.equals(other.urr)) {
			return false;
		}
		return true;
	}

}
