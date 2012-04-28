package org.s23m.cell.communication.xml;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;

public class CellXmlSchemaTerminology implements XmlSchemaTerminology {

	private String nameOf(Set concept) {
		// TODO use language functionality when available
		return concept.identity().name();
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
	public String maxCardinality() {
		return nameOf(S23MSemanticDomains.maxCardinality);
	}

	@Override
	public String minCardinality() {
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
	public String technicalName() {
		return nameOf(S23MSemanticDomains.technicalName);
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
