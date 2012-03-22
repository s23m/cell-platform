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
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell;

import org.s23m.cell.api.KernelSets;
import org.s23m.cell.api.ProperClasses;
import org.s23m.cell.api.models2.EnterpriseArchitecture;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.F_SemanticStateOfInMemoryModel;

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
public class S23MKernel {

	public static final KernelSets coreSets = new KernelSets(F_Instantiation.identityFactory);
	public static final ProperClasses coreGraphs = new ProperClasses();

	/**
	 * COMMANDS
	 */

	public static void switchOnDebugMode() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.switchOnDebugMode();
	}
	public static void switchOffDebugMode() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.switchOffDebugMode();
	}
	public static void goLiveWithCellEditor() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.goLiveWithCellEditor();
	}
	public static void completeCellKernelInitialization() {
		org.s23m.cell.core.F_SemanticStateOfInMemoryModel.completeCellKernelSemanticDomainInitialization();
		if (!SemanticStateOfInMemoryModel.cellEditorIsLive()) {
			Visualization.instantiateFeature();
			//EnterpriseArchitecture.instantiateFeature();
			RepositoryStructure.instantiateFeature();
			EnterpriseArchitecture.instantiateFeature();
		}
		SemanticStateOfInMemoryModel.cellKernelIsInitialized = true;
	}
	public static void boot() {
		completeCellKernelInitialization();
	}
}
