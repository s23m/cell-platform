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
package org.s23m.cell.communication.xml.schemainstance;

import org.s23m.cell.communication.xml.dom.BasicCompositeNode;
import org.s23m.cell.communication.xml.dom.Namespace;

/*
	<!-- minOccurs="0" maxOccurs="unbounded" -->
	<s23m:vertex>
		<s23m:semanticIdentity>
			<s23m:uniqueRepresentationReference>5</s23m:uniqueRepresentationReference>
			<s23m:identifier>6</s23m:identifier>
		</s23m:semanticIdentity>
		<s23m:category>
			<s23m:uniqueRepresentationReference>7</s23m:uniqueRepresentationReference>
			<s23m:identifier>8</s23m:identifier>
		</s23m:category>
		
		<s23m:isAbstract>
			<s23m:uniqueRepresentationReference>9</s23m:uniqueRepresentationReference>
			<s23m:identifier>10</s23m:identifier>
		</s23m:isAbstract>
		<s23m:maxCardinality>
			<s23m:uniqueRepresentationReference>11</s23m:uniqueRepresentationReference>
			<s23m:identifier>12</s23m:identifier>
		</s23m:maxCardinality>
	</s23m:vertex>
 */
public class Vertex extends BasicCompositeNode {

	public Vertex(Namespace namespace, String name) {
		super(namespace, name);
	}

}
