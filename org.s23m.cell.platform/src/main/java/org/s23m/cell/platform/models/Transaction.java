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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.models;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class Transaction {

	public static final Set transaction = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.transaction);
	public static final Set creationTimestamp = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.creationTimestamp);
	public static final Set creator = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.creator);
	public static final Set transactions = Root.models.addAbstract(coreGraphs.vertex, CellPlatformDomain.transactions);

	public static final Set transaction_to_creationTimestamp = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.transaction_to_creationTimestamp,
			transaction,
			transaction,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			creationTimestamp,
			creationTimestamp,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set transaction_to_creator = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.transaction_to_creator,
			transaction,
			transaction,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			creator,
			creator,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);


	public static Set instantiateFeature() {

		Instantiation.arrow(coreGraphs.superSetReference, transaction, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, creationTimestamp, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, creator, coreGraphs.vertex);

		return transaction;
	}
}
