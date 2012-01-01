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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell;

import java.util.UUID;

public interface Identity {

	/**
	 * Simplistic prototypical implementation of a unique identifier for SemanticIdentities.
	 * Must be replaced by a mechanism that scales to distributed systems
	 */
	UUID identifier();

	/**
	 * Simplistic prototypical implementation of a unique identifier for representations.
	 * Must be replaced by a mechanism that scales to distributed systems
	 */
	UUID uniqueRepresentationReference();

	/**
	 * Retrieves the name, e.g. "identity"
	 */
	String name();

	/**
	 * Retrieves the plural name, e.g., "identities"
	 */
	String pluralName();

	/**
	 * Computes a technicalName that is suitable for use as a name in textual programming languages
	 * In the multi-vocabulary edition of Gmodel, the result depends on the context in which the
	 * getTechnicalName() is used.
	 * In a "Java" context the result may comply with specific naming standards,
	 * in a "COBOL" context the result may comply with different naming standards,
	 * and in an "SQL"context the result may comply with yet another naming standard
	 */
	String technicalName();
	/**
	 * Indicates whether the provided {@link Identity} concept
	 * <code>concept</code> is equal to this one
	 * 
	 * @param concept
	 * @return whether the provided {@link Identity} concept is equal to this one
	 */
	boolean isEqualTo(Identity concept) ;
	/**
	 * Indicates whether the provided {@link Identity} representation
	 * <code>concept</code> is equal to this one
	 * 
	 * @param concept
	 * @return whether the provided {@link Identity} representation is equal to this one
	 */
	boolean isEqualToRepresentation(Identity representation) ;

	/**
	 * Indicates whether the Identity is part of the Gmodel kernel.
	 * All kernel Identities have immutable identifiers and immutable uniqueRepresentationReferences
	 * (the corresponding UUIDs are fixed in all Gmodel systems)
	 */
	boolean isPartOfKernel();

	/**
	 * Indicates whether the Identity is part of the universal container concept.
	 * All artifacts based on Identities that are part of the universal container concept
	 * can be instantiated such that the instances may reference any container,
	 * without any need for visibility based declarations of scope
	 */
	boolean isPartOfUniversalArtifactConcept();
	/**
	 * An Identity can be made part of the universal container concept.
	 * Once an Identity is part of the universal container concept, it may reference any container,
	 * without any need for visibility based declarations of scope.
	 * There is no operation to reverse the effect of the makePartOfUniversalArtifactConcept(),
	 * as such an operation could leave a repository in a corrupt state with respect to
	 * semantics relating to scope (visibility declarations).
	 */
	void makePartOfUniversalArtifactConcept();

	/**
	 * Retrieves the payload
	 */
	String payload();
	/**
	 * Sets the payload
	 */
	String setPayload(String payload);

}
