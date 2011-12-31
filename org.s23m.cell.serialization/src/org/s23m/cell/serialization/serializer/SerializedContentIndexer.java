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
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.util.ArrayList;
import java.util.List;

import org.s23m.cell.G;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.serialization.Gmodel.Instance;
import org.s23m.cell.serialization.InstanceType;
import org.s23m.cell.serialization.SemanticIdentityIndex;

public class SerializedContentIndexer {

	public static List<SemanticIdentityIndex> indexContent(final Instance instance) {
		final List<SemanticIdentityIndex> indexList = new ArrayList<SemanticIdentityIndex>();
		if (isRootInstance(instance.getSemanticIdentity().getUniqueRepresentationReference())) {
			indexList.add(indexInstance(instance));
		} else {
			createInstanceIndex(instance, indexList);
		}
		return indexList;
	}

	private static void createInstanceIndex(final Instance instance, final List<SemanticIdentityIndex> indexList) {
		indexList.add(indexInstance((instance)));
		for (final InstanceType subInstance: instance.getInstance()) {
			indexSubInstances(subInstance, indexList);
		}
	}

	private static void indexSubInstances(final InstanceType instance, final List<SemanticIdentityIndex> indexList) {
		indexList.add(indexInstance(instance));
		for (final InstanceType subInstance: instance.getInstance()) {
			indexSubInstances(subInstance, indexList);
		}
	}

	protected static SemanticIdentityIndex indexInstance(final InstanceType instance) {
		final SemanticIdentityIndex index = new SemanticIdentityIndex();
		index.setIdentifier(instance.getSemanticIdentity().getUniqueRepresentationReference());
		index.setUniqueRepresentationReference(instance.getSemanticIdentity().getUniqueRepresentationReference());
		index.setIsAnonymous(instance.getSemanticIdentity().isIsAnonymous());
		index.setMetaElementId(instance.getMetaElement());
		index.setMetaElementTypeName(G.coreGraphs.vertex.identity().name());
		index.setName(instance.getSemanticIdentity().getName());
		index.setPayload(instance.getSemanticIdentity().getPayload());
		index.setPluralName(instance.getSemanticIdentity().getPluralName());
		index.setTechnicalName(instance.getSemanticIdentity().getTechnicalName());
		return index;
	}

	private static boolean isRootInstance(final String urr) {
		return Root.root.identity().uniqueRepresentationReference().toString().equals(urr);
	}

}
