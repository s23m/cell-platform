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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.editor.semanticdomain.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.s23m.cell.G;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.serializerinterface.Reconstitution;
import org.s23m.cell.core.IdentityImpl;

@SuppressWarnings("serial")
public class DetailsData implements Serializable {

	private static final String PAYLOAD_PROPERTY = "payload";
	private static final String TECHNICAL_NAME_PROPERTY = "technicalName";
	private static final String PLURAL_NAME_PROPERTY = "pluralName";
	private static final String NAME_PROPERTY = "name";
	private static final String IS_ABSTRACT_PROPERTY = "isAbstract";
	private static final String CONTAINER_PROPERTY = "container";
	private static final String META_ELEMENT_PROPERTY = "metaElement";

	public static final String EDGES_PROPERTY = "edges";
	public static final String SUPER_SET_REFERENCES_PROPERTY = "superSetReferences";
	public static final String VISIBILITIES_PROPERTY = "visibilities";
	public static final String VERTEX_FLAVOURED_SETS_PROPERTY = "vertexFlavoredSets";
	public static final String COMMANDS_PROPERTY = "commands";
	public static final String QUERIES_PROPERTY = "queries";

	// TODO use Guava immutable lists
	private static final List<String> NON_LIST_FIELDS = Arrays.asList(
		META_ELEMENT_PROPERTY,
		CONTAINER_PROPERTY,
		IS_ABSTRACT_PROPERTY,
		NAME_PROPERTY,
		PLURAL_NAME_PROPERTY,
		TECHNICAL_NAME_PROPERTY,
		PAYLOAD_PROPERTY
	);

	/**
	 * Display order for lists. Note that this is consistent with the
	 * order in the Details tab of the Eclipse-based visualisation interface.
	 */
	private static final List<String> LIST_FIELDS = Arrays.asList(
		VERTEX_FLAVOURED_SETS_PROPERTY,
		SUPER_SET_REFERENCES_PROPERTY,
		EDGES_PROPERTY,
		VISIBILITIES_PROPERTY,
		COMMANDS_PROPERTY,
		QUERIES_PROPERTY
	);

	private static final List<String> DISPLAY_ORDER = createDisplayOrderList();

	private static final List<String> MODIFIABLE_FIELDS = Arrays.asList(
		PAYLOAD_PROPERTY
	);

	private static final List<String> NAME_FIELD_IDS = Arrays.asList(
		NAME_PROPERTY,
		PLURAL_NAME_PROPERTY
	);

	private final String container;

	private final Set instance;

	private final boolean isAbstract;

	private final String metaElement;

	private String name;

	private String payload;

	private String pluralName;

	private final List<Set> superSetReferences;

	private final List<Set> edges;

	private final List<Set> visibilities;

	private final List<Set> vertexFlavoredSets;

	private final List<Set> commands;

	private final List<Set> queries;

	private String technicalName;

	private final String urr;

	public DetailsData(final Set instance) {
		this.instance = instance;
		this.isAbstract = instance.value(GmodelSemanticDomains.isAbstract) == (GmodelSemanticDomains.isAbstract_TRUE);
		this.urr = instance.identity().uniqueRepresentationReference().toString();
		this.name = getIdentityInstanceIdentity(instance.identity()).name();
		this.pluralName = getIdentityInstanceIdentity(instance.identity()).pluralName();
		this.technicalName = getIdentityInstanceIdentity(instance.identity()).technicalName();
		this.container = instance.container().identity().name();
		this.metaElement = instance.category().identity().name();
		visibilities = getEdgesOfType(instance, G.coreGraphs.visibility);
		superSetReferences = getEdgesOfType(instance, G.coreGraphs.superSetReference);
		edges = getEdgesOfType(instance, G.coreGraphs.edge);
		vertexFlavoredSets = instance.filterFlavor(G.coreGraphs.vertex).asList();
		commands = instance.commands().asList();
		queries = instance.queries().asList();

		setUpPayLoadContent();
	}

	private static List<String> createDisplayOrderList() {
		final List<String> result = new ArrayList<String>(NON_LIST_FIELDS);
		result.addAll(LIST_FIELDS);
		return result;
	}

