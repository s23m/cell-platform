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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.semanticextensions.outershells;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.GmodelSemanticDomains;

public class SemanticExtensionsDomain {

	// GOMODEL SEMANTIC EXTENSIONS DOMAIN
	public static final Set semanticExtensionsDomain = Instantiation.addSemanticDomain("gmodel semantic extensions", "gmodel semantic extensions", GmodelSemanticDomains.finiteSets);
	public static final Set world = Instantiation.addDisjunctSemanticIdentitySet("world", "set of worlds", semanticExtensionsDomain);
	public static final Set legalEntity = Instantiation.addDisjunctSemanticIdentitySet("legal entity", "set of legal entities", semanticExtensionsDomain);
	public static final Set person = Instantiation.addSemanticRole("person", "people", semanticExtensionsDomain, legalEntity);
	public static final Set organization = Instantiation.addSemanticRole("organization", "organizations", semanticExtensionsDomain, legalEntity);
	public static final Set user = Instantiation.addSemanticRole("user", "users", semanticExtensionsDomain, person);
	public static final Set creator = Instantiation.addSemanticRole("creator", "creators", semanticExtensionsDomain, legalEntity);
	public static final Set role = Instantiation.addDisjunctSemanticIdentitySet("role", "roles", semanticExtensionsDomain);
	public static final Set languagePreference = Instantiation.addDisjunctSemanticIdentitySet("language preference", "set of language preferences", semanticExtensionsDomain);
	public static final Set parentOrganization = Instantiation.addSemanticRole("parent", "set of parents", semanticExtensionsDomain, organization);
	public static final Set subOrganization = Instantiation.addSemanticRole("sub organization", "set of sub organizations", semanticExtensionsDomain, organization);
	public static final Set parentRole = Instantiation.addSemanticRole("parent", "set of parents", semanticExtensionsDomain, role);
	public static final Set includedRole = Instantiation.addSemanticRole("included role", "set of included roles", semanticExtensionsDomain, role);
	public static final Set excludedRole = Instantiation.addSemanticRole("excluded role", "set of excluded roles", semanticExtensionsDomain, role);
	public static final Set disjunctRole = Instantiation.addSemanticRole("disjunct role", "set of disjunct roles", semanticExtensionsDomain, role);
	public static final Set producer = Instantiation.addSemanticRole("producer", "producers", semanticExtensionsDomain, role);
	public static final Set consumer = Instantiation.addSemanticRole("consumer", "consumers", semanticExtensionsDomain, role);


	public static final Set lifeCycle = Instantiation.addDisjunctSemanticIdentitySet("life cycle", "life cycles", semanticExtensionsDomain);
	public static final Set state = Instantiation.addDisjunctSemanticIdentitySet("state", "states", semanticExtensionsDomain);
	public static final Set artifactState = Instantiation.addSemanticRole("container state", "container states", semanticExtensionsDomain, state);
	public static final Set artifact = Instantiation.addDisjunctSemanticIdentitySet("container", "artifacts", semanticExtensionsDomain);
	public static final Set artifact_to_validityInterval = Instantiation.addDisjunctSemanticIdentitySet("container to validity interval", "set of artifacts to validity intervals", semanticExtensionsDomain);
	public static final Set artifact_to_transaction = Instantiation.addDisjunctSemanticIdentitySet("container to transaction", "set of artifacts to transactions", semanticExtensionsDomain);
	public static final Set artifact_to_producer = Instantiation.addDisjunctSemanticIdentitySet("container to producer", "set of artifacts to producers", semanticExtensionsDomain);
	public static final Set artifact_to_consumers = Instantiation.addDisjunctSemanticIdentitySet("container to consumers", "set of artifacts to consumers", semanticExtensionsDomain);


	public static final Set transition = Instantiation.addDisjunctSemanticIdentitySet("transition", "transitions", semanticExtensionsDomain);
	public static final Set source = Instantiation.addSemanticRole("source", "set of sources", semanticExtensionsDomain, state);
	public static final Set target = Instantiation.addSemanticRole("target", "set of targets", semanticExtensionsDomain, state);
	public static final Set artifact_to_lifeCycle = Instantiation.addDisjunctSemanticIdentitySet("container to life cycle", "set of container to life cycles", semanticExtensionsDomain);
	public static final Set artifact_to_state = Instantiation.addDisjunctSemanticIdentitySet("container to state", "set of container to states", semanticExtensionsDomain);
	public static final Set artifact_to_semanticUnit = Instantiation.addDisjunctSemanticIdentitySet("container to semantic unit", "set of container to semantic unit", semanticExtensionsDomain);

