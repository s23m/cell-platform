package org.s23m.cell.communication.xml.model.schemainstance;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.AbstractCompositeNode;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;

import com.google.common.collect.ImmutableList;

public class Identity extends AbstractCompositeNode {
	
	private String identifier;
	private String name;
	private String pluralName;
	private String technicalName;
	private StringElement payload;
	
	private final XmlSchemaTerminology terminology;

	public Identity(Namespace namespace, XmlSchemaTerminology terminology) {
		super(namespace, terminology.identity());
		this.terminology = terminology;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
		setAttribute(terminology.identifier(), identifier);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setAttribute(terminology.name(), name);
	}

	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
		setAttribute(terminology.pluralName(), pluralName);
	}

	public String getTechnicalName() {
		return technicalName;
	}

	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
		setAttribute(terminology.technicalName(), technicalName);
	}
	
	public StringElement getPayload() {
		return payload;
	}

	public void setPayload(StringElement payload) {
		this.payload = payload;
	}

	@Override
	public Iterable<? extends Node> getChildren() {
		return ImmutableList.of(payload);
	}
}