	public static List<String> getDisplayOrder() {
		return DISPLAY_ORDER;
	}

	public static List<String> getListFieldIds() {
		return LIST_FIELDS;
	}

	public static List<String> getModifiableFieldIds() {
		return MODIFIABLE_FIELDS;
	}

	public static List<String> getNameFieldIds() {
		return NAME_FIELD_IDS;
	}

	public static boolean isEdge(final String id) {
		return id.equals(EDGES_PROPERTY);
	}

	public static boolean isVertexFlavoredSetsList(final String id) {
		return VERTEX_FLAVOURED_SETS_PROPERTY.equals(id);
	}

	private Identity getIdentityInstanceIdentity(final Identity identity) {
		return Reconstitution.getSetFromLocalMemory(identity).identity();
//		if (identity.uniqueRepresentationReference().equals(identity.identifier())) {
//			return identity;
//		} else {
//			return InstanceGetter.getInstanceByUUID(identity.identifier()).identity();
//		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DetailsData other = (DetailsData) obj;
		if (urr == null) {
			if (other.urr != null) {
				return false;
			}
		} else if (!urr.equals(other.urr)) {
			return false;
		}
		return true;
	}

	public String getContainer() {
		return container;
	}

	public List<Set> getEdges() {
		return edges;
	}

	// TODO can we use "filterFlavor" here?
	private List<Set> getEdgesOfType(final Set instance, final Set type) {
		final List<Set> edges = new ArrayList<Set>();
		// TODO do we need the instanceof checks?
		for (final Set s: instance.filterLinks()) {
			if (type.identity().isEqualToRepresentation(G.coreGraphs.edge.identity())) {
				if (s.flavor().isEqualTo(G.coreGraphs.edge)) {
					edges.add(s);
				}
			} else if (type.identity().isEqualToRepresentation(G.coreGraphs.superSetReference.identity())) {
				if (s.flavor().isEqualTo(G.coreGraphs.superSetReference)) {
					edges.add(s);
				}
			} else if (type.identity().isEqualToRepresentation(G.coreGraphs.visibility.identity())) {
				if (s.flavor().isEqualTo(G.coreGraphs.visibility)) {
					edges.add(s);
				}
			}
		}
		return edges;
	}

	public Set getInstance() {
		return instance;
	}

	public boolean getIsAbstract() {
		return isAbstract;
	}

	public String getMetaElement() {
		return metaElement;
	}

	public String getName() {
		return name;
	}

	public String getPayload() {
		return payload;
	}

	public String getPluralName() {
		return pluralName;
	}

	public List<Set> getSuperSetReferences() {
		return superSetReferences;
	}

	public String getTechnicalName() {
		return technicalName;
	}

	public String getUrr() {
		return urr;
	}

	public List<Set> getVisibilities() {
		return visibilities;
	}

	public List<Set> getCommands() {
		return commands;
	}

	public List<Set> getQueries() {
		return queries;
	}

	public List<Set> getVertexFlavoredSets() {
		return vertexFlavoredSets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((urr == null) ? 0 : urr.hashCode());
		return result;
	}

	public void setName(final String name) {
		if (!name.equals(this.name)) {
			this.name = name;
			instance.assignNewName(name);
			// TODO: replace by proper API commit and remove dependency on IdentityImpl
			((IdentityImpl)instance.identity()).commitNewName();
		    this.technicalName = instance.identity().technicalName();
		}
	}

	public void setPluralName(final String pluralName) {
		if (!pluralName.equals(this.pluralName)) {
			this.pluralName = pluralName;
			instance.assignNewPluralName(pluralName);
			// TODO: replace by proper API commit and remove dependency on IdentityImpl
			((IdentityImpl)instance.identity()).commitNewPluralName();
		}
	}

	public void setPayload(final String payload) {
		if (this.payload != null && !payload.isEmpty() && !payload.equals(this.payload)) {
			this.payload = payload;
			instance.assignNewPayload(payload);
		}
	}
	private void setUpPayLoadContent() {
		final String pl = this.instance.identity().payload();
		if (pl != null) {
			this.payload = pl;
		} else {
			this.payload = "";
		}
	}

}
