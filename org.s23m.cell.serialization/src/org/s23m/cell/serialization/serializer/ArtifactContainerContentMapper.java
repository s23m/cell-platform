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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.serialization.Gmodel;
import org.s23m.cell.serialization.Gmodel.Instance;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ContainerTypeMapper;

public class ArtifactContainerContentMapper {

	private final Serializer serializer = SerializerHolder.getGmodelInstanceSerializer(SerializationType.XML);

	public ArtifactContainerContentMapper() {
	}

	private boolean isSemanticDomainInstance(final String metaId) {
		final String ssetId1 = SemanticDomain.disjunctSemanticIdentitySet.identity().uniqueRepresentationReference().toString();
		final String ssetId2 = SemanticDomain.semanticRole.identity().uniqueRepresentationReference().toString();
		final String ssetId3 = SemanticDomain.semanticdomain.identity().uniqueRepresentationReference().toString();
		return metaId.equals(ssetId1) || metaId.equals(ssetId2) || metaId.equals(ssetId3);
	}

	private void recreateGmodelInstances(final Map<String, Gmodel> deserializedInstances) {
		if (!deserializedInstances.isEmpty()) {
			serializer.deserializeInstances(deserializedInstances);
		}
	}

	public void recreateInstancesFromArtifactMap(final Map<String, Object> returnedArtifacts) {
		final Map<String,String> serializedTreeInstances =  new HashMap<String,String>();
		final Map.Entry<String, Object> mapContent = returnedArtifacts.entrySet().iterator().next();
		final ArtefactContainer responseContainer = serializer.unmarshallContainer(mapContent.getValue().toString());

		System.err.println("# of returned instances: "+responseContainer.getContent().size());
		serializedTreeInstances.putAll(ContainerTypeMapper.mapToArtefactMap(responseContainer));
		recreateInstances(serializedTreeInstances);
	}

	private void recreateInstances(final Map<String, String> serializedTreeInstances) {
		//Get semantic domain instances
		final Map<String, Gmodel> deserializedSemanticDomainInstances = new HashMap<String, Gmodel>();
		final Map<String,Gmodel> deserializedModelInstances = new HashMap<String,Gmodel>();

		for (final Iterator<Map.Entry<String,String>> itr = serializedTreeInstances.entrySet().iterator(); itr.hasNext(); ) {
			final Map.Entry<String,String> entry = itr.next();
			final String key = entry.getKey().toString();
			final Gmodel deserializedModel = serializer.unmarshallModel(entry.getValue());
			final Instance i = deserializedModel.getInstance().get(0);
			final String metaId = i.getMetaElement();

			if (isSemanticDomainInstance(metaId)) {
				deserializedSemanticDomainInstances.put(entry.getKey(), deserializedModel);
				itr.remove();
			} else {
				deserializedModelInstances.put(entry.getKey(), deserializedModel);
			}
		}
		//re-create the fetched instances
		recreateGmodelInstances(deserializedSemanticDomainInstances);
		recreateGmodelInstances(deserializedModelInstances);
	}

	public void recreateInstancesFromArtifactContainer(final ArtefactContainer returnedArtifacts) {
		final Map<String,String> serializedTreeInstances =  new HashMap<String,String>();
		for (final Content c : returnedArtifacts.getContent()) {
			serializedTreeInstances.put(c.getId(), c.getContent());
		}
		recreateInstances(serializedTreeInstances);
	}

}
