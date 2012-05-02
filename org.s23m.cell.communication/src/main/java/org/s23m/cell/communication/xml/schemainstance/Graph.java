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

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;

/*
<xsd:element name="container" type="s23m:identityReference"/>
<xsd:element ref="s23m:isAbstract"/>
<xsd:element name="vertex" type="s23m:vertex" minOccurs="0" maxOccurs="unbounded"/>
<xsd:element name="visibility" type="s23m:visibility" minOccurs="0" maxOccurs="unbounded"/>
<xsd:element name="edge" type="s23m:edge" minOccurs="0" maxOccurs="unbounded"/>
<xsd:element name="superSetReference" type="s23m:superSetReference" minOccurs="0" maxOccurs="unbounded"/>            
<xsd:element name="command" type="s23m:command" minOccurs="0" maxOccurs="unbounded"/>
<xsd:element name="query" type="s23m:query" minOccurs="0" maxOccurs="unbounded"/>
 */
public class Graph extends Category {
	
	private IdentityReference container;
	
	protected Graph(Namespace namespace, String name, XmlSchemaTerminology terminology) {
		// TODO add parameters
		super(namespace, name, terminology, null, null);
	}
	
	public Graph(Namespace namespace, XmlSchemaTerminology terminology) {
		this(namespace, terminology.graph(), terminology);
	}
	
}
