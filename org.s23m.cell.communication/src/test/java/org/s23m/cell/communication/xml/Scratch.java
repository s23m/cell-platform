package org.s23m.cell.communication.xml;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.communication.SetMarshallingException;
import org.s23m.cell.communication.XmlSetMarshaller;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;
import org.w3c.dom.Document;

public class Scratch {
	public static void main(String[] args) throws SetMarshallingException {
		S23MKernel.boot();
		
		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		
		XmlSetMarshaller marshaller = new XmlSetMarshaller(NamespaceConstants.NS_S23M, terminology);
		Document document = marshaller.serialise(InstantiationSequences.getInstance().crm);
		
	}
}
