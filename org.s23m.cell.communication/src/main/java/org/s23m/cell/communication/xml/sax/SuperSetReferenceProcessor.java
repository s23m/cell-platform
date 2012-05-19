package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;

public class SuperSetReferenceProcessor implements SaxElementProcessor<SuperSetReference> {

	@Override
	public SuperSetReference startElement(Namespace namespace, XmlSchemaTerminology terminology, Node top) {
		return new SuperSetReference(namespace, terminology);
	}

	@Override
	public void endElement(Node removed, Node top, String textContent) {
		if (top instanceof Model) {
			((Model) top).addSuperSetReference((SuperSetReference) removed);
		}
	}

}
