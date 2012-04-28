package org.s23m.cell.communication.xml;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;
import org.s23m.cell.S23MKernel;
import org.s23m.cell.platform.S23MPlatform;

public class CellXmlSchemaTerminologyTest extends TestCase {
	
	private static final Class<XmlSchemaTerminology> CLASS = XmlSchemaTerminology.class; 
	
	private static CellXmlSchemaTerminology terminology;
	
	private Map<String, String> terms;
	
	protected void setUp() throws Exception {
		// prerequisite - must have been booted
		if (terminology == null) {
			S23MKernel.boot();
			S23MPlatform.boot();
			terminology = new CellXmlSchemaTerminology();
		}
		
		terms = getTerms();
	}
		
	private Map<String, String> getTerms() {
		final Map<String, String> result = new HashMap<String, String>();
		final Method[] methods = XmlSchemaTerminology.class.getDeclaredMethods();
		for (final Method method : methods) {
			final String name = method.getName();
			try {
				final String term = (String) method.invoke(terminology);
				result.put(name, term);
			} catch (Exception e) {
				throw new RuntimeException("Problem invoking method " + method + ": ", e);
			}
		}
		return result;
	}

	@Test
	public void testUniqueness() throws Exception {
		final Map<String, String> terms = getTerms();
		final Set<String> setOfTerms = new HashSet<String>();
		for (final String key : terms.keySet()) {
			final String term = terms.get(key);
			final boolean added = setOfTerms.add(term);
			if (!added) {
				fail("Term '" + term + "' is not unique");
			}
		}
	}
	
	@Test
	public void testSomeTerms() {
		assertEquals("category", terminology.category());
		assertEquals("from", terminology.from());
	}
	
	@Test
	public void testTermsHaveNoSpaces() {
		for (final Map.Entry<String, String> entry : terms.entrySet()) {
			final String term = entry.getValue();
			// TODO re-enable
			//assertFalse("Term " + term + " contains a space", term.contains(" "));
		}
	}
}
