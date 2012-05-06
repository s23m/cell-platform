package org.s23m.cell.communication.xml

import static org.s23m.cell.communication.xml.NamespaceConstants.*
import static org.s23m.cell.communication.xml.NamespaceExtensions.*

class InstanceBuilderTest {
	def static void main(String[] args) {
		// construct the same basic example as in cell-communication-instance.xml
		val s23m = NamespaceConstants::NS_S23M
		val terminology = DefaultXmlSchemaTerminology::getInstance()
		val languageIdentifier = "ENGLISH";
		val builder = new InstanceBuilder(s23m, terminology, languageIdentifier, [
			setAttribute(xmlns(INSTANCE_NAMESPACE_PREFIX), INSTANCE_SCHEMA_URI)
			setAttribute(xmlns(S23M_SCHEMA), S23M_SCHEMA)
		]);
		
		// TODO finish creating object graph
		
		val model = builder.model([
			// TODO remove need for redundant terminology calls (add to DSL) 
			semanticIdentity = builder.identityReference(terminology.semanticIdentity, "1", "2")
			category = builder.identityReference(terminology.category, "3", "4")
			container = builder.identityReference(terminology.container, "5", "6")
			isAbstract = builder.identityReference(terminology.abstract, "7", "8")
			
			
		])
		
		val result = builder.build();
		val xml = XmlRendering::render(result).toString();
		
		println("xml: " + xml);
		null
	}
}