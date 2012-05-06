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

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet;
import static org.s23m.cell.communication.xml.NamespaceConstants.*;

public class XmlInstanceBuilderTest {

	public static void main(String[] args) {
		// construct the same basic example as in cell-communication-instance.xml
		Namespace s23m = NamespaceConstants.NS_S23M;
		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		String languageIdentifier = "ENGLISH";
		Procedure1<ArtifactSet> initialiser = new Procedure1<ArtifactSet>() {
			@Override
			public void apply(ArtifactSet arg0) {
				arg0.setAttribute(NamespaceExtensions.xmlns(INSTANCE_NAMESPACE_PREFIX), INSTANCE_SCHEMA_URI);
				arg0.setAttribute(NamespaceExtensions.xmlns(S23M_SCHEMA), S23M_SCHEMA);
			}
		};
		InstanceBuilder builder = new InstanceBuilder(s23m, terminology, languageIdentifier, initialiser);

		// TODO
		
		
		ArtifactSet result = builder.build();
		String xml = XmlRendering.render(result).toString();
		
		System.out.println("xml: " + xml);
	}
}
