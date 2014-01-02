package org.s23m.cell.communication.xml.model.schemainstance;

import java.util.Collections;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.AbstractCompositeNode;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;

import com.google.common.collect.ImmutableList;

public class Identity extends AbstractCompositeNode {
	
	private String identifier;
	private String nameAttribute;
	private String pluralName;
	private String codeName;
	private String pluralCodeName;
	
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

	public String getNameAttribute() {
		return nameAttribute;
	}

	public void setNameAttribute(String nameAttribute) {
		this.nameAttribute = nameAttribute;
		setAttribute(terminology.name(), nameAttribute);
	}

	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
		setAttribute(terminology.pluralName(), pluralName);
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
		setAttribute(terminology.codeName(), codeName);
	}
	
	public String getPluralCodeName() {
		return pluralCodeName;
	}

	public void setPluralCodeName(String pluralCodeName) {
		this.pluralCodeName = pluralCodeName;
		setAttribute(terminology.pluralCodeName(), pluralCodeName);
	}

	public String getPayload() {
		return payload.getText();
	}

	public void setPayload(String payloadContents) {
		this.payload = new StringElement(namespace, terminology.payload(), payloadContents);
	}
	
	public void setPayload(StringElement payload) {
		this.payload = payload;
	}

	@Override
	public Iterable<? extends Node> getChildren() {
		if (payload.hasText()) {
			return ImmutableList.of(payload);	
		} else {
			return Collections.emptyList();
		}
	}
}
