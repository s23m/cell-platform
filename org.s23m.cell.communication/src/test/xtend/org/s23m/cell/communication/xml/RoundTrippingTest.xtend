package org.s23m.cell.communication.xml

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

class RoundTrippingTest extends TestCase {
	
	private static int counter = 1
	
	@Test
	def void testSerialisationRoundTrip() {
		// Create in-memory model
		val model = createInstanceModel()
		
		// Serialise as XML
		val xml = XmlRendering::render(model)
		
		// De-serialise it
		val deserialised = deserialise(xml)
		
		// Render the original in-memory model and compare it with de-serialised version
		val renderedDeserialisedModel = XmlRendering::render(deserialised)
		
		assertEquals(xml, renderedDeserialisedModel)
	}
	
	def private ArtifactSet deserialise(String xml) {
		val schemaFactory = new XmlSchemaFactory()
		val terminology = DefaultXmlSchemaTerminology::getInstance()
		val schema = schemaFactory.createSchema(terminology)
		
		val factory = SAXParserFactory::newInstance()
		factory.setNamespaceAware(true)
		// http://tutorials.jenkov.com/java-xml/sax-schema-validation.html
		factory.setSchema(schema)
	    try {
	        val saxParser = factory.newSAXParser()
			val is = new ByteArrayInputStream(xml.getBytes(Charsets::UTF_8))
			val handler = new ArtifactSetElementHandler(NS_S23M, terminology, "ENGLISH")
			saxParser.parse(is, handler)
			handler.result
	    }
	    catch(ParserConfigurationException e1) {
	    	throw new IllegalStateException("De-serialisation failed", e1)
	    }
	    catch(SAXException e1) {
	    	throw new IllegalStateException("De-serialisation failed", e1)
	    }
	    catch(IOException e) {
	    	throw new IllegalStateException("De-serialisation failed", e)
	    }
	}
	
	def private createInstanceModel() {
		val s23m = NamespaceConstants::NS_S23M
		val terminology = DefaultXmlSchemaTerminology::getInstance()
		val languageIdentifier = "ENGLISH"
		val builder = new InstanceBuilder(s23m, terminology, languageIdentifier);
		
		val model = builder.model(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id),
			builder.container(id -> id),
			builder.isAbstract(id -> id)
		)
		
		model += builder.vertex(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id),
			builder.isAbstract(id -> id),
			builder.maxCardinality(id -> id)
		)
		
		model += builder.visibility(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id),
			builder.isAbstract(id -> id),
			builder.from(id -> id),
			builder.to(id -> id)
		)
		
		model += builder.edge(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id),
			builder.isAbstract(id -> id),
			/* from */
			builder.fromEdgeEnd(
				builder.semanticIdentity(id -> id),
				builder.category(id -> id),
				builder.isAbstract(id -> id),
				builder.minCardinality(id -> id),
				builder.maxCardinality(id -> id),
				builder.isContainer(id -> id),
				builder.isNavigable(id -> id)
			),
			/* to */
			builder.toEdgeEnd(
				builder.semanticIdentity(id -> id),
				builder.category(id -> id),
				builder.isAbstract(id -> id),
				builder.minCardinality(id -> id),
				builder.maxCardinality(id -> id),
				builder.isContainer(id -> id),
				builder.isNavigable(id -> id)
			)
		)
		
		model += builder.command(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id)
		)
		
		val query = builder.query(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id)
		)
		
		query.addParameter(builder.parameter(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id)
		))
		
		model += query
		
		model += builder.superSetReference(
			builder.semanticIdentity(id -> id),
			builder.category(id -> id),
			builder.isAbstract(id -> id),
			builder.from(id -> id),
			builder.to(id -> id)
		)
		
		builder.build()
	}
	
	def private String id() {
		val result = counter
		counter = counter + 1
		result.toString
	}
	
}