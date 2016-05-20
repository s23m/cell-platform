package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.model.schemainstance.Command
import org.s23m.cell.communication.xml.model.schemainstance.Edge
import org.s23m.cell.communication.xml.model.schemainstance.Model
import org.s23m.cell.communication.xml.model.schemainstance.Parameter
import org.s23m.cell.communication.xml.model.schemainstance.Query
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference
import org.s23m.cell.communication.xml.model.schemainstance.Vertex
import org.s23m.cell.communication.xml.model.schemainstance.Visibility
import org.s23m.cell.communication.xml.model.schemainstance.Structure

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
	
	/* Structure */
	
	def static operator_add(Structure s, Vertex v) {
		s.addVertex(v)
	}
	
	def static operator_add(Structure s, Edge e) {
		s.addEdge(e)
	}
	
	def static operator_add(Structure s, Command c) {
		s.addCommand(c)
	}
	
	def static operator_add(Structure s, Query q) {
		s.addQuery(q)
	}
	
	def static operator_add(Structure s, Visibility v) {
		s.addVisibility(v)
	}
	
	def static operator_add(Structure s, SuperSetReference superSetReference) {
		s.addSuperSetReference(superSetReference)
	}
	
	/* Function */
	
	def static operator_add(Command c, Parameter p) {
		c.addParameter(p)
	}
	
	def static operator_add(Query q, Parameter p) {
		q.addParameter(p)
	}
}
