package org.s23m.cell.editor.semanticdomain;

import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.mediator.RepositoryClientMediator;
import org.s23m.cell.semanticextensions.outershells.SemanticExtensions;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ObjectFactoryHolder;
import org.s23m.cell.serialization.serializer.ProtocolType;
import org.s23m.cell.serialization.serializer.SerializationType;

/**
 * Run to populate the local database once it has been created
 */
public class PopulateLocalDatabaseTables {

	public static void main(final String[] args) {
		System.out.println("Populating data...");

		org.s23m.cell.G.completeOpenSourceKernelInitialization();
		SemanticExtensions.instantiateFeature();

		//you can add more instantiation code here

		System.setProperty("gmodel.development.local.database", "true");

		//Serialize and persist all instances in memory
		final ArtefactContainer container = ObjectFactoryHolder.getInstance().createArtefactContainer();
		container.setContentType(SerializationType.IN_MEMORY_PERSISTENCE.name());

		final RepositoryClient client = RepositoryClientMediator.getInstance().getComponent(ProtocolType.REPOSITORY_CLIENT);
		client.put(container);

		System.out.println("Done");
	}

}
