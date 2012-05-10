package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.Namespace
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.schemainstance.Command
import org.s23m.cell.communication.xml.schemainstance.Edge
import org.s23m.cell.communication.xml.schemainstance.Model
import org.s23m.cell.communication.xml.schemainstance.SuperSetReference
import org.s23m.cell.communication.xml.schemainstance.Vertex
import org.s23m.cell.communication.xml.schemainstance.Visibility
import org.s23m.cell.communication.xml.schemainstance.Query
import org.s23m.cell.communication.xml.schemainstance.EdgeEnd
import org.s23m.cell.communication.xml.schemainstance.CategoryIdentityReference
import org.s23m.cell.communication.xml.schemainstance.SemanticIdentityIdentityReference
import org.s23m.cell.communication.xml.schemainstance.ContainerIdentityReference
import org.s23m.cell.communication.xml.schemainstance.IsAbstractIdentityReference
import org.s23m.cell.communication.xml.schemainstance.MaximumCardinalityIdentityReference

import static org.s23m.cell.communication.xml.NamespaceConstants.*
import static org.s23m.cell.communication.xml.NamespaceExtensions.*
import org.s23m.cell.communication.xml.schemainstance.ToIdentityReference
import org.s23m.cell.communication.xml.schemainstance.FromIdentityReference
import org.s23m.cell.communication.xml.schemainstance.MinimumCardinalityIdentityReference
import org.s23m.cell.communication.xml.schemainstance.IsContainerIdentityReference
import org.s23m.cell.communication.xml.schemainstance.IsNavigableIdentityReference

class InstanceBuilder {
	
	ArtifactSet artifactSet
	
	Namespace namespace
	
	XmlSchemaTerminology terminology
		
	new(Namespace namespace, XmlSchemaTerminology terminology, String languageIdentifier) {
		
		this.namespace = namespace
		this.terminology = terminology
		
		this.artifactSet = new ArtifactSet(namespace, terminology, languageIdentifier)
		this.artifactSet.setAttribute(xmlns(INSTANCE_NAMESPACE_PREFIX), INSTANCE_SCHEMA_URI)
		this.artifactSet.setAttribute(xmlns(S23M), S23M_SCHEMA_URI)
	}
	
	def build() {
		artifactSet
	}
	
	def model(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			ContainerIdentityReference container,
			IsAbstractIdentityReference isAbstract) {

		val result = new Model(
			namespace,
			terminology,
			semanticIdentity,
			category,
			container,
			isAbstract
		)
		
		artifactSet.addModel(result)
		
		result
	}
	
	def vertex(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			MaximumCardinalityIdentityReference maxCardinality) {
		
		new Vertex(
			namespace,
			terminology,
			semanticIdentity,
			category,
			isAbstract,
			maxCardinality
		)
	}
	
	def visibility(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			FromIdentityReference from,
			ToIdentityReference to) {
				
		new Visibility(
			namespace,
			terminology,
			semanticIdentity,
			category,
			isAbstract,
			from,
			to
		)
	}
	
	def edge(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			EdgeEnd from,
			EdgeEnd to) {
		
		new Edge(
			namespace,
			terminology,
			semanticIdentity,
			category,
			isAbstract,
			from,
			to
		)
	}
	
	def edgeEnd(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			MinimumCardinalityIdentityReference minCardinality,
			MaximumCardinalityIdentityReference maxCardinality,
			IsContainerIdentityReference isContainer,
			IsNavigableIdentityReference isNavigable) {
		
		new EdgeEnd(
			namespace,
			terminology,
			semanticIdentity,
			category,
			isAbstract,
			minCardinality,
			maxCardinality,
			isContainer,
			isNavigable
		)
	}
	
	def superSetReference(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			FromIdentityReference from,
			ToIdentityReference to) {

		new SuperSetReference(
			namespace,
			terminology,
			semanticIdentity,
			category,
			isAbstract,
			from,
			to
		)
	}
	
	def command(SemanticIdentityIdentityReference semanticIdentity,	CategoryIdentityReference category) {
		new Command(namespace, terminology, semanticIdentity, category)
	}

	def query(SemanticIdentityIdentityReference semanticIdentity, CategoryIdentityReference category) {
		new Query(namespace, terminology, semanticIdentity, category)
	}
	
	def semanticIdentity(String uniqueRepresentationReference, String identifier) {
		new SemanticIdentityIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def category(String uniqueRepresentationReference, String identifier) {
		new CategoryIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def container(String uniqueRepresentationReference, String identifier) {
		new ContainerIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def isAbstract(String uniqueRepresentationReference, String identifier) {
		new IsAbstractIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def from(String uniqueRepresentationReference, String identifier) {
		new FromIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def to(String uniqueRepresentationReference, String identifier) {
		new ToIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def maxCardinality(String uniqueRepresentationReference, String identifier) {
		new MaximumCardinalityIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
}