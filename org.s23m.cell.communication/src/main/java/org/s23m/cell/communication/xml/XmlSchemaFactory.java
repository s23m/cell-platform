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
 * The Original Code is Cell.
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

import org.s23m.cell.communication.xml.schema.Schema;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// TODO add caching
public class XmlSchemaFactory {
	
	public Document createSchema(final XmlSchemaTerminology terminology) {
		try {
			final XmlSchemaTemplate schemaTemplate = new XmlSchemaTemplate();
			// generate schema from template
			final Schema schema = schemaTemplate.createSchemaModel(terminology);
			final CharSequence schemaText = SchemaRendering.render(schema);
			
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
