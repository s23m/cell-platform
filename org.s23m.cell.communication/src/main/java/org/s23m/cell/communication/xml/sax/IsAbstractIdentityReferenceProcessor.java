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
import org.s23m.cell.communication.xml.model.schemainstance.Graph;
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;

public class IsAbstractIdentityReferenceProcessor extends AbstractIdentityReferenceProcessor<IsAbstractIdentityReference> {

	@Override
	protected IsAbstractIdentityReference createIdentityReference(Namespace namespace,
			XmlSchemaTerminology terminology,
			String uniqueRepresentationReference,
			String identifier) {
		return new IsAbstractIdentityReference(namespace, terminology, uniqueRepresentationReference, identifier, null);
	}
	
	@Override
	public void endElement(Node removed, Node top, String textContent) {
		if (top instanceof Graph) {
			((Graph) top).setIsAbstract((IsAbstractIdentityReference) removed);
		} else if (top instanceof Vertex) {
			((Vertex) top).setIsAbstract((IsAbstractIdentityReference) removed);
		} else if (top instanceof Visibility) {
			((Visibility) top).setIsAbstract((IsAbstractIdentityReference) removed);
		} else if (top instanceof Edge) {
			((Edge) top).setIsAbstract((IsAbstractIdentityReference) removed);
		} else if (top instanceof SuperSetReference) {
			((SuperSetReference) top).setIsAbstract((IsAbstractIdentityReference) removed);
		} else if (top instanceof EdgeEnd) {
			((EdgeEnd) top).setIsAbstract((IsAbstractIdentityReference) removed);
		}
	}
}
