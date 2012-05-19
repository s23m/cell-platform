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
 * The Original Code is S23M.
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
package org.s23m.cell.communication.xml.sax;

import java.util.Map;
import java.util.Stack;

import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.XmlRendering;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.ImmutableMap;

/*
 * TODO also implement the setDocumentLocator() method.
 * "It can be very helpful in debugging. The org.xml.sax.Locator interface
 * acts like a cursor, pointing to the position in the XML document where
 * the last event occurred. It provides useful methods such as getLineNumber()
 * and getColumnNumber()"
 */
// TODO use a "cursor" pointing to the current top-level element being processed, to avoid instanceof checks
public class ArtifactSetElementHandler extends DefaultHandler {
	
	private final InstanceBuilder builder;
	
	private final Stack<Node> stack;

	private final Namespace namespace;

	private final XmlSchemaTerminology terminology;

	private final Map<String, SaxElementProcessor<?>> processors;
	
	private ArtifactSet result;
	
	private String textContent;
	
	public ArtifactSetElementHandler(Namespace namespace, XmlSchemaTerminology terminology, String languageIdentifier) {
		this.terminology = terminology;
		this.namespace = namespace;
		builder = new InstanceBuilder(namespace, terminology, languageIdentifier);
		stack = new Stack<Node>();
		
		processors = new ImmutableMap.Builder<String, SaxElementProcessor<?>>()
	        .put(terminology.artifactSet(), new ArtifactSetProcessor(builder))
	        .put(terminology.languageIdentifier(), new LanguageIdentifierProcessor())
	        .put(terminology.model(), new ModelProcessor())
	        .put(terminology.container(), new ContainerIdentityReferenceProcessor())
	        .put(terminology.isAbstract(), new IsAbstractIdentityReferenceProcessor())
	        .put(terminology.semanticIdentity(), new SemanticIdentityIdentityReferenceProcessor())
	        .put(terminology.category(), new CategoryIdentityReferenceProcessor())
	        .put(terminology.uniqueRepresentationReference(), new UniqueRepresentationReferenceProcessor())
	        .put(terminology.identifier(), new IdentifierProcessor())
	        .put(terminology.vertex(), new VertexProcessor())
	        .put(terminology.maximumCardinality(), new MaximumCardinalityIdentityReferenceProcessor())
	        .put(terminology.visibility(), new VisibilityProcessor())
	        .put(terminology.edge(), new EdgeProcessor())
	        .put(terminology.from(), new FromProcessor())
	        .put(terminology.to(), new ToProcessor())
	        .put(terminology.minimumCardinality(), new MinimumCardinalityIdentityReferenceProcessor())
	        .put(terminology.isContainer(), new IsContainerIdentityReferenceProcessor())
	        .put(terminology.isNavigable(), new IsNavigableIdentityReferenceProcessor())
	        .put(terminology.superSetReference(), new SuperSetReferenceProcessor())
	        .put(terminology.parameter(), new ParameterProcessor())
	        .put(terminology.command(), new CommandProcessor())
	        .put(terminology.query(), new QueryProcessor())
	        .build();
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("result: " + XmlRendering.render(result));
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		final Node top = stack.isEmpty() ? null : stack.peek();
		final SaxElementProcessor<?> processor = processors.get(localName);
		if (processor == null) {
			throw new IllegalStateException("No processor was defined for '" + localName + "'");
		}
		Node element = processor.startElement(namespace, terminology, top);
		stack.push(element);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		final Node removed = stack.pop();
		final Node top = stack.isEmpty() ? null : stack.peek();
		if (localName.equals(terminology.artifactSet())) {
			result = (ArtifactSet) removed;
		}
		final SaxElementProcessor<?> processor = processors.get(localName);
		processor.endElement(removed, top, textContent);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		textContent = new String(ch, start, length);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("error: " + e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("fatalError: " + e);
		super.fatalError(e);
	}
}
