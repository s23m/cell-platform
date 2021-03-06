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

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.Graph;

public class CellPlatformDomain {

	public static final Set cellPlatformDomain = Instantiation.addSemanticDomain("cell platform - JAVA", "cell platform - JAVA", S23MSemanticDomains.agentSemanticDomains);
	public static final Set world = Instantiation.addDisjunctSemanticIdentitySet("world", "set of worlds", cellPlatformDomain);
	public static final Set legalEntity = Instantiation.addDisjunctSemanticIdentitySet("legal entity", "set of legal entities", cellPlatformDomain);
	public static final Set organization = Instantiation.addSemanticRole("organization", "organizations", cellPlatformDomain, legalEntity);

	public static final Set privilege = Instantiation.addDisjunctSemanticIdentitySet("privilege", "privileges", cellPlatformDomain);
	public static final Set findPrivilege = Instantiation.addSemanticRole("find privilege", "set of find privileges", cellPlatformDomain, privilege);
	public static final Set readPrivilege = Instantiation.addSemanticRole("read privilege", "set of read privileges", cellPlatformDomain, privilege);
	public static final Set addPrivilege = Instantiation.addSemanticRole("add privilege", "add privileges", cellPlatformDomain, privilege);
	public static final Set modifyPrivilege = Instantiation.addSemanticRole("modify privilege", "set of modify privileges", cellPlatformDomain, privilege);
	public static final Set executePrivilege = Instantiation.addSemanticRole("execute privilege", "set of execute privileges", cellPlatformDomain, privilege);
	public static final Set superUserPrivilege = Instantiation.addSemanticRole("super user privilege", "set of super user privileges", cellPlatformDomain, privilege);
	public static final Set authorized = Instantiation.addDisjunctSemanticIdentitySet("authorized", "set of authorized", cellPlatformDomain);
	public static final Set forbidden = Instantiation.addDisjunctSemanticIdentitySet("forbidden", "set of forbidden", cellPlatformDomain);
	public static final Set accessGrant = Instantiation.addDisjunctSemanticIdentitySet("access grant", "set of access grants", cellPlatformDomain);
	public static final Set accessRestriction = Instantiation.addDisjunctSemanticIdentitySet("access restriction", "set of access restrictions", cellPlatformDomain);

	public static final Set agency = Instantiation.addDisjunctSemanticIdentitySet("agency", "set of agency", cellPlatformDomain);
	public static final Set agent = ((Graph)cellPlatformDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, F_Instantiation.xtensionIdentityFactory.agent());
	public static final Set nonLinearSystem = ((Graph)cellPlatformDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, F_Instantiation.xtensionIdentityFactory.nonLinearSystem());
	public static final Set linearSystem = ((Graph)cellPlatformDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, F_Instantiation.xtensionIdentityFactory.linearSystem());
	public static final Set stage = Instantiation.addDisjunctSemanticIdentitySet("stage", "stages", cellPlatformDomain);
	public static final Set collaboration = Instantiation.addDisjunctSemanticIdentitySet("collaboration", "collaborations", cellPlatformDomain);

	public static final Set collaborationChannel = Instantiation.addDisjunctSemanticIdentitySet("collaboration channel", "collaboration channels", cellPlatformDomain);
	public static final Set supplier = Instantiation.addDisjunctSemanticIdentitySet("supplier", "suppliers", cellPlatformDomain);
	public static final Set consumer = Instantiation.addDisjunctSemanticIdentitySet("consumer", "consumers", cellPlatformDomain);

	public static final Set location = Instantiation.addDisjunctSemanticIdentitySet("location", "locations", cellPlatformDomain);
	public static final Set locations = Instantiation.addDisjunctSemanticIdentitySet("locations", "set of locations", cellPlatformDomain);
	public static final Set organization_to_location = Instantiation.addDisjunctSemanticIdentitySet("organization to location", "set of organization to location", cellPlatformDomain);
	public static final Set agent_to_location = Instantiation.addDisjunctSemanticIdentitySet("agent to location", "set of agent to location", cellPlatformDomain);
	public static final Set country = Instantiation.addDisjunctSemanticIdentitySet("country", "countries", cellPlatformDomain);
	public static final Set city = Instantiation.addDisjunctSemanticIdentitySet("city", "cities", cellPlatformDomain);
	public static final Set countryState = Instantiation.addDisjunctSemanticIdentitySet("state", "states", cellPlatformDomain);
	public static final Set street = Instantiation.addDisjunctSemanticIdentitySet("street", "streets", cellPlatformDomain);
	public static final Set postCode = Instantiation.addDisjunctSemanticIdentitySet("post code", "post codes", cellPlatformDomain);
	public static final Set latitude = Instantiation.addDisjunctSemanticIdentitySet("latitude", "set of latitude", cellPlatformDomain);
	public static final Set longitude = Instantiation.addDisjunctSemanticIdentitySet("longitude", "set of longitude", cellPlatformDomain);


	public static final Set replication = Instantiation.addDisjunctSemanticIdentitySet("replication", "replications", cellPlatformDomain);
	public static final Set original = Instantiation.addDisjunctSemanticIdentitySet("original", "originals", cellPlatformDomain);
	public static final Set copy = Instantiation.addDisjunctSemanticIdentitySet("copy", "copies", cellPlatformDomain);

	public static final Set user = Instantiation.addSemanticRole("user", "users", cellPlatformDomain, agent);
	public static final Set member = Instantiation.addSemanticRole("member", "members", cellPlatformDomain, agent);

