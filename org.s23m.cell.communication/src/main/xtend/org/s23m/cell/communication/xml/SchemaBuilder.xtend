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

class SchemaBuilder {
	
	static String S23M = "s23m"
	
	static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012"
	
	static Namespace NS_S23M = new Namespace(S23M, S23M_SCHEMA)
	
	def static schema((Schema)=>void initialiser) {
		val result = new Schema()
		initialiser.apply(result)
		result
	}
	
	def static simpleType(String nameAttribute, DataType restrictionDataType) {
		new SimpleType(NS_S23M, nameAttribute, restrictionDataType)
	}
		
	def static complexType(String name, (Sequence)=>void initialiser) {
		new ComplexType(NS_S23M, name, sequence(initialiser))
	}
	
	def static complexType(String name, Extension ext) {
		new ComplexType(NS_S23M, name, ext)
	}
	
	def static withExtension(ComplexType base, (Sequence)=>void initialiser) {
		new Extension(base, sequence(initialiser))
	}
	
	def static sequence((Sequence)=>void initialiser) {
		val result = new Sequence()
		initialiser.apply(result)
		result
	}
	
	def static ElementReference element(Element referencedElement) {
		new ElementReference(referencedElement)
	}
	
	def static element(String name, Type type) {
		element(name, type, [])				   	
	}
	
	def static element(String name, Type type, (Element)=>void initialiser) {
		element(name, type, Cardinality::EXACTLY_ONE, initialiser)				   	
	}
	
	def static element(String name, Type type, Cardinality cardinality) {
		new Element(NS_S23M, name, type, cardinality)
	}
	
	def static element(String name, Type type, Cardinality cardinality, (Element)=>void initialiser) {
		val result = new Element(NS_S23M, name, type, cardinality)
		initialiser.apply(result)
		result
	}
}