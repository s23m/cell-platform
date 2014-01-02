package org.s23m.cell.communication.xml.test;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.communication.SetMarshallingException;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.XmlSetMarshaller;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.models.CellPlatformDomain;

public class XmlSetMarshallerTest extends TestCase {

	public XmlSetMarshallerTest() {
		S23MPlatform.boot();
	}
	
	public void testSerialise() throws SetMarshallingException {
		// should be able to convert the set of models and the set of semantic domains to the XML format
		Set exampleModel = CellPlatformDomain.language;
		
		Namespace namespace = NamespaceConstants.NS_S23M;
		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		XmlSetMarshaller xmlSetMarshaller = new XmlSetMarshaller(namespace, terminology);
		
		//String serialised = xmlSetMarshaller.serialise(exampleModel);
		//System.out.println("serialised: " + serialised);
		
		String serialised = xmlSetMarshaller.serialise(S23MSemanticDomains.cellKernel);
		System.out.println("serialised: " + serialised);
		
		
	}
}
