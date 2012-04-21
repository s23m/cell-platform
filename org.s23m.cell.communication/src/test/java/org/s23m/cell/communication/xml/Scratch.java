package org.s23m.cell.communication.xml;

import org.s23m.cell.communication.xml.schema.Schema;

public class Scratch {
	public static void main(String[] args) {

		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		
		XmlSchemaTemplate template = new XmlSchemaTemplate();
		Schema schema = template.createSchemaModel(terminology);
		
		System.out.println("Template output:\n" + SchemaRendering.render(schema));
		
	}
}
