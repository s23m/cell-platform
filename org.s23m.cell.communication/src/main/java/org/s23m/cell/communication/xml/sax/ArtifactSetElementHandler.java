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
import org.s23m.cell.communication.xml.model.schemainstance.Category;
import org.s23m.cell.communication.xml.model.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Command;
import org.s23m.cell.communication.xml.model.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Edge;
import org.s23m.cell.communication.xml.model.schemainstance.EdgeEnd;
import org.s23m.cell.communication.xml.model.schemainstance.FromIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Graph;
import org.s23m.cell.communication.xml.model.schemainstance.Identifier;
import org.s23m.cell.communication.xml.model.schemainstance.IdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsNavigableIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MaximumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MinimumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.Parameter;
import org.s23m.cell.communication.xml.model.schemainstance.Query;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.StringElement;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.ToIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.UniqueRepresentationReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;
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
		} else if (localName.equals(terminology.vertex())) {
			stack.push(new Vertex(namespace, terminology));
		} else if (localName.equals(terminology.maximumCardinality())) {
			stack.push(new MaximumCardinalityIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.visibility())) {
			stack.push(new Visibility(namespace, terminology));
		} else if (localName.equals(terminology.edge())) {
			stack.push(new Edge(namespace, terminology));
		} else if (localName.equals(terminology.from())) {
			System.out.println("Current stack: " + stack);
			// TODO problem with ambiguity - which "from" are we talking about?
			Node top = stack.peek();
			if (top instanceof Visibility || top instanceof SuperSetReference) {
				stack.push(new FromIdentityReference(namespace, terminology));
			} else if (top instanceof Edge) {
				stack.push(new EdgeEnd(namespace, terminology));
			}
		} else if (localName.equals(terminology.to())) {
			System.out.println("Current stack: " + stack);
			// TODO problem with ambiguity - which "to" are we talking about?
			Node top = stack.peek();
			if (top instanceof Visibility || top instanceof SuperSetReference) {
				stack.push(new ToIdentityReference(namespace, terminology));
			} else if (top instanceof Edge) {
				stack.push(new EdgeEnd(namespace, terminology));
			}
		} else if (localName.equals(terminology.minimumCardinality())) {
			stack.push(new MinimumCardinalityIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.isContainer())) {
			stack.push(new IsContainerIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.isNavigable())) {
			stack.push(new IsNavigableIdentityReference(namespace, terminology));
		} else if (localName.equals(terminology.superSetReference())) {
			stack.push(new SuperSetReference(namespace, terminology));
		} else if (localName.equals(terminology.command())) {
			stack.push(new Command(namespace, terminology));
		} else if (localName.equals(terminology.query())) {
			stack.push(new Query(namespace, terminology));
		} else if (localName.equals(terminology.parameter())) {
			stack.push(new Parameter(namespace, terminology));
		}
		
		// TODO handle each element type - need a stack to keep track of outstanding elements
		
		System.out.println("startElement: " + localName + ", " + qName + ", " + attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// pop stack and add to 'parent' element, which is next on the stack
		// important to pop stack first, then peek at top element!
		
		Node tmp = stack.pop();

		if (localName.equals(terminology.artifactSet())) {
			result = (ArtifactSet) tmp;
		} else if (localName.equals(terminology.languageIdentifier())) {
			final StringElement languageIdentifier = (StringElement) tmp;
			languageIdentifier.setText(textContent);
			((ArtifactSet) stack.peek()).setLanguageIdentifier(languageIdentifier);
		} else if (localName.equals(terminology.model())) {
			Node top = stack.peek();
			if (top instanceof ArtifactSet) {
				((ArtifactSet) top).addModel((Model) tmp);	
			}
		} else if (localName.equals(terminology.container())) {
			Node top = stack.peek();
			ContainerIdentityReference identityReference = (ContainerIdentityReference) tmp;
			if (top instanceof Graph) {
				((Graph) top).setContainer(identityReference);
			}
		} else if (localName.equals(terminology.isAbstract())) {
			Node top = stack.peek();
			if (top instanceof Graph) {
				((Graph) top).setIsAbstract((IsAbstractIdentityReference) tmp);
			} else if (top instanceof Vertex) {
				((Vertex) top).setIsAbstract((IsAbstractIdentityReference) tmp);
			} else if (top instanceof Visibility) {
				((Visibility) top).setIsAbstract((IsAbstractIdentityReference) tmp);
			} else if (top instanceof Edge) {
				((Edge) top).setIsAbstract((IsAbstractIdentityReference) tmp);
			} else if (top instanceof SuperSetReference) {
				((SuperSetReference) top).setIsAbstract((IsAbstractIdentityReference) tmp);
			} else if (top instanceof EdgeEnd) {
				((EdgeEnd) top).setIsAbstract((IsAbstractIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.semanticIdentity())) {
			Node top = stack.peek();
			if (top instanceof Category) {
				((Category) top).setSemanticIdentity((SemanticIdentityIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.category())) {
			Node top = stack.peek();
			if (top instanceof Category) {
				((Category) top).setCategory((CategoryIdentityReference) tmp);
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
		} else if (localName.equals(terminology.vertex())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).addVertex((Vertex) tmp);
			}
		} else if (localName.equals(terminology.maximumCardinality())) {
			Node top = stack.peek();
			if (top instanceof Vertex) {
				((Vertex) top).setMaxCardinality((MaximumCardinalityIdentityReference) tmp);
			} else if (top instanceof EdgeEnd) {
				((EdgeEnd) top).setMaxCardinality((MaximumCardinalityIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.visibility())) {
			Node top = stack.peek();
			if (top instanceof Visibility) {
				((Model) top).addVisibility((Visibility) tmp);
			}
		} else if (localName.equals(terminology.edge())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).addEdge((Edge) tmp);
			}
		} else if (localName.equals(terminology.from())) {
			Node top = stack.peek();
			if (top instanceof Visibility) {
				((Visibility) top).setFrom((FromIdentityReference) tmp);
			} else if (top instanceof SuperSetReference) {
				((SuperSetReference) top).setFrom((FromIdentityReference) tmp);
			} else if (top instanceof Edge) {
				((Edge) top).setFrom((EdgeEnd) tmp);
			}
		} else if (localName.equals(terminology.to())) {
			Node top = stack.peek();
			if (top instanceof Visibility) {
				((Visibility) top).setTo((ToIdentityReference) tmp);
			} else if (top instanceof SuperSetReference) {
				((SuperSetReference) top).setTo((ToIdentityReference) tmp);
			} else if (top instanceof Edge) {
				((Edge) top).setTo((EdgeEnd) tmp);
			}
		} else if (localName.equals(terminology.minimumCardinality())) {
			Node top = stack.peek();
			if (top instanceof EdgeEnd) {
				((EdgeEnd) top).setMinCardinality((MinimumCardinalityIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.isContainer())) {
			Node top = stack.peek();
			if (top instanceof EdgeEnd) {
				((EdgeEnd) top).setIsContainer((IsContainerIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.isNavigable())) {
			Node top = stack.peek();
			if (top instanceof EdgeEnd) {
				((EdgeEnd) top).setIsNavigable((IsNavigableIdentityReference) tmp);
			}
		} else if (localName.equals(terminology.superSetReference())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).addSuperSetReference((SuperSetReference) tmp);
			}
		} else if (localName.equals(terminology.command())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).addCommand((Command) tmp);
			}
		} else if (localName.equals(terminology.query())) {
			Node top = stack.peek();
			if (top instanceof Model) {
				((Model) top).addQuery((Query) tmp);
			}
		} else if (localName.equals(terminology.parameter())) {
			Node top = stack.peek();
			if (top instanceof Command) {
				((Command) top).addParameter((Parameter) tmp);
			} else if (top instanceof Query) {
				((Query) top).addParameter((Parameter) tmp);
			}
		} else {
			if (!stack.isEmpty()) {
				System.out.println("Ignored element. Top of stack: " + stack.peek());
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
