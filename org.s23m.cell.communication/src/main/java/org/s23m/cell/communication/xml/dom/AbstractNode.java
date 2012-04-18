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

public abstract class AbstractNode implements Node {
	/* Declared namespace */
	private final Namespace namespace;
    
	/* Tag name */
	private final String name;
	
	private final LinkedHashMap<String, String> attributes;
	
	public AbstractNode(Namespace namespace, String name) {
		this.namespace = namespace;
		this.name = name;
		
		attributes = new LinkedHashMap<String, String>();
	}

	protected String createQualifiedName(Namespace namespace, String name) {
		return namespace.getPrefix() + ":" + name;
	}

	public String getQualifiedName() {
		return createQualifiedName(namespace, name);
	}

	public void setAttribute(String key, String value) {
		this.attributes.put(key, value);
	}

	public void updateCardinality(Cardinality cardinality) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((namespace == null) ? 0 : namespace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNode other = (AbstractNode) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (namespace == null) {
			if (other.namespace != null)
				return false;
		} else if (!namespace.equals(other.namespace))
			return false;
		return true;
	}	
}
