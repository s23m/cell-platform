package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;
import org.s23m.cell.communication.xml.dom.WrapperNode;

public class AbstractType extends WrapperNode implements Type {

	private final String nameAttribute;
	
	private final Namespace targetNamespace;
	
	public AbstractType(Namespace targetNamespace, String name, String nameAttribute, Node child) {
		super(Constants.XML_SCHEMA_NAMESPACE, name, child);
		this.targetNamespace = targetNamespace;
		this.nameAttribute = nameAttribute;

		setAttribute("name", nameAttribute);
	}

	@Override
	public String getNameAttribute() {
		return nameAttribute;
	}

	@Override
	public Namespace getTargetNamespace() {
		return targetNamespace;
	}

	@Override
	public String getIdentifier() {
		return createQualifiedName(targetNamespace, nameAttribute);
	}

}
