package org.s23m.cell.communication.xml;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.communication.SetMarshallingException;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;
import org.s23m.cell.platform.api.CellQueries;
import org.s23m.cell.platform.models.CellEngineering;
import org.s23m.cell.platform.models.CellPlatformAgent;
import org.s23m.cell.platform.models.CellPlatformDomain;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

public class Scratch {
	public static void main(String[] args) throws SetMarshallingException {
		//S23MKernel.boot();
		org.s23m.cell.platform.S23MPlatform.boot();
		AgencyTestFoundation.instantiateFeature();

		
		XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
		
		XmlSetMarshaller marshaller = new XmlSetMarshaller(NamespaceConstants.NS_S23M, terminology);
		
		//Set set = InstantiationSequences.getInstance().crm;
		Set set = AgencyTestFoundation.ithanku;

		// The "cell meta language" is the human readable language used by the S23M kernel (equates to English) 
		Set cellMetaLanguage = CellPlatformAgent.cellMetaLanguage;
		Set javaLanguage = CellPlatformAgent.javaLanguage;
		Set sqlLanguage = CellPlatformAgent.sqlLanguage;
		Set englishLanguage = CellPlatformAgent.englishLanguage;
		Set deutschLanguage = CellPlatformAgent.deutschLanguage;
		Set koreanLanguage = CellPlatformAgent.koreanLanguage;
		
		// retrieving the name of one of the languages supported by the cell platform in clear text  
		String cellMeta = cellMetaLanguage.identity().name();
		String english = englishLanguage.identity().name();
		String deutsch = deutschLanguage.identity().name();
		// the "semanticIdentityEncoding" is the only language not defined as a "language" in the Cell Platform
		String semanticIdentityEncoding = "Semantic Identity Encoding";
		
		// example of retrieving the name of a set in a specific language (in this case in German)
		// using functions from the CellQueries API is the only legitimate way of retrieving the name of a set for all languages except the semanticIdentityEncoding
		String nameAsString = CellQueries.nameAsString(CellQueries.name(AgencyTestFoundation.object_cell, deutschLanguage));

		// this is the only legitimate way of retrieving the semanticIdentityEncoding of a set
		String identityAsString = AgencyTestFoundation.object_cell.identity().identifier().toString();
		String uniqueRepresentationReferenceAsString = AgencyTestFoundation.object_cell.identity().uniqueRepresentationReference().toString();
		
		String xml = marshaller.serialise(set);
		System.out.println("xml: " + xml);
	}
}