	public static final Set creator = Instantiation.addSemanticRole("creator", "creators", cellPlatformDomain, agent);
	public static final Set role = Instantiation.addDisjunctSemanticIdentitySet("role", "roles", cellPlatformDomain);
	public static final Set languagePreference = Instantiation.addDisjunctSemanticIdentitySet("language preference", "set of language preferences", cellPlatformDomain);
	public static final Set parentRole = Instantiation.addSemanticRole("parent", "set of parents", cellPlatformDomain, role);
	public static final Set includedRole = Instantiation.addSemanticRole("included role", "set of included roles", cellPlatformDomain, role);
	public static final Set excludedRole = Instantiation.addSemanticRole("excluded role", "set of excluded roles", cellPlatformDomain, role);
	public static final Set disjunctRole = Instantiation.addSemanticRole("disjunct role", "set of disjunct roles", cellPlatformDomain, role);
	public static final Set producer = Instantiation.addSemanticRole("producer", "producers", cellPlatformDomain, role);

	public static final Set legal = Instantiation.addDisjunctSemanticIdentitySet("legal", "set of legal", cellPlatformDomain);
	public static final Set license = Instantiation.addDisjunctSemanticIdentitySet("license", "licenses", cellPlatformDomain);
	public static final Set usageLicense = Instantiation.addDisjunctSemanticIdentitySet("usage license", "usage licenses", cellPlatformDomain);
	public static final Set legalEntity_to_usageLicense = Instantiation.addDisjunctSemanticIdentitySet("legalEntity_to_usageLicense", "set of legalEntity_to_usageLicense", cellPlatformDomain);
	public static final Set copyrightHolder = Instantiation.addDisjunctSemanticIdentitySet("copyright holder", "copyright holders", cellPlatformDomain);
	public static final Set semanticUnit_to_copyrightHolder = Instantiation.addDisjunctSemanticIdentitySet("semanticUnit_to_copyrightHolder", "set of semanticUnit_to_copyrightHolder", cellPlatformDomain);
	public static final Set availableLicense = Instantiation.addDisjunctSemanticIdentitySet("available license", "available licenses", cellPlatformDomain);
	public static final Set semanticUnit_to_availableLicenses = Instantiation.addDisjunctSemanticIdentitySet("semanticUnit_to_availableLicenses", "set of semanticUnit_to_availableLicenses", cellPlatformDomain);
	public static final Set usageLicence_to_vertex = Instantiation.addDisjunctSemanticIdentitySet("usageLicence_to_vertex", "set of usageLicence_to_vertex", cellPlatformDomain);
	public static final Set licencedProduct = Instantiation.addDisjunctSemanticIdentitySet("licenced product", "licenced products", cellPlatformDomain);
	public static final Set issuer = Instantiation.addDisjunctSemanticIdentitySet("issuer", "issuers", cellPlatformDomain);
	public static final Set usageLicence_to_issuer = Instantiation.addDisjunctSemanticIdentitySet("usageLicence_to_issuer", "set of usageLicence_to_issuer", cellPlatformDomain);
	public static final Set usageLicence_to_license = Instantiation.addDisjunctSemanticIdentitySet("usageLicence_to_license", "set of usageLicence_to_license", cellPlatformDomain);

	public static final Set initialDeveloper = Instantiation.addDisjunctSemanticIdentitySet("initial developer", "initial developers", cellPlatformDomain);
	public static final Set thes23mfoundation = Instantiation.addDisjunctSemanticIdentitySet("The S23M Foundation", "The S23M Foundation", cellPlatformDomain);
	public static final Set softmetaware = Instantiation.addDisjunctSemanticIdentitySet("SoftMetaWare Limited, New Zealand", "SoftMetaWare Limited, New Zealand", cellPlatformDomain);
	public static final Set perisoft = Instantiation.addDisjunctSemanticIdentitySet("Perisoft GmbH, Schweiz", "Perisoft GmbH, Schweiz", cellPlatformDomain);

	public static final Set originalCode = Instantiation.addDisjunctSemanticIdentitySet("original code", "original codes", cellPlatformDomain);
	public static final Set s23mCellPlatformCode = Instantiation.addDisjunctSemanticIdentitySet("S23M Cell Platform", "S23M Cell Platform", cellPlatformDomain);

	public static final Set s23m_platform_license = Instantiation.addDisjunctSemanticIdentitySet("s23m_platform_license", "s23m_platform_license", cellPlatformDomain);
	public static final Set s23m_kernel_license = Instantiation.addDisjunctSemanticIdentitySet("s23m_kernel_license", "s23m_kernel_license", cellPlatformDomain);

	public static final Set mozilla1dot1 = Instantiation.addDisjunctSemanticIdentitySet("Mozilla 1.1", "Mozilla 1.1", cellPlatformDomain);
	private static final String s = mozilla1dot1.identity().setPayload(
		"***** BEGIN LICENSE BLOCK *****"
		+ " * Version: MPL 1.1"
		+ " *"
		+ " * The contents of this file are subject to the Mozilla Public License Version"
		+ " * 1.1 (the 'License'); you may not use this file except in compliance with"
		+ " * the License. You may obtain a copy of the License at"
		+ " * http://www.mozilla.org/MPL/"
		+ " *"
		+ " * Software distributed under the License is distributed on an 'AS IS' basis,"
		+ " * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License"
		+ " * for the specific language governing rights and limitations under the"
		+ " * License."
		+ " *"
		+ " * The Original Code is <original code>"
		+ " *"
		+ " * The Initial Developer of the Original Code is"
		+ " * <original developer>."
		+ " * Portions created by the Initial Developer are"
		+ " * Copyright (C) <from year> to <until year> <copyright holder>."
		+ " * All Rights Reserved."
		+ " *"
		+ " * Contributor(s):"
		+ " * <names of contributor>"
		+ " * ***** END LICENSE BLOCK ***** ");

