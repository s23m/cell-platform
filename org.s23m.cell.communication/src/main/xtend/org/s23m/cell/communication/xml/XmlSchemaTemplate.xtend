package org.s23m.cell.communication.xml

import java.util.List
import java.util.Collections
import static java.util.Arrays.*
import static extension org.eclipse.xtext.xbase.lib.IterableExtensions.*
import static extension java.lang.String.*

class XmlSchemaTemplate {
	
	/* Schema constants */
	
	static String XSD = "xsd"
	static String XSD_STRING = xsd("string")
	
	/* Namespace-related constants */
	
	static String S23M = "s23m"
	
	static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012"
	
	static String SCHEMA_ATTRIBUTES = 'xmlns:'+XSD+'="http://www.w3.org/2001/XMLSchema" ' +
									  'xmlns:'+S23M+'="'+S23M_SCHEMA+'" ' +
									  'targetNamespace="'+S23M_SCHEMA+'" ' +
									  'elementFormDefault="qualified" ' +
									  'attributeFormDefault="unqualified"'

	XmlSchemaTerminology terminology
	
	// reused elements
	String semanticIdentity
	String identityReference
	String identityReferenceQualified
	String model
	String category
	String uuid
	String uuidQualified
	String isAbstract
	String maxCardinality
	String minCardinality
	String isContainer
	String isNavigable
	String from
	String to
	
	// kernel concepts
	String graph
	String vertex
	String visibility
	String edge
	String edgeEnd
	String superSetReference
	String command
	String query
	String function
									  
	new(XmlSchemaTerminology terminology) {
		this.terminology = terminology
		this.semanticIdentity = terminology.semanticIdentity
		this.identityReference = terminology.identityReference
		this.identityReferenceQualified = s23m(identityReference)
		this.model = terminology.model
		this.function = terminology.function
		this.category = terminology.category
		this.uuid = terminology.uuid
		this.uuidQualified = s23m(uuid)
		this.isAbstract = terminology.isAbstract
		this.maxCardinality = terminology.maxCardinality
		this.minCardinality = terminology.minCardinality
		this.isContainer = terminology.isContainer
		this.isNavigable = terminology.isNavigable
		this.from = terminology.from
		this.to = terminology.to
		
		this.graph = terminology.graph 
		this.vertex = terminology.vertex
		this.visibility = terminology.visibility
		this.edge = terminology.edge
		this.edgeEnd = terminology.edgeEnd
		this.superSetReference = terminology.superSetReference
		this.command = terminology.command
		this.query = terminology.query
	}

	def createSchema() '''
		<?xml version="1.0" encoding="UTF-8"?>
		«"schema".node(SCHEMA_ATTRIBUTES, asList(
			reusedElements,
			rootElement,
			modelArtefactEncoding,
			vertices,
			arrows,
			artifactFunctionality,
			semanticDomainArtefactEncoding
		))»
	'''
	
	def private reusedElements() '''
		«element(semanticIdentity, identityReferenceQualified)»
		«element(model, s23m(model))»
		«element(category, identityReferenceQualified)»
		«element(isAbstract, identityReferenceQualified)»
		«element(maxCardinality, identityReferenceQualified)»
		«element(minCardinality, identityReferenceQualified)»
		«element(isContainer, identityReferenceQualified)»
		«element(isNavigable, identityReferenceQualified)»
		«element(from, identityReferenceQualified)»
		«element(to, identityReferenceQualified)»
		«element(function, s23m(function))»
	
		«complexType(identityReference, asList(
			element(terminology.uniqueRepresentationReference, uuidQualified),
			element(terminology.identifier, uuidQualified)
		))»
		
		«complexType(category, asList(
			element(s23m(semanticIdentity)),
			element(s23m(category))
		))»
		
		«simpleType(uuid, XSD_STRING)»
	'''
	
	def private rootElement() '''
		«val artifactSet = terminology.artifactSet»
		«val semanticDomain = terminology.semanticDomain»
		
		«element(artifactSet, s23m(artifactSet))»		
		«complexType(artifactSet, asList(
			elementList(s23m(model)),
			elementList(semanticDomain, s23m(semanticDomain))
		))»
	'''
	
	def private modelArtefactEncoding() '''
		«val container = terminology.container»
		
		«complexTypeWithExtension(model, s23m(graph))»
	
		«categoryComplexType(graph, asList(
			element(container, identityReferenceQualified),
			element(s23m(isAbstract)),
			elementList(vertex, s23m(vertex)),
			elementList(visibility, s23m(visibility)),
			elementList(edge, s23m(edge)),
			elementList(superSetReference, s23m(superSetReference)),
			elementList(command, s23m(command)),
			elementList(query, s23m(query))
		))»
	'''
	
