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

class SchemaBuilder {
	
	def static schema((Schema)=>void initialiser) {
		val result = new Schema()
		initialiser.apply(result)
		result
	}
	
	def static complexType(Namespace targetNamespace, String name, Sequence sequence) {
		new ComplexType(targetNamespace, name, sequence)
	}
	
	def static complexType(Namespace targetNamespace, String name, Extension ext) {
		new ComplexType(targetNamespace, name, ext)
	}
	
	def static withExtension(ComplexType base, Sequence sequence) {
		new Extension(base, sequence)
	}
	
	def static sequence((Sequence)=>void initialiser) {
		val result = new Sequence()
		initialiser.apply(result)
		result
	}
	
	def static ElementReference element(Element referencedElement) {
		new ElementReference(referencedElement)
	}
	
	def static element(Namespace namespace, String name, Type type) {
		element(namespace, name, type, [])				   	
	}
	
	def static element(Namespace namespace, String name, Type type, (Element)=>void initialiser) {
		element(namespace, name, type, Cardinality::EXACTLY_ONE, initialiser)				   	
	}
	
	def static element(Namespace namespace,
					   String name,
					   Type type,
					   Cardinality cardinality,
					   (Element)=>void initialiser) {
		val result = new Element(namespace, name, type, cardinality)
		initialiser.apply(result)
		result
	}
}