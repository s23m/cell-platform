package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import junit.framework.TestCase;

import org.s23m.cell.G;
import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlSchemaFactoryTest extends TestCase {
	
	private static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
	
	private static final String NAMESPACE_PREFIX = "xmlns:";
	
	/* Selection of kernel types */
	private final List<Set> kernelTypes;
	
	private final List<String> kernelTypeNames;
	
	public XmlSchemaFactoryTest() {
		G.boot();
		
		kernelTypes = Arrays.asList(Query.edge, Query.graph, Query.vertex, Query.visibility);
		
		kernelTypeNames = createNames(kernelTypes);
	}

	public void testCreateHumanReadableSchema() throws TransformerFactoryConfigurationError, TransformerException {
		XmlSchemaFactory factory = new XmlSchemaFactory();
		Document document = factory.createHumanReadableSchema();
		
		NodeList rootList = document.getChildNodes();
		assertEquals(1, rootList.getLength());
		
		Node root = rootList.item(0);
		Node xmlSchemaAttributeNode = findNodeWithValue(root.getAttributes(), XML_SCHEMA_NAMESPACE);
		String namespaceName = xmlSchemaAttributeNode.getNodeName();
		String namespacePrefix = namespaceName.substring(NAMESPACE_PREFIX.length());
		assertEquals(namespacePrefix + ":schema", root.getNodeName());
		
		NodeList schemaChildren = root.getChildNodes();
		assertTrue(schemaChildren.getLength() > 0);
		
		List<String> complexTypeNames = new ArrayList<String>();
		for (int i = 0; i < schemaChildren.getLength(); i++) {
			Node node = schemaChildren.item(i);
			if (node.getNodeName().equals(namespacePrefix + ":complexType")) {
				Node nameAttributeNode = node.getAttributes().getNamedItem("name");
				String nameAttribute = nameAttributeNode.getNodeValue();
				
				complexTypeNames.add(nameAttribute);	
			}
		}
		assertTrue("Expected: " + complexTypeNames + "\nActual: " + kernelTypeNames, complexTypeNames.containsAll(kernelTypeNames));
	}
	
	private static List<String> createNames(List<Set> sets) {
		List<String> result = new ArrayList<String>();
		for (Set set : sets) {
			result.add(set.identity().name());
		}
		return result;
	}
	
	private Node findNodeWithValue(NamedNodeMap map, String value) {
		for (int i = 0; i < map.getLength(); i++) {
			Node node = map.item(i);
			if (value.equals(node.getNodeValue())) {
				return node;
			}
		}
		fail("Could not find node with value '" + value + "'");
		return null;
	}
}
