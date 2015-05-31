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
package org.s23m.cell.communication.impl;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.Command;
import org.s23m.cell.communication.xml.model.schemainstance.Edge;
import org.s23m.cell.communication.xml.model.schemainstance.Identity;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticDomainNode;
import org.s23m.cell.communication.xml.model.schemainstance.Structure;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;
import org.s23m.cell.platform.api.Instantiation;
import org.s23m.cell.platform.api.models.Agency;
import org.s23m.cell.platform.api.models.CellPlatformAgent;

public class ArtifactSetFactory {

	private final Namespace namespace;
	
	private final XmlSchemaTerminology terminology;
	
	public ArtifactSetFactory(final Namespace namespace, final XmlSchemaTerminology terminology) {
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
	 * Later on:
	 * 1) serialisation of all sets contained within a top-level agent (recursively)
	 * 2) processing all changed sets within a transaction, all of which should be related to one agent
	 * (instantiation API needs to be agent-aware: multiple transactions open at once, and every transaction
	 * relates to exactly one agent)
	 * 
	 * Test case:
	 * -> processing all semantic domains of an agent and the all models of an agent.
	 * Need to make at least 2 passes along the containment tree to resolve all references.
	 * 
	 * Sets must be serialised in the right order:
	 * - identities
	 * - semantic domains and semantic identities (which can only be reconstituted with appropriate identities)
	 * - models (which can only be reconstituted with appropriate semantic domains)
	 */
	public ArtifactSet createArtifactSet(Set graph) {
		// TODO allow language to be customised
		Set language = CellPlatformAgent.cellMetaLanguage;
		final InstanceBuilder builder = new InstanceBuilder(namespace, terminology, language);
		final ArtifactSet artifactSet = builder.artifactSet();
		
		for (final Set containedInstance : graph.filterInstances()) {
			serialiseModel(artifactSet, builder, containedInstance);			
		}
		
		final Set containedSemanticDomains = Instantiation.toSemanticDomain(graph).filterPolymorphic(SemanticDomain.semanticdomain);
		for (final Set semanticDomain : containedSemanticDomains) {
			serialiseSemanticDomain(artifactSet, builder, semanticDomain);
		}

		return artifactSet;
	}

	private void serialiseModel(ArtifactSet parent, InstanceBuilder builder, Set set) {
		if (isSuperSetOf(Agency.agent, set.category()) || isSuperSetOf(Agency.stage, set.category())) {
			// cover potential polymorphic extensions of Agent and Stage (these could become a variability)
			final Set semanticDomain = Instantiation.toSemanticDomain(set);
			serialiseSemanticDomain(parent, builder, semanticDomain);
		}
		final Model model = builder.model(set);
		populateStructure(builder, model, set);
		
		parent.addModel(model);
	}

	private void serialiseSemanticDomain(ArtifactSet parent, InstanceBuilder builder, Set set) {
		final SemanticDomainNode semanticDomain = builder.semanticDomain(set);
		
		// serialise identities
		final Set orderedSetOfSemanticIdentities = set.filterPolymorphic(SemanticDomain.semanticIdentity);
		for (Set semanticIdentitySet: orderedSetOfSemanticIdentities) {
			Identity identity = builder.identity(semanticIdentitySet);
			semanticDomain.addIdentity(identity);	
		}
		
		populateStructure(builder, semanticDomain, set);
		
		parent.addSemanticDomain(semanticDomain);
	}

	private Structure populateStructure(InstanceBuilder builder, Structure container, Set structure) {
		
		// process Vertex list
		for (Set vertexInstance : structure.filterProperClass(Query.vertex)) {
			Vertex vertex = builder.vertex(vertexInstance);
			container.addVertex(vertex);
		}
		
		// process Edge list
		for (Set edgeInstance : structure.filterProperClass(Query.edge)) {
			Edge edge = builder.edge(edgeInstance);
			container.addEdge(edge);
		}
		
		// process Visibility list
		for (Set visibilityInstance : structure.filterProperClass(Query.visibility)) {
			Visibility visibility = builder.visibility(visibilityInstance);
			container.addVisibility(visibility);
		}
		
		// process SuperSetReference list
		for (Set superSetReferenceInstance : structure.filterProperClass(Query.superSetReference)) {
			SuperSetReference superSetReference = builder.superSetReference(superSetReferenceInstance);
			container.addSuperSetReference(superSetReference);
		}
		
		// process Command list
		for (Set commandInstance : structure.filter(S23MSemanticDomains.command)) {
			Command command = builder.command(commandInstance);
			container.addCommand(command);
		}
		
		// process Query list
		for (Set queryInstance : structure.filter(S23MSemanticDomains.query)) {
			org.s23m.cell.communication.xml.model.schemainstance.Query query = builder.query(queryInstance);
			container.addQuery(query);
		}
		
		return container;
	}
	
	private boolean isSuperSetOf(Set set, Set candidateSuperSet) {
		return set.isSuperSetOf(candidateSuperSet).isEqualTo(S23MSemanticDomains.is_TRUE);
	}
}
