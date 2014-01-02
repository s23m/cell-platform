package org.s23m.cell.communication.xml.sax;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.IdentityReference;
import org.xml.sax.Attributes;

public abstract class AbstractIdentityReferenceProcessor<T extends IdentityReference> extends AbstractAttributeAwareProcessor<T> {

	@Override
	public final T startElement(Namespace namespace,
			XmlSchemaTerminology terminology,
			Node top,
			Attributes attributes) {
		
		final String uniqueRepresentationReference = getUniqueRepresentationReferenceAttribute(attributes, terminology);
		final String identifier = getIdentifierAttribute(attributes, terminology);
		
		return createIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier);
	}
	
	protected abstract T createIdentityReference(Namespace namespace,
			XmlSchemaTerminology terminology,
			String uniqueRepresentationReference,
			String identifier);

}
