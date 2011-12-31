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
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell;

import org.s23m.cell.api.CoreGraphs;
import org.s23m.cell.api.CoreSets;
import org.s23m.cell.api.models2.EnterpriseArchitecture;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.F_SemanticStateOfInMemoryModel;

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
public class G {

	public static final CoreSets coreSets = new CoreSets(F_Instantiation.identityFactory);
	public static final CoreGraphs coreGraphs = new CoreGraphs();

	/**
	 * COMMANDS
	 */

	public static void switchOnDebugMode() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.switchOnDebugMode();
	}
	public static void switchOffDebugMode() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.switchOffDebugMode();
	}
	public static void goLiveWithGmodelEditor() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.goLiveWithGmodelEditor();
	}
	public static void completeOpenSourceKernelInitialization() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.completeGmodelSemanticDomainInitialization();
		if (!SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			Visualization.instantiateFeature();
			EnterpriseArchitecture.instantiateFeature();
			RepositoryStructure.instantiateFeature();
		}
		SemanticStateOfInMemoryModel.openSourceKernelIsInitialized = true;
	}
	public static void boot() {
		completeOpenSourceKernelInitialization();
	}
}
