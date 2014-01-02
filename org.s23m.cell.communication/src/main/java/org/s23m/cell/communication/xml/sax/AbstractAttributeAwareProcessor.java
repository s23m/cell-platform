package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.xml.sax.Attributes;

public abstract class AbstractAttributeAwareProcessor<T extends Node> implements SaxElementProcessor<T> {

	protected String getUniqueRepresentationReferenceAttribute(Attributes attributes, XmlSchemaTerminology terminology) {
		return attributes.getValue(terminology.uniqueRepresentationReference());
	}

	protected String getIdentifierAttribute(Attributes attributes, XmlSchemaTerminology terminology) {
		return attributes.getValue(terminology.identifier());
	}
}
