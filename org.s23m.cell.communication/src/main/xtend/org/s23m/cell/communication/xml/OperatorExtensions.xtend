package org.s23m.cell.communication.xml

import java.util.LinkedHashMap
import org.eclipse.xtext.xbase.lib.Pair
import org.s23m.cell.communication.xml.schemainstance.Command
import org.s23m.cell.communication.xml.schemainstance.Edge
import org.s23m.cell.communication.xml.schemainstance.Query
import org.s23m.cell.communication.xml.schemainstance.Vertex
import org.s23m.cell.communication.xml.schemainstance.Model
import org.s23m.cell.communication.xml.schemainstance.Visibility
import org.s23m.cell.communication.xml.schemainstance.SuperSetReference

/**
 * Contains syntactic sugar for operations so that we do not
 * need to pollute the domain model with foreign types
 */
class OperatorExtensions {
	
	def static <A, B> operator_add(LinkedHashMap<A, B> map, Pair<A, B> pair) {
		map.put(pair.key, pair.value)
	}
	
	def static <A, B> operator_add(LinkedHashMap<A, B> map, LinkedHashMap<A, B> additions) {
		map.putAll(additions)
	}
	
	def static void operator_add(Model m, Vertex v) {
		m.addVertex(v)
	}
	
	def static void operator_add(Model m, Edge e) {
		m.addEdge(e)
	}
	
	def static void operator_add(Model m, Command c) {
		m.addCommand(c)
	}
	
	def static void operator_add(Model m, Query q) {
		m.addQuery(q)
	}
	
	def static void operator_add(Model m, Visibility v) {
		m.addVisibility(v)
	}
	
	def static void operator_add(Model m, SuperSetReference s) {
		m.addSuperSetReference(s)
	}
}
