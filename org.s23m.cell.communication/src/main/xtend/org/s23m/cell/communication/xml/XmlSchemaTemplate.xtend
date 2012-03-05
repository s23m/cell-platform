package org.s23m.cell.communication.xml

import java.util.List
import java.util.Collections
import static java.util.Arrays.*
import static java.lang.String.*
import org.eclipse.xtext.xbase.lib.StringExtensions
import org.eclipse.xtext.xbase.lib.ListExtensions

import static extension org.eclipse.xtext.xbase.lib.IterableExtensions.*
import static extension java.lang.String.*

// TODO use builder API approach
class XmlSchemaTemplate {
	
	static String XSD = "xsd"
	static String S23M = "s23m"
	static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012"
	
	// TODO: add jargon support
	def createHumanReadableSchema() '''
		<?xml version="1.0" encoding="UTF-8"?>
		<«xsd("schema")» xmlns:«XSD»="http://www.w3.org/2001/XMLSchema"
				xmlns:«S23M»="«S23M_SCHEMA»"
				targetNamespace="«S23M_SCHEMA»"
				elementFormDefault="qualified"
				attributeFormDefault="unqualified">

			«reusedElements»
			«rootElement»
			«modelArtefactEncoding»
			«vertices»
			«arrows»
			«artifactFunctionality»
			«semanticDomainArtefactEncoding»

		</«xsd("schema")»>
	'''
	
	def createMachineReadableSchema() '''
	'''
	
	def private reusedElements() '''
		«element("semanticIdentity", s23m("identityReference"))»
		«element("model", s23m("model"))»
		«element("category", s23m("identityReference"))»
		«element("isAbstract", s23m("identityReference"))»
		«element("maxCardinality", s23m("identityReference"))»
		«element("minCardinality", s23m("identityReference"))»
		«element("isContainer", s23m("identityReference"))»
		«element("isNavigable", s23m("identityReference"))»
		«element("from", s23m("identityReference"))»
		«element("to", s23m("identityReference"))»
		«element("function", s23m("function"))»
	
		«complexType("identityReference", asList(
			element("uniqueRepresentationReference", s23m("uuid")),
			element("identifier", s23m("uuid"))
		))»
		
		«complexType("category", asList(
			element(s23m("semanticIdentity")),
			element(s23m("category"))
		))»
		
		«simpleType("uuid", xsd("string"))»
	'''
	
	def private rootElement() '''
		«element("artifactSet", s23m("artifactSet"))»
		
		«complexType("artifactSet", asList(
			elementList(s23m("model")),
			elementList("semanticDomain", s23m("semanticDomain"))
		))»
	'''
	
	def private modelArtefactEncoding() '''
		«complexTypeWithExtension("model", s23m("graph"))»
	
		«categoryComplexType("graph", asList(
			element("container", s23m("identityReference")),
			element(s23m("isAbstract")),
			elementList("vertex", s23m("vertex")),
			elementList("visibility", s23m("visibility")),
			elementList("edge", s23m("edge")),
			elementList("superSetReference", s23m("superSetReference")),
			elementList("command", s23m("command")),
			elementList("query", s23m("query"))
		))»
	'''
	
	def private vertices() '''
		«categoryComplexType("vertex", asList(
			element(s23m("isAbstract")),
			element(s23m("maxCardinality"))
		))»
	'''
	
	def private arrows() '''
		«categoryComplexType("superSetReference", asList(
			element(s23m("isAbstract")),
			element(s23m("from")),
			element(s23m("to"))
		))»
		«categoryComplexType("visibility", asList(
			element(s23m("isAbstract")),
			element(s23m("from")),
			element(s23m("to"))
		))»
		«categoryComplexType("edge", asList(
			element(s23m("isAbstract")),
			element("from", s23m("edgeEnd")),
			element("to", s23m("edgeEnd"))
		))»
		«categoryComplexType("edgeEnd", asList(
			element(s23m("isAbstract")),
			element(s23m("minCardinality")),
			element(s23m("maxCardinality")),
			element(s23m("isContainer")),
			element(s23m("isNavigable"))
		))»
	'''
	
	def private artifactFunctionality() '''
		«categoryComplexType("function", asList(
			elementList("parameter", s23m("parameter"))
		))»
		«categoryComplexType("parameter", Collections::emptyList)»
		«complexTypeWithExtension("command", s23m("function"))»
		«complexTypeWithExtension("query", s23m("function"))»
	'''
	
	def private semanticDomainArtefactEncoding() '''
		«complexType("semanticDomain", asList(
			element(s23m("model")),
			elementList("identity", s23m("identity"))
		))»
		
		«complexType("identity", asList(
			element("identifier", s23m("uuid")),
			element("name", xsd("string")),
			element("pluralName", xsd("string")),
			element("payload", xsd("string")),
			element("technicalName", xsd("string"))
		))»
	'''
	
	def private complexType(String name, List<CharSequence> containedElements) {
		"complexType".node('name="%s"'.format(name), containedElements.join("\n"))
	}
	
	def private complexTypeWithExtension(String name, String extensionBase) {
		complexTypeWithExtension(name, extensionBase, Collections::emptyList)
	}
	
	def private categoryComplexType(String name, List<CharSequence> elementsInSequence) '''
		«complexTypeWithExtension(name, "category", elementsInSequence)»
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
	
	def private node(String tagName, String attributeContents, CharSequence nestedElements) '''
		<«xsd(tagName)»«IF attributeContents.empty»>«ELSE» «attributeContents»>«ENDIF»
			«nestedElements»
		</«xsd(tagName)»>
	'''
	
	def private node(String tagName, String attributeContents) '''
		<«xsd(tagName)» «attributeContents»/>
	'''
	
	def private xsd(String name) {
		qualifiedName(XSD, name)
	}
	
	def private s23m(String name) {
		qualifiedName(S23M, name)
	}
	
	def private qualifiedName(String namespacePrefix, String name) {
		namespacePrefix + ":" + name
	}
}