	public static final Set contributor = Instantiation.addDisjunctSemanticIdentitySet("contributor", "contributors", cellPlatformDomain);
	public static final Set jornbettin = Instantiation.addDisjunctSemanticIdentitySet("Jorn Bettin", "Jorn Bettin", cellPlatformDomain);
	public static final Set andrewshewring = Instantiation.addDisjunctSemanticIdentitySet("Andrew Shewring", "Andrew Shewring", cellPlatformDomain);
	public static final Set chulkim = Instantiation.addDisjunctSemanticIdentitySet("Chul Kim", "Chul Kim", cellPlatformDomain);
	public static final Set xaverwiesmann = Instantiation.addDisjunctSemanticIdentitySet("Xaver Wiesmann", "Xaver Wiesmann", cellPlatformDomain);

	public static final Set perspective_cellplatform_kernel = Instantiation.addDisjunctSemanticIdentitySet("perspective_cellplatform_kernel", "perspective_cellplatform_kernel", cellPlatformDomain);
	public static final Set perspective_technology = Instantiation.addDisjunctSemanticIdentitySet("perspective_technology", "perspective_technology", cellPlatformDomain);
	public static final Set technology_to_jargon = Instantiation.addDisjunctSemanticIdentitySet("technology_to_jargon", "technology_to_jargon", cellPlatformDomain);
	public static final Set perspective_xmlProcessor = Instantiation.addDisjunctSemanticIdentitySet("perspective_xmlProcessor", "perspective_xmlProcessor", cellPlatformDomain);
	public static final Set xmlProcessor_to_jargon = Instantiation.addDisjunctSemanticIdentitySet("xmlProcessor_to_jargon", "xmlProcessor_to_jargon", cellPlatformDomain);
	public static final Set perspective_javaVirtualMachine = Instantiation.addDisjunctSemanticIdentitySet("perspective_javaVirtualMachine", "perspective_javaVirtualMachine", cellPlatformDomain);
	public static final Set javaVirtualMachine_to_classJargon = Instantiation.addDisjunctSemanticIdentitySet("javaVirtualMachine_to_classJargon", "javaVirtualMachine_to_classJargon", cellPlatformDomain);
	public static final Set javaVirtualMachine_to_memberJargon = Instantiation.addDisjunctSemanticIdentitySet("javaVirtualMachine_to_memberJargon", "javaVirtualMachine_to_memberJargon", cellPlatformDomain);
	public static final Set javaVirtualMachine_to_packageJargon = Instantiation.addDisjunctSemanticIdentitySet("javaVirtualMachine_to_packageJargon", "javaVirtualMachine_to_packageJargon", cellPlatformDomain);

	public static final Set perspective_sqlDatabase = Instantiation.addDisjunctSemanticIdentitySet("perspective_sqlDatabase", "perspective_sqlDatabase", cellPlatformDomain);
	public static final Set sqlDatabase_to_jargon = Instantiation.addDisjunctSemanticIdentitySet("sqlDatabase_to_jargon", "sqlDatabase_to_jargon", cellPlatformDomain);

	public static final Set allCharactersToLower = Instantiation.addDisjunctSemanticIdentitySet("allCharactersToLower", "allCharactersToLower", cellPlatformDomain);
	public static final Set allCharactersToUpper = Instantiation.addDisjunctSemanticIdentitySet("allCharactersToUpper", "allCharactersToUpper", cellPlatformDomain);
	public static final Set firstCharacterOfAllWordsToUpper = Instantiation.addDisjunctSemanticIdentitySet("firstCharacterOfAllWordsToUpper", "firstCharacterOfAllWordsToUpper", cellPlatformDomain);
	public static final Set firstCharacterToLower = Instantiation.addDisjunctSemanticIdentitySet("firstCharacterToLower", "firstCharacterToLower", cellPlatformDomain);
	public static final Set whiteToNoCharacter = Instantiation.addDisjunctSemanticIdentitySet("whiteToNoCharacter", "whiteToNoCharacter", cellPlatformDomain);
	public static final Set whiteToMinus = Instantiation.addDisjunctSemanticIdentitySet("whiteToMinus", "whiteToMinus", cellPlatformDomain);
	public static final Set whiteToUnderscore = Instantiation.addDisjunctSemanticIdentitySet("whiteToUnderscore", "whiteToUnderscore", cellPlatformDomain);

	public static final Set lifeCycle = Instantiation.addDisjunctSemanticIdentitySet("life cycle", "life cycles", cellPlatformDomain);
	public static final Set state = Instantiation.addDisjunctSemanticIdentitySet("state", "states", cellPlatformDomain);
	public static final Set cellState = Instantiation.addSemanticRole("cell state", "cell states", cellPlatformDomain, state);
	public static final Set cell = Instantiation.addDisjunctSemanticIdentitySet("cell", "cells", cellPlatformDomain);
	public static final Set connection = Instantiation.addDisjunctSemanticIdentitySet("connection", "connections", cellPlatformDomain);
	public static final Set cellContent = Instantiation.addDisjunctSemanticIdentitySet("cell content", "set of cell content", cellPlatformDomain);
	public static final Set cell_to_validityInterval = Instantiation.addDisjunctSemanticIdentitySet("cell to validity interval", "set of cell to validity intervals", cellPlatformDomain);
	public static final Set cell_to_transaction = Instantiation.addDisjunctSemanticIdentitySet("cell to transaction", "set of cell to transactions", cellPlatformDomain);
	public static final Set cell_to_producer = Instantiation.addDisjunctSemanticIdentitySet("cell to producer", "set of cell to producers", cellPlatformDomain);
	public static final Set cell_to_consumers = Instantiation.addDisjunctSemanticIdentitySet("cell to consumers", "set of cell to consumers", cellPlatformDomain);

