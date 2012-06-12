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

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.Edge;
import org.s23m.cell.communication.xml.model.schemainstance.EdgeEnd;
import org.s23m.cell.communication.xml.model.schemainstance.FromIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;
import org.xml.sax.Attributes;

public class FromProcessor extends AbstractAttributeAwareProcessor<Node> {

	@Override
	public Node startElement(Namespace namespace, XmlSchemaTerminology terminology, Node top, Attributes attributes) {
		// ambiguity - which "from" are we talking about?
		if (top instanceof Visibility || top instanceof SuperSetReference) {
			final String uniqueRepresentationReference = getUniqueRepresentationReferenceAttribute(attributes, terminology);
			final String identifier = getIdentifierAttribute(attributes, terminology);
			return new FromIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier, null);
		} else if (top instanceof Edge) {
			return EdgeEnd.fromEdgeEnd(namespace, terminology);
		} else {
			throw new IllegalStateException("From element could not be created for outer element " + top);
		}
	}

	@Override
	public void endElement(Node removed, Node top, String textContent) {
		if (top instanceof Visibility) {
			((Visibility) top).setFrom((FromIdentityReference) removed);
		} else if (top instanceof SuperSetReference) {
			((SuperSetReference) top).setFrom((FromIdentityReference) removed);
		} else if (top instanceof Edge) {
			((Edge) top).setFrom((EdgeEnd) removed);
		} else {
			throw new IllegalStateException("From element could not be terminated: " + removed);
		}
	}

}
