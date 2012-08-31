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

package org.s23m.cell.platform.api.models;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class Jargon {

	private static final Set v2 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.jargon);

	public static final Set namingConvention = CellEngineering.language.addAbstract(coreGraphs.vertex, CellPlatformDomain.namingConvention);
	private static final Set s0 = Instantiation.arrow(coreGraphs.superSetReference, namingConvention, coreGraphs.vertex);
	public static final Set characterTransformation = CellEngineering.language.addAbstract(coreGraphs.vertex, CellPlatformDomain.characterTransformation);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, characterTransformation, namingConvention);
	public static final Set wordTransformation = CellEngineering.language.addAbstract(coreGraphs.vertex, CellPlatformDomain.characterTransformation);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, wordTransformation, namingConvention);
	public static final Set statementTransformation = CellEngineering.language.addAbstract(coreGraphs.vertex, CellPlatformDomain.characterTransformation);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, statementTransformation, namingConvention);
	public static final Set whiteTransformation = CellEngineering.language.addAbstract(coreGraphs.vertex, CellPlatformDomain.characterTransformation);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, whiteTransformation, namingConvention);

	public static Set instantiateFeature() {

		return CellEngineering.jargon;
	}

}
