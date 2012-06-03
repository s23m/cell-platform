package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.xml.sax.Attributes;

public interface SaxElementProcessor<T extends Node> {

	/**
	 * Creates the element
	 */
	T startElement(Namespace namespace, XmlSchemaTerminology terminology, Node top, Attributes attributes);
	
	/**
	 * Associates the element with its parent after it (together with any children)
	 * has been created
	 */
	void endElement(Node removed, Node top, String textContent);
}
