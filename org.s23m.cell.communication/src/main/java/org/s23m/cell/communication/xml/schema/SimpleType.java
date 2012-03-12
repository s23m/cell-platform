package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.WrapperNode;

public class SimpleType extends WrapperNode implements ReferenceableNode {
	
	private final String name;
	
	private final Namespace targetNamespace;

	public SimpleType(Namespace targetNamespace, String name, Restriction child) {
		super(Constants.XML_SCHEMA_NAMESPACE, "simpleType", child);
		this.name = name;
		this.targetNamespace = targetNamespace;
		
		setAttribute("name", name);
	}
	
	@Override
	public String getName() {
		return name;
	}

	public Namespace getTargetNamespace() {
		return targetNamespace;
	}

	@Override
	public String getIdentifier() {
		return targetNamespace + ":" + name;
	}
}
