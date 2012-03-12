package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.Namespace
import org.s23m.cell.communication.xml.schema.Cardinality
import org.s23m.cell.communication.xml.schema.Element
import org.s23m.cell.communication.xml.schema.Schema
import org.s23m.cell.communication.xml.schema.Sequence

import static org.s23m.cell.communication.xml.SchemaBuilder.*
import org.s23m.cell.communication.xml.schema.Type

class SchemaBuilder {
	
	def static schema((Schema)=>void initialiser) {
		val result = new Schema()
		initialiser.apply(result)
		result
	}
	
	def static sequence((Sequence)=>void initialiser) {
		val result = new Sequence()
		initialiser.apply(result)
		result
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