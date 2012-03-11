package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.Namespace
import org.s23m.cell.communication.xml.schema.Schema
import org.s23m.cell.communication.xml.schema.Element
import org.s23m.cell.communication.xml.dom.Node
import org.s23m.cell.communication.xml.schema.Cardinality
import org.s23m.cell.communication.xml.schema.Sequence

class SchemaBuilder {
	
	def static schema(Namespace namespace, (Schema)=>void initialiser) {
		val result = new Schema(namespace)
		initialiser.apply(result)
		result
	}
	
	def static sequence(Namespace namespace, (Sequence)=>void initialiser) {
		val result = new Sequence(namespace)
		initialiser.apply(result)
		result
	}
	
	def static element(Namespace namespace,
					   String name,
					   Node type) {
		element(namespace, name, type, [])				   	
	}
	
	def static element(Namespace namespace,
					   String name,
					   Node type,
					   (Element)=>void initialiser) {
		element(namespace, name, type, Cardinality::EXACTLY_ONE, initialiser)				   	
	}
	
	def static element(Namespace namespace,
					   String name,
					   Node type,
					   Cardinality cardinality,
					   (Element)=>void initialiser) {
		val result = new Element(namespace, name, type, cardinality)
		initialiser.apply(result)
		result
	}
}