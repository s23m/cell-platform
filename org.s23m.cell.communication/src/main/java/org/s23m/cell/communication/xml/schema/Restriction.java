package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Node;

public class Restriction extends Node {
	
	public Restriction(DataType base) {
		super(Constants.XML_SCHEMA_NAMESPACE, "restriction");

		setAttribute("base", base.getQualifiedName());
	}
}