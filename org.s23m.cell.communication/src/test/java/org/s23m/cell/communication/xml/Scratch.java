package org.s23m.cell.communication.xml;

public class Scratch {
	public static void main(String[] args) {

		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		
		XmlSchemaTemplate template = new XmlSchemaTemplate(terminology);
		System.out.println("Template output:\n" + template.createSchemaModel());
	}
}
