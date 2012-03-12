package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;

public class SimpleType extends AbstractType {

	public SimpleType(Namespace targetNamespace, String nameAttribute, DataType restrictionDataType) {
		super(targetNamespace, "simpleType", nameAttribute, new Restriction(restrictionDataType));
	}
}
