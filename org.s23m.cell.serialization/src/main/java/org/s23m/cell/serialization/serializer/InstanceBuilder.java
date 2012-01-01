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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.s23m.cell.G;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.CoreGraphs;
import org.s23m.cell.api.CoreSets;
import org.s23m.cell.api.models.ArtifactDerivation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.HTMLRepresentation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.serializerinterface.Reconstitution;
import org.s23m.cell.serialization.Gmodel.Instance;
import org.s23m.cell.serialization.ObjectFactory;
import org.s23m.cell.serialization.SemanticIdType;

public class InstanceBuilder {

	protected static final String ANONYMOUS_ID_NAME = GmodelSemanticDomains.anonymous.identity().name();
	private static ObjectFactory objectFactory = null;

	protected static Set addAbstractVertex(final Set artifact,
			final Set metaElement, final SemanticIdType identity) {
		final Identity si = buildIdentity(identity);
		return Reconstitution.addAbstract(artifact,metaElement, si);
	}

	protected static Set addConcreteVertex(final Set artifact,
			final Set metaElement, final SemanticIdType identity) {
		final Identity si = buildIdentity(identity);
		Set concVertex = null;
		try {
			concVertex =  Reconstitution.addConcrete(artifact,metaElement, si);;
		} catch (final Exception ex) {
			System.err.println(ex.getMessage());
		}
		return concVertex;
	}

