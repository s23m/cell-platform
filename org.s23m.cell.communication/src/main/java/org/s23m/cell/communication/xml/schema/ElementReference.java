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

import java.util.LinkedHashMap;

import org.s23m.cell.communication.xml.dom.AbstractLeafNode;

public final class ElementReference extends AbstractLeafNode {
	private final Element referencedElement;
	
	/**
	 * Cardinality (if not specified explicitly, the cardinality
	 * of the referenced element will be used)
	 */
	private final Cardinality cardinality;
	
	public ElementReference(Element referencedElement) {
		this(referencedElement, referencedElement.getCardinality());
	}
	
	public ElementReference(Element referencedElement, Cardinality cardinality) {
		super(Constants.XML_SCHEMA_NAMESPACE, "element");
		this.referencedElement = referencedElement;
		this.referencedElement.addReference(this);
		this.cardinality = cardinality;
		
		final LinkedHashMap<String, String> attributes = getAttributes();
		attributes.putAll(referencedElement.getAttributes());
		
		// replace attributes
		Cardinality.removeFromAttributes(attributes);
		cardinality.addToAttributes(attributes);
	}

	public Element getReferencedElement() {
		return referencedElement;
	}

	public Cardinality getCardinality() {
		return cardinality;
	}
}