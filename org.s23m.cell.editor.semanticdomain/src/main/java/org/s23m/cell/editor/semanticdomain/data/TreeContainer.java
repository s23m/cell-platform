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
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.data;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.serialization.serializer.InstanceGetter;

import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;

@SuppressWarnings("serial")
public class TreeContainer extends HierarchicalContainer {

	private static final String ID = "id";
	private static final String SET = "set";
	public static final String NAME = "name";

	public static TreeContainer setupContainmentTree() {
		final TreeContainer container = new TreeContainer();
		container.addContainerProperty(NAME, String.class, null);
		container.addContainerProperty(SET, Set.class, null);
		container.addContainerProperty(ID, String.class, null);
		final TreeNode id = addSetToContainer(container, Root.root);
		container.getContainerProperty(id, NAME).setValue(
				Root.root.identity().name());
		setUpTree(id, id, container);
		final Object[] properties = new Object[1];
		properties[0] = NAME;
		final boolean[] ascending = new boolean[1];
		ascending[0] = true;
		container.sort(properties, ascending);
		return container;
	}

	private static void setUpTree(final Object parentId,
			final TreeNode rootNode, final TreeContainer container) {
		final Set rootSet = rootNode.getSet();
		for (final Set s : rootSet.filterInstances()) {
			final TreeNode node = createTreeNodeFromSet(s);
			final Object id = addSetToContainer(container, s);
			container.setParent(id, parentId);
			setUpTree(id, node, container);
		}
	}

	private static TreeNode addSetToContainer(final TreeContainer container, final Set set) {
		final TreeNode node = createTreeNodeFromSet(set);
		final Item item = container.addItem(node);
		if (item != null) {
			item.getItemProperty(NAME).setValue(node.getName());
			item.getItemProperty(ID).setValue(node.getUrr());
		}
		return node;
	}

	private static TreeNode createTreeNodeFromSet(final Set set) {
		Set idSet;
		try {
			idSet = (set.identity().identifier().equals(set.identity()
					.uniqueRepresentationReference())) ? set : InstanceGetter
					.getInstanceByUUID(set.identity().identifier());
		} catch (final IllegalStateException ex) {
			idSet = set;
		}
		final TreeNode node = new TreeNode(set.identity().uniqueRepresentationReference().toString(), set);
		return node;
	}

}
