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
package org.s23m.cell.communication.xml.model.dom;

import java.util.LinkedHashMap;

import junit.framework.TestCase;

public class AbstractNodeTest extends TestCase {
	
	public void testSettingAttributeToNullClearsIt() {
		SimpleAbstractNode node = new SimpleAbstractNode(null, "test");
		String key1 = "abc";
		String value1 = "1234";
		node.setAttribute(key1, value1);
		LinkedHashMap<String, String> attributes = node.getAttributes();
		assertEquals(1, attributes.size());
		assertEquals(value1, attributes.get(key1));
		
		node.setAttribute(key1, null);
		assertTrue(node.getAttributes().isEmpty());
	}
	
	private static class SimpleAbstractNode extends AbstractNode {

		public SimpleAbstractNode(Namespace namespace, String name) {
			super(namespace, name);
		}
		
	}
	
}
