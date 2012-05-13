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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.google.common.io.Resources;

public class SaxParsingTest {

	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
	    factory.setValidating(true);
	    try {
	        SAXParser saxParser = factory.newSAXParser();

			URL resource = Resources.getResource("cell-communication-instance.xml");
			byte[] byteArray = Resources.toByteArray(resource);
			InputStream is = new ByteArrayInputStream(byteArray);
			saxParser.parse(is, new ArtifactSetElementHandler());
	    }
	    catch(ParserConfigurationException e1) {
	    	e1.printStackTrace();
	    }
	    catch(SAXException e1) {
	    	e1.printStackTrace();
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	}
}
