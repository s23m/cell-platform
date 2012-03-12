package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;

public interface ReferenceableNode extends Node {
	
	String getNameAttribute();

	Namespace getTargetNamespace();
	
	/**
	 * Combination of the name and the target namespace
	 */
	String getIdentifier();
}
