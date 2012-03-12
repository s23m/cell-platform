package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.Namespace
import org.s23m.cell.communication.xml.schema.Cardinality
import org.s23m.cell.communication.xml.schema.Element
import org.s23m.cell.communication.xml.schema.Schema
import org.s23m.cell.communication.xml.schema.Sequence

import static org.s23m.cell.communication.xml.SchemaBuilder.*
import org.s23m.cell.communication.xml.schema.ComplexType
import org.s23m.cell.communication.xml.schema.SimpleType

class SchemaBuilder {
	
	def static schema(Namespace namespace, (Schema)=>void initialiser) {
		val result = new Schema(namespace)
		initialiser.apply(result)
		result
	}
	
	def static sequence((Sequence)=>void initialiser) {
		val result = new Sequence()
		initialiser.apply(result)
		result
	}
	
	
	def static element(Namespace namespace,
					   String name,
					   SimpleType type) {
		element(namespace, name, type, [])				   	
	}
	
	def static element(Namespace namespace,
					   String name,
					   SimpleType type,
					   (Element)=>void initialiser) {
		element(namespace, name, type, Cardinality::EXACTLY_ONE, initialiser)				   	
	}
	
	def static element(Namespace namespace,
					   String name,
					   SimpleType type,
					   Cardinality cardinality,
					   (Element)=>void initialiser) {
		val result = new Element(namespace, name, type, cardinality)
		initialiser.apply(result)
		result
	}
	
	def static element(Namespace namespace,
					   String name,
					   ComplexType type) {
		element(namespace, name, type, [])				   	
	}
	
	def static element(Namespace namespace,
					   String name,
					   ComplexType type,
					   (Element)=>void initialiser) {
		element(namespace, name, type, Cardinality::EXACTLY_ONE, initialiser)				   	
	}
	
	def static element(Namespace namespace,
					   String name,
					   ComplexType type,
					   Cardinality cardinality,
					   (Element)=>void initialiser) {
		val result = new Element(namespace, name, type, cardinality)
		initialiser.apply(result)
		result
	}
}