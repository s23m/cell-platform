package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.Namespace

class NamespaceConstants {
	
	/* Schema */
	
	public static String S23M = "s23m"
	
	public static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012"
	
	public static Namespace NS_S23M = new Namespace(S23M, S23M_SCHEMA)
	
	/* Instance */
	
	public static String INSTANCE_NAMESPACE_PREFIX = "xsi"
	
	public static String INSTANCE_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema-instance"
	
}