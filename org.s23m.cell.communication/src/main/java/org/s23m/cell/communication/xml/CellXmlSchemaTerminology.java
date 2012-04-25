package org.s23m.cell.communication.xml;

import org.s23m.cell.Identity;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.IdentityFactory;

public class CellXmlSchemaTerminology implements XmlSchemaTerminology {

	private static final IdentityFactory identityFactory = F_Instantiation.identityFactory;
	
	private final String nameOf(Identity identity) {
		return identity.name();
	}

	@Override
	public String artifactSet() {
		return "artifactSet";
	}

	@Override
	public String category() {
		return nameOf(identityFactory.category());
	}

	@Override
	public String command() {
		return nameOf(identityFactory.command());
	}

	@Override
	public String container() {
		return nameOf(identityFactory.container());
	}

	@Override
	public String edge() {
		return nameOf(identityFactory.edge());
	}

	@Override
	public String edgeEnd() {
		return nameOf(identityFactory.edgeEnd());
	}

	@Override
	public String from() {
		return nameOf(identityFactory.from());
	}

	@Override
	public String function() {
		return nameOf(identityFactory.function());
	}

	@Override
	public String graph() {
		return nameOf(identityFactory.graph());
	}

	@Override
	public String identifier() {
		return nameOf(identityFactory.identifier());
	}

	@Override
	public String identity() {
		return nameOf(identityFactory.identity());
	}

	@Override
	public String identityReference() {
		return "identityReference";
	}

	@Override
	public String isAbstract() {
		return nameOf(identityFactory.isAbstract());
	}

	@Override
	public String isContainer() {
		return nameOf(identityFactory.isContainer());
	}

	@Override
	public String isNavigable() {
		return nameOf(identityFactory.isNavigable());
	}

	@Override
	public String maxCardinality() {
		return nameOf(identityFactory.maxCardinality());
	}

	@Override
	public String minCardinality() {
		return nameOf(identityFactory.minCardinality());
	}

	@Override
	public String model() {
		return "model";
	}

	@Override
	public String name() {
		return nameOf(identityFactory.name());
	}

	@Override
	public String parameter() {
		return nameOf(identityFactory.parameter());
	}

	@Override
	public String payload() {
		return "payload";
	}

	@Override
	public String pluralName() {
		return nameOf(identityFactory.pluralName());
	}

	@Override
	public String query() {
		return nameOf(identityFactory.query());
	}

	@Override
	public String semanticDomain() {
		return nameOf(identityFactory.semanticDomain());
	}

	@Override
	public String semanticIdentity() {
		return nameOf(identityFactory.semanticIdentity());
	}

	@Override
	public String superSetReference() {
		return nameOf(identityFactory.superSetReference());
	}

	@Override
	public String technicalName() {
		return nameOf(identityFactory.technicalName());
	}

	@Override
	public String to() {
		return nameOf(identityFactory.to());
	}

	@Override
	public String uniqueRepresentationReference() {
		return "uniqueRepresentationReference";
	}

	@Override
	public String uuid() {
		return "uuid";
	}

	@Override
	public String vertex() {
		return nameOf(identityFactory.vertex());
	}

	@Override
	public String visibility() {
		return nameOf(identityFactory.visibility());
	}

}
