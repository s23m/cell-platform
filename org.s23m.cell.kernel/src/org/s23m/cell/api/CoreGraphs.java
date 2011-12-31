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

package org.s23m.cell.api;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_Query;

// functionality is scheduled to be removed
// TODO remove all usage of this class

public final class CoreGraphs {

	public final Set vertex;
	public final Set edgeEnd;
	public final Set link;
	public final Set edge;
	public final Set superSetReference;
	public final Set visibility;
	public final Set graph;

	public CoreGraphs() {
		vertex = F_Query.vertexFlavor();
		edgeEnd = F_Query.edgeEndFlavor();
		link = F_Query.linkFlavor();
		edge = F_Query.edgeFlavor();
		superSetReference = F_Query.superSetReferenceFlavor();
		visibility = F_Query.visibilityFlavor();
		graph = F_Query.graph();
	}

}
