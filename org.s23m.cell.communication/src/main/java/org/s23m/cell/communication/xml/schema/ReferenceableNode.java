package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;

public interface ReferenceableNode {
	
	String getName();

	Namespace getTargetNamespace();
	
	/**
	 * Combination of the name and the target namespace
	 */
	String getIdentifier();
}
