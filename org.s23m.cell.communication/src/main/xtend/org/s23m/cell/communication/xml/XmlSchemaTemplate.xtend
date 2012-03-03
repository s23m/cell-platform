package org.s23m.cell.communication.xml

import java.util.List

class XmlSchemaTemplate {
	
	private static String XSD_NAMESPACE_PREFIX = "xsd"
	
	private static String TARGET_NAMESPACE_PREFIX = "s23m"
	private static String TARGET_NAMESPACE_VALUE = "http://schemas.s23m.org/serialization/2012"
	
	// TODO: add jargon support
	def createHumanReadableSchema() '''
		<?xml version="1.0" encoding="UTF-8"?>
		<xsd:schema xmlns:«XSD_NAMESPACE_PREFIX»="http://www.w3.org/2001/XMLSchema"
				xmlns:«TARGET_NAMESPACE_PREFIX»="«TARGET_NAMESPACE_VALUE»"
				targetNamespace="«TARGET_NAMESPACE_VALUE»"
				elementFormDefault="qualified"
				attributeFormDefault="unqualified">
		
			<!-- Reused elements -->	
		
			«element("semanticIdentity", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("model", "«TARGET_NAMESPACE_PREFIX»:model")»
			«element("category", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("isAbstract", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("maxCardinality", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("minCardinality", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("isContainer", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("isNavigable", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("from", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("to", "«TARGET_NAMESPACE_PREFIX»:identityReference")»
			«element("function", "«TARGET_NAMESPACE_PREFIX»:function")»
		
			<xsd:complexType name="identityReference">
				<xsd:sequence>
					<xsd:element name="uniqueRepresentationReference" type="«TARGET_NAMESPACE_PREFIX»:uuid"/>
					<xsd:element name="identifier" type="«TARGET_NAMESPACE_PREFIX»:uuid"/>
				</xsd:sequence>
			</xsd:complexType>
		
			<xsd:complexType name="category">
				<xsd:sequence>
					<xsd:element ref="«TARGET_NAMESPACE_PREFIX»:semanticIdentity"/>
					<xsd:element ref="«TARGET_NAMESPACE_PREFIX»:category"/>
				</xsd:sequence>
			</xsd:complexType>
		
			<xsd:simpleType name="uuid">
				<xsd:restriction base="xsd:string"/>
			</xsd:simpleType>
		
			<!-- Root element -->
			<xsd:element name="artifactSet" type="«TARGET_NAMESPACE_PREFIX»:artifactSet"/>
			
			<xsd:complexType name="artifactSet">
				<xsd:sequence>
					<xsd:element ref="«TARGET_NAMESPACE_PREFIX»:model" minOccurs="0" maxOccurs="unbounded"/>
					<xsd:element name="semanticDomain" type="«TARGET_NAMESPACE_PREFIX»:semanticDomain" minOccurs="0" maxOccurs="unbounded"/>
				</xsd:sequence>
			</xsd:complexType>
		
			<!-- Encoding of model artifacts -->
		
			<xsd:complexType name="model">
				<xsd:complexContent>
					<xsd:extension base="«TARGET_NAMESPACE_PREFIX»:graph"/>
				</xsd:complexContent>
			</xsd:complexType>
		
			«categoryComplexType("graph", newArrayList(
				element("container", "«TARGET_NAMESPACE_PREFIX»:identityReference"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:isAbstract"),
				elementList("vertex", "«TARGET_NAMESPACE_PREFIX»:vertex"),
				elementList("visibility", "«TARGET_NAMESPACE_PREFIX»:visibility"),
				elementList("edge", "«TARGET_NAMESPACE_PREFIX»:edge"),
				elementList("superSetReference", "«TARGET_NAMESPACE_PREFIX»:superSetReference"),
				elementList("command", "«TARGET_NAMESPACE_PREFIX»:command"),
				elementList("query", "«TARGET_NAMESPACE_PREFIX»:query")
			))»
			
			<!-- Vertices --> 
			<xsd:complexType name="vertex">
				<xsd:complexContent>
					<xsd:extension base="«TARGET_NAMESPACE_PREFIX»:category">
						<xsd:sequence>
							<xsd:element ref="«TARGET_NAMESPACE_PREFIX»:isAbstract"/>
							<xsd:element ref="«TARGET_NAMESPACE_PREFIX»:maxCardinality"/>			
						</xsd:sequence>
					</xsd:extension>
				</xsd:complexContent>
			</xsd:complexType>
		
			<!-- Arrows --> 

			«categoryComplexType("superSetReference", newArrayList(
				elementRef("«TARGET_NAMESPACE_PREFIX»:isAbstract"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:from"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:to")
			))»
			«categoryComplexType("visibility", newArrayList(
				elementRef("«TARGET_NAMESPACE_PREFIX»:isAbstract"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:from"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:to")
			))»
			«categoryComplexType("edge", newArrayList(
				elementRef("«TARGET_NAMESPACE_PREFIX»:isAbstract"),
				element("from", "«TARGET_NAMESPACE_PREFIX»:edgeEnd"),
				element("to", "«TARGET_NAMESPACE_PREFIX»:edgeEnd")
			))»
			«categoryComplexType("edgeEnd", newArrayList(
				elementRef("«TARGET_NAMESPACE_PREFIX»:isAbstract"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:minCardinality"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:maxCardinality"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:isContainer"),
				elementRef("«TARGET_NAMESPACE_PREFIX»:isNavigable")
			))»
			
			<!-- Artifact functionality -->
			
			<xsd:complexType name="function">
				<xsd:complexContent>
					<xsd:extension base="«TARGET_NAMESPACE_PREFIX»:category">
						<xsd:sequence>
							<xsd:element name="parameter" type="«TARGET_NAMESPACE_PREFIX»:parameter" minOccurs="0" maxOccurs="unbounded"/>
						</xsd:sequence>
					</xsd:extension>			
				</xsd:complexContent>
			</xsd:complexType>
			<xsd:complexType name="parameter">
				<xsd:complexContent>
					<xsd:extension base="«TARGET_NAMESPACE_PREFIX»:category"/>
				</xsd:complexContent>
			</xsd:complexType>
			<xsd:complexType name="command">
				<xsd:complexContent>
					<xsd:extension base="«TARGET_NAMESPACE_PREFIX»:function"/>
				</xsd:complexContent>
			</xsd:complexType>
			<xsd:complexType name="query">
				<xsd:complexContent>
					<xsd:extension base="«TARGET_NAMESPACE_PREFIX»:function"/>
				</xsd:complexContent>
			</xsd:complexType>
		
			<!-- Encoding of semantic domain artifacts -->
		
			<xsd:complexType name="semanticDomain">
				<xsd:sequence>
					<xsd:element ref="«TARGET_NAMESPACE_PREFIX»:model"/>
					<xsd:element name="identity" type="«TARGET_NAMESPACE_PREFIX»:identity" minOccurs="0" maxOccurs="unbounded"/>
				</xsd:sequence>
			</xsd:complexType>
		
			<xsd:complexType name="identity">
				<xsd:sequence>
					<xsd:element name="identifier" type="«TARGET_NAMESPACE_PREFIX»:uuid"/>
					<xsd:element name="name" type="xsd:string"/>
					<xsd:element name="pluralName" type="xsd:string"/>
					<xsd:element name="payload" type="xsd:string"/>
					<xsd:element name="technicalName" type="xsd:string"/>
				</xsd:sequence>
			</xsd:complexType>
		</xsd:schema>
	'''
	
	def createMachineReadableSchema() '''
	'''
	
	def private categoryComplexType(String name, List<CharSequence> elementsInSequence) '''
		<xsd:complexType name="«name»">
			<xsd:complexContent>
				<xsd:extension base="«TARGET_NAMESPACE_PREFIX»:category">
					«IF !elementsInSequence.empty»
					<xsd:sequence>
						«FOR element : elementsInSequence»
						«element»
						«ENDFOR»
					</xsd:sequence>
					«ENDIF»
				</xsd:extension>
			</xsd:complexContent>
		</xsd:complexType>
	'''
	
	def private element(String name, String type) '''
		<«XSD_NAMESPACE_PREFIX»:element name="«name»" type="«type»"/>
	'''
	
	def private elementRef(String referencedName) '''
		<«XSD_NAMESPACE_PREFIX»:element ref="«referencedName»"/>
	'''
	
	def private elementList(String name, String type) '''
		<«XSD_NAMESPACE_PREFIX»:element name="«name»" type="«type»" minOccurs="0" maxOccurs="unbounded"/>
	'''
}