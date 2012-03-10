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
package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A simplified version of the DOM.
 * 
 * @author andrew
 *
 */
public interface DocumentObjectModel {
	
    abstract class Node {
    	final Namespace namespace;
        
    	/* The tag name */
    	final String name;
    	
    	public Node(Namespace namespace, String name) {
			this.namespace = namespace;
			this.name = name;
		}

		String qualifiedName() {
    		return namespace.prefix + ":" + name;
    	}
     }

    class Namespace {
        final String prefix;
        final String uri;
        
		public Namespace(String prefix, String uri) {
			this.prefix = prefix;
			this.uri = uri;
		}
    }

    abstract class LeafNode extends Node {
    	final LinkedHashMap<String, String> attributes;
		
    	public LeafNode(Namespace namespace, String name) {
			super(namespace, name);
			attributes = new LinkedHashMap<String, String>();
		}
    }

    abstract class CompositeNode extends LeafNode {
        final List<Node> children;
        
        public CompositeNode(Namespace namespace, String name) {
        	super(namespace, name);
        	children = new ArrayList<Node>();
		}
    }
}
