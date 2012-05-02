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
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import junit.framework.TestCase;

import org.s23m.cell.S23MKernel;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.io.Resources;

public class XmlDocumentValidationTest extends TestCase {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		S23MKernel.boot();
		
		XmlSchemaFactory xmlSchemaFactory = new XmlSchemaFactory();
		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		Schema schema = xmlSchemaFactory.createSchema(terminology);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		factory.setSchema(schema);
		
		// load document
		
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		URL resource = Resources.getResource("cell-communication-instance.xml");
		//String instanceContents = Resources.toString(resource, Charsets.UTF_8);
		byte[] byteArray = Resources.toByteArray(resource);
		//InputSupplier<InputStreamReader> supplier = Resources.newReaderSupplier(resource, Charsets.UTF_8);
		
		Document document = builder.parse(new ByteArrayInputStream(byteArray));
		
	}
	
	// http://www.edankert.com/validate.html
	/*
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		schemaFactory.newSchema(new Source[] {new StreamSource("contacts.xsd")});
		
		factory.setSchema();

		DocumentBuilder builder = factory.newDocumentBuilder();

		builder.setErrorHandler(new SimpleErrorHandler());

		Document document = builder.parse(new InputSource("document.xml"));
	}
	*/
}
