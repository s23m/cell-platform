package org.s23m.cell.communication.json.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.s23m.cell.Set;
import org.s23m.cell.communication.SetMarshallingException;
import org.s23m.cell.communication.json.JsonSetMarshaller;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.XmlSetMarshaller;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.test.DefaultXmlSchemaTerminology;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

public class JsonSetMarshallerTest extends TestCase {

	private List<Set> exampleModels = new ArrayList<Set>();
	
	public JsonSetMarshallerTest() {
		S23MPlatform.boot();
		AgencyTestFoundation.instantiateFeature();
		
		// only instances of Agents or any of their contained models ever need to be serialised 
		// ithanku is an example of an instance of an Agent. 
		exampleModels.add(AgencyTestFoundation.ithanku);
		exampleModels.add(AgencyTestFoundation.ernst);
	}
	
	public void testSerialise() throws SetMarshallingException {
		// should be able to convert the set of models and the set of semantic domains to the XML format
		
		for (Set exampleModel : exampleModels) {
			Namespace namespace = NamespaceConstants.NS_S23M;
			XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
			JsonSetMarshaller setMarshaller = new JsonSetMarshaller(namespace, terminology);
			
			String serialised = setMarshaller.serialise(exampleModel);
			//System.out.println("serialised: " + serialised);
			
			// String serialised = xmlSetMarshaller.serialise(Instantiation.toSemanticDomain(ithanku));
			// ALTERNATIVE EXAMPLE : String serialised = xmlSetMarshaller.serialise(Instantiation.toSemanticDomain(ernst));
			//System.out.println("serialised: " + serialised);
		}	
	}
}
