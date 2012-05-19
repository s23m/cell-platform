package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.Identifier;
import org.s23m.cell.communication.xml.model.schemainstance.IdentityReference;

public class IdentifierProcessor implements SaxElementProcessor<Identifier> {

	@Override
	public Identifier startElement(Namespace namespace, XmlSchemaTerminology terminology, Node top) {
		return new Identifier(namespace, terminology);
	}

	@Override
	public void endElement(Node removed, Node top, String textContent) {
		final Identifier identifier = (Identifier) removed;
		identifier.setText(textContent);
		if (top instanceof IdentityReference) {
			((IdentityReference) top).setIdentifier(identifier);
		}
	}

}
