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

import java.util.ArrayList;
import java.util.List;

import org.s23m.cell.communication.xml.model.dom.AbstractLeafNode;
import org.s23m.cell.communication.xml.model.dom.Namespace;

public final class Element extends AbstractLeafNode implements ReferenceableNode {
	private final Namespace targetNamespace;
	private final String name;
	private final Cardinality cardinality;
	private final Type type;
	private final List<ElementReference> references;
	
	public Element(Namespace targetNamespace, String name, Type type, Cardinality cardinality) {
		super(XmlSchemaConstants.XML_SCHEMA_NAMESPACE, "element");
		this.targetNamespace = targetNamespace;
		this.name = name;
		this.type = type;
		this.cardinality = cardinality;
		this.references = new ArrayList<ElementReference>();
		
		setAttribute("name", name);
		setAttribute("type", type.getIdentifier());
		updateCardinality(cardinality);
	}

	public void addReference(ElementReference elementReference) {
		references.add(elementReference);
	}
	
	public List<ElementReference> getReferences() {
		return references;
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