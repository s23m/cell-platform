package org.s23m.cell.communication.xml

import java.util.UUID

import org.s23m.cell.Identity

import org.s23m.cell.communication.xml.model.dom.Namespace
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.model.schemainstance.Command
import org.s23m.cell.communication.xml.model.schemainstance.Edge
import org.s23m.cell.communication.xml.model.schemainstance.Model
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference
import org.s23m.cell.communication.xml.model.schemainstance.Vertex
import org.s23m.cell.communication.xml.model.schemainstance.Visibility
import org.s23m.cell.communication.xml.model.schemainstance.Query
import org.s23m.cell.communication.xml.model.schemainstance.EdgeEnd
import org.s23m.cell.communication.xml.model.schemainstance.CategoryIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.ContainerIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.MaximumCardinalityIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.ToIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.FromIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.MinimumCardinalityIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.IsContainerIdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.IsNavigableIdentityReference

import static org.s23m.cell.communication.xml.NamespaceConstants.*
import static org.s23m.cell.communication.xml.NamespaceExtensions.*
import org.s23m.cell.Set
import org.s23m.cell.api.models.S23MSemanticDomains
import org.eclipse.xtext.xbase.lib.Pair
import org.s23m.cell.communication.xml.model.schemainstance.IdentityReference
import org.s23m.cell.communication.xml.model.schemainstance.UniqueRepresentationReference
import org.s23m.cell.communication.xml.model.schemainstance.Identifier

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
	
	def model(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val container = container(set)
		val isAbstract = isAbstract(set)
		model(semanticIdentity, category, container, isAbstract)
	}
	
	def model(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			ContainerIdentityReference container,
			IsAbstractIdentityReference isAbstract) {

		val result = new Model(
			namespace,
			terminology
		)
		
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setContainer(container)
		result.setIsAbstract(isAbstract)
		
		artifactSet.addModel(result)
		
		result
	}
	
	def vertex(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val isAbstract = isAbstract(set)
		val maxCardinality = maxCardinality(set)
		vertex(semanticIdentity, category, isAbstract, maxCardinality)
	}
	
	def vertex(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			MaximumCardinalityIdentityReference maxCardinality) {
		
		val result = new Vertex(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setIsAbstract(isAbstract)
		result.setMaxCardinality(maxCardinality)
		result
	}
	
	def visibility(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val isAbstract = isAbstract(set)
		val from = from(set.from)
		val to = to(set.to)
		visibility(semanticIdentity, category, isAbstract, from, to)
	}
	
	def visibility(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			FromIdentityReference from,
			ToIdentityReference to) {
		val result = new Visibility(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setIsAbstract(isAbstract)
		result.setFrom(from)
		result.setTo(to)
		result
	}
	
	def edge(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val isAbstract = isAbstract(set)
		val from = edgeEnd(set.fromEdgeEnd)
		val to = edgeEnd(set.toEdgeEnd)
		edge(semanticIdentity, category, isAbstract, from, to)
	}
		
	def edge(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			EdgeEnd from,
			EdgeEnd to) {
		
		val result = new Edge(
			namespace,
			terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setIsAbstract(isAbstract)
		result.setFrom(from)
		result.setTo(to)
		result
	}
	
	def edgeEnd(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val isAbstract = isAbstract(set)
		val minCardinality = minCardinality(set)
		val maxCardinality = maxCardinality(set)
		val isContainer = isContainer(set)
		val isNavigable = isNavigable(set)
				
		edgeEnd(
			semanticIdentity,
			category,
			isAbstract,
			minCardinality,
			maxCardinality,
			isContainer,
			isNavigable
		)
	}
	
	def edgeEnd(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			MinimumCardinalityIdentityReference minCardinality,
			MaximumCardinalityIdentityReference maxCardinality,
			IsContainerIdentityReference isContainer,
			IsNavigableIdentityReference isNavigable) {
		
		val result = new EdgeEnd(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setIsAbstract(isAbstract)
		result.setMinCardinality(minCardinality)
		result.setMaxCardinality(maxCardinality)
		result.setIsContainer(isContainer)
		result.setIsNavigable(isNavigable)
		result
	}
	
	def superSetReference(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val isAbstract = isAbstract(set)
		val from = from(set.from)
		val to = to(set.to)
		superSetReference(semanticIdentity, category, isAbstract, from, to)
	}
	
	def superSetReference(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			FromIdentityReference from,
			ToIdentityReference to) {

		val result = new SuperSetReference(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setIsAbstract(isAbstract)
		result.setFrom(from)
		result.setTo(to)
		result
	}
	
	def command(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		command(semanticIdentity, category)
	}
	
	def command(SemanticIdentityIdentityReference semanticIdentity,	CategoryIdentityReference category) {
		val result = new Command(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result
	}
	
	def query(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		query(semanticIdentity, category)
	}
	
	def query(SemanticIdentityIdentityReference semanticIdentity, CategoryIdentityReference category) {
		val result = new Query(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result
	}
	
	def semanticIdentity(Set set) {
		val identity = set.identity
		semanticIdentity(identityPair(identity))
	}
	
	def semanticIdentity(Pair<String, String> pair) {
		initialise(new SemanticIdentityIdentityReference(namespace, terminology), pair)
	}
	
	def category(Set set) {
		val identity = set.category.identity
		category(identityPair(identity))
	}
	
	def category(Pair<String, String> pair) {
		initialise(new CategoryIdentityReference(namespace, terminology), pair)
	}
	
	def container(Set set) {
		val identity = set.container.identity
		container(identityPair(identity))
	}
	
	def container(Pair<String, String> pair) {
		initialise(new ContainerIdentityReference(namespace, terminology), pair)
	}
	
	def isAbstract(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::isAbstract)
		isAbstract(pair)
	}
	
	def isAbstract(Pair<String, String> pair) {
		initialise(new IsAbstractIdentityReference(namespace, terminology), pair)
	}
	
	def from(Set set) {
		val identity = set.identity
		from(identityPair(identity))
	}
	
	def from(Pair<String, String> pair) {
		initialise(new FromIdentityReference(namespace, terminology), pair)
	}
	
	def to(Set set) {
		val identity = set.identity
		to(identityPair(identity))
	}
	
	def to(Pair<String, String> pair) {
		initialise(new ToIdentityReference(namespace, terminology), pair)
	}
	
	def maxCardinality(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::maxCardinality)
		maxCardinality(pair)
	}
	
	def maxCardinality(Pair<String, String> pair) {
		initialise(new MaximumCardinalityIdentityReference(namespace, terminology), pair)
	}
	
	def minCardinality(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::minCardinality)
		minCardinality(pair)
	}
	
	def minCardinality(Pair<String, String> pair) {
		initialise(new MinimumCardinalityIdentityReference(namespace, terminology), pair)
	}
	
	def isContainer(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::isContainer)
		isContainer(pair)
	}
	
	def isContainer(Pair<String, String> pair) {
		initialise(new IsContainerIdentityReference(namespace, terminology), pair)
	}
	
	def isNavigable(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::isNavigable)
		isNavigable(pair)
	}
	
	def isNavigable(Pair<String, String> pair) {
		initialise(new IsNavigableIdentityReference(namespace, terminology), pair)
	}
	
	def private <T extends IdentityReference> initialise(T identityReference, Pair<String, String> pair) {
		val urr = new UniqueRepresentationReference(namespace, terminology)
		urr.setText(pair.key)
		identityReference.setUniqueRepresentationReference(urr)
		val identifier = new Identifier(namespace, terminology)
		identifier.setText(pair.value)
		identityReference.setIdentifier(identifier)
		identityReference
	}
	
	def private valueIdentityPair(Set set, Set variable) {
		val retrievedValue = set.value(variable)
		val identity = retrievedValue.identity
		identityPair(identity)
	}
	
	def private Pair<String, String> identityPair(Identity identity) {
		uuid(identity.uniqueRepresentationReference) -> uuid(identity.identifier)
	}
	
	def private String uuid(UUID uuid) {
		uuid.toString
	}
}