	public static final Set transition = Instantiation.addDisjunctSemanticIdentitySet("transition", "transitions", cellPlatformDomain);
	public static final Set source = Instantiation.addSemanticRole("source", "set of sources", cellPlatformDomain, state);
	public static final Set target = Instantiation.addSemanticRole("target", "set of targets", cellPlatformDomain, state);
	public static final Set cell_to_lifeCycle = Instantiation.addDisjunctSemanticIdentitySet("cell to life cycle", "set of cells to life cycles", cellPlatformDomain);
	public static final Set cell_to_state = Instantiation.addDisjunctSemanticIdentitySet("cell to state", "set of cell to states", cellPlatformDomain);
	public static final Set cell_to_semanticUnit = Instantiation.addDisjunctSemanticIdentitySet("cell to semantic unit", "set of cell to semantic unit", cellPlatformDomain);

	public static final Set timestamp = Instantiation.addDisjunctSemanticIdentitySet("timestamp", "set of timestamps", cellPlatformDomain);
	public static final Set validFromTimestamp = Instantiation.addSemanticRole("valid from timestamp", "set of valid from timestamps", cellPlatformDomain, timestamp);
	public static final Set validUntilTimestamp = Instantiation.addSemanticRole("valid until timestamp", "set of valid until timestamps", cellPlatformDomain, timestamp);
	public static final Set creationTimestamp = Instantiation.addSemanticRole("creation timestamp", "set of creation timestamps", cellPlatformDomain, timestamp);
	public static final Set validityInterval = Instantiation.addDisjunctSemanticIdentitySet("validity interval", "set of validity intervals", cellPlatformDomain);
	public static final Set validityIntervals = Instantiation.addDisjunctSemanticIdentitySet("validity intervals", "set of validity intervals", cellPlatformDomain);
	public static final Set timestamps = Instantiation.addDisjunctSemanticIdentitySet("timestamps", "set of timestamps", cellPlatformDomain);

	public static final Set timeline = Instantiation.addDisjunctSemanticIdentitySet("timeline", "set of timelines", cellPlatformDomain);

	public static final Set transaction = Instantiation.addDisjunctSemanticIdentitySet("transaction", "set of transactions", cellPlatformDomain);
	public static final Set baseline = Instantiation.addDisjunctSemanticIdentitySet("baseline", "set of baselines", cellPlatformDomain);
	public static final Set transactions = Instantiation.addDisjunctSemanticIdentitySet("transactions", "set of transactions", cellPlatformDomain);
	public static final Set time = Instantiation.addDisjunctSemanticIdentitySet("time", "set of time", cellPlatformDomain);
	public static final Set cells = Instantiation.addDisjunctSemanticIdentitySet("cells", "set of cells", cellPlatformDomain);
	public static final Set languages = Instantiation.addDisjunctSemanticIdentitySet("languages", "set of languages", cellPlatformDomain);
	public static final Set contracts = Instantiation.addDisjunctSemanticIdentitySet("contracts", "set of contracts", cellPlatformDomain);
	public static final Set organizations = Instantiation.addDisjunctSemanticIdentitySet("organizations", "set of organizations", cellPlatformDomain);
	public static final Set terminologies = Instantiation.addDisjunctSemanticIdentitySet("terminologies", "set of terminologies", cellPlatformDomain);
	public static final Set cellVisualizations = Instantiation.addDisjunctSemanticIdentitySet("cell visualizations", "set of cell visualizations", cellPlatformDomain);
	public static final Set formulas = Instantiation.addDisjunctSemanticIdentitySet("formulas", "set of formulas", cellPlatformDomain);

	public static final Set timeConsciousness = Instantiation.addDisjunctSemanticIdentitySet("time consciousness", "set of time consciousness", cellPlatformDomain);
	public static final Set dynamicVertex = Instantiation.addDisjunctSemanticIdentitySet("dynamic vertex", "set of dynamic vertices", cellPlatformDomain);
	public static final Set dynamicConnection = Instantiation.addDisjunctSemanticIdentitySet("dynamic connection", "set of dynamic connections", cellPlatformDomain);
	public static final Set dynamicConnection_to_validityInterval = Instantiation.addDisjunctSemanticIdentitySet("dynamic connection to validity interval", "set of dynamic connections to validity intervals", cellPlatformDomain);
	public static final Set dynamicConnection_to_transaction = Instantiation.addDisjunctSemanticIdentitySet("dynamic connection to transaction", "set of dynamic connections to transactions", cellPlatformDomain);

	public static final Set validityInterval_to_validFromTimestamp = Instantiation.addDisjunctSemanticIdentitySet("validity interval to valid from timestamp", "set of validity intervals to valid from timestamps", cellPlatformDomain);
	public static final Set validityInterval_to_validUntilTimestamp = Instantiation.addDisjunctSemanticIdentitySet("validity interval to valid until timestamp", "set of validity intervals to valid until timestamps", cellPlatformDomain);

