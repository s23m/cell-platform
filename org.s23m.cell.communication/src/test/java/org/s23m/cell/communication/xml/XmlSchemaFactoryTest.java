/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Cell.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

// TODO test for maximum reuse (reusable elements whenever there are repeated (name, type) pairs)
// TODO check that all types are qualified
// TODO check children of complexTypes (make sure elements are wrapped in a sequence)
// TODO check isAbstract usage
public class XmlSchemaFactoryTest extends TestCase {
	
	private static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
	
	private static final String NAMESPACE_PREFIX = "xmlns:";

	// TODO discover this automatically
	private static final String OUR_NAMESPACE = "s23m";
	
	private static final Predicate<Node> IS_ELEMENT = new Predicate<Node>() {
		public boolean apply(Node input) {
			return Node.ELEMENT_NODE == input.getNodeType();
		}
	};
	
	private static final Function<String, String> QUALIFY_NAME = new Function<String, String>() {
		public String apply(String input) {
			return OUR_NAMESPACE + ":" + input;
		}
	};
	
	private static final String XSD_STRING = "string";
	
	/* Selection of kernel types */
	private final List<Set> kernelTypes;
	
	private final List<String> kernelTypeNames;
	
	private final XmlSchemaTerminology terminology;
	
	private Document document;
	
	public XmlSchemaFactoryTest() {
		G.boot();
		
		kernelTypes = Arrays.asList(Query.edge, Query.graph, Query.vertex, Query.visibility);
		
		kernelTypeNames = createNames(kernelTypes);
		
		terminology = DefaultXmlSchemaTerminology.getInstance();
	}

	@Override
	protected void setUp() throws Exception {
		XmlSchemaFactory factory = new XmlSchemaFactory();
		document = factory.createSchema(terminology);
	}
	
	public void testBasicStructure() throws TransformerFactoryConfigurationError, TransformerException {
		NodeList rootList = document.getChildNodes();
		assertEquals(1, rootList.getLength());
		
		List<String> complexTypeNames = retrieveComplexTypeNames();
		assertEquals("The list of complex type names must be unique", new HashSet<String>(complexTypeNames).size(), complexTypeNames.size());
		
		assertTrue("Expected: " + kernelTypeNames + "\nActual: " + complexTypeNames, complexTypeNames.containsAll(kernelTypeNames));
	}
	
	public void testAllTermsAreBeingUsed() {
		java.util.Set<String> allTerms = DefaultXmlSchemaTerminology.getAllTerms();
		
		java.util.Set<String> names = retrieveAllElementNames();
		
		assertTrue("Not all terms are being used", names.containsAll(allTerms));
	}
	
	public void testAllDeclaredTypesReferToElements() {
		Collection<Node> allElements = retrieveAllElements();
		java.util.Set<String> ourDeclaredTypes = new HashSet<String>(allElements.size());
		for (Node elementNode : allElements) {
			NamedNodeMap attributes = elementNode.getAttributes();
			Node typeNode = attributes.getNamedItem("type");
			if (typeNode != null) {
				ourDeclaredTypes.add(typeNode.getNodeValue());
			}
		}
		
		// ensure that only our types are kept
		String xsdStringType = retrieveXsdStringType();
		ourDeclaredTypes.remove(xsdStringType);
		
		java.util.Set<String> names = retrieveAllElementNames();
		List<String> qualifiedNames = Lists.transform(new ArrayList<String>(names), QUALIFY_NAME);
		assertTrue("At least one type does not refer to a declared element", qualifiedNames.containsAll(ourDeclaredTypes));
	}
	
	public void testOnlyXsdTypeInUseIsStringType() {
		List<Node> declaredNodes = retrieveAllDeclaredNodes();
		String xsdNamespacePrefix = retrieveXsdNamespacePrefix();
		for (Node node : declaredNodes) {
			NamedNodeMap attributes = node.getAttributes();
			if (attributes != null) {
				Node type = attributes.getNamedItem("type");
				if (type != null) {
					String qualifiedXsdTypeName = type.getNodeValue();
					if (qualifiedXsdTypeName.startsWith(xsdNamespacePrefix)) {
						String xsdTypeName = retrieveXsdStringType();
						assertEquals(qualifiedXsdTypeName, xsdTypeName);
					}
				}
			}
		}
	}
	
	public void testNoAttributesAreDeclared() {
		List<Node> declaredNodes = retrieveAllDeclaredNodes();
		for (Node node : declaredNodes) {
			assertFalse("Node " + node + " is an attribute", Node.ATTRIBUTE_NODE == node.getNodeType());
		}
	}
	
	private String retrieveXsdStringType() {
		return retrieveXsdNamespacePrefix() + ":" + XSD_STRING;
	}
	
	private java.util.Set<String> retrieveAllElementNames() {
		Collection<Node> allElements = retrieveAllElements();
		java.util.Set<String> names = new HashSet<String>(allElements.size());
		for (Node elementNode : allElements) {
			NamedNodeMap attributes = elementNode.getAttributes();
			Node nameNode = attributes.getNamedItem("name");
			if (nameNode != null) {
				names.add(nameNode.getNodeValue());
			}
		}
		return names;
	}
	
	private Collection<Node> retrieveAllElements() {
		Collection<Node> result = Collections2.filter(retrieveAllDeclaredNodes(), IS_ELEMENT);
		assertFalse(result.isEmpty());
		return result;
	}
	
	private List<Node> retrieveAllDeclaredNodes() {
		return retrieveAllChildren(document);
	}
	
	private List<Node> retrieveAllChildren(Node node) {
		// get all child nodes
		List<Node> seen = new ArrayList<Node>();
		NodeList list = node.getChildNodes();
		seen.add(node);
		for (int i = 0; i < list.getLength(); i++) {
			// get child node
			Node childNode = list.item(i);
			seen.add(childNode);
			// visit child node
			List<Node> allChildren = retrieveAllChildren(childNode);
			seen.addAll(allChildren);
		}
		return seen;
	}

	private Node retrieveRoot() {
		NodeList rootList = document.getChildNodes();
		assertEquals(1, rootList.getLength());
		return rootList.item(0);
	}
	
	private String retrieveXsdNamespacePrefix() {
		Node root = retrieveRoot();	
		Node xmlSchemaAttributeNode = findNodeWithValue(root.getAttributes(), XML_SCHEMA_NAMESPACE);
		String namespaceName = xmlSchemaAttributeNode.getNodeName();
		return namespaceName.substring(NAMESPACE_PREFIX.length());
	}
	
	private List<Node> retrieveRootChildNodes() {
		Node root = retrieveRoot();
		return asList(root.getChildNodes());
	}
	
	private List<String> retrieveComplexTypeNames() {
		List<Node> schemaChildren = retrieveRootChildNodes();
		String namespacePrefix = retrieveXsdNamespacePrefix();
		
		List<String> result = new ArrayList<String>();
		for (Node node : schemaChildren) {
			if (node.getNodeName().equals(namespacePrefix + ":complexType")) {
				Node nameAttributeNode = node.getAttributes().getNamedItem("name");
				String nameAttribute = nameAttributeNode.getNodeValue();
				
				result.add(nameAttribute);	
			}
		}
		return result;
	}
	
	private static List<String> createNames(List<Set> sets) {
		List<String> result = new ArrayList<String>(sets.size());
		for (Set set : sets) {
			result.add(set.identity().name());
		}
		return result;
	}
	
	private List<Node> asList(NodeList nodeList) {
		List<Node> result = new ArrayList<Node>(nodeList.getLength());
		for (int i = 0; i < nodeList.getLength(); i++) {
			result.add(nodeList.item(i));
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
