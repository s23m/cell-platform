package org.s23m.cell.communication.xml;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.platform.S23MPlatform;

import junit.framework.TestCase;

public class CellXmlSchemaTerminologyTest extends TestCase {
	
	private static CellXmlSchemaTerminology terminology;
	
	protected void setUp() throws Exception {
		// prerequisite - must have been booted
		if (terminology == null) {
			S23MKernel.boot();
			S23MPlatform.boot();
			terminology = new CellXmlSchemaTerminology();	
		}
	}

	public void testUniqueness() throws Exception {
		Method[] methods = CellXmlSchemaTerminology.class.getDeclaredMethods(); 
		Set<String> terms = new HashSet<String>();
		for (Method method : methods) {
			if ((method.getModifiers() & Modifier.PUBLIC) != 0) {
				String term = (String) method.invoke(terminology);
				boolean added = terms.add(term);
				if (!added) {
					fail("Term '" + term + "' is not unique (method " + method.getName() + ")");
				}
			}
		}
	}
	
	public void testSomeTerms() {
		assertEquals("from", terminology.from());
		assertEquals("category", terminology.category());
		
		// TODO add more
		
	}
	
	/*
	public void testTermsHaveNoSpaces() {
		// TODO
	}
	*/
}
