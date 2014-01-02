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

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.AbstractLeafNode;
import org.s23m.cell.communication.xml.model.dom.Namespace;

public abstract class IdentityReference extends AbstractLeafNode {
	
	private final String uniqueRepresentationReference;
	
	private final String identifier;
	
	private final String nameAttribute;
	
	protected IdentityReference(Namespace namespace,
			XmlSchemaTerminology terminology,
			String name,
			String uniqueRepresentationReference,
			String identifier,
			String nameAttribute) {
		super(namespace, name);
		this.uniqueRepresentationReference = uniqueRepresentationReference;
		this.identifier = identifier;
		this.nameAttribute = nameAttribute;
		
		setAttribute(terminology.uniqueRepresentationReference(), uniqueRepresentationReference);
		setAttribute(terminology.identifier(), identifier);
		
		if (nameAttribute != null) {
			setAttribute(terminology.name(), nameAttribute);	
		}
	}
	
	public String getUniqueRepresentationReference() {
		return uniqueRepresentationReference;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getNameAttribute() {
		return nameAttribute;
	}
}
