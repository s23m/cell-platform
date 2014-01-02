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

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;

public class CellXmlSchemaTerminology implements XmlSchemaTerminology {

	@Override
	public boolean isMachineEncoding() {
		return false;
	}

	private String nameOf(Set concept) {
		// TODO use language functionality when available
		return concept.identity().name().replace(" ", "-");
	}
	
	@Override
	public String artifactSet() {
		// TODO add to languages
		return "artifactSet";
	}

	@Override
	public String category() {
		return nameOf(S23MSemanticDomains.category);
	}
	
	@Override
	public String codeName() {
		// TODO add to languages
		return "codeName";
	}

	@Override
	public String command() {
		return nameOf(S23MSemanticDomains.command);
	}

	@Override
	public String container() {
		// TODO add to languages
		return "container";
	}

	@Override
	public String edge() {
		return nameOf(Query.edge);
	}

	@Override
	public String edgeEnd() {
		return nameOf(Query.edgeEnd);
	}

	@Override
	public String from() {
		return nameOf(S23MSemanticDomains.from);
	}

	@Override
	public String function() {
		return nameOf(S23MSemanticDomains.function);
	}

	@Override
	public String graph() {
		return nameOf(Query.graph);
	}

	@Override
	public String identifier() {
		return nameOf(S23MSemanticDomains.identifier);
	}

	@Override
	public String identity() {
		return nameOf(S23MSemanticDomains.identity);
	}

	@Override
	public String identityReference() {
		// TODO add to languages
		return "identityReference";
	}

	@Override
	public String isAbstract() {
		return nameOf(S23MSemanticDomains.isAbstract);
	}

	@Override
	public String isContainer() {
		return nameOf(S23MSemanticDomains.isContainer);
	}

	@Override
	public String isNavigable() {
		return nameOf(S23MSemanticDomains.isNavigable);
	}

	@Override
	public String language() {
		// TODO add to languages
		return "language";
	}
	
	@Override
	public String maximumCardinality() {
		return nameOf(S23MSemanticDomains.maxCardinality);
	}

	@Override
	public String minimumCardinality() {
		return nameOf(S23MSemanticDomains.minCardinality);
	}

	@Override
	public String model() {
		// TODO add to languages
		return "model";
	}

	@Override
	public String name() {
		return nameOf(S23MSemanticDomains.name);
	}

	@Override
	public String parameter() {
		return nameOf(S23MSemanticDomains.parameter);
	}

	@Override
	public String payload() {
		// TODO add to languages
		return "payload";
	}

	@Override
	public String pluralCodeName() {
		// TODO add to languages
		return "pluralCodeName";
	}
	
	@Override
	public String pluralName() {
		return nameOf(S23MSemanticDomains.pluralName);
	}

	@Override
	public String query() {
		return nameOf(S23MSemanticDomains.query);
	}

	@Override
	public String semanticDomain() {
		return nameOf(S23MSemanticDomains.semanticDomain);
	}

	@Override
	public String semanticIdentity() {
		return nameOf(S23MSemanticDomains.semanticIdentity);
	}

	@Override
	public String superSetReference() {
		return nameOf(Query.superSetReference);
	}

	@Override
	public String structure() {
		// TODO add to languages
		return "structure";
	}
	
	@Override
	public String to() {
		return nameOf(S23MSemanticDomains.to);
	}

	@Override
	public String uniqueRepresentationReference() {
		// TODO add to languages
		return "uniqueRepresentationReference";
	}

	@Override
	public String uuid() {
		// TODO add to languages
		return "uuid";
	}

	@Override
	public String vertex() {
		return nameOf(Query.vertex);
	}

	@Override
	public String visibility() {
		return nameOf(Query.visibility);
	}
}
