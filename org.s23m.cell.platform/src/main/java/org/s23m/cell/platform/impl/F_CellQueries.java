package org.s23m.cell.platform.impl;

import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.core.F_InstantiationImpl;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.models.CellPlatformAgent;
import org.s23m.cell.platform.models.CellPlatformDomain;
import org.s23m.cell.platform.models.Legal;
import org.s23m.cell.platform.models.Organization;


public  class F_CellQueries {


	public static final Set copyrightHolders(final Set cell) {
		Set result = null;
		for (final Set license : availableLicenses(cell)) {
			result = result.union(license.filter(Legal.copyrightHolders));
		}
		if (result == null) {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		} else {
			return result;
		}
	}

	public static final Set availableLicenses(final Set cell) {
		final Set localLicenses = cell.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();
		if (localLicenses.size() < 1) {
				if (S23MSemanticDomains.cellKernel.filterInstances().containsSemanticMatch(cell)) {
					return CellPlatformAgent.s23mCellKernelOrg.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();
				} else {
					if (S23MPlatform.coreGraphs.vertex.isSuperSetOf(cell.category()).is_TRUE()
							|| CellPlatformDomain.cellPlatformDomain.filterInstances().containsSemanticMatch(cell)) {
						return CellPlatformAgent.s23mCellPlatformOrg.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();

					} else {
						return availableLicenses(cell.container());
					}
				}
		} else {
			if (S23MPlatform.coreGraphs.vertex.isSuperSetOf(cell.category()).is_TRUE()
					|| CellPlatformDomain.cellPlatformDomain.filterInstances().containsSemanticMatch(cell)) {
				return CellPlatformAgent.s23mCellPlatformOrg.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();
			} else {
				return localLicenses;
			}
		}
	}

	public static final Set usageLicense(final Set user, final Set product) {
		return null;
	}
	public static final Set name(final Set cell, final Set session) {
		return null;
	}

}