	def private vertices() '''
		«categoryComplexType(vertex, asList(
			element(s23m(isAbstract)),
			element(s23m(maxCardinality))
		))»
	'''
	
	def private arrows() '''
	
		«categoryComplexType(superSetReference, asList(
			element(s23m(isAbstract)),
			element(s23m(from)),
			element(s23m(to))
		))»
		«categoryComplexType(visibility, asList(
			element(s23m(isAbstract)),
			element(s23m(from)),
			element(s23m(to))
		))»
		«categoryComplexType(edge, asList(
			element(s23m(isAbstract)),
			element(from, s23m(edgeEnd)),
			element(to, s23m(edgeEnd))
		))»
		«categoryComplexType(edgeEnd, asList(
			element(s23m(isAbstract)),
			element(s23m(minCardinality)),
			element(s23m(maxCardinality)),
			element(s23m(isContainer)),
			element(s23m(isNavigable))
		))»
	'''
	
	def private artifactFunctionality() '''
		«val parameter = terminology.parameter»
		
		«categoryComplexType(function, asList(
			elementList(parameter, s23m(parameter))
		))»
		«categoryComplexType(parameter, Collections::emptyList)»
		«complexTypeWithExtension(command, s23m(function))»
		«complexTypeWithExtension(query, s23m(function))»
	'''
	
	def private semanticDomainArtefactEncoding() '''
		«val identity = terminology.identity»
		«val semanticDomain = terminology.semanticDomain»
		«val identifier = terminology.identifier»
		«val name = terminology.name»
		«val pluralName = terminology.pluralName»
		«val payload = terminology.payload»
		«val technicalName = terminology.technicalName»
		
		«complexType(semanticDomain, asList(
			element(s23m(model)),
			elementList(identity, s23m(identity))
		))»
		
		«complexType(identity, asList(
			element(identifier, s23m(uuid)),
			element(name, XSD_STRING),
			element(pluralName, XSD_STRING),
			element(payload, XSD_STRING),
			element(technicalName, XSD_STRING)
		))»
	'''
	
	def private complexType(String name, List<CharSequence> containedElements) {
		"complexType".node('name="%s"'.format(name), containedElements.join("\n"))
	}
	
	def private complexTypeWithExtension(String name, String extensionBase) {
		complexTypeWithExtension(name, extensionBase, Collections::emptyList)
	}
	
	def private categoryComplexType(String name, List<CharSequence> elementsInSequence) '''
		«complexTypeWithExtension(name, category, elementsInSequence)»
	'''
	
	def private complexTypeWithExtension(String name, String extensionBase, List<CharSequence> containedElements) {
		"complexType".node('name="%s"'.format(name),
			"complexContent".node("",
				"extension".node('base="%s"'.format(s23m(extensionBase)),
					sequence(containedElements)
				)
			)
		)
	}
	
	def private sequence(List<CharSequence> containedElements) '''
		«IF !containedElements.empty»«node("sequence", "", containedElements.join("\n"))»«ENDIF»
	'''
	
	def private simpleType(String name, String baseType) {
		"simpleType".node('name="%s"'.format(name),
			"restriction".node('base="%s"'.format(baseType))
		)
	}
	
	def private element(String name, String type) {
		"element".node('name="%s" type="%s"'.format(name, type))
	}
	
	def private element(String referencedName) {
		"element".node('ref="%s"'.format(referencedName))
	}
	
	def private elementList(String referencedName) {
		"element".node('ref="%s" minOccurs="0" maxOccurs="unbounded"'.format(referencedName))
	}
	
	def private elementList(String name, String type) {
		"element".node('name="%s" type="%s" minOccurs="0" maxOccurs="unbounded"'.format(name, type))
	}
	
	def private node(String tagName, String attributeContents, List<CharSequence> nestedElements) {
		node(tagName, attributeContents, nestedElements.join("\n"))
	}
	
	def private node(String tagName, String attributeContents, CharSequence nestedElements) '''
		<«xsd(tagName)»«IF attributeContents.empty»>«ELSE» «attributeContents»>«ENDIF»
			«nestedElements»
		</«xsd(tagName)»>
	'''
	
	def private node(String tagName, String attributeContents) '''
		<«xsd(tagName)» «attributeContents»/>
	'''
	
	def private static xsd(String name) {
		qualifiedName(XSD, name)
	}
	
	def private static s23m(String name) {
		qualifiedName(S23M, name)
	}
	
	def private static qualifiedName(String namespacePrefix, String name) {
		namespacePrefix + ":" + name
	}
}