package org.s23m.cell.communication.xml

import java.util.UUID

import org.s23m.cell.Identity

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
import org.s23m.cell.communication.xml.schemainstance.ToIdentityReference
import org.s23m.cell.communication.xml.schemainstance.FromIdentityReference
import org.s23m.cell.communication.xml.schemainstance.MinimumCardinalityIdentityReference
import org.s23m.cell.communication.xml.schemainstance.IsContainerIdentityReference
import org.s23m.cell.communication.xml.schemainstance.IsNavigableIdentityReference

import static org.s23m.cell.communication.xml.NamespaceConstants.*
import static org.s23m.cell.communication.xml.NamespaceExtensions.*
import org.s23m.cell.Set
import org.s23m.cell.api.models.S23MSemanticDomains
import org.eclipse.xtext.xbase.lib.Pair

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
			terminology,
			semanticIdentity,
			category,
			container,
			isAbstract
		)
		
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
		
		new Vertex(
			namespace,
			terminology,
			semanticIdentity,
			category,
			isAbstract,
			maxCardinality
		)
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
	
	def command(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		command(semanticIdentity, category)
	}
	
	def command(SemanticIdentityIdentityReference semanticIdentity,	CategoryIdentityReference category) {
		new Command(namespace, terminology, semanticIdentity, category)
	}
	
	def query(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		query(semanticIdentity, category)
	}
	
	def query(SemanticIdentityIdentityReference semanticIdentity, CategoryIdentityReference category) {
		new Query(namespace, terminology, semanticIdentity, category)
	}
	
	def semanticIdentity(Set set) {
		val identity = set.identity
		semanticIdentity(identityPair(identity))
	}
	
	def semanticIdentity(Pair<String, String> pair) {
		new SemanticIdentityIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def category(Set set) {
		val identity = set.category.identity
		category(identityPair(identity))
	}
	
	def category(Pair<String, String> pair) {
		new CategoryIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def container(Set set) {
		val identity = set.container.identity
		container(identityPair(identity))
	}
	
	def container(Pair<String, String> pair) {
		new ContainerIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def isAbstract(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::isAbstract)
		isAbstract(pair)
	}
	
	def isAbstract(Pair<String, String> pair) {
		new IsAbstractIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def from(Set set) {
		val identity = set.identity
		from(identityPair(identity))
	}
	
	def from(Pair<String, String> pair) {
		new FromIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def to(Set set) {
		val identity = set.identity
		to(identityPair(identity))
	}
	
	def to(Pair<String, String> pair) {
		new ToIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def maxCardinality(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::maxCardinality)
		maxCardinality(pair)
	}
	
	def maxCardinality(Pair<String, String> pair) {
		new MaximumCardinalityIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def minCardinality(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::minCardinality)
		minCardinality(pair)
	}
	
	def minCardinality(Pair<String, String> pair) {
		new MinimumCardinalityIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def isContainer(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::isContainer)
		isContainer(pair)
	}
	
	def isContainer(Pair<String, String> pair) {
		new IsContainerIdentityReference(namespace, terminology, pair.key, pair.value)
	}
	
	def isNavigable(Set set) {
		val pair = valueIdentityPair(set, S23MSemanticDomains::isNavigable)
		isNavigable(pair)
	}
	
	def isNavigable(Pair<String, String> pair) {
		new IsNavigableIdentityReference(namespace, terminology, pair.key, pair.value)
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