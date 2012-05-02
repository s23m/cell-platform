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
package org.s23m.cell.communication.xml.schemainstance;

/*
<xsd:element name="uniqueRepresentationReference" type="s23m:uuid"/>
<xsd:element name="identifier" type="s23m:uuid"/>
 */
public class IdentityReference {
	
	// s23m:uuid
	private final String uniqueRepresentationReference;
	
	// s23m:uuid
	private final String identifier;

	public IdentityReference(String uniqueRepresentationReference, String identifier) {
		this.uniqueRepresentationReference = uniqueRepresentationReference;
		this.identifier = identifier;
	}

	public String getUniqueRepresentationReference() {
		return uniqueRepresentationReference;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
