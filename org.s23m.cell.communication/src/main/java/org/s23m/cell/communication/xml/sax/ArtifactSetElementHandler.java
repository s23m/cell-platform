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
package org.s23m.cell.communication.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ArtifactSetElementHandler extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		System.out.println("startDocument");
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument");
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("startElement");
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("endElement");
		super.endElement(uri, localName, qName);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("error: " + e);
		super.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("fatalError: " + e);
		super.fatalError(e);
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		System.out.println("startPrefixMapping");
		super.startPrefixMapping(prefix, uri);
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		System.out.println("endPrefixMapping");
		super.endPrefixMapping(prefix);
	}

}
