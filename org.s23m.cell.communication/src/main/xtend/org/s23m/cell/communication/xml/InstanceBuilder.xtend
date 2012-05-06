package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.Namespace
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.schemainstance.Command
import org.s23m.cell.communication.xml.schemainstance.Edge
import org.s23m.cell.communication.xml.schemainstance.Model
import org.s23m.cell.communication.xml.schemainstance.SuperSetReference
import org.s23m.cell.communication.xml.schemainstance.Vertex
import org.s23m.cell.communication.xml.schemainstance.Visibility
import org.s23m.cell.communication.xml.schemainstance.Query
import org.s23m.cell.communication.xml.schemainstance.EdgeEnd
import org.s23m.cell.communication.xml.schemainstance.IdentityReference

class InstanceBuilder {
	
	ArtifactSet artifactSet
	
	Namespace namespace
	
	XmlSchemaTerminology terminology
		
	new(Namespace namespace,
		XmlSchemaTerminology terminology,
		String languageIdentifier,
		(ArtifactSet)=>void initialiser) {
		
		this.namespace = namespace
		this.terminology = terminology
		this.artifactSet = new ArtifactSet(namespace, terminology, languageIdentifier)
		initialiser.apply(artifactSet)
	}
	
	def build() {
		artifactSet
	}
	
	def model((Model)=>void initialiser) {
		val result = new Model(namespace, terminology)
		initialiser.apply(result)
	}
	
	def vertex((Vertex)=>void initialiser) {
		val result = new Vertex(namespace, terminology)
		initialiser.apply(result)
	}
	
	def visibility((Visibility)=>void initialiser) {
		val result = new Visibility(namespace, terminology)
		initialiser.apply(result)
	}
	
	def edge((Edge)=>void initialiser) {
		val result = new Edge(namespace, terminology)
		initialiser.apply(result)
	}
	
	def edgeEnd((EdgeEnd)=>void initialiser) {
		val result = new EdgeEnd(namespace, terminology)
		initialiser.apply(result)
	}
	
	def identityReference(String name, String uniqueRepresentationReference, String identifier) {
		new IdentityReference(namespace, terminology, name, uniqueRepresentationReference, identifier)
	}
	
	def superSetReference((SuperSetReference)=>void initialiser) {
		val result = new SuperSetReference(namespace, terminology)
		initialiser.apply(result)
	}
	
	def command((Command)=>void initialiser) {
		val result = new Command(namespace, terminology)
		initialiser.apply(result)
	}

	def query((Query)=>void initialiser) {
		val result = new Query(namespace, terminology)
		initialiser.apply(result)
	}
}