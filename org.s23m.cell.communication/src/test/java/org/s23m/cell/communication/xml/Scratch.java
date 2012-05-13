package org.s23m.cell.communication.xml;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.communication.SetMarshallingException;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;

public class Scratch {
	public static void main(String[] args) throws SetMarshallingException {
		S23MKernel.boot();
		
		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		
		XmlSetMarshaller marshaller = new XmlSetMarshaller(NamespaceConstants.NS_S23M, terminology);
		
		Set set = InstantiationSequences.getInstance().crm;
		
		String xml = marshaller.serialise(set);
		System.out.println("xml: " + xml);
	}
}
