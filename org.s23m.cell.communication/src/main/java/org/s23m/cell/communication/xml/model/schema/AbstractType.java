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

import org.s23m.cell.communication.xml.model.dom.BasicCompositeNode;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;

public abstract class AbstractType extends BasicCompositeNode implements Type {

	private final String nameAttribute;
	
	private final Namespace targetNamespace;
	
	/**
	 * Constructor for the case where the type has a single child 
	 */
	public AbstractType(Namespace targetNamespace, String name, String nameAttribute, Node child) {
		this(targetNamespace, name, nameAttribute);
		addChild(child);
	}

	public AbstractType(Namespace targetNamespace, String name, String nameAttribute) {
		super(XmlSchemaConstants.XML_SCHEMA_NAMESPACE, name);
		this.targetNamespace = targetNamespace;
		this.nameAttribute = nameAttribute;
		setAttribute("name", nameAttribute);
	}

	@Override
	public String getIdentifier() {
		return createQualifiedName(targetNamespace, nameAttribute);
	}
}
