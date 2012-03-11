package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.WrapperNode;

public class SimpleType extends WrapperNode {
	Namespace targetNamespace; // TODO

	public SimpleType(Namespace namespace, String name, Restriction child) {
		super(namespace, name, child);
	}

}
