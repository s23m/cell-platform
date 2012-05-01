package org.s23m.cell.communication.xml.dom;

public interface CompositeNode extends Node {
	
	Iterable<? extends Node> getChildren();
}
