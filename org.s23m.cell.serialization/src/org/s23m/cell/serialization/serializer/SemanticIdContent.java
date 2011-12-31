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
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.util.List;

import org.s23m.cell.serialization.SemanticIdentityIndex;

public class SemanticIdContent {

	private List<SemanticIdentityIndex> semanticIds;

	public List<SemanticIdentityIndex> getSemanticIds() {
		return semanticIds;
	}

	public void setSemanticIds(final List<SemanticIdentityIndex> semanticIds) {
		this.semanticIds = semanticIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
		+ ((semanticIds == null) ? 0 : semanticIds.hashCode());
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
		final SemanticIdContent other = (SemanticIdContent) obj;
		if (semanticIds == null) {
			if (other.semanticIds != null) {
				return false;
			}
		} else if (!semanticIds.equals(other.semanticIds)) {
			return false;
		}
		return true;
	}

}