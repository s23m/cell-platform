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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.client;

import java.util.UUID;

import org.s23m.cell.connector.Component;
import org.s23m.cell.serialization.container.ArtefactContainer;

public interface RepositoryClient extends Component {

	public static UUID CLIENT_ID = UUID.randomUUID();

	public static int INFINITE_DEPTH = -1;

	/**
	 * Retrieve artifacts that confirms to the given constraints
	 * @param container
	 * @return
	 * @throws UnsupportedOperationException
	 */
	public ArtefactContainer get(ArtefactContainer artifact)throws UnsupportedOperationException ;

	/**
	 * Persist the given artifacts
	 * @param container
	 * @throws UnsupportedOperationException
	 */
	public void put(ArtefactContainer artifact) throws UnsupportedOperationException;

}