	public static final Set transaction_to_creationTimestamp = Instantiation.addDisjunctSemanticIdentitySet("transaction to creation timestamp", "set of transaction to creation timestamps", cellPlatformDomain);
	public static final Set transaction_to_creator = Instantiation.addDisjunctSemanticIdentitySet("transaction to creator", "set of transaction to creators", cellPlatformDomain);

	public static final Set organization_to_members = Instantiation.addDisjunctSemanticIdentitySet("organization to member", "set of organizations to members", cellPlatformDomain);
	public static final Set organization_to_roles = Instantiation.addDisjunctSemanticIdentitySet("organization to roles", "set of organizations to roles", cellPlatformDomain);
	public static final Set agent_to_roles = Instantiation.addDisjunctSemanticIdentitySet("agent to roles", "set of agent to roles", cellPlatformDomain);
	public static final Set role_to_includedRoles = Instantiation.addDisjunctSemanticIdentitySet("role to included roles", "set of role to included roles", cellPlatformDomain);
	public static final Set role_to_excludedRoles = Instantiation.addDisjunctSemanticIdentitySet("role to excluded roles", "set of role to excluded roles", cellPlatformDomain);

	public static final Set sessionHandling = Instantiation.addDisjunctSemanticIdentitySet("session handling", "set of session handling", cellPlatformDomain);
	public static final Set session = Instantiation.addDisjunctSemanticIdentitySet("session", "set of sessions", cellPlatformDomain);
	public static final Set error = Instantiation.addDisjunctSemanticIdentitySet("error", "set of errors", cellPlatformDomain);
	public static final Set session_to_error = Instantiation.addDisjunctSemanticIdentitySet("session to error", "set of session to error", cellPlatformDomain);
	public static final Set session_to_transaction = Instantiation.addDisjunctSemanticIdentitySet("session to transaction", "set of session to transaction", cellPlatformDomain);
	public static final Set error_to_transaction = Instantiation.addDisjunctSemanticIdentitySet("error to transaction", "set of error to transaction", cellPlatformDomain);
	public static final Set sessions = Instantiation.addDisjunctSemanticIdentitySet("sessions", "sessions", cellPlatformDomain);


	public static final Set language = Instantiation.addDisjunctSemanticIdentitySet("language", "languages", cellPlatformDomain);
	public static final Set jargon = Instantiation.addDisjunctSemanticIdentitySet("jargon", "jargons", cellPlatformDomain);
	public static final Set namingConvention = Instantiation.addDisjunctSemanticIdentitySet("naming convention", "naming conventions", cellPlatformDomain);
	public static final Set characterTransformation = Instantiation.addDisjunctSemanticIdentitySet("character transformation", "character transformations", cellPlatformDomain);
	public static final Set wordTransformation = Instantiation.addDisjunctSemanticIdentitySet("word transformation", "word transformations", cellPlatformDomain);
	public static final Set statementTransformation = Instantiation.addDisjunctSemanticIdentitySet("statement transformation", "statement transformations", cellPlatformDomain);
	public static final Set whiteTransformation = Instantiation.addDisjunctSemanticIdentitySet("white transformation", "white transformations", cellPlatformDomain);

	public static final Set nativeLanguage = Instantiation.addDisjunctSemanticIdentitySet("native language", "native languages", cellPlatformDomain);
	public static final Set perspective = Instantiation.addDisjunctSemanticIdentitySet("perspective", "perspectives", cellPlatformDomain);
	public static final Set perspective_to_jargons = Instantiation.addDisjunctSemanticIdentitySet("perspective_to_jargons", "set of perspective_to_jargons", cellPlatformDomain);
	public static final Set agent_to_nativeLanguage = Instantiation.addDisjunctSemanticIdentitySet("agent_to_nativeLanguage", "set of agent_to_nativeLanguage", cellPlatformDomain);
	public static final Set cell_to_nativeLanguage = Instantiation.addDisjunctSemanticIdentitySet("cell_to_nativeLanguage", "set of cell_to_nativeLanguage", cellPlatformDomain);

	public static final Set cellMetaLanguage = Instantiation.addSemanticIdentitySet("Cell Meta", "Cell Meta", cellPlatformDomain);
	public static final Set codingLanguage = Instantiation.addDisjunctSemanticIdentitySet("Coding", "Coding", cellPlatformDomain);

	public static final Set xmlJargon = Instantiation.addDisjunctSemanticIdentitySet("XML", "XML", cellPlatformDomain);
	public static final Set javaClassJargon = Instantiation.addDisjunctSemanticIdentitySet("Java Class", "Java Classes", cellPlatformDomain);
	public static final Set javaMemberJargon = Instantiation.addDisjunctSemanticIdentitySet("Java Member", "Java Members", cellPlatformDomain);
	public static final Set javaPackageJargon = Instantiation.addDisjunctSemanticIdentitySet("Java Package", "Java Packages", cellPlatformDomain);
	public static final Set sqlJargon = Instantiation.addDisjunctSemanticIdentitySet("SQL", "SQL", cellPlatformDomain);
	public static final Set englishLanguage = Instantiation.addDisjunctSemanticIdentitySet("English", "English", cellPlatformDomain);
	public static final Set deutschLanguage = Instantiation.addDisjunctSemanticIdentitySet("Deutsch", "Deutsch", cellPlatformDomain);
	public static final Set koreanLanguage = Instantiation.addDisjunctSemanticIdentitySet("Korean", "Korean", cellPlatformDomain);

