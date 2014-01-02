package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.model.dom.Node
import org.s23m.cell.communication.xml.model.schema.Cardinality
import org.s23m.cell.communication.xml.model.schema.ComplexType
import org.s23m.cell.communication.xml.model.schema.DataType
import org.s23m.cell.communication.xml.model.schema.Element
import org.s23m.cell.communication.xml.model.schema.ElementReference
import org.s23m.cell.communication.xml.model.schema.Extension
import org.s23m.cell.communication.xml.model.schema.Schema
import org.s23m.cell.communication.xml.model.schema.Sequence
import org.s23m.cell.communication.xml.model.schema.Type

import static org.s23m.cell.communication.xml.model.schema.XmlSchemaConstants.*
import static extension org.s23m.cell.communication.xml.NamespaceExtensions.*
import static org.s23m.cell.communication.xml.NamespaceConstants.*
import org.s23m.cell.communication.xml.model.schema.Attribute
import java.util.List
import org.s23m.cell.communication.xml.model.schema.SimpleType

class SchemaBuilder {
	
	Schema schema
	
	String rootElementName
	
	new(String rootElementName) {
		this.rootElementName = rootElementName
		this.schema = new Schema()
		this.schema.setAttribute(xmlns(XML_SCHEMA_PREFIX), XML_SCHEMA_URI)
		this.schema.setAttribute(xmlns(S23M), S23M_SCHEMA_URI)
		this.schema.setAttribute("targetNamespace", S23M_SCHEMA_URI)
		this.schema.setAttribute("elementFormDefault", "qualified")
		this.schema.setAttribute("attributeFormDefault", "unqualified")
	}
	
	def build() {
		removeElementsWithoutReferences()
		schema
	}
	
	def private removeElementsWithoutReferences() {
		// always exclude the root element from removal
		val predicate = [Node it | it instanceof Element && (it as Element).references.empty && (it as Element).nameAttribute != rootElementName]
		val elements = schema.getChildren.filter(predicate).toList
		schema.getChildren.removeAll(elements)
	}
	
	def private <T extends Node> T store(T node) {
		schema.getChildren += node
		node
	}
	
	def simpleType(String nameAttribute, DataType restrictionDataType) {
		store(new org.s23m.cell.communication.xml.model.schema.ConstrainedSimpleType(NS_S23M, nameAttribute, restrictionDataType))
	}
		
	def complexType(String name, (Sequence)=>void initialiser) {
		store(new ComplexType(NS_S23M, name, sequence(initialiser)))
	}
	
	def complexType(String name, Extension ext) {
		store(new ComplexType(NS_S23M, name, ext))
	}
	
	def complexType(String name, List<Attribute> attributes) {
		store(new ComplexType(NS_S23M, name, attributes))
	}
	
	def complexType(String name, List<Attribute> attributes, (Sequence)=>void initialiser) {
		store(new ComplexType(NS_S23M, name, attributes, sequence(initialiser)))
	}
	
	def mandatoryAttribute(String name, SimpleType type) {
		new Attribute(name, type, true)
	}
	
	def optionalAttribute(String name, SimpleType type) {
		new Attribute(name, type, false)
	}
	
	def element(String name, Type type) {
		element(name, type, [])
	}
	
	def element(String name, Type type, (Element)=>void initialiser) {
		element(name, type, Cardinality::EXACTLY_ONE, initialiser)				   	
	}
	
	def element(String name, Type type, Cardinality cardinality) {
		store(new Element(NS_S23M, name, type, cardinality))
	}
	
	def element(String name, Type type, Cardinality cardinality, (Element)=>void initialiser) {
		val result = new Element(NS_S23M, name, type, cardinality)
		initialiser.apply(result)
		store(result)
	}
	
	def ElementReference element(Element referencedElement) {
		new ElementReference(referencedElement)
	}
	
	def ElementReference element(Element referencedElement, Cardinality cardinality) {
		new ElementReference(referencedElement, cardinality)
	}
	
	/* Helpers used in creation of top-level nodes */
	
	def static withExtension(ComplexType base) {
		// trivial extension
		new Extension(base, new Sequence())
	}
	
	def static withExtension(ComplexType base, (Sequence)=>void initialiser) {
		new Extension(base, sequence(initialiser))
	}
	
	def private static sequence((Sequence)=>void initialiser) {
		val result = new Sequence()
		initialiser.apply(result)
		result
	}
}