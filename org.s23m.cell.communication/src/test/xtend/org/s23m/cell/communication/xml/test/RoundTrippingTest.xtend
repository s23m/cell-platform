package org.s23m.cell.communication.xml.test

import junit.framework.TestCase
import org.junit.Test
import javax.xml.parsers.SAXParserFactory
import javax.xml.parsers.ParserConfigurationException
import org.xml.sax.SAXException
import java.io.IOException
import org.s23m.cell.communication.xml.sax.ArtifactSetElementHandler
import java.io.ByteArrayInputStream
import com.google.common.base.Charsets

import static org.s23m.cell.communication.xml.NamespaceConstants.*
import static extension org.s23m.cell.communication.xml.OperatorExtensions.*
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet
import org.s23m.cell.api.Query
import org.s23m.cell.platform.S23MPlatform
import org.s23m.cell.Set
import org.s23m.cell.Identity
import java.util.UUID
import org.s23m.cell.communication.xml.test.MethodDescriptor
import org.s23m.cell.communication.xml.test.MockIdentity
import org.s23m.cell.communication.xml.XmlRendering
import org.s23m.cell.communication.xml.XmlSchemaFactory
import org.s23m.cell.communication.xml.InstanceBuilder

import org.s23m.cell.platform.api.models.CellPlatformAgent

class RoundTrippingTest extends TestCase {
	
	private Identity identity = createIdentity()
	
	private Set set
	
	override setUp() {
		S23MPlatform.boot()
		set = createSet()
	}
	
	@Test
	def void testSerialisationRoundTrip() {
		// Create in-memory model
		val artifactSetModel = createInstanceModel()
		
		// Serialise as XML
		val xml = XmlRendering.render(artifactSetModel)
		
		// De-serialise it
		val deserialised = deserialise(xml)
		
		// Render the original in-memory model and compare it with de-serialised version
		val renderedDeserialisedModel = XmlRendering.render(deserialised)
		
		assertEquals(xml, renderedDeserialisedModel)
	}
	
	def private ArtifactSet deserialise(String xml) {
		val schemaFactory = new XmlSchemaFactory()
		val terminology = DefaultXmlSchemaTerminology.getInstance
		val schema = schemaFactory.createSchema(terminology)
		
		val factory = SAXParserFactory.newInstance
		factory.setNamespaceAware(true)
		// http://tutorials.jenkov.com/java-xml/sax-schema-validation.html
		factory.setSchema(schema)
	    try {
	        val saxParser = factory.newSAXParser
			val is = new ByteArrayInputStream(xml.getBytes(Charsets.UTF_8))
			val handler = new ArtifactSetElementHandler(NS_S23M, terminology, CellPlatformAgent.cellMetaLanguage)
			saxParser.parse(is, handler)
			handler.result
	    } catch(ParserConfigurationException e1) {
	    	throw new IllegalStateException("De-serialisation failed", e1)
	    } catch(SAXException e1) {
	    	throw new IllegalStateException("De-serialisation failed", e1)
	    } catch(IOException e) {
	    	throw new IllegalStateException("De-serialisation failed", e)
	    }
	}
	
	def private createInstanceModel() {
		val s23m = NS_S23M
		val terminology = DefaultXmlSchemaTerminology.getInstance()
		val language = CellPlatformAgent.cellMetaLanguage
		val builder = new InstanceBuilder(s23m, terminology, language)
		
		val result = builder.artifactSet
		
		val model = builder.model(
			builder.semanticIdentity(set),
			builder.category(set),
			builder.container(set),
			builder.isAbstract(set)
		)
		model += vertex(builder, set)
		model += visibility(builder, set)
		model += edge(builder, set)
		model += command(builder, set)
		model += query(builder, set)
		model += superSetReference(builder, set)
		result.addModel(model)
		
		val semanticDomain = builder.semanticDomain(
			builder.semanticIdentity(set),
			builder.category(set),
			builder.container(set),
			builder.isAbstract(set)
		)
		semanticDomain += vertex(builder, set)
		semanticDomain += visibility(builder, set)
		semanticDomain += edge(builder, set)
		semanticDomain += command(builder, set)
		semanticDomain += query(builder, set)
		semanticDomain += superSetReference(builder, set)
		
		val identity = builder.identity(set)
		identity.setPayload("contents")
		identity.setNameAttribute("name")
		identity.setPluralName("pluralName")
		identity.setCodeName("codeName")
		identity.setPluralCodeName("pluralCodeName")
		semanticDomain.addIdentity(identity)
		
		result.addSemanticDomain(semanticDomain)
		
		result
	}
	
	def private superSetReference(InstanceBuilder builder, Set set) { 
		builder.superSetReference(
			builder.semanticIdentity(set),
			builder.category(set),
			builder.isAbstract(set),
			builder.from(set),
			builder.to(set)
		)
	}
	
	def private query(InstanceBuilder builder, Set set) { 
		val query = builder.query(
			builder.semanticIdentity(set),
			builder.category(set)
		)
		
		query.addParameter(builder.parameter(
			builder.semanticIdentity(set),
			builder.category(set)
		))
		
		query
	}
	
	def private command(InstanceBuilder builder, Set set) { 
		builder.command(
			builder.semanticIdentity(set),
			builder.category(set)
		)
	}
	
	def private edge(InstanceBuilder builder, Set set) { 
		builder.edge(
			builder.semanticIdentity(set),
			builder.category(set),
			builder.isAbstract(set),
			/* from */
			builder.fromEdgeEnd(
				builder.semanticIdentity(set),
				builder.category(set),
				builder.isAbstract(set),
				builder.minCardinality(set),
				builder.maxCardinality(set),
				builder.isContainer(set),
				builder.isNavigable(set)
			),
			/* to */
			builder.toEdgeEnd(
				builder.semanticIdentity(set),
				builder.category(set),
				builder.isAbstract(set),
				builder.minCardinality(set),
				builder.maxCardinality(set),
				builder.isContainer(set),
				builder.isNavigable(set)
			)
		)
	}
	
	def private visibility(InstanceBuilder builder, Set set) { 
		builder.visibility(
			builder.semanticIdentity(set),
			builder.category(set),
			builder.isAbstract(set),
			builder.from(set),
			builder.to(set)
		)
	}
	
	def private vertex(InstanceBuilder builder, Set set) { 
		builder.vertex(
			builder.semanticIdentity(set),
			builder.category(set),
			builder.isAbstract(set),
			builder.maxCardinality(set)
		)
	}
	
	def private createSet() {
		val identityHandler = new IdempotentMethodInvocationHandler(identity)
		val categoryHandler = new IdempotentMethodInvocationHandler(Query.vertex)
		val valueHandler = new IdempotentMethodInvocationHandler(Query.vertex)
		val handlers = newHashMap(
			new MethodDescriptor("identity") -> identityHandler,
			new MethodDescriptor("category") -> categoryHandler,
			new MethodDescriptor("value", typeof(Set)) -> valueHandler
		)
		GenericFactory::createInstance(typeof(Set), handlers)
	}
	
	def private createIdentity() {
		val identifier = UUID.randomUUID()
		val uniqueRepresentationReference = UUID.randomUUID()
		new MockIdentity(identifier, uniqueRepresentationReference)
	}	
}