	public static final Set s23mNativeLanguage = Instantiation.addDisjunctSemanticIdentitySet("s23mNativeLanguage", "s23mNativeLanguage", cellPlatformDomain);
	public static final Set s23mKernelNativeLanguage = Instantiation.addDisjunctSemanticIdentitySet("s23mKernelNativeLanguage", "s23mKernelNativeLanguage", cellPlatformDomain);

	public static final Set languageElement = Instantiation.addDisjunctSemanticIdentitySet("language element", "set of language elements", cellPlatformDomain);
	public static final Set abstractWord = Instantiation.addDisjunctSemanticIdentitySet("abstract word", "set of abstract words", cellPlatformDomain);
	public static final Set word = Instantiation.addDisjunctSemanticIdentitySet("word", "set of words", cellPlatformDomain);
	public static final Set wordSeparator = Instantiation.addDisjunctSemanticIdentitySet("word separator", "set of word separators", cellPlatformDomain);
	public static final Set whiteSpaceElement = Instantiation.addDisjunctSemanticIdentitySet("white space element", "set of white space elements", cellPlatformDomain);
	public static final Set sentenceSeparator = Instantiation.addDisjunctSemanticIdentitySet("sentence separator", "set of sentence separators", cellPlatformDomain);
	public static final Set toBeTranslated = Instantiation.addDisjunctSemanticIdentitySet("TO BE TRANSLATED", "TO BE TRANSLATED", cellPlatformDomain);


	public static final Set terminology = Instantiation.addDisjunctSemanticIdentitySet("terminology", "set of terminologies", cellPlatformDomain);
	public static final Set includedTerminology = Instantiation.addSemanticRole("included terminology", "set of included terminologies", cellPlatformDomain, terminology);
	public static final Set abbreviation = Instantiation.addDisjunctSemanticIdentitySet("abbreviation", "set of abbreviations", cellPlatformDomain);
	public static final Set idiom = Instantiation.addDisjunctSemanticIdentitySet("idiom", "set of idioms", cellPlatformDomain);
	public static final Set idiomPart = Instantiation.addSemanticRole("part", "set of parts", cellPlatformDomain, abstractWord);
	public static final Set semanticUnit = Instantiation.addDisjunctSemanticIdentitySet("semantic unit", "set of semantic units", cellPlatformDomain);
	public static final Set wordDefinition = Instantiation.addDisjunctSemanticIdentitySet("word definition", "set of word definitions", cellPlatformDomain);
	public static final Set oppositeSemanticUnit = Instantiation.addSemanticRole("opposite", "set of opposites", cellPlatformDomain, semanticUnit);

	public static final Set semanticDimension = Instantiation.addDisjunctSemanticIdentitySet("semantic dimension", "set of semantic dimensions", cellPlatformDomain);
	public static final Set idiom_to_languages = Instantiation.addDisjunctSemanticIdentitySet("idiom to languages", "set of idiom to languages", cellPlatformDomain);
	public static final Set idiom_to_idiomParts = Instantiation.addDisjunctSemanticIdentitySet("idiom to idiom parts", "set of idiom to idiom parts", cellPlatformDomain);
	public static final Set abbreviation_to_languages = Instantiation.addDisjunctSemanticIdentitySet("abbreviation to languages", "set of abbreviation to languages", cellPlatformDomain);
	public static final Set wordDefinition_to_language = Instantiation.addDisjunctSemanticIdentitySet("word definition to language", "set of word definition to language", cellPlatformDomain);
	public static final Set word_to_semanticUnit = Instantiation.addDisjunctSemanticIdentitySet("word to semantic unit", "set of word to semantic unit", cellPlatformDomain);
	public static final Set semanticUnit_to_abstractWords = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to abstract words", "set of semantic unit to abstract words", cellPlatformDomain);
	public static final Set semanticUnit_to_abbreviations = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to abbreviations", "set of semantic unit to abbreviations", cellPlatformDomain);
	public static final Set semanticUnit_to_wordDefinitions = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to word definitions", "set of semantic unit to word definitions", cellPlatformDomain);
	public static final Set semanticUnit_to_oppositeSemanticUnit = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to opposite semantic unit", "set of semantic unit to opposite semantic unit", cellPlatformDomain);

	public static final Set formula = Instantiation.addDisjunctSemanticIdentitySet("formula", "set of formulas", cellPlatformDomain);
	public static final Set variable = Instantiation.addDisjunctSemanticIdentitySet("variable", "set of variables", cellPlatformDomain);
	public static final Set constant = Instantiation.addDisjunctSemanticIdentitySet("constant", "set of constants", cellPlatformDomain);
	public static final Set representation = Instantiation.addDisjunctSemanticIdentitySet("representation", "set of representations", cellPlatformDomain);
	public static final Set variable_to_representation = Instantiation.addDisjunctSemanticIdentitySet("variable to representation", "set of variable to representation", cellPlatformDomain);
	public static final Set constant_to_representation = Instantiation.addDisjunctSemanticIdentitySet("constant to representation", "set of constant to representation", cellPlatformDomain);
	public static final Set evaluationOf = Instantiation.addDisjunctSemanticIdentitySet("evaluation of", "evaluation of", cellPlatformDomain);
	public static final Set closeSquareBracket = Instantiation.addDisjunctSemanticIdentitySet(" ] ", " ] ", cellPlatformDomain);
	public static final Set openSquareBracket = Instantiation.addDisjunctSemanticIdentitySet(" [ ", " [ ", cellPlatformDomain);


