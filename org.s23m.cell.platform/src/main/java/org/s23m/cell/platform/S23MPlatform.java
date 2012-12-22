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
package org.s23m.cell.platform;

import org.s23m.cell.api.KernelSets;
import org.s23m.cell.api.ProperClasses;
import org.s23m.cell.core.F_SemanticStateOfInMemoryModel;
import org.s23m.cell.platform.api.models.CellPlatform;

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
 */
public class S23MPlatform {

	private static volatile Boolean cellPlatformIsInitialized = false;

	public static final KernelSets coreSets = org.s23m.cell.S23MKernel.coreSets;
	public static final ProperClasses coreGraphs = org.s23m.cell.S23MKernel.coreGraphs;

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

	public static void boot() {
		// initialisation must only ever be done once
		if (!cellPlatformIsInitialized) {
			synchronized (cellPlatformIsInitialized) {
				if (!cellPlatformIsInitialized) {
					completeCellPlatformInitialization();
					cellPlatformIsInitialized = true;
				}
			}
		}
	}

	private static void completeCellPlatformInitialization() {
		org.s23m.cell.S23MKernel.completeCellKernelInitialization();
		if (!org.s23m.cell.SemanticStateOfInMemoryModel.cellEditorIsLive()) {
			CellPlatform.instantiateFeature();
		}
	}
}
