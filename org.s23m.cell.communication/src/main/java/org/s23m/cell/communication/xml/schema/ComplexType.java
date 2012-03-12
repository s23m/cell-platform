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
 * The Original Code is Cell.
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
package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;
import org.s23m.cell.communication.xml.dom.WrapperNode;

public final class ComplexType extends WrapperNode implements ReferenceableNode {
	
	private final Namespace targetNamespace;
	private final String name;
	
	private ComplexType(Node child, String name, Namespace targetNamespace) {
		super(Constants.XML_SCHEMA_NAMESPACE, "complexType", child);
		this.name = name;
		this.targetNamespace = targetNamespace;
		
		setAttribute("name", name);
	}
	
	public ComplexType(Namespace targetNamespace, String name, Sequence child) {
		this(child, name, targetNamespace);
	}
	
	public ComplexType(Namespace targetNamespace, String name, ComplexContent child) {
		this(child, name, targetNamespace);
	}
	
	public Namespace getTargetNamespace() {
		return targetNamespace;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getIdentifier() {
		return targetNamespace + ":" + name;
	}
}