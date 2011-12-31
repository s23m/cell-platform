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
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.client.mediator;

import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.RepositoryClientImpl;
import org.s23m.cell.repository.client.connector.RepositoryClientConnector;
import org.s23m.cell.repository.client.server.ConfigValues;
import org.s23m.cell.serialization.serializer.ProtocolType;

public class RepositoryClientMediator {

	private static class MediatorHolder {
		public static final RepositoryClientMediator MEDIATOR = new RepositoryClientMediator();
	}

	private RepositoryClientMediator() {
	}

	public static RepositoryClientMediator getInstance() {
		return MediatorHolder.MEDIATOR;
	}

	public RepositoryClient getComponent(final ProtocolType type)throws UnsupportedOperationException {
		if (!type.equals(ProtocolType.REPOSITORY_CLIENT)) {
			throw new UnsupportedOperationException("Not supported protocol");
		}
		if (isLocallyDeployed()) {
			return RepositoryClientImpl.getInstance();
		} else {
			return RepositoryClientConnector.getComponent(); //pass back an interface that send artefacts over AMQP
		}
	}

	private boolean isLocallyDeployed() {
		// TODO remove the need for this system property
		return Boolean.valueOf(ConfigValues.getString("RepositoryClientServer.IS_LOCALLY_DEPLOYED")) ||
				Boolean.valueOf(System.getProperty("gmodel.development.local.database"));
	}

}
