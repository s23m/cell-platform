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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml.model.schemainstance;

import org.s23m.cell.communication.xml.model.dom.AbstractCompositeNode;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public abstract class Category extends AbstractCompositeNode {

	private SemanticIdentityIdentityReference semanticIdentity;
	
	private CategoryIdentityReference category;	
	
	protected Category(Namespace namespace, String name) {
		super(namespace, name);
	}

	public SemanticIdentityIdentityReference getSemanticIdentity() {
		return semanticIdentity;
	}
	
	public void setSemanticIdentity(SemanticIdentityIdentityReference semanticIdentity) {
		this.semanticIdentity = semanticIdentity;
	}
	
	public CategoryIdentityReference getCategory() {
		return category;
	}
	
	public void setCategory(CategoryIdentityReference category) {
		this.category = category;
	}
	
	@Override
	public final Iterable<? extends Node> getChildren() {
		return Iterables.concat(
			ImmutableList.of(semanticIdentity, category),
			getAdditionalChildren()
		);
	}
	
	protected abstract Iterable<? extends Node> getAdditionalChildren();
}
