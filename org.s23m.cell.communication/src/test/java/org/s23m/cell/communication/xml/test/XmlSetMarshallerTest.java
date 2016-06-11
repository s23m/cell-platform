package org.s23m.cell.communication.xml.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.s23m.cell.Set;
import org.s23m.cell.communication.AgencyTestFoundationTestCase;
import org.s23m.cell.communication.SetMarshallingException;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.XmlSetMarshaller;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

public class XmlSetMarshallerTest extends AgencyTestFoundationTestCase {

	private List<Set> exampleModels = new ArrayList<Set>();
	
	protected void doAdditionalSetup() {
		// only instances of Agents or any of their contained models ever need to be serialised 
		// ithanku is an example of an instance of an Agent. 
		exampleModels.add(AgencyTestFoundation.ithanku);
		exampleModels.add(AgencyTestFoundation.ernst);
	}
	
	public void testSerialise() throws SetMarshallingException {
		// should be able to convert the set of models and the set of semantic domains to the XML format
		// WRONG EXAMPLE Set exampleModel = CellPlatformDomain.language;
		// does not work because CellPlatformDomain.language is part of the definition (category model) of the Cell Platform, and not a direct or indirect instance of a category defined in the CellPlatform 
		
		
		for (Set exampleModel : exampleModels) {
			Namespace namespace = NamespaceConstants.NS_S23M;
			XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
			XmlSetMarshaller xmlSetMarshaller = new XmlSetMarshaller(namespace, terminology);
			
			String serialised = xmlSetMarshaller.serialise(exampleModel);
			Assert.assertNotNull(serialised);
			
			//System.out.println("serialised: " + serialised);
			
			// String serialised = xmlSetMarshaller.serialise(Instantiation.toSemanticDomain(ithanku));
			// ALTERNATIVE EXAMPLE : String serialised = xmlSetMarshaller.serialise(Instantiation.toSemanticDomain(ernst));
			//System.out.println("serialised: " + serialised);
		}	
	}
}
