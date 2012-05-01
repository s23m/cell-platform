package org.s23m.cell.communication;

import org.s23m.cell.Set;
import org.w3c.dom.Document;

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
