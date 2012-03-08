package org.s23m.cell.communication.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// TODO add caching
public class XmlSchemaFactory {
	
	public Document createSchema(final XmlSchemaTerminology terminology) {
		try {
			final XmlSchemaTemplate schemaTemplate = new XmlSchemaTemplate(terminology);
			// generate schema from template
			final CharSequence schemaText = schemaTemplate.createSchema();
			
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);

			final DocumentBuilder builder = factory.newDocumentBuilder();
			
			final byte[] schemaContents = schemaText.toString().getBytes("UTF-8");
			final InputStream stream = new ByteArrayInputStream(schemaContents);
			// parse document from stream
			return builder.parse(stream);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	// TODO support resolving schema for a specified jargon
	public Document createHumanReadableSchema() {
		// TODO replace by implementation which uses kernel sets to construct names
		final XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		return createSchema(terminology);
	}
	
	public Document createMachineReadableSchema() {
		// TODO
		return null;
	}
}
