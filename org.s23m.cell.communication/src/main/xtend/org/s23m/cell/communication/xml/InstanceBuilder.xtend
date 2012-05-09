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
import org.s23m.cell.communication.xml.schemainstance.CategoryIdentityReference
import org.s23m.cell.communication.xml.schemainstance.SemanticIdentityIdentityReference
import org.s23m.cell.communication.xml.schemainstance.ContainerIdentityReference
import org.s23m.cell.communication.xml.schemainstance.IsAbstractIdentityReference

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
	
	// TODO store as ArtifactSet child
	// TODO consider using Pairs of Strings as arguments? Obscures real dependencies
	def model(SemanticIdentityIdentityReference semanticIdentity,
			  CategoryIdentityReference category,
			  ContainerIdentityReference container,
			  IsAbstractIdentityReference isAbstract,
			  (Model)=>void initialiser) {
		val result = new Model(namespace, terminology)
		result.setSemanticIdentity(semanticIdentity)
		result.setCategory(category)
		result.setContainer(container)
		result.setIsAbstract(isAbstract)
		
		initialiser.apply(result)
		result
	}
	
	def vertex((Vertex)=>void initialiser) {
		val result = new Vertex(namespace, terminology)
		initialiser.apply(result)
		result
	}
	
	def visibility((Visibility)=>void initialiser) {
		val result = new Visibility(namespace, terminology)
		initialiser.apply(result)
		result
	}
	
	def edge((Edge)=>void initialiser) {
		val result = new Edge(namespace, terminology)
		initialiser.apply(result)
		result
	}
	
	def edgeEnd((EdgeEnd)=>void initialiser) {
		val result = new EdgeEnd(namespace, terminology)
		initialiser.apply(result)
		result
	}
	
	def superSetReference((SuperSetReference)=>void initialiser) {
		val result = new SuperSetReference(namespace, terminology)
		initialiser.apply(result)
		result
	}
	
	def command((Command)=>void initialiser) {
		val result = new Command(namespace, terminology)
		initialiser.apply(result)
		result
	}

	def query((Query)=>void initialiser) {
		val result = new Query(namespace, terminology)
		initialiser.apply(result)
	}
	
	def semanticIdentity(String uniqueRepresentationReference, String identifier) {
		new SemanticIdentityIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def category(String uniqueRepresentationReference, String identifier) {
		new CategoryIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def container(String uniqueRepresentationReference, String identifier) {
		new ContainerIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}
	
	def isAbstract(String uniqueRepresentationReference, String identifier) {
		new IsAbstractIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier)
	}		
}