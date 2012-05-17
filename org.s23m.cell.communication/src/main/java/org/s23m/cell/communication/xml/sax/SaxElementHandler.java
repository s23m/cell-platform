package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;

public interface SaxElementHandler<T extends Node> {

	T startElement(Namespace namespace, XmlSchemaTerminology terminology);
	
	void endElement(Node removed, Node top, String textContent);
}
