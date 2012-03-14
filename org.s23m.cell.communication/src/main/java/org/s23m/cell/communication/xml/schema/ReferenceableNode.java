package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Node;

public interface ReferenceableNode extends Node {

	/**
	 * A combination of the target namespace prefix and the name, e.g., "xsd:string"
	 */
	String getIdentifier();
}
