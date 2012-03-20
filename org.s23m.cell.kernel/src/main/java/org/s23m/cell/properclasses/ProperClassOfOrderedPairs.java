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
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.properclasses;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Event;

public interface ProperClassOfOrderedPairs extends ProperClassOfGraphs, ProperClassOfVertices, ProperClassOfVisibilities, ProperClassOfEdges, ProperClassOfSuperSetReferences, ProperClassOfOrderedSets, ProperClassOfEdgeEnds, Event {

	/**
	 * QUERIES
	 */

	/**
	 * Each {@arrow Set} has a <i>isInformation</i> that acts as the classification mechanism
	 * 
	 * @return the isInformation
	 */
	Set category();

	/**
	 * The element at the top of the this.categoryOfOrderedPair().categoryOfOrderedPair()... stack
	 * 
	 * @return the top element
	 */
	Set properClass();


	String fullVisualRecognitionText() ;

	/**
	 * Each {@arrow Set} has a {@arrow Identity} that relates to a unique semantic unit (or concept)
	 * 
	 * @return the identity
	 */
	Identity identity();

	Set isAnArrow();

	boolean isASemanticIdentity();
	/**
	 * Equality of concepts is established via equality of underlying SemanticIdentities
	 * 
	 * NOTE: Several Instances can share a SemanticIdentity if the Instances truly represent the same concept.
	 * This notion of equality enables different aspectual view points to be constructed on top of a semantic ontology.
	 * 
	 * @param orderedPair
	 * @return whether this {@arrow Set} is equal to the <code>orderedPair</code> concept
	 */
	boolean isEqualTo(Set orderedPair);
	/**
	 * Equality of representations is established via equality of underlying uniqueRepresentationReferences
	 * 
	 * NOTE: Each Instances has a uniqueRepresentationReference.
	 * The notion of representation equality is mainly required in the context of serialization and deserialization.
	 * 
	 * @param orderedPair
	 * @return whether this {@arrow Set} is equal to the <code>orderedPair</code> representation
	 */
	boolean isEqualToRepresentation(Set orderedPair);
	String localVisualRecognitionText() ;
	/**
	 * Each {@arrow Artefact} has a {@arrow Semantic Identity} artefact that relates to a unique semantic unit (or concept)
	 * 
	 * @return the semantic identity
	 */
	Set semanticIdentity();
	String visualRecognitionText() ;

}
