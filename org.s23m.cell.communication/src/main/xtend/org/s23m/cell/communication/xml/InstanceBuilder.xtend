package org.s23m.cell.communication.xml

import java.util.UUID

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
import org.s23m.cell.communication.xml.model.schemainstance.Parameter
import org.s23m.cell.communication.xml.model.schemainstance.Identity
import org.s23m.cell.communication.xml.model.schemainstance.LanguageIdentityReference

class InstanceBuilder {
	
	Set chosenLanguage	
	
	Namespace namespace
	
	XmlSchemaTerminology terminology
	
	boolean populateIdentityNameAttributes
		
	new(Namespace namespace, XmlSchemaTerminology terminology, Set chosenLanguage) {
		this.namespace = namespace
		this.terminology = terminology
		this.chosenLanguage = chosenLanguage
		this.populateIdentityNameAttributes = !terminology.machineEncoding	
	}
	
	def artifactSet() {
		val languageReference = language(chosenLanguage)
		val result = new ArtifactSet(namespace, terminology, languageReference)
		result.setAttribute(xmlns(INSTANCE_NAMESPACE_PREFIX), INSTANCE_SCHEMA_URI)
		result.setAttribute(xmlns(S23M), S23M_SCHEMA_URI)
		result
	}
	
	/* Model */
	
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
			
		result
	}
	
	/* Semantic Domain */
	
	def semanticDomain(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val container = container(set)
		val isAbstract = isAbstract(set)		
		semanticDomain(semanticIdentity, category, container, isAbstract)
	}
	
	def semanticDomain(SemanticIdentityIdentityReference semanticIdentity,
					CategoryIdentityReference category,
					ContainerIdentityReference container,
					IsAbstractIdentityReference isAbstract) {
		val result = new org.s23m.cell.communication.xml.model.schemainstance.SemanticDomainNode(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setContainer(container)
		result.setIsAbstract(isAbstract)
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
		val from = fromEdgeEnd(set.fromEdgeEnd)
		val to = toEdgeEnd(set.toEdgeEnd)
		edge(semanticIdentity, category, isAbstract, from, to)
	}
		
	def edge(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			EdgeEnd from,
			EdgeEnd to) {
		
		val result = new Edge(
			namespace,
			terminology
		)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setIsAbstract(isAbstract)
		result.setFrom(from)
		result.setTo(to)
		result
	}
	
	def toEdgeEnd(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val isAbstract = isAbstract(set)
		val minCardinality = minCardinality(set)
		val maxCardinality = maxCardinality(set)
		val isContainer = isContainer(set)
		val isNavigable = isNavigable(set)
				
		toEdgeEnd(
			semanticIdentity,
			category,
			isAbstract,
			minCardinality,
			maxCardinality,
			isContainer,
			isNavigable
		)
	}
	
	def fromEdgeEnd(Set set) {
		val semanticIdentity = semanticIdentity(set)
		val category = category(set)
		val isAbstract = isAbstract(set)
		val minCardinality = minCardinality(set)
		val maxCardinality = maxCardinality(set)
		val isContainer = isContainer(set)
		val isNavigable = isNavigable(set)
				
		fromEdgeEnd(
			semanticIdentity,
			category,
			isAbstract,
			minCardinality,
			maxCardinality,
			isContainer,
			isNavigable
		)
	}
	
	def toEdgeEnd(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			MinimumCardinalityIdentityReference minCardinality,
			MaximumCardinalityIdentityReference maxCardinality,
			IsContainerIdentityReference isContainer,
			IsNavigableIdentityReference isNavigable) {
		
		initialise(
			EdgeEnd::toEdgeEnd(namespace, terminology),
			semanticIdentity,
			category,
			isAbstract,
			minCardinality,
			maxCardinality,
			isContainer,
			isNavigable
		)		
	}	
	
	def fromEdgeEnd(SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			MinimumCardinalityIdentityReference minCardinality,
			MaximumCardinalityIdentityReference maxCardinality,
			IsContainerIdentityReference isContainer,
			IsNavigableIdentityReference isNavigable) {
		
		initialise(
			EdgeEnd::fromEdgeEnd(namespace, terminology),
			semanticIdentity,
			category,
			isAbstract,
			minCardinality,
			maxCardinality,
			isContainer,
			isNavigable
		)		
	}
	
	def private initialise(EdgeEnd result,
			SemanticIdentityIdentityReference semanticIdentity,
			CategoryIdentityReference category,
			IsAbstractIdentityReference isAbstract,
			MinimumCardinalityIdentityReference minCardinality,
			MaximumCardinalityIdentityReference maxCardinality,
			IsContainerIdentityReference isContainer,
			IsNavigableIdentityReference isNavigable) {
		
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
	
	def parameter(SemanticIdentityIdentityReference semanticIdentity, CategoryIdentityReference category) {
		val result = new Parameter(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result
	}
	
	def semanticIdentity(Set set) {
		val identityTriple = identityReference(set.category)
		new SemanticIdentityIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def category(Set set) {
		val identityTriple = identityReference(set.category)
		new CategoryIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def container(Set set) {
		val identityTriple = identityReference(set)
		new ContainerIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def isAbstract(Set set) {
		val identityTriple = valueIdentityReference(set, S23MSemanticDomains::isAbstract)
		new IsAbstractIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def from(Set set) {
		val identityTriple = identityReference(set)
		new FromIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def to(Set set) {
		val identityTriple = identityReference(set)
		new ToIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def language(Set set) {
		val ref = identityReference(set)
		new LanguageIdentityReference(
			namespace,
			terminology,
			ref.uniqueRepresentationReference,
			ref.identifier,
			ref.nameAttribute
		)
	}
	
	def maxCardinality(Set set) {
		val identityTriple = valueIdentityReference(set, S23MSemanticDomains::maxCardinality)
		new MaximumCardinalityIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def minCardinality(Set set) {
		val identityTriple = valueIdentityReference(set, S23MSemanticDomains::minCardinality)
		new MinimumCardinalityIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def isContainer(Set set) {
		val identityTriple = valueIdentityReference(set, S23MSemanticDomains::isContainer)
		new IsContainerIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)
	}
	
	def isNavigable(Set set) {
		val identityTriple = valueIdentityReference(set, S23MSemanticDomains::isNavigable)
		new IsNavigableIdentityReference(
			namespace,
			terminology,
			identityTriple.uniqueRepresentationReference,
			identityTriple.identifier,
			identityTriple.nameAttribute
		)		
	}
		
	def identity(Set set) {
		val result = new Identity(namespace, terminology)
		val identity = set.identity()
		result.setIdentifier(identity.identifier.toString)
		result.setNameAttribute(identity.name)
		result.setPluralName(identity.pluralName)
		result.setCodeName(identity.codeName)
		result.setPluralCodeName(identity.pluralCodeName)
		result.setPayload(identity.payload)
		result
	}
	
	def private valueIdentityReference(Set set, Set variable) {
		val retrievedValue = set.value(variable)
		identityReference(retrievedValue)
	}
	
	def private identityReference(Set set) {
		val identity = set.identity
		val uniqueRepresentationReference = uuid(identity.uniqueRepresentationReference)
		val identifier = uuid(identity.identifier)
		val nameAttribute = if (populateIdentityNameAttributes) identity.name() else null
		new IdentityReferenceAttributes(uniqueRepresentationReference, identifier, nameAttribute)
	}
	
	def private String uuid(UUID uuid) {
		uuid.toString
	}
}