package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.AbstractNode;

/**
 * Used for built-in data types only
 */
public class DataType extends AbstractNode {
	
	public static final DataType STRING = new DataType("string");

	private DataType(String name) {
		super(Constants.XML_SCHEMA_NAMESPACE, name);
	}
}
