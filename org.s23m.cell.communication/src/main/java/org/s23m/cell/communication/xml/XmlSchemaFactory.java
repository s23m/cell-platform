/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.base.Charsets;

// TODO add caching
public class XmlSchemaFactory {
	
	private byte[] generateSchema(final XmlSchemaTerminology terminology) {
		// generate schema from template
		final XmlSchemaTemplate schemaTemplate = new XmlSchemaTemplate();
		final org.s23m.cell.communication.xml.model.schema.Schema schema = schemaTemplate.createSchemaModel(terminology);
		final CharSequence schemaText = XmlRendering.render(schema);
		return schemaText.toString().getBytes(Charsets.UTF_8);
	}
	
	public Document createSchemaAsDocument(final XmlSchemaTerminology terminology) {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//factory.setValidating(true);
		factory.setNamespaceAware(true);

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		
			final byte[] schemaContents = generateSchema(terminology);
			
			final InputStream stream = new ByteArrayInputStream(schemaContents);
			// parse document from stream
			return builder.parse(stream);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Could not create schema", e);
		} catch (SAXException e) {
			throw new IllegalStateException("Could not create schema", e);
		} catch (IOException e) {
			throw new IllegalStateException("Could not create schema", e);
		}
	}
	
	public Schema createSchema(final XmlSchemaTerminology terminology) {
		try {
			final byte[] schemaContents = generateSchema(terminology);
			
			final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			final ByteArrayInputStream stream = new ByteArrayInputStream(schemaContents);
			return schemaFactory.newSchema(new StreamSource(stream));
		} catch (SAXException e) {
			throw new IllegalStateException("Could not create schema", e);
		}
	}
	
	/**
	 * Retrieves a document based on a given terminology
	 * 
	 * TODO support jargons
	 * @return
	 */
	public Schema createHumanReadableSchema() {
		final CellXmlSchemaTerminology terminology = new CellXmlSchemaTerminology();
		return createSchema(terminology);
	}
	
	public Schema createMachineReadableSchema() {
		// TODO
		return null;
	}
}
