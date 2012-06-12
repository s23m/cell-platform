package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.Category;
import org.s23m.cell.communication.xml.model.schemainstance.CategoryIdentityReference;

public class CategoryIdentityReferenceProcessor extends AbstractIdentityReferenceProcessor<CategoryIdentityReference> {

	@Override
	protected CategoryIdentityReference createIdentityReference(Namespace namespace,
			XmlSchemaTerminology terminology,
			String uniqueRepresentationReference,
			String identifier) {
		return new CategoryIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier, null);
	}
	
	@Override
	public void endElement(Node removed, Node top, String textContent) {
		if (top instanceof Category) {
			((Category) top).setCategory((CategoryIdentityReference) removed);
		}
	}
}
