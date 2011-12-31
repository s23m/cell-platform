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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.semanticextensions.outershells;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class ValidityInterval {

	public static final Set validityInterval = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.validityInterval);
	public static final Set validFromTimestamp = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.validFromTimestamp);
	public static final Set validUntilTimestamp = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.validUntilTimestamp);
	public static final Set validityIntervals = Root.models.addAbstract(coreGraphs.vertex, SemanticExtensionsDomain.validityIntervals);
	public static final Set timestamps = Root.models.addAbstract(coreGraphs.vertex, SemanticExtensionsDomain.timestamps);


	public static final Set validityInterval_to_validFromTimestamp = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.validityInterval_to_validFromTimestamp,
			validityInterval,
			validityInterval,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			validFromTimestamp,
			validFromTimestamp,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set validityInterval_to_validUntilTimestamp = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.validityInterval_to_validUntilTimestamp,
			validityInterval,
			validityInterval,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			validUntilTimestamp,
			validUntilTimestamp,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);


	public static Set instantiateFeature() {

		Instantiation.link(coreGraphs.superSetReference, validityInterval, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, validFromTimestamp, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, validUntilTimestamp, coreGraphs.vertex);

		return validityInterval;
	}
}