	public static final Set logicalFormula = Instantiation.addDisjunctSemanticIdentitySet("logical formula", "set of logical formulas", cellPlatformDomain);

	public static final Set literal = Instantiation.addDisjunctSemanticIdentitySet("literal", "set of literals", cellPlatformDomain);
	public static final Set literalInstance = Instantiation.addDisjunctSemanticIdentitySet("literal instance", "set of literal instances", cellPlatformDomain);

	public static final Set properSubFormula = Instantiation.addDisjunctSemanticIdentitySet("proper  subformula", "set of proper subformulas", cellPlatformDomain);
	public static final Set domain = Instantiation.addDisjunctSemanticIdentitySet("domain", "set of domains", cellPlatformDomain);
	public static final Set unaryFunction = Instantiation.addDisjunctSemanticIdentitySet("unary function", "set of unary functions", cellPlatformDomain);
	public static final Set binaryFunction = Instantiation.addDisjunctSemanticIdentitySet("binary function", "set of binary functions", cellPlatformDomain);
	public static final Set isAssociative = Instantiation.addDisjunctSemanticIdentitySet("is associative", "is associative", cellPlatformDomain);
	public static final Set binaryFunction_to_isAssociative = Instantiation.addDisjunctSemanticIdentitySet("binary function to is associative", "binary function to is associative", cellPlatformDomain);
	public static final Set identityElement = Instantiation.addDisjunctSemanticIdentitySet("identity element", "identity element", cellPlatformDomain);
	public static final Set isCommutative = Instantiation.addDisjunctSemanticIdentitySet("is commutative", "is commutative", cellPlatformDomain);
	public static final Set isDistributive = Instantiation.addDisjunctSemanticIdentitySet("is distributive", "is distributive", cellPlatformDomain);
	public static final Set binaryFunction_to_isCommutative = Instantiation.addDisjunctSemanticIdentitySet("binary function to is commutative", "binary function to is commutative", cellPlatformDomain);
	public static final Set hasInverseElements = Instantiation.addDisjunctSemanticIdentitySet("has inverse elements", "has inverse elements", cellPlatformDomain);
	public static final Set binaryFunction_to_hasInverseElements = Instantiation.addDisjunctSemanticIdentitySet("binary function to has inverse elements", "binary function to has inverse elements", cellPlatformDomain);
	public static final Set naryFunction = Instantiation.addDisjunctSemanticIdentitySet("n-ary function", "set of n-ary functions", cellPlatformDomain);
	public static final Set empty = Instantiation.addDisjunctSemanticIdentitySet("EMPTY", "set of EMPTYs", cellPlatformDomain);
	public static final Set exist = Instantiation.addDisjunctSemanticIdentitySet("EXIST", "set of EXISTs", cellPlatformDomain);
	public static final Set not = Instantiation.addDisjunctSemanticIdentitySet("NOT", "set of NOTs", cellPlatformDomain);
	public static final Set contains = Instantiation.addDisjunctSemanticIdentitySet("CONTAINS", "set of CONTAINS", cellPlatformDomain);
	public static final Set equal = Instantiation.addDisjunctSemanticIdentitySet("EQUAL", "set of EQUALs", cellPlatformDomain);
	public static final Set equalToRepresentation = Instantiation.addDisjunctSemanticIdentitySet("EQUAL REPRESENTATION", "set of EQUAL REPRESENTATIONs", cellPlatformDomain);

	public static final Set smallerEqual = Instantiation.addDisjunctSemanticIdentitySet("SMALLER or EQUAL", "set of SMALLER or EQUALs", cellPlatformDomain);
	public static final Set greaterEqual = Instantiation.addDisjunctSemanticIdentitySet("GREATER or EQUAL", "set of GREATER or EQUALs", cellPlatformDomain);
	public static final Set smaller = Instantiation.addDisjunctSemanticIdentitySet("SMALLER", "set of SMALLERs", cellPlatformDomain);
	public static final Set greater = Instantiation.addDisjunctSemanticIdentitySet("GREATER", "set of GREATERs", cellPlatformDomain);
	public static final Set and = Instantiation.addDisjunctSemanticIdentitySet("AND", "set of ANDs", cellPlatformDomain);
	public static final Set or = Instantiation.addDisjunctSemanticIdentitySet("OR", "set of ORs", cellPlatformDomain);
	public static final Set xor = Instantiation.addDisjunctSemanticIdentitySet("EXCLUSIVE OR", "set of EXCLUSIVE ORs", cellPlatformDomain);
	public static final Set term = Instantiation.addDisjunctSemanticIdentitySet("term", "set of terms", cellPlatformDomain);

	public static final Set literal_to_properSubFormula = Instantiation.addDisjunctSemanticIdentitySet("literal to proper subformula", "set of literals to proper subformulas", cellPlatformDomain);
	public static final Set binaryFunction_to_terms = Instantiation.addDisjunctSemanticIdentitySet("binary function to terms", "set of logical function to terms", cellPlatformDomain);
	public static final Set naryFunction_to_terms = Instantiation.addDisjunctSemanticIdentitySet("nary function to terms", "set of nary functions to terms", cellPlatformDomain);
	public static final Set unaryFunction_to_term = Instantiation.addDisjunctSemanticIdentitySet("unary function to term", "set of unary functions to term", cellPlatformDomain);
	public static final Set literalInstance_to_properSubFormula = Instantiation.addDisjunctSemanticIdentitySet("literal to proper subformula", "set of literals to proper subformulas", cellPlatformDomain);
	public static final Set binaryFunctionInstance_to_terms = Instantiation.addDisjunctSemanticIdentitySet("binary function to terms", "set of logical function to terms", cellPlatformDomain);
	public static final Set naryFunctionInstance_to_terms = Instantiation.addDisjunctSemanticIdentitySet("nary function to terms", "set of nary functions to terms", cellPlatformDomain);
	public static final Set unaryFunctionInstance_to_term = Instantiation.addDisjunctSemanticIdentitySet("unary function to term", "set of unary functions to term", cellPlatformDomain);

