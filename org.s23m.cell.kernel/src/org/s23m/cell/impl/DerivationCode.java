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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.impl;

import static org.s23m.cell.G.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.ArtifactDerivation;
import org.s23m.cell.core.F_InstantiationImpl;

public class DerivationCode {

	public static void execute(final Set derivationRule, final Set setOfInstances) {
		if (   derivationRule.category().isEqualTo(ArtifactDerivation.derivationRule)) {
			//	TODO validation to be strengthened!
			//	&&
			//  setOfInstances.categoryOfOrderedPair().isEqualTo(F_SemanticStateOfInMemoryModel.coreGraphs.orderedSet)) {

			final String tech = derivationRule.value(ArtifactDerivation.derivationTechnology).identity().technicalName();
			final String lf = derivationRule.value(ArtifactDerivation.locationFunction).identity().technicalName();;
			for (final Set element : setOfInstances) {
				final Set iInstance = element;
				if (derivationRule.to().isLocalSuperSetOf(iInstance).isEqualTo(coreSets.is_TRUE)) {
					// if (derivationRule.fromConnectedInstance().isEqualTo(iInstance)) {
					//	TODO condition to be verified and generalised!
					//	if (derivationRule.fromConnectedInstance().isSuperSetOf(iInstance.metaArtifact()).isEqualTo(coreSets.is_TRUE)) {

					// FIXME
					/*
					final String generatedOutput = generator.generate(iInstance, derivationRule.identity().getTechnicalName());
					final String name = lf + "/" + iInstance.identity().getName();
					final Identity identity = identityFactory.createIdentity(name);
					identity.setPayload(generatedOutput);
					final Set result = F_SemanticStateOfInMemoryModel.instantiateConcrete(HTMLRepresentation.htmlRepresentation,identity);
					 */
				}
			}
		} else {
			F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

}