	public static final Set timestamp = Instantiation.addDisjunctSemanticIdentitySet("timestamp", "set of timestamps", semanticExtensionsDomain);
	public static final Set validFromTimestamp = Instantiation.addSemanticRole("valid from timestamp", "set of valid from timestamps", semanticExtensionsDomain, timestamp);
	public static final Set validUntilTimestamp = Instantiation.addSemanticRole("valid until timestamp", "set of valid until timestamps", semanticExtensionsDomain, timestamp);
	public static final Set creationTimestamp = Instantiation.addSemanticRole("creation timestamp", "set of creation timestamps", semanticExtensionsDomain, timestamp);
	public static final Set validityInterval = Instantiation.addDisjunctSemanticIdentitySet("validity interval", "set of validity intervals", semanticExtensionsDomain);
	public static final Set validityIntervals = Instantiation.addDisjunctSemanticIdentitySet("validity intervals", "set of validity intervals", semanticExtensionsDomain);
	public static final Set timestamps = Instantiation.addDisjunctSemanticIdentitySet("timestamps", "set of timestamps", semanticExtensionsDomain);

	public static final Set timeline = Instantiation.addDisjunctSemanticIdentitySet("timeline", "set of timelines", semanticExtensionsDomain);

	public static final Set transaction = Instantiation.addDisjunctSemanticIdentitySet("transaction", "set of transactions", semanticExtensionsDomain);
	public static final Set baseline = Instantiation.addDisjunctSemanticIdentitySet("baseline", "set of baselines", semanticExtensionsDomain);
	public static final Set transactions = Instantiation.addDisjunctSemanticIdentitySet("transactions", "set of transactions", semanticExtensionsDomain);


	public static final Set timeConsciousEdge = Instantiation.addDisjunctSemanticIdentitySet("time conscious edge", "set of time conscious edges", semanticExtensionsDomain);
	public static final Set timeConsciousEdge_to_validityInterval = Instantiation.addDisjunctSemanticIdentitySet("time conscious edge to validity interval", "set of time conscious edges to validity intervals", semanticExtensionsDomain);
	public static final Set timeConsciousEdge_to_transaction = Instantiation.addDisjunctSemanticIdentitySet("time conscious edge to transaction", "set of time conscious edge to transactions", semanticExtensionsDomain);

	public static final Set validityInterval_to_validFromTimestamp = Instantiation.addDisjunctSemanticIdentitySet("validity interval to valid from timestamp", "set of validity intervals to valid from timestamps", semanticExtensionsDomain);
	public static final Set validityInterval_to_validUntilTimestamp = Instantiation.addDisjunctSemanticIdentitySet("validity interval to valid until timestamp", "set of validity intervals to valid until timestamps", semanticExtensionsDomain);

	public static final Set transaction_to_creationTimestamp = Instantiation.addDisjunctSemanticIdentitySet("transaction to creation timestamp", "set of transaction to creation timestamps", semanticExtensionsDomain);
	public static final Set transaction_to_creator = Instantiation.addDisjunctSemanticIdentitySet("transaction to creator", "set of transaction to creators", semanticExtensionsDomain);

	public static final Set organization_to_user = Instantiation.addDisjunctSemanticIdentitySet("organization to user", "set of organizations to users", semanticExtensionsDomain);
	public static final Set organization_to_roles = Instantiation.addDisjunctSemanticIdentitySet("organization to roles", "set of organizations to roles", semanticExtensionsDomain);
	public static final Set organization_to_subOrganizations = Instantiation.addDisjunctSemanticIdentitySet("organization to sub organizations", "set of organizations to sub organizations", semanticExtensionsDomain);
	public static final Set user_to_languagePreferences = Instantiation.addDisjunctSemanticIdentitySet("user to language preferences", "set of user to language preferences", semanticExtensionsDomain);
	public static final Set user_to_roles = Instantiation.addDisjunctSemanticIdentitySet("user to roles", "set of user to roles", semanticExtensionsDomain);
	public static final Set role_to_includedRoles = Instantiation.addDisjunctSemanticIdentitySet("role to included roles", "set of role to included roles", semanticExtensionsDomain);
	public static final Set role_to_excludedRoles = Instantiation.addDisjunctSemanticIdentitySet("role to excluded roles", "set of role to excluded roles", semanticExtensionsDomain);
	public static final Set languagePreference_to_language = Instantiation.addDisjunctSemanticIdentitySet("language preference to language", "set of language preference to languages", semanticExtensionsDomain);


