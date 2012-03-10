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

import org.s23m.cell.communication.xml.dom.LeafNode;
import org.s23m.cell.communication.xml.dom.Namespace;

public final class ElementReference extends LeafNode {
	final Element referencedElement;
	/**
	 * Optional cardinality which can override
	 * that of the referenced element
	 */
	final Cardinality cardinality;
	
	public ElementReference(Namespace namespace, Element referencedElement) {
		this(namespace, referencedElement, null);
	}
	
	public ElementReference(Namespace namespace, Element referencedElement, Cardinality cardinality) {
		super(namespace, "element");
		this.referencedElement = referencedElement;
		this.cardinality = cardinality;
		
		attributes.putAll(referencedElement.attributes);
		if (cardinality != null) {
			// replace attributes
			Cardinality.removeFromAttributes(attributes);
			cardinality.addToAttributes(attributes);
		}
	}
}