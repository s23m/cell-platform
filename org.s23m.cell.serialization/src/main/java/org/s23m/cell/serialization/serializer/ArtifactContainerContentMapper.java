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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.serialization.S23M;
import org.s23m.cell.serialization.S23M.Instance;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ContainerTypeMapper;

public class ArtifactContainerContentMapper {

	private final Serializer serializer = SerializerHolder.getS23MInstanceSerializer(SerializationType.XML);

	public ArtifactContainerContentMapper() {
	}

	private boolean isSemanticDomainInstance(final String metaId) {
		final String ssetId1 = SemanticDomain.disjunctSemanticIdentitySet.identity().uniqueRepresentationReference().toString();
		final String ssetId2 = SemanticDomain.semanticRole.identity().uniqueRepresentationReference().toString();
		final String ssetId3 = SemanticDomain.semanticdomain.identity().uniqueRepresentationReference().toString();
		return metaId.equals(ssetId1) || metaId.equals(ssetId2) || metaId.equals(ssetId3);
	}

	private void recreateS23MInstances(final Map<String, S23M> deserializedInstances) {
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
		final Map<String, S23M> deserializedSemanticDomainInstances = new HashMap<String, S23M>();
		final Map<String,S23M> deserializedModelInstances = new HashMap<String,S23M>();

		for (final Iterator<Map.Entry<String,String>> itr = serializedTreeInstances.entrySet().iterator(); itr.hasNext(); ) {
			final Map.Entry<String,String> entry = itr.next();
			final String key = entry.getKey().toString();
			final S23M deserializedModel = serializer.unmarshallModel(entry.getValue());
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
		recreateS23MInstances(deserializedSemanticDomainInstances);
		recreateS23MInstances(deserializedModelInstances);
	}

	public void recreateInstancesFromArtifactContainer(final ArtefactContainer returnedArtifacts) {
		final Map<String,String> serializedTreeInstances =  new HashMap<String,String>();
		for (final Content c : returnedArtifacts.getContent()) {
			serializedTreeInstances.put(c.getId(), c.getContent());
		}
		recreateInstances(serializedTreeInstances);
	}

}
