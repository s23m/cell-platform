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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.editor.semanticdomain;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.platform.api.models.CellPlatform;
import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.mediator.RepositoryClientMediator;
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

		S23MKernel.completeCellKernelInitialization();
		CellPlatform.instantiateFeature();

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
