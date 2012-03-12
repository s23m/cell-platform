package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;

public class Constants {

	private Constants() {}
	
	public static final String XML_SCHEMA_PREFIX = "xsd";
	
	public static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";
	
	public static final Namespace XML_SCHEMA_NAMESPACE = new Namespace(XML_SCHEMA_PREFIX, XML_SCHEMA_URI);

}
