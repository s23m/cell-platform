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

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;


/**
 * {@link SemanticDimension} implements instantiation semantics related to information modelling
 *
 * The semantics enforced in SemanticDimension relate to specific subsets of cells
 * that are useful for categorising information relevant to agents
 *
 */
public final class SemanticDimension {

	private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.semanticDimension, CellEngineering.cellContent);

	private static final Set s0 = Instantiation.arrow(coreGraphs.superSetReference, CellEngineering.semanticDimension, CellContent.cell);

	public static final Set how = CellEngineering.semanticDimension.addConcrete(CellContent.cell, org.s23m.cell.api.models2.EnterpriseArchitecture.how);
	public static final Set who = CellEngineering.semanticDimension.addConcrete(CellContent.cell, org.s23m.cell.api.models2.EnterpriseArchitecture.who);
	public static final Set what = CellEngineering.semanticDimension.addConcrete(CellContent.cell, org.s23m.cell.api.models2.EnterpriseArchitecture.what);
	public static final Set when = CellEngineering.semanticDimension.addConcrete(CellContent.cell, org.s23m.cell.api.models2.EnterpriseArchitecture.when);
	public static final Set where = CellEngineering.semanticDimension.addConcrete(CellContent.cell, org.s23m.cell.api.models2.EnterpriseArchitecture.where);
	public static final Set why = CellEngineering.semanticDimension.addConcrete(CellContent.cell, org.s23m.cell.api.models2.EnterpriseArchitecture.why);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, how, CellContent.cell);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, who, CellContent.cell);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, what, CellContent.cell);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, when, CellContent.cell);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, where, CellContent.cell);
	private static final Set s6 = Instantiation.arrow(coreGraphs.superSetReference, why, CellContent.cell);

	public static Set instantiateFeature() {

		return CellEngineering.semanticDimension;
	}

}