	public static final Set language = Instantiation.addDisjunctSemanticIdentitySet("language", "set of languages", semanticExtensionsDomain);
	public static final Set languageElement = Instantiation.addDisjunctSemanticIdentitySet("language element", "set of language elements", semanticExtensionsDomain);
	public static final Set abstractWord = Instantiation.addDisjunctSemanticIdentitySet("abstract word", "set of abstract words", semanticExtensionsDomain);
	public static final Set word = Instantiation.addDisjunctSemanticIdentitySet("word", "set of words", semanticExtensionsDomain);
	public static final Set wordSeparator = Instantiation.addDisjunctSemanticIdentitySet("word separator", "set of word separators", semanticExtensionsDomain);
	public static final Set whiteSpaceElement = Instantiation.addDisjunctSemanticIdentitySet("white space element", "set of white space elements", semanticExtensionsDomain);
	public static final Set sentenceSeparator = Instantiation.addDisjunctSemanticIdentitySet("sentence separator", "set of sentence separators", semanticExtensionsDomain);

	public static final Set terminology = Instantiation.addDisjunctSemanticIdentitySet("terminology", "set of terminologies", semanticExtensionsDomain);
	public static final Set includedTerminology = Instantiation.addSemanticRole("included terminology", "set of included terminologies", semanticExtensionsDomain, terminology);
	public static final Set abbreviation = Instantiation.addDisjunctSemanticIdentitySet("abbreviation", "set of abbreviations", semanticExtensionsDomain);
	public static final Set idiom = Instantiation.addDisjunctSemanticIdentitySet("idiom", "set of idioms", semanticExtensionsDomain);
	public static final Set idiomPart = Instantiation.addSemanticRole("part", "set of parts", semanticExtensionsDomain, abstractWord);
	public static final Set semanticUnit = Instantiation.addDisjunctSemanticIdentitySet("semantic unit", "set of semantic units", semanticExtensionsDomain);
	public static final Set wordDefinition = Instantiation.addDisjunctSemanticIdentitySet("word definition", "set of word definitions", semanticExtensionsDomain);
	public static final Set oppositeSemanticUnit = Instantiation.addSemanticRole("opposite", "set of opposites", semanticExtensionsDomain, semanticUnit);

	public static final Set idiom_to_languages = Instantiation.addDisjunctSemanticIdentitySet("idiom to languages", "set of idiom to languages", semanticExtensionsDomain);
	public static final Set idiom_to_idiomParts = Instantiation.addDisjunctSemanticIdentitySet("idiom to idiom parts", "set of idiom to idiom parts", semanticExtensionsDomain);
	public static final Set abbreviation_to_languages = Instantiation.addDisjunctSemanticIdentitySet("abbreviation to languages", "set of abbreviation to languages", semanticExtensionsDomain);
	public static final Set wordDefinition_to_language = Instantiation.addDisjunctSemanticIdentitySet("word definition to language", "set of word definition to language", semanticExtensionsDomain);
	public static final Set semanticUnit_to_abstractWords = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to abstract words", "set of semantic unit to abstract words", semanticExtensionsDomain);
	public static final Set semanticUnit_to_abbreviations = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to abbreviations", "set of semantic unit to abbreviations", semanticExtensionsDomain);
	public static final Set semanticUnit_to_wordDefinitions = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to word definitions", "set of semantic unit to word definitions", semanticExtensionsDomain);
	public static final Set semanticUnit_to_oppositeSemanticUnit = Instantiation.addDisjunctSemanticIdentitySet("semantic unit to opposite semantic unit", "set of semantic unit to opposite semantic unit", semanticExtensionsDomain);

