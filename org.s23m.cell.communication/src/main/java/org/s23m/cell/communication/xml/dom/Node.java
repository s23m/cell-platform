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
package org.s23m.cell.communication.xml.dom;

import java.util.LinkedHashMap;

import org.s23m.cell.communication.xml.schema.Cardinality;

public abstract class Node {
	/* Declared namespace */
	private final Namespace namespace;
    
	/* Tag name */
	private final String name;
	
	private final LinkedHashMap<String, String> attributes;
	
	public Node(Namespace namespace, String name) {
		this.namespace = namespace;
		this.name = name;
		
		attributes = new LinkedHashMap<String, String>();
	}

	public String getQualifiedName() {
		return namespace.prefix + ":" + name;
	}

	protected void setAttribute(String key, String value) {
		this.attributes.put(key, value);
	}

	protected void updateCardinality(Cardinality cardinality) {
		cardinality.addToAttributes(attributes);
	}

	public Namespace getNamespace() {
		return namespace;
	}
	
	public LinkedHashMap<String, String> getAttributes() {
		return attributes;
	}
	
	public String getName() {
		return name;
	}
}
