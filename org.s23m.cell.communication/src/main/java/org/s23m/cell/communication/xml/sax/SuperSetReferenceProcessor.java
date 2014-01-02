package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.Structure;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.xml.sax.Attributes;

public class SuperSetReferenceProcessor implements SaxElementProcessor<SuperSetReference> {

	@Override
	public SuperSetReference startElement(Namespace namespace, XmlSchemaTerminology terminology, Node top, Attributes attributes) {
		return new SuperSetReference(namespace, terminology);
	}

	@Override
	public void endElement(Node removed, Node top, String textContent) {
		if (top instanceof Structure) {
			((Structure) top).addSuperSetReference((SuperSetReference) removed);
		}
	}

}
