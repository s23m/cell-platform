package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.model.schemainstance.Command
import org.s23m.cell.communication.xml.model.schemainstance.Edge
import org.s23m.cell.communication.xml.model.schemainstance.Model
import org.s23m.cell.communication.xml.model.schemainstance.Parameter
import org.s23m.cell.communication.xml.model.schemainstance.Query
import org.s23m.cell.communication.xml.model.schemainstance.SemanticDomainNode
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference
import org.s23m.cell.communication.xml.model.schemainstance.Vertex
import org.s23m.cell.communication.xml.model.schemainstance.Visibility

/**
 * Syntactic sugar for operations
 */
class OperatorExtensions {
	
	/* ArtifactSet */
	
	def static operator_add(ArtifactSet a, Model m) {
		a.addModel(m)
	}
	
	def static operator_add(ArtifactSet a, org.s23m.cell.communication.xml.model.schemainstance.SemanticDomainNode s) {
		a.addSemanticDomain(s)
	}
	
	/* Model */
	
	def static operator_add(Model m, Vertex v) {
		m.addVertex(v)
	}
	
	def static operator_add(Model m, Edge e) {
		m.addEdge(e)
	}
	
	def static operator_add(Model m, Command c) {
		m.addCommand(c)
	}
	
	def static operator_add(Model m, Query q) {
		m.addQuery(q)
	}
	
	def static operator_add(Model m, Visibility v) {
		m.addVisibility(v)
	}
	
	def static operator_add(Model m, SuperSetReference s) {
		m.addSuperSetReference(s)
	}
	
	/* Function */
	
	def static operator_add(Command c, Parameter p) {
		c.addParameter(p)
	}
	
	def static operator_add(Query q, Parameter p) {
		q.addParameter(p)
	}
}
