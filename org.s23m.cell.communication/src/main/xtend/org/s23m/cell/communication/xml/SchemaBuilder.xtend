package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.Namespace
import org.s23m.cell.communication.xml.schema.Cardinality
import org.s23m.cell.communication.xml.schema.Element
import org.s23m.cell.communication.xml.schema.Schema
import org.s23m.cell.communication.xml.schema.Sequence

import static org.s23m.cell.communication.xml.SchemaBuilder.*
import org.s23m.cell.communication.xml.schema.Type
import org.s23m.cell.communication.xml.schema.ComplexType
import org.s23m.cell.communication.xml.schema.Extension
import org.s23m.cell.communication.xml.schema.ElementReference
import org.s23m.cell.communication.xml.schema.SimpleType
import org.s23m.cell.communication.xml.schema.DataType
import org.s23m.cell.communication.xml.dom.Node

class SchemaBuilder {
	
	static String S23M = "s23m"
	
	static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012"
	
	static Namespace NS_S23M = new Namespace(S23M, S23M_SCHEMA)
	
	Schema schema
	
	String rootElementName
	
	new(String rootElementName, (Schema)=>void initialiser) {
		this.rootElementName = rootElementName
		this.schema = new Schema()
		initialiser.apply(schema)
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
		store(new SimpleType(NS_S23M, nameAttribute, restrictionDataType))
	}
		
	def complexType(String name, (Sequence)=>void initialiser) {
		store(new ComplexType(NS_S23M, name, sequence(initialiser)))
	}
	
	def complexType(String name, Extension ext) {
		store(new ComplexType(NS_S23M, name, ext))
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
	
	def static sequence((Sequence)=>void initialiser) {
		val result = new Sequence()
		initialiser.apply(result)
		result
	}
}