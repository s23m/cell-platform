package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public interface DocumentObjectModel {
    abstract class Node {
    	Namespace namespace;
        
    	/* The tag name */
    	String name;
    	
    	String qualifiedName() {
    		return namespace.prefix + ":" + name;
    	}
     }

    class Namespace {
        String prefix;
        String uri;
    }

    abstract class LeafNode extends Node {
    	LinkedHashMap<String, String> attributes;
    }

    abstract class CompositeNode extends LeafNode {
        List<Node> children;
        
        public CompositeNode() {
        	this.children = new ArrayList<Node>();
		}
    }
}