	protected static void addKernelInstances(final Map<String, Set> instanceMap) {
		try {
			final CoreGraphs cg = G.coreGraphs;
			final CoreSets cs = G.coreSets;
			addPublicSetFields(instanceMap, cg);
			addPublicSetFields(instanceMap, cs);
			instanceMap.put(Root.root.identity().uniqueRepresentationReference().toString(), Root.root);
			addPublicSetFields(instanceMap, Root.class);
			addPublicSetFields(instanceMap, ArtifactDerivation.class);
			//addPublicSetFields(instanceMap, GmodelSemanticDomain.class);
			addPublicSetFields(instanceMap, HTMLRepresentation.class);
			addPublicSetFields(instanceMap, SemanticDomain.class);
			addPublicSetFields(instanceMap, GmodelSemanticDomains.class);
		} catch (final IllegalArgumentException ex) {
			java.util.logging.Logger.getLogger("global").log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (final IllegalAccessException ex) {
			java.util.logging.Logger.getLogger("global").log(
					java.util.logging.Level.SEVERE, null, ex);
		}
	}

	protected static void addInnershellInstancesInMemory(final Map<String, Set> instanceMap) {
		final Set e = G.coreGraphs.edge;
		transverseInnershell(G.coreGraphs.graph, instanceMap);
		try {
			addPublicSetFields(instanceMap, G.coreGraphs);
		} catch (final IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (final IllegalAccessException e1) {
			e1.printStackTrace();
		}
		final boolean gotEdge = instanceMap.containsKey(""+e.identity().uniqueRepresentationReference());
	}

	private static void transverseInnershell(final Set set, final Map<String, Set> instanceMap) {
		instanceMap.put(set.identity().uniqueRepresentationReference().toString(), set);
		for (final Set s : set.filterInstances()) {
			transverseInnershell(s, instanceMap);
		}
	}

	protected static void addPublicSetFields(final Map<String, Set> map, final Object instance) throws IllegalArgumentException, IllegalAccessException {
		Class<?> cls;
		if (instance instanceof Class) {
			cls = (Class<?>) instance;
		} else {
			cls = instance.getClass();
		}
		final Field[] fields = cls.getFields();
		for (final Field f : fields) {
			if (Modifier.isPublic(f.getModifiers())) {
				if (f.getType().equals(Set.class)) {
					final Set set = (Set) f.get(instance);
					map.put(set.identity().uniqueRepresentationReference().toString(), set);
				}
			}
		}
	}

	protected static Set build(final Instance instance, final Set metaElement, final Set artifact,
			final Identity reconstitutedId) {

		Set builtInstance = null;

		switch (instance.getType()) {
		case INSTANTIATED_ABSTRACT_ARTIFACT:
			builtInstance = InstanceBuilder.instantiateAbstractArtifact(artifact, metaElement,
					instance.getSemanticIdentity());
			break;
		case ABSTRACT_VERTEX:
			builtInstance = InstanceBuilder.addAbstractVertex(artifact,	metaElement,
					instance.getSemanticIdentity());
			break;
		case INSTANTIATED_CONCRETE_ARTIFACT:
			builtInstance = InstanceBuilder
			.instantiateConcreteArtifact(artifact, metaElement,instance.getSemanticIdentity());
			break;
		case INSTANTIATE_SEMANTIC_DOMAIN:
			//DO NOTHING
			break;
		case SEMANTIC_DOMAIN_ADDITION:
			builtInstance = Reconstitution.addAbstract(artifact,SemanticDomain.semanticdomain, reconstitutedId);
			break;
		case DISJUNCT_SEMANTIC_IDENTITY_SET_ADDITION:
			builtInstance = Reconstitution.addConcrete(artifact,SemanticDomain.disjunctSemanticIdentitySet, reconstitutedId);
			break;
		case ANON_DISJUNCT_SEMANTIC_IDENTITY_SET_ADDITION:
			builtInstance = Reconstitution.addConcrete(artifact,SemanticDomain.disjunctSemanticIdentitySet, reconstitutedId);
			break;
		case SEMANTIC_ROLE_ADDITION:
			builtInstance = Reconstitution.addConcrete(artifact,SemanticDomain.semanticRole, reconstitutedId);
			//build semantic role edge
			//SemanticDomainCode.addSemanticRole(result, abstractSemanticRole);
			break;
		case CONCRETE_VERTEX:
			builtInstance = InstanceBuilder.addConcreteVertex(artifact,
					metaElement, instance.getSemanticIdentity());
			break;
		}
		return builtInstance;
	}

	protected static Identity buildIdentity(final SemanticIdType identity) {
		final String name = identity.getName();
		final String pluralName = identity.getPluralName();
		final String identifier = identity.getIdentifier();
		final String URR = identity.getUniqueRepresentationReference();
		final Identity si = Reconstitution.reconstituteIdentity(name, pluralName, UUID.fromString(identifier), UUID.fromString(URR));
		if (identity.getPayload() != null) {
			si.setPayload(identity.getPayload());
		}
		return si;
	}

	protected static Set createContainer (final boolean isContainer) {
		if (isContainer) {
			return GmodelSemanticDomains.isContainer_TRUE;
		} else {
			return GmodelSemanticDomains.isContainer_FALSE;
		}
	}

	protected static Set createMaxCardinality (final String n) {
		if (n.equals(GmodelSemanticDomains.maxCardinality_0.identity().name())) {
			return GmodelSemanticDomains.maxCardinality_0;
		} else if (n.equals(GmodelSemanticDomains.maxCardinality_1.identity().name())) {
			return GmodelSemanticDomains.maxCardinality_1;
		} else if (n.equals(GmodelSemanticDomains.maxCardinality_2.identity().name())) {
			return GmodelSemanticDomains.maxCardinality_2;
		} else if (n.equals(GmodelSemanticDomains.maxCardinality_n.identity().name())) {
			return GmodelSemanticDomains.maxCardinality_n;
		} else if (n.equals(GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE.identity().name())) {
			return GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE;
		} else if (n.equals(GmodelSemanticDomains.maxCardinality_UNKNOWN.identity().name())) {
			return GmodelSemanticDomains.maxCardinality_UNKNOWN;
		} else {
			throw new java.lang.IllegalArgumentException("Non legal cardinality value");
		}
	}

	protected static Set createMinCardinality (final String n) {
		if (n.equals(GmodelSemanticDomains.minCardinality_0.identity().name())) {
			return GmodelSemanticDomains.minCardinality_0;
		} else if (n.equals(GmodelSemanticDomains.minCardinality_1.identity().name())) {
			return GmodelSemanticDomains.minCardinality_1;
		} else if (n.equals(GmodelSemanticDomains.minCardinality_2.identity().name())) {
			return GmodelSemanticDomains.minCardinality_2;
		} else if (n.equals(GmodelSemanticDomains.minCardinality_n.identity().name())) {
			return GmodelSemanticDomains.minCardinality_n;
		} else if (n.equals(GmodelSemanticDomains.minCardinality_NOTAPPLICABLE.identity().name())) {
			return GmodelSemanticDomains.minCardinality_NOTAPPLICABLE;
		} else if (n.equals(GmodelSemanticDomains.minCardinality_UNKNOWN.identity().name())) {
			return GmodelSemanticDomains.minCardinality_UNKNOWN;
		} else {
			throw new java.lang.IllegalArgumentException("Non legal cardinality value");
		}
	}

	protected static Set createNavigability (final boolean isNavigable) {
		if (isNavigable) {
			return GmodelSemanticDomains.isNavigable_TRUE;
		} else {
			return GmodelSemanticDomains.isNavigable_FALSE;
		}
	}

	public static void decommssionInstances() {
		//TODO: Kernel's decommission method does not work as intended
		removeInstances(GmodelSemanticDomains.infiniteSets, true);
		removeInstances(GmodelSemanticDomains.finiteSets, true);
		removeInstances(Root.models, true);
	}

	public static void decommssionOutdatedOuterShellInstances() {
		//TODO: Kernel's decommission method does not work as intended
		removeInstances(Root.semanticdomains, true);
		removeInstances(GmodelSemanticDomains.infiniteSets, false);
		removeInstances(GmodelSemanticDomains.finiteSets, false);
		removeInstances(Root.models, true);
	}

	protected static ObjectFactory getObjectFactory() {
		if (objectFactory == null) {
			objectFactory = new ObjectFactory();
		}
		return objectFactory;
	}

	protected static Set instantiateAbstractArtifact(
			final Set artifact, final Set metaElement, final SemanticIdType identity) {
		final Identity si = buildIdentity(identity);
		return Reconstitution.instantiateAbstract(metaElement, si);
	}

	protected static Set instantiateConcreteArtifact(
			final Set artifact, final Set metaElement, final SemanticIdType identity) {
		final Identity si = buildIdentity(identity);
		return Reconstitution.instantiateConcrete(metaElement, si);
	}

	protected static Set instantiateSemanticDomain(final SemanticIdType identity) {
		final Identity si = buildIdentity(identity);
		return Reconstitution.instantiateSemanticDomain(si);
	}

	public static boolean IsSemanticDomainTopInstances(final Set set) {
		return set.identity().isEqualToRepresentation(GmodelSemanticDomains.finiteSets.identity()) ||
		set.identity().isEqualToRepresentation(GmodelSemanticDomains.infiniteSets.identity());
	}

	public static boolean isSerializableInstance(final Set set) {
		return !set.identity().isPartOfKernel() ||
		set.identity().isEqualToRepresentation(Root.universalartifactengineering.identity()) ||
		set.identity().isEqualToRepresentation(Root.semanticdomains.identity()) ||
		set.identity().isEqualToRepresentation(Root.models.identity()) ||
		set.container().identity().isEqualToRepresentation(Root.models.identity());
	}

	public static boolean isTopSemanticDomainSet(final Set set) {
		return set.identity().isEqualToRepresentation(Root.semanticdomains.identity());
	}

	/**
	 * Map a given Gmodel identity to a serialization identity type
	 * @param identity Gmodel Identity
	 * @return SemanticIdType Serialization identity Type
	 */
	protected static SemanticIdType mapSemanticIdentity(final Identity identity) {
		final SemanticIdType semanticId = getObjectFactory().createSemanticIdType();
		semanticId.setName(identity.name());
		semanticId.setPluralName(identity.pluralName());
		semanticId.setIdentifier(identity.identifier().toString());
		semanticId.setUniqueRepresentationReference(identity.uniqueRepresentationReference().toString());
		if (identity.name().equals(ANONYMOUS_ID_NAME)) {
			semanticId.setIsAnonymous(true);
		}
		if (identity.technicalName() != null) {
			semanticId.setTechnicalName(identity.technicalName());
		}
		if (identity.payload() != null) {
			semanticId.setPayload(identity.payload());
		}

		return semanticId;
	}

	/**
	 * Map a given serialization identity type to a newly created a serialization identity type 1:1
	 * @param identity Serialization Identity
	 * @return SemanticIdType A newly created SemanticIdType which is 1:1 mapped to the given serialization identity
	 */
	protected static SemanticIdType mapSemanticIdentity(final SemanticIdType identity) {
		final SemanticIdType semanticId = getObjectFactory().createSemanticIdType();
		semanticId.setName(identity.getName());
		semanticId.setPluralName(identity.getPluralName());
		semanticId.setIdentifier(identity.getIdentifier());
		semanticId.setUniqueRepresentationReference(identity.getUniqueRepresentationReference());
		if (identity.getName().equals(InstanceBuilder.ANONYMOUS_ID_NAME)) {
			semanticId.setIsAnonymous(true);
		}
		if (identity.getTechnicalName() != null) {
			semanticId.setTechnicalName(identity.getTechnicalName());
		}
		if (identity.getPayload() != null) {
			semanticId.setPayload(identity.getPayload());
		}
		return semanticId;
	}

	protected static boolean hasSemanticError(final Set set, final Identity identity) {
		if (set.identity().isEqualToRepresentation(identity)) {
			return false;
		} else {
			return true;
		}
	}

	private static void removeInstances(final Set set, final boolean outershellOnlyDeletion) {
		final Iterator<Set> itr =set.filterInstances().iterator();
		while(itr.hasNext()) {
			final Set s = itr.next();
			if (outershellOnlyDeletion) {
				if (!s.identity().isPartOfKernel()) {
					itr.remove();
				}
			} else {
				if (!s.identity().isEqualTo(GmodelSemanticDomains.gmodel.identity())) {
					itr.remove();
				}
			}
		}
	}


}
