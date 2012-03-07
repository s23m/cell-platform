package org.s23m.cell.communication.xml;

import junit.framework.TestCase;

public class DefaultXmlSchemaTerminologyTest extends TestCase {

	public void testTerminology() {
		XmlSchemaTerminology t = DefaultXmlSchemaTerminology.getInstance();
		assertEquals("category", t.category());
		assertEquals("superSetReference", t.superSetReference());
	}
}
