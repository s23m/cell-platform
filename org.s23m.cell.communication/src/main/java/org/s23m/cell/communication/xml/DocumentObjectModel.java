package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
