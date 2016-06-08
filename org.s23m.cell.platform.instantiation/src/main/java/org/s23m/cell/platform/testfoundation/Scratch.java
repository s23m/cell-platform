package org.s23m.cell.platform.testfoundation;

import org.s23m.cell.Set;
import org.s23m.cell.platform.api.CellQueries;
import org.s23m.cell.platform.api.models.CellPlatformAgent;

public class Scratch {
	public static void main(final String[] args) {
		//S23MKernel.boot();
		org.s23m.cell.platform.S23MPlatform.boot();
		AgencyTestFoundation.instantiateFeature();

		//Set set = InstantiationSequences.getInstance().crm;
		final Set set = AgencyTestFoundation.ithanku;

		// The "cell meta language" is the human readable language used by the S23M kernel (equates to English)
		final Set cellMetaLanguage = CellPlatformAgent.cellMetaLanguage;
		final Set javaLanguage = CellPlatformAgent.javaClassJargon;
		final Set sqlLanguage = CellPlatformAgent.sqlJargon;
		final Set englishLanguage = CellPlatformAgent.englishLanguage;
		final Set deutschLanguage = CellPlatformAgent.deutschLanguage;
		final Set koreanLanguage = CellPlatformAgent.koreanLanguage;

		// retrieving the name of one of the languages supported by the cell platform in clear text
		final String cellMeta = cellMetaLanguage.identity().name();
		final String english = englishLanguage.identity().name();
		final String deutsch = deutschLanguage.identity().name();
		// the "semanticIdentityEncoding" is the only language not defined as a "language" in the Cell Platform
		final String semanticIdentityEncoding = "Semantic Identity Encoding";

		// example of retrieving the name of a set in a specific language (in this case in German)
		// using functions from the CellQueries API is the only legitimate way of retrieving the name of a set for all languages except the semanticIdentityEncoding
		final String nameAsString = CellQueries.nameAsString(CellQueries.name(AgencyTestFoundation.object_cell, deutschLanguage));

		// this is the only legitimate way of retrieving the semanticIdentityEncoding of a set
		final String identityAsString = AgencyTestFoundation.object_cell.identity().identifier().toString();
		final String uniqueRepresentationReferenceAsString = AgencyTestFoundation.object_cell.identity().uniqueRepresentationReference().toString();
	}
}