	public static final Set logicalExpression = Instantiation.addDisjunctSemanticIdentitySet("logical expression", "set of logical expressions", semanticExtensionsDomain);
	public static final Set logicalFunction = Instantiation.addDisjunctSemanticIdentitySet("logical function", "set of logical functions", semanticExtensionsDomain);
	public static final Set unaryLogicalFunction = Instantiation.addDisjunctSemanticIdentitySet("unary logical function", "set of unary logical functions", semanticExtensionsDomain);
	public static final Set binaryLogicalFunction = Instantiation.addDisjunctSemanticIdentitySet("binary logical function", "set of binary logical functions", semanticExtensionsDomain);
	public static final Set naryLogicalFunction = Instantiation.addDisjunctSemanticIdentitySet("n-ary logical function", "set of n-ary logical functions", semanticExtensionsDomain);
	public static final Set empty = Instantiation.addDisjunctSemanticIdentitySet("EMPTY", "set of EMPTYs", semanticExtensionsDomain);
	public static final Set exist = Instantiation.addDisjunctSemanticIdentitySet("EXIST", "set of EXISTs", semanticExtensionsDomain);
	public static final Set not = Instantiation.addDisjunctSemanticIdentitySet("NOT", "set of NOTs", semanticExtensionsDomain);
	public static final Set contains = Instantiation.addDisjunctSemanticIdentitySet("CONTAINS", "set of CONTAINS", semanticExtensionsDomain);
	public static final Set equal = Instantiation.addDisjunctSemanticIdentitySet("EQUAL", "set of EQUALs", semanticExtensionsDomain);
	public static final Set equalToRepresentation = Instantiation.addDisjunctSemanticIdentitySet("EQUAL REPRESENTATION", "set of EQUAL REPRESENTATIONs", semanticExtensionsDomain);

	public static final Set smallerEqual = Instantiation.addDisjunctSemanticIdentitySet("SMALLER or EQUAL", "set of SMALLER or EQUALs", semanticExtensionsDomain);
	public static final Set greaterEqual = Instantiation.addDisjunctSemanticIdentitySet("GREATER or EQUAL", "set of GREATER or EQUALs", semanticExtensionsDomain);
	public static final Set smaller = Instantiation.addDisjunctSemanticIdentitySet("SMALLER", "set of SMALLERs", semanticExtensionsDomain);
	public static final Set greater = Instantiation.addDisjunctSemanticIdentitySet("GREATER", "set of GREATERs", semanticExtensionsDomain);
	public static final Set and = Instantiation.addDisjunctSemanticIdentitySet("AND", "set of ANDs", semanticExtensionsDomain);
	public static final Set or = Instantiation.addDisjunctSemanticIdentitySet("OR", "set of ORs", semanticExtensionsDomain);
	public static final Set xor = Instantiation.addDisjunctSemanticIdentitySet("EXCLUSIVE OR", "set of EXCLUSIVE ORs", semanticExtensionsDomain);
	public static final Set operand = Instantiation.addDisjunctSemanticIdentitySet("operand", "set of operands", semanticExtensionsDomain);

	public static final Set binaryLogicalFunction_to_operands = Instantiation.addDisjunctSemanticIdentitySet("binary logical function to operands", "set of binary logical function to operands", semanticExtensionsDomain);
	public static final Set and_to_operands = Instantiation.addDisjunctSemanticIdentitySet("AND to operands", "set of AND to operands", semanticExtensionsDomain);
	public static final Set or_to_operands = Instantiation.addDisjunctSemanticIdentitySet("OR to operands", "set of OR to operands", semanticExtensionsDomain);
	public static final Set xor_to_operands = Instantiation.addDisjunctSemanticIdentitySet("XOR to operands", "set of XOR to operands", semanticExtensionsDomain);
	public static final Set not_to_operand = Instantiation.addDisjunctSemanticIdentitySet("NOT to operand", "set of NOT to operands", semanticExtensionsDomain);
	public static final Set exist_to_operand = Instantiation.addDisjunctSemanticIdentitySet("EXIST to operand", "set of EXIST to operands", semanticExtensionsDomain);
	public static final Set empty_to_operand = Instantiation.addDisjunctSemanticIdentitySet("EMPTY to operand", "set of EMPTY to operands", semanticExtensionsDomain);

