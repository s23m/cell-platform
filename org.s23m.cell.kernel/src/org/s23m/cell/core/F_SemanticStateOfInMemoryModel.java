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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.api.models.ArtifactDerivation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.HTMLRepresentation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.SemanticDomain;

/**
 * {@link F_SemanticStateOfInMemoryModel} provides access to the Sets and Properties of the Gmodel kernel
 * that constitute the basic Gmodel vocabulary.
 * 
 * Additionally F_SemanticStateOfInMemoryModel enables the creation of links between Sets,
 * and automatically attaches the link to the appropriate container Set.
 * 
 * Note: F_SemanticStateOfInMemoryModel contains no implementation, it simply delegates to LinkConstraints, IdentityFactory, CoreSets,
 * and KernelOrderedSets.
 * 
 * Extensions: Gmodel is designed to be extensible. All extensions that only involve a structural extension
 * of the meta model can be achieved by modelling the extension in Gmodel. Beyond such basic extensions,
 * Gmodel can be extended/modified by plugging in a different IdentityFactory and/or by writing a custom Shell.
 * Such extensions are created by creating a subclass of F_SemanticStateOfInMemoryModel that
 * 
 * 	(a) adds a method that references the appropriate SemanticIndentityFactory:
 * 
 * 		public static final CustomSemanticIdentityFactory customSemanticIdentityFactory = new CustomSemanticIdentityFactory();
 * 
 * 	and/or
 * 
 * 	(b) reference the appropriate custom Shell by overriding the raiseError and link methods in F_SemanticStateOfInMemoryModel and by delegating to LinkConstraints
 * 		to invoke the raiseError and link methods in the kernel.
 * 
 * All extensions must use F_SemanticStateOfInMemoryModel's CoreSets and KernelOrderedSets.
 * 
 */
public class F_SemanticStateOfInMemoryModel {

	/**
	 * QUERIES
	 */
	//public static final CoreSets coreSets = new CoreSets(F_Instantiation.identityFactory);
	//public static final CoreGraphs coreGraphs = new CoreGraphs();
	public static final int indexIsNotAvailable = -1;

	private static boolean semanticDomainIsInitialized = false;
	private static boolean gmodelSemanticDomainIsInitialized = false;
	private static boolean gmodelEditorIsLive = false;
	private static boolean isDebugModeOn = false;

	public static boolean semanticDomainIsInitialized() {
		return semanticDomainIsInitialized;
	}

	public static boolean gmodelSemanticDomainIsInitialized() {
		return gmodelSemanticDomainIsInitialized;
	}

	public static boolean gmodelEditorIsLive() {
		return gmodelEditorIsLive;
	}

	public static boolean isDebugModeOn() {
		return isDebugModeOn;
	}

	/**
	 * COMMANDS
	 */

	public static void completeSemanticDomainInitialization() {
		Root.instantiateFeature();
		SemanticDomain.instantiateFeature();
		final int kernelComplexity = identityFactory.kernelComplexity();
		final int inMemoryComplexity = identityFactory.inMemoryComplexity();
		semanticDomainIsInitialized = true;
	}

	public static void completeGmodelSemanticDomainInitialization() {
		if (!gmodelEditorIsLive()) {
			if (!semanticDomainIsInitialized) {
				completeSemanticDomainInitialization();
			}
			GmodelSemanticDomains.instantiateFeature();
			ArtifactDerivation.instantiateFeature();
			HTMLRepresentation.instantiateFeature();
		}
		final int kernelComplexity = identityFactory.kernelComplexity();
		final int inMemoryComplexity = identityFactory.inMemoryComplexity();
		gmodelSemanticDomainIsInitialized = true;
		semanticDomainIsInitialized = true;
	}

	public static void goLiveWithGmodelEditor() {
		gmodelEditorIsLive = true;
	}

	public static void switchOnDebugMode() {
		isDebugModeOn = true;
	}

	public static void switchOffDebugMode() {
		isDebugModeOn = false;
	}
	//public static Set inMemorySets() {
	//	return F_Query.inMemorySets();
	//}

	//public static Set isLoadedInLocalMemory(final Set set) {
	//	if (Query.inMemorySets().containsRepresentation(set)) {
	//		return F_SemanticStateOfInMemoryModel.coreSets.is_TRUE;
	//	} else {return F_SemanticStateOfInMemoryModel.coreSets.is_FALSE;
	//	}
	//}
	//public static Set changedSets() {
	//	return F_Query.changedSets();
	//}
	//public static Set commitChangedSets() {
	//	return F_Transaction.commitChangedSets();
	//}
	//public static Set rollbackChangedSets() {
	//	return F_Transaction.rollbackChangedSets();
	//}


}
