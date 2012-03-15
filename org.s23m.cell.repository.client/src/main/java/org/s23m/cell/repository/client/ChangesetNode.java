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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.client;

import java.util.ArrayList;
import java.util.List;

import org.s23m.cell.Set;

public class ChangesetNode {

	private final Set set;

	private final ChangesetNode parentNode;

	private final List<ChangesetNode> childNodes;

	public ChangesetNode(final ChangesetNode parentNode, final Set set) {
		this.parentNode = parentNode;
		this.set = set;
		childNodes = new ArrayList<ChangesetNode>();
	}

	protected Set getSet() {
		return set;
	}

	protected ChangesetNode getParentNode() {
		return parentNode;
	}

	protected List<ChangesetNode> getChildNodes() {
		return childNodes;
	}

	public void addChildNode(final ChangesetNode node) {
		childNodes.add(node);
	}

}