	public static final Set cellVisualization = Instantiation.addDisjunctSemanticIdentitySet("cell visualization", "cell visualizations", cellPlatformDomain);
	public static final Set representationStyleCategories = Instantiation.addDisjunctSemanticIdentitySet("representation style categories", "set of representation style categories", cellPlatformDomain);
	public static final Set representationStyleElements = Instantiation.addDisjunctSemanticIdentitySet("representation style elements", "set of representation style elements", cellPlatformDomain);
	public static final Set representationStyle = Instantiation.addDisjunctSemanticIdentitySet("representation style", "representation styles", cellPlatformDomain);
	public static final Set vertexRepresentationStyle = Instantiation.addDisjunctSemanticIdentitySet("vertex representation style", "vertex representation styles", cellPlatformDomain);
	public static final Set arrowRepresentationStyle = Instantiation.addDisjunctSemanticIdentitySet("arrow representation style", "arrow representation styles", cellPlatformDomain);
	public static final Set color = Instantiation.addDisjunctSemanticIdentitySet("color", "colors", cellPlatformDomain);
	public static final Set lineWidth = Instantiation.addDisjunctSemanticIdentitySet("line width", "set of line widths", cellPlatformDomain);
	public static final Set lineStyle = Instantiation.addDisjunctSemanticIdentitySet("line style", "line styles", cellPlatformDomain);
	public static final Set connectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("connector symbol", "connector symbols", cellPlatformDomain);
	public static final Set sourceConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("source connector symbol", "source connector symbols", cellPlatformDomain);
	public static final Set targetConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("target connector symbol", "target connector symbols", cellPlatformDomain);
	public static final Set symbolBoundaryShape = Instantiation.addDisjunctSemanticIdentitySet("symbol boundary shape", "symbol boundary shapes", cellPlatformDomain);
	public static final Set symbolIcon = Instantiation.addDisjunctSemanticIdentitySet("symbol icon", "symbol icons", cellPlatformDomain);
	public static final Set symbolImage = Instantiation.addDisjunctSemanticIdentitySet("symbol image", "symbol images", cellPlatformDomain);
	public static final Set includesName = Instantiation.addDisjunctSemanticIdentitySet("includes name", "set of includes names", cellPlatformDomain);
	public static final Set includesIcon = Instantiation.addDisjunctSemanticIdentitySet("includes icon", "set of includes icons", cellPlatformDomain);
	public static final Set includesImage = Instantiation.addDisjunctSemanticIdentitySet("includes image", "set of includes images", cellPlatformDomain);
	public static final Set isBold = Instantiation.addDisjunctSemanticIdentitySet("is bold", "set of is bolds", cellPlatformDomain);
	public static final Set isInItalics = Instantiation.addDisjunctSemanticIdentitySet("is in italics", "set of is in italics", cellPlatformDomain);
	public static final Set imageSize = Instantiation.addDisjunctSemanticIdentitySet("image size", "set of image sizes", cellPlatformDomain);

	public static final Set representationStyle_to_color = Instantiation.addDisjunctSemanticIdentitySet("representation style to color", "set of representation style to colors", cellPlatformDomain);
	public static final Set representationStyle_to_lineWidth = Instantiation.addDisjunctSemanticIdentitySet("representation style to line width", "set of representation style to line widths", cellPlatformDomain);
	public static final Set vertexRepresentationStyle_to_superset_representationStyle = Instantiation.addDisjunctSemanticIdentitySet("vertex representation style to superset representation style", "set of vertex representation style to superset representation styles", cellPlatformDomain);
	public static final Set arrowRepresentationStyle_to_sourceConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("arrow representation style to source connector symbol", "set of arrow representation style to source connector symbols", cellPlatformDomain);
	public static final Set arrowRepresentationStyle_to_targetConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("arrow representation style to target connector symbol", "set of arrow representation style to target connector symbols", cellPlatformDomain);

	public static final Set theDefault = Instantiation.addDisjunctSemanticIdentitySet("default", "defaults", S23MSemanticDomains.cellKernel);
	public static final Set icon = Instantiation.addDisjunctSemanticIdentitySet("icon", "icons", S23MSemanticDomains.cellKernel);

	public static Set instantiateFeature() {

		validityInterval.addElement(validFromTimestamp);
		validityInterval.addElement(validUntilTimestamp);

		transaction.addElement(creationTimestamp);
		transaction.addElement(creator);

		isAssociative.addElement(S23MSemanticDomains.is_TRUE);
		isAssociative.addElement(S23MSemanticDomains.is_FALSE);
		isCommutative.addElement(S23MSemanticDomains.is_TRUE);
		isCommutative.addElement(S23MSemanticDomains.is_FALSE);
		hasInverseElements.addElement(S23MSemanticDomains.is_TRUE);
		hasInverseElements.addElement(S23MSemanticDomains.is_FALSE);
		isDistributive.addElement(S23MSemanticDomains.is_TRUE);
		isDistributive.addElement(S23MSemanticDomains.is_FALSE);


		return cellPlatformDomain;
	}

}
