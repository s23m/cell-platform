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
package org.s23m.cell.communication.xml;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.communication.SetMarshaller;
import org.s23m.cell.communication.SetMarshallingException;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.schemainstance.Command;
import org.s23m.cell.communication.xml.model.schemainstance.Edge;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;

// TODO add a constructor accepting the Schema to use and validate against
public class XmlSetMarshaller implements SetMarshaller<String> {

	private final Namespace namespace;
	
	private final XmlSchemaTerminology terminology;
		
	public XmlSetMarshaller(Namespace namespace, XmlSchemaTerminology terminology) {
		this.namespace = namespace;
		this.terminology = terminology;
	}		
	
	/*
	 * Implementation:
	 * -> for processing exactly one instance, use the filter() operations:
	 *  filterInstances() goes over all the contained instances (but remember to
	 *  catch the Edge Ends that must be accessed from each Edge via toEdgeEnd() and fromEdgeEnd()).
	 * -> can also use filter(<properClass>) to structure the work by proper classes.
	 * 
	 * Test case:
	 * -> should be able to convert the set of models and the set of semantic domains to the XML format.
	 * 
	 * 
	 * Later on:
	 * 1) serialisation of all sets contained within a top-level agent (recursively)
	 * 2) processing all changed sets within a transaction, all of which should be related to one agent
	 * (instantiation API needs to be agent-aware: multiple transactions open at once, and every transaction
	 * relates to exactly one agent)
	 * 
	 * Test case:
	 * -> processing all semantic domains of an agent and the all models of an agent.
	 * Need to make at least 2 passes along the containment tree to resolve all references.
	 */
	@Override
	public String serialise(Set instance) throws SetMarshallingException {
		// TODO use sets as shown in Scratch.java
		String languageIdentifier = "ENGLISH";
		InstanceBuilder builder = new InstanceBuilder(namespace, terminology, languageIdentifier);
		
		for (final Set containedInstance : instance.filterInstances()) {
			processInstance(builder, containedInstance);
		}
		
		return XmlRendering.render(builder.build()).toString();
	}
	
	// TODO: need to process instances recursively
	// TODO include the Edge Ends from each Edge via toEdgeEnd() and fromEdgeEnd()
	private Model processInstance(InstanceBuilder builder, Set instance) {
		Model model = builder.model(instance);
		
		// process Vertex list
		for (Set vertexInstance : instance.filterProperClass(Query.vertex)) {
			Vertex vertex = builder.vertex(vertexInstance);
			model.addVertex(vertex);
		}
		
		// process Edge list
		for (Set edgeInstance : instance.filterProperClass(Query.edge)) {
			Edge edge = builder.edge(edgeInstance);
			model.addEdge(edge);
		}
		
		// process Visibility list
		for (Set visibilityInstance : instance.filterProperClass(Query.visibility)) {
			Visibility visibility = builder.visibility(visibilityInstance);
			model.addVisibility(visibility);
		}
		
		// process SuperSetReference list
		for (Set superSetReferenceInstance : instance.filterProperClass(Query.superSetReference)) {
			SuperSetReference superSetReference = builder.superSetReference(superSetReferenceInstance);
			model.addSuperSetReference(superSetReference);
		}
		
		// process Command list
		for (Set commandInstance : instance.filter(S23MSemanticDomains.command)) {
			Command command = builder.command(commandInstance);
			model.addCommand(command);
		}
		
		// process Query list
		for (Set queryInstance : instance.filter(S23MSemanticDomains.query)) {
			org.s23m.cell.communication.xml.model.schemainstance.Query query = builder.query(queryInstance);
			model.addQuery(query);
		}
		
		return model;
	}

	@Override
	public Set deserialise(String input) throws SetMarshallingException {
		
	    
		return null;
	}

}
