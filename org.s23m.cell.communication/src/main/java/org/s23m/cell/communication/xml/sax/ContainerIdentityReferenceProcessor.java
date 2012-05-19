package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Graph;

public class ContainerIdentityReferenceProcessor implements SaxElementProcessor<ContainerIdentityReference> {

	@Override
	public ContainerIdentityReference startElement(Namespace namespace, XmlSchemaTerminology terminology, Node top) {
		return new ContainerIdentityReference(namespace, terminology);
	}

	@Override
	public void endElement(Node removed, Node top, String textContent) {
		ContainerIdentityReference identityReference = (ContainerIdentityReference) removed;
		if (top instanceof Graph) {
			((Graph) top).setContainer(identityReference);
		}
	}

}
