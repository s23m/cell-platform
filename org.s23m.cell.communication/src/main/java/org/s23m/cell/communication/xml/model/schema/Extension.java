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
package org.s23m.cell.communication.xml.model.schema;

import java.util.Collections;
import java.util.List;

import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.dom.WrapperNode;

public final class Extension extends WrapperNode {
	private final ComplexType base;
	private final Sequence sequence;
	
	public Extension(ComplexType base, Sequence sequence) {
		super(XmlSchemaConstants.XML_SCHEMA_NAMESPACE, "extension", sequence);
		this.base = base;
		this.sequence = sequence;
		
		setAttribute("base", base.getIdentifier());
	}
	
	@Override
	public List<Node> getChildren() {
		if (sequence.getChildren().isEmpty()) {
			// omit sequence element for trivial extensions
			return Collections.emptyList();
		} else {
			return super.getChildren();	
		}
	}
	
	public ComplexType getBase() {
		return base;
	}
}