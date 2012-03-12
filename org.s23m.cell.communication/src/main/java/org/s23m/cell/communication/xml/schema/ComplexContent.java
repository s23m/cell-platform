package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.WrapperNode;

public final class ComplexContent extends WrapperNode {
	
	public ComplexContent(Extension extension) {
		super(Constants.XML_SCHEMA_NAMESPACE, "complexContent", extension);
	}
}