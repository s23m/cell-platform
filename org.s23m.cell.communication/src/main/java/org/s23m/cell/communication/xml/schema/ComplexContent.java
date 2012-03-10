package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.WrapperNode;

public final class ComplexContent extends WrapperNode {
	
	public ComplexContent(Namespace namespace, Extension extension) {
		super(namespace, "complexContent", extension);
	}
}