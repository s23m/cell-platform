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


public class Location {

	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.location);

	public static final Set country = CellEngineering.location.addConcrete(coreGraphs.vertex, CellPlatformDomain.country);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, country, coreGraphs.vertex);
	public static final Set city = CellEngineering.location.addConcrete(coreGraphs.vertex, CellPlatformDomain.city);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, city, coreGraphs.vertex);
	public static final Set countryState = CellEngineering.location.addConcrete(coreGraphs.vertex, CellPlatformDomain.countryState);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, countryState, coreGraphs.vertex);
	public static final Set street = CellEngineering.location.addConcrete(coreGraphs.vertex, CellPlatformDomain.street);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, street, coreGraphs.vertex);
	public static final Set postCode = CellEngineering.location.addConcrete(coreGraphs.vertex, CellPlatformDomain.postCode);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, postCode, coreGraphs.vertex);
	public static final Set latitude = CellEngineering.location.addConcrete(coreGraphs.vertex, CellPlatformDomain.latitude);
	private static final Set s6 = Instantiation.arrow(coreGraphs.superSetReference, latitude, coreGraphs.vertex);
	public static final Set longitude = CellEngineering.location.addConcrete(coreGraphs.vertex, CellPlatformDomain.longitude);
	private static final Set s7 = Instantiation.arrow(coreGraphs.superSetReference, longitude, coreGraphs.vertex);

	public static Set instantiateFeature() {

		return CellEngineering.location;
	}

}
