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

import org.s23m.cell.communication.xml.dom.AbstractLeafNode;
import org.s23m.cell.communication.xml.dom.Namespace;

public final class Element extends AbstractLeafNode implements ReferenceableNode {
	private final Namespace targetNamespace;
	private final String name;
	private final Cardinality cardinality;
	private final Type type;
	
	public Element(Namespace targetNamespace, String name, Type type, Cardinality cardinality) {
		super(Constants.XML_SCHEMA_NAMESPACE, "element");
		this.targetNamespace = targetNamespace;
		this.name = name;
		this.type = type;
		this.cardinality = cardinality;
		
		setAttribute("name", name);
		setAttribute("type", type.getIdentifier());
		updateCardinality(cardinality);
	}
	
	public Namespace getTargetNamespace() {
		return targetNamespace;
	}

	public String getNameAttribute() {
		return name;
	}
	
	public Cardinality getCardinality() {
		return cardinality;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public String getIdentifier() {
		return createQualifiedName(targetNamespace, name);
	}
}