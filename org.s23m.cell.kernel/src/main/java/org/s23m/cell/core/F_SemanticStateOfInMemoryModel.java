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

import org.s23m.cell.api.models.HTMLRepresentation;
import org.s23m.cell.api.models.InstanceDerivation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;

/**
 * {@link F_SemanticStateOfInMemoryModel} provides access to the Sets and Properties of the S23M kernel
 * that constitute the basic S23M vocabulary.
 * 
 * Additionally F_SemanticStateOfInMemoryModel enables the creation of arrows between Sets,
 * and automatically attaches the arrow to the appropriate container Set.
 * 
 * Note: F_SemanticStateOfInMemoryModel contains no implementation, it simply delegates to ArrowConstraints, IdentityFactory, CoreSets,
 * and KernelOrderedSets.
 * 
 * Extensions: S23M is designed to be extensible. All extensions that only involve a structural extension
 * of the meta model can be achieved by modelling the extension in S23M. Beyond such basic extensions,
 * S23M can be extended/modified by plugging in a different IdentityFactory and/or by writing a custom Shell.
 * Such extensions are created by creating a subclass of F_SemanticStateOfInMemoryModel that
 * 
 * 	(a) adds a method that references the appropriate SemanticIndentityFactory:
 * 
 * 		public static final CustomSemanticIdentityFactory customSemanticIdentityFactory = new CustomSemanticIdentityFactory();
 * 
 * 	and/or
 * 
 * 	(b) reference the appropriate custom Shell by overriding the raiseError and arrow methods in F_SemanticStateOfInMemoryModel and by delegating to ArrowConstraints
 * 		to invoke the raiseError and arrow methods in the kernel.
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
	private static boolean cellKernelSemanticDomainIsInitialized = false;
	private static boolean cellEditorIsLive = false;

	private static boolean isDebugModeOn = false;

	public static boolean semanticDomainIsInitialized() {
		return semanticDomainIsInitialized;
	}

	public static boolean cellKernelSemanticDomainIsInitialized() {
		return cellKernelSemanticDomainIsInitialized;
	}

	public static boolean cellEditorIsLive() {
		return cellEditorIsLive;
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

	public static void completeCellKernelSemanticDomainInitialization() {
		if (!cellEditorIsLive()) {
			if (!semanticDomainIsInitialized) {
				completeSemanticDomainInitialization();
			}
			S23MSemanticDomains.instantiateFeature();
			InstanceDerivation.instantiateFeature();
			HTMLRepresentation.instantiateFeature();
		}
		final int kernelComplexity = identityFactory.kernelComplexity();
		final int inMemoryComplexity = identityFactory.inMemoryComplexity();
		cellKernelSemanticDomainIsInitialized = true;
		semanticDomainIsInitialized = true;
	}

	public static void goLiveWithCellEditor() {
		cellEditorIsLive = true;
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
