package org.s23m.cell.communication.xml

import java.util.List
import java.util.Collections
import static java.util.Arrays.*

// TODO use builder API approach
class XmlSchemaTemplate {
	
	private static String XSD = "xsd"
	
	private static String S23M = "s23m"
	
	private static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012"
	
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
			elementRef(s23m("semanticIdentity")),
			elementRef(s23m("category"))
		))»
		
		«simpleType("uuid", xsd("string"))»
	'''
	
	def private rootElement() '''
		«element("artifactSet", s23m("artifactSet"))»
		
		«complexType("artifactSet", asList(
			elementRefList(s23m("model")),
			elementList("semanticDomain", s23m("semanticDomain"))
		))»
	'''
	
	def private modelArtefactEncoding() '''
		«complexTypeWithExtension("model", s23m("graph"))»
	
		«categoryComplexType("graph", asList(
			element("container", s23m("identityReference")),
			elementRef(s23m("isAbstract")),
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
			elementRef(s23m("isAbstract")),
			elementRef(s23m("maxCardinality"))
		))»
	'''
	
	def private arrows() '''
		«categoryComplexType("superSetReference", asList(
			elementRef(s23m("isAbstract")),
			elementRef(s23m("from")),
			elementRef(s23m("to"))
		))»
		«categoryComplexType("visibility", asList(
			elementRef(s23m("isAbstract")),
			elementRef(s23m("from")),
			elementRef(s23m("to"))
		))»
		«categoryComplexType("edge", asList(
			elementRef(s23m("isAbstract")),
			element("from", s23m("edgeEnd")),
			element("to", s23m("edgeEnd"))
		))»
		«categoryComplexType("edgeEnd", asList(
			elementRef(s23m("isAbstract")),
			elementRef(s23m("minCardinality")),
			elementRef(s23m("maxCardinality")),
			elementRef(s23m("isContainer")),
			elementRef(s23m("isNavigable"))
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
			elementRef(s23m("model")),
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
	
	def private complexType(String name, List<CharSequence> elementsInSequence) '''
		<«xsd("complexType")» name="«name»">
			<«xsd("sequence")»>
				«FOR element : elementsInSequence»
				«element»
				«ENDFOR»
			</«xsd("sequence")»>
		</«xsd("complexType")»>
	'''
	
	def private complexTypeWithExtension(String name, String extensionBase) {
		complexTypeWithExtension(name, extensionBase, Collections::emptyList)
	}
	
	def private complexTypeWithExtension(String name, String extensionBase, List<CharSequence> elementsInSequence) '''
		<«xsd("complexType")» name="«name»">
			<«xsd("complexContent")»>
				<«xsd("extension")» base="«S23M»:«extensionBase»">
					«IF !elementsInSequence.empty»
					<«xsd("sequence")»>
						«FOR element : elementsInSequence»
						«element»
						«ENDFOR»
					</«xsd("sequence")»>
					«ENDIF»
				</«xsd("extension")»>
			</«xsd("complexContent")»>
		</«xsd("complexType")»>
	'''
	
	def private categoryComplexType(String name, List<CharSequence> elementsInSequence) '''
		«complexTypeWithExtension(name, "category", elementsInSequence)»
	'''
	
	def private simpleType(String name, String baseType) '''
		<«xsd("simpleType")» name="«name»">
			<«xsd("restriction")» base="«baseType»"/>
		</«xsd("simpleType")»>
	'''
	
	def private element(String name, String type) '''
		<«xsd("element")» name="«name»" type="«type»"/>
	'''
	
	def private elementRef(String referencedName) '''
		<«xsd("element")» ref="«referencedName»"/>
	'''
	
	def private elementRefList(String referencedName) '''
		<«xsd("element")» ref="«referencedName»" minOccurs="0" maxOccurs="unbounded"/>
	'''
	
	def private elementList(String name, String type) '''
		<«xsd("element")» name="«name»" type="«type»" minOccurs="0" maxOccurs="unbounded"/>
	'''
	
	def private xsd(String name) {
		XSD + ":" + name
	}
	
	def private s23m(String name) {
		S23M + ":" + name
	}
}