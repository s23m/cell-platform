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

import java.util.Stack;

import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.XmlRendering;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Identifier;
import org.s23m.cell.communication.xml.model.schemainstance.IdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.StringElement;
import org.s23m.cell.communication.xml.model.schemainstance.UniqueRepresentationReference;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.ImmutableList;

/*
 * http://onjava.com/pub/a/onjava/2002/06/26/xml.html?page=2
 * 
 * At the heart of a program (or class) utilizing the SAX parser typically lies a stack.
 * Whenever an element is started, a new data object of the appropriate type is pushed
 * onto the stack. Later, when the element is closed, the topmost object on the stack
 * has been finished and can be popped. Unless it has been the root element (in which
 * case the stack will be empty after it has been popped), the most recently popped element
 * will have been a child element of the object that now occupies the top position of the stack,
 * and can be inserted into its parent object. This process corresponds to the shift-reduce cycle 
 * of bottom-up parsers. Note how the requirement that XML elements must not overlap is crucial
 * for the proper functioning of this idiom.
 * 
 * TODO also implement the setDocumentLocator() method.
 * "It can be very helpful in debugging. The org.xml.sax.Locator interface
 * acts like a cursor, pointing to the position in the XML document where
 * the last event occurred. It provides useful methods such as getLineNumber()
 * and getColumnNumber()"
 */

// TODO instead of if-else chain, we could have an index of "handlers" for each element type, used in both startElement and endElement
// TODO use a "cursor" pointing to the current top-level element being processed, to avoid instanceof checks
public class ArtifactSetElementHandler extends DefaultHandler {
	
	private final InstanceBuilder builder;
	
	private final Stack<Node> stack;

	private final Namespace namespace;

	private final XmlSchemaTerminology terminology;
	
	private ArtifactSet result;
	
	private String textContent;
	
	public ArtifactSetElementHandler(Namespace namespace, XmlSchemaTerminology terminology, String languageIdentifier) {
		this.terminology = terminology;
		this.namespace = namespace;
		builder = new InstanceBuilder(namespace, terminology, languageIdentifier);
		stack = new Stack<Node>();
		// TODO change so that languageIdentifier is read from the document
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
		
		if (localName.equals(terminology.artifactSet())) {
			stack.push(builder.build());
		} else if (localName.equals(terminology.languageIdentifier())) {
			stack.push(new StringElement(namespace, localName));
		} else if (localName.equals(terminology.model())) {
			stack.push(new Model(namespace, terminology));
		} else if (localName.equals(terminology.container())) {
			stack.push(new ContainerIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.isAbstract())) {
			stack.push(new IsAbstractIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.semanticIdentity())) {
			stack.push(new SemanticIdentityIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.category())) {
			stack.push(new CategoryIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.uniqueRepresentationReference())) {
			stack.push(new UniqueRepresentationReference(namespace, terminology));
		} else if (localName.equals(terminology.identifier())) {
			stack.push(new Identifier(namespace, terminology));
		}
		
		// TODO handle each element type - need a stack to keep track of outstanding elements
		
		System.out.println("startElement: " + localName + ", " + qName + ", " + attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// pop stack and add to 'parent' element, which is next on the stack
		// important to pop stack first, then peek at top element!
		
		Node tmp = null;
		// TODO remove condition once we implement rules for all elements
		if (ImmutableList.of(terminology.artifactSet(),
				terminology.languageIdentifier(),
				terminology.model(),
				terminology.container(),
				terminology.isAbstract(),
				terminology.semanticIdentity(),
				terminology.category(),
				terminology.uniqueRepresentationReference(),
				terminology.identifier()).contains(localName)) {
			tmp = stack.pop();
		}

		if (localName.equals(terminology.artifactSet())) {
			result = (ArtifactSet) tmp;
		} else if (localName.equals(terminology.languageIdentifier())) {
			final StringElement languageIdentifier = (StringElement) tmp;
			languageIdentifier.setText(textContent);
			((ArtifactSet) stack.peek()).setLanguageIdentifier(languageIdentifier);
		} else if (localName.equals(terminology.model())) {
			Node top = stack.peek();
			((ArtifactSet) top).addModel((Model) tmp);
		} else if (localName.equals(terminology.container())) {
			Node top = stack.peek();
			ContainerIdentityReference identityReference = (ContainerIdentityReference) tmp;
			if (top instanceof Model) {
				((Model) top).setContainer(identityReference);
			}
		} else if (localName.equals(terminology.isAbstract())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).setIsAbstract((IsAbstractIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.semanticIdentity())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).setSemanticIdentity((SemanticIdentityIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.category())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).setCategory((CategoryIdentityReference) tmp);
			}			
		} else if (localName.equals(terminology.uniqueRepresentationReference())) {
			final UniqueRepresentationReference reference = (UniqueRepresentationReference) tmp;
			reference.setText(textContent);
			Node top = stack.peek();
			if (top instanceof IdentityReference) {
				((IdentityReference) top).setUniqueRepresentationReference(reference);
			}
		} else if (localName.equals(terminology.identifier())) {
			final Identifier identifier = (Identifier) tmp;
			identifier.setText(textContent);
			Node top = stack.peek();
			if (top instanceof IdentityReference) {
				((IdentityReference) top).setIdentifier(identifier);
			}
		}
		
		System.out.println("endElement: " + localName + ", " + qName);
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