	public static final Set artifactVisualization = Instantiation.addDisjunctSemanticIdentitySet("container visualizedGraph", "container visualizations", semanticExtensionsDomain);
	public static final Set representationStyleCategories = Instantiation.addDisjunctSemanticIdentitySet("representation style categories", "set of representation style categories", semanticExtensionsDomain);
	public static final Set representationStyleElements = Instantiation.addDisjunctSemanticIdentitySet("representation style elements", "set of representation style elements", semanticExtensionsDomain);
	public static final Set representationStyle = Instantiation.addDisjunctSemanticIdentitySet("representation style", "representation styles", semanticExtensionsDomain);
	public static final Set vertexRepresentationStyle = Instantiation.addDisjunctSemanticIdentitySet("vertex representation style", "vertex representation styles", semanticExtensionsDomain);
	public static final Set linkRepresentationStyle = Instantiation.addDisjunctSemanticIdentitySet("link representation style", "link representation styles", semanticExtensionsDomain);
	public static final Set color = Instantiation.addDisjunctSemanticIdentitySet("color", "colors", semanticExtensionsDomain);
	public static final Set lineWidth = Instantiation.addDisjunctSemanticIdentitySet("line width", "set of line widths", semanticExtensionsDomain);
	public static final Set lineStyle = Instantiation.addDisjunctSemanticIdentitySet("line style", "line styles", semanticExtensionsDomain);
	public static final Set connectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("connector symbol", "connector symbols", semanticExtensionsDomain);
	public static final Set sourceConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("source connector symbol", "source connector symbols", semanticExtensionsDomain);
	public static final Set targetConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("target connector symbol", "target connector symbols", semanticExtensionsDomain);
	public static final Set symbolBoundaryShape = Instantiation.addDisjunctSemanticIdentitySet("symbol boundary shape", "symbol boundary shapes", semanticExtensionsDomain);
	public static final Set symbolIcon = Instantiation.addDisjunctSemanticIdentitySet("symbol icon", "symbol icons", semanticExtensionsDomain);
	public static final Set symbolImage = Instantiation.addDisjunctSemanticIdentitySet("symbol image", "symbol images", semanticExtensionsDomain);
	public static final Set includesName = Instantiation.addDisjunctSemanticIdentitySet("includes name", "set of includes names", semanticExtensionsDomain);
	public static final Set includesIcon = Instantiation.addDisjunctSemanticIdentitySet("includes icon", "set of includes icons", semanticExtensionsDomain);
	public static final Set includesImage = Instantiation.addDisjunctSemanticIdentitySet("includes image", "set of includes images", semanticExtensionsDomain);
	public static final Set isBold = Instantiation.addDisjunctSemanticIdentitySet("is bold", "set of is bolds", semanticExtensionsDomain);
	public static final Set isInItalics = Instantiation.addDisjunctSemanticIdentitySet("is in italics", "set of is in italics", semanticExtensionsDomain);
	public static final Set imageSize = Instantiation.addDisjunctSemanticIdentitySet("image size", "set of image sizes", semanticExtensionsDomain);

	public static final Set representationStyle_to_color = Instantiation.addDisjunctSemanticIdentitySet("representation style to color", "set of representation style to colors", semanticExtensionsDomain);
	public static final Set representationStyle_to_lineWidth = Instantiation.addDisjunctSemanticIdentitySet("representation style to line width", "set of representation style to line widths", semanticExtensionsDomain);
	public static final Set vertexRepresentationStyle_to_superset_representationStyle = Instantiation.addDisjunctSemanticIdentitySet("vertex representation style to superset representation style", "set of vertex representation style to superset representation styles", semanticExtensionsDomain);
	public static final Set linkRepresentationStyle_to_sourceConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("link representation style to source connector symbol", "set of link representation style to source connector symbols", semanticExtensionsDomain);
	public static final Set linkRepresentationStyle_to_targetConnectorSymbol = Instantiation.addDisjunctSemanticIdentitySet("link representation style to target connector symbol", "set of link representation style to target connector symbols", semanticExtensionsDomain);

	public static final Set theDefault = Instantiation.addDisjunctSemanticIdentitySet("default", "defaults", GmodelSemanticDomains.gmodel);
	public static final Set icon = Instantiation.addDisjunctSemanticIdentitySet("icon", "icons", GmodelSemanticDomains.gmodel);


	public static Set instantiateFeature() {

		validityInterval.addElement(validFromTimestamp);
		validityInterval.addElement(validUntilTimestamp);

		transaction.addElement(creationTimestamp);
		transaction.addElement(creator);

		return semanticExtensionsDomain;
	}

}
