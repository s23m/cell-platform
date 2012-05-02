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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication;

import org.s23m.cell.Set;
import org.w3c.dom.Document;

// TODO add a constructor accepting the Schema to use and validate against
public class XmlSetMarshaller implements SetMarshaller<Document> {

	/*
	 * Implementation:
	 * -> for processing exactly one instance, use the filter() operations:
	 *  filterInstances() goes over all the contained instances (but remember to
	 *  catch the Edge Ends that must be accessed from each Edge via toEdgeEnd() and fromEdgeEnd()).
	 * -> can also use filter(<properClass>) to structure the work by proper classes.
	 * 
	 * Test case:
	 * -> should be able to convert the set of models and the set of semantic domains to the XML format.
	 * 
	 * 
	 * Later on:
	 * 1) serialisation of all sets contained within a top-level agent (recursively)
	 * 2) processing all changed sets within a transaction, all of which should be related to one agent (instantiation API needs to be agent-aware: multiple transactions open at once, and every transaction relates to exactly one agent)
	 * 
	 * Test case:
	 * -> processing all semantic domains of an agent and the all models of an agent.
	 * Need to make at least 2 passes along the containment tree to resolve all references.
	 */
	@Override
	public Document serialise(Set input) throws SetMarshallingException {
		// TODO
		
		// create an ArtifactSet-builder, like we did for the schema builder
		// we can populate the different "aspects" of the artifactSet using similar code to the HTML visualisation
		
		// this method only needs to serialise a single artefact with its "keys" (UUIDs for category, container, etc)
		
			
		return null;
	}

	@Override
	public Set deserialise(Document input) throws SetMarshallingException {
		// TODO Auto-generated method stub
		return null;
	}

}
