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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import static org.s23m.cell.api.models.SemanticDomain.semanticRole_to_equivalenceClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.map.MultiValueMap;
import org.s23m.cell.S23MKernel;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.serializerinterface.Reconstitution;
import org.s23m.cell.serialization.EdgeType;
import org.s23m.cell.serialization.EdgeType.EdgeEnd;
import org.s23m.cell.serialization.S23M.Instance;
import org.s23m.cell.serialization.InstanceType;
import org.s23m.cell.serialization.SemanticIdType;
import org.s23m.cell.serialization.SuperSetReferenceType;
import org.s23m.cell.serialization.ValueType;
import org.s23m.cell.serialization.VariableType;
import org.s23m.cell.serialization.VisibilityType;

public final class InstanceMap {

	private final Map<String, Set> builtIntances;

	private final Map<String, Set> innerShellInstances;

	private final MultiValueMap instancesToBuild;

	private final MultiValueMap edgesToBuild;

	private final MultiValueMap semanticRoleEdgesToBuild;

	private final MultiValueMap superReferencesToBuild;

	private final MultiValueMap valuesToBuild;

	private final MultiValueMap variablesToBuild;

	private final MultiValueMap visibilitiesToBuild;

	private final List<String> semanticErrors;

	private static class InstanceMapHolder {
		public static final InstanceMap INSTANCE = new InstanceMap();
	}

	public static InstanceMap getInstance() {
		return InstanceMapHolder.INSTANCE;
	}

	private InstanceMap() {
		builtIntances = new HashMap<String, Set>();
		innerShellInstances = new HashMap<String, Set>();
		InstanceBuilder.addInnershellInstancesInMemory(innerShellInstances);
		instancesToBuild = MultiValueMap.decorate(new HashMap<String, List<Instance>>());
		visibilitiesToBuild = MultiValueMap.decorate(new HashMap<String, List<VisibilityType>>());
		valuesToBuild = MultiValueMap.decorate(new HashMap<String, List<MapValue>>());
		variablesToBuild = MultiValueMap.decorate(new HashMap<String, List<MapValue>>());
		edgesToBuild = MultiValueMap.decorate(new HashMap<String, List<EdgeType>>());
		semanticRoleEdgesToBuild = MultiValueMap.decorate(new HashMap<String, List<EdgeType>>());
		superReferencesToBuild = MultiValueMap.decorate(new HashMap<String, List<SuperSetReferenceType>>());
		semanticErrors = new ArrayList<String>();
	}

	protected void addToBeBuiltEdges(final EdgeType edge) {
		if (!isBuiltInstance(edge.getMetaElement())) {
			addToEdgesToBuild(edge.getMetaElement(), edge);
		}
		if (!isBuiltInstance(edge.getEdgeEnd().get(0).getInstanceId())) {
			addToEdgesToBuild(edge.getEdgeEnd().get(0).getInstanceId(), edge);
		}
		if (!isBuiltInstance(edge.getEdgeEnd().get(1).getInstanceId())) {
			addToEdgesToBuild(edge.getEdgeEnd().get(1).getInstanceId(), edge);
		}
	}

	protected void addToBeBuiltSemanticRoleEdges(final EdgeType edge) {
		if (!isBuiltInstance(edge.getEdgeEnd().get(0).getInstanceId())) {
			addToSemanticRoleEdgesToBuild(edge.getEdgeEnd().get(0)
					.getInstanceId(), edge);
		}
		if (!isBuiltInstance(edge.getEdgeEnd().get(1).getInstanceId())) {
			addToSemanticRoleEdgesToBuild(edge.getEdgeEnd().get(1)
					.getInstanceId(), edge);
		}
	}

	private void addToSemanticRoleEdgesToBuild(final String instanceId,
			final EdgeType edge) {
		semanticRoleEdgesToBuild.put(instanceId, edge);
	}

	protected void addToBeBuiltInstances(final Instance instance) {
		if (!isBuiltInstance(instance.getMetaElement())) {
			putToInstancesToBuild(instance.getMetaElement(), instance);
		}
		if (!isBuiltInstance(instance.getArtifact())) {
			putToInstancesToBuild(instance.getArtifact(), instance);
		}
	}

	protected void addToBeBuiltSuperReferences(
			final SuperSetReferenceType superReference) {
		if (!isBuiltInstance(superReference.getSubSetInstance())) {
			addToReferencesToBuild(superReference.getSubSetInstance(),
					superReference);
		}
		if (!isBuiltInstance(superReference.getSuperSetInstance())) {
			addToReferencesToBuild(superReference.getSuperSetInstance(),
					superReference);
		}
	}

	protected void addToBeBuiltValues(final String hostInstanceId,
			final ValueType value) {
		final String valElementId = value.getValuePair().getElement()
		.getIdentifier();
		final String valMetaElementId = value.getValuePair().getMetaElement()
		.getIdentifier();
		if (!isBuiltInstance(valElementId)) {
			addToValuesToBuild(valElementId, hostInstanceId, value);
		}
		if (isBuiltInstance(valMetaElementId)) {
			addToValuesToBuild(valMetaElementId, hostInstanceId, value);
		}
	}

	protected void addToBeBuiltVariables(final String hostInstanceId,
			final VariableType variable) {
		final String varElementId = variable.getVariablePair().getElement()
		.getIdentifier();
		final String varMetaElementId = variable.getVariablePair()
		.getMetaElement().getIdentifier();
		if (!isBuiltInstance(varElementId)) {
			addToVariablesToBuild(varElementId, hostInstanceId, variable);
		}
		if (!isBuiltInstance(varMetaElementId)) {
			addToVariablesToBuild(varMetaElementId, hostInstanceId, variable);
		}
	}

	protected void addToBeBuiltVisibilities(final VisibilityType visibility) {
		if (!isBuiltInstance(visibility.getSourceInstance())) {
			addToVisibilitiesToBuild(visibility.getSourceInstance(), visibility);
		}
		if (!isBuiltInstance(visibility.getTargetInstance())) {
			addToVisibilitiesToBuild(visibility.getTargetInstance(), visibility);
		}
	}

	private void addToEdgesToBuild(final String missingSetId,
			final EdgeType edge) {
		edgesToBuild.put(missingSetId, edge);
	}

	public void addToInstanceMap(final String id, final Set instance) {
		System.err.println("@MAP: Added to the instance map "
				+ instance.identity().name() + " " + id);
		if (builtIntances.containsKey(id)) {
			builtIntances.remove(id);
		}

		builtIntances.put(id, instance);
		// see if there are instances that need the instance
		if (visibilitiesToBuild.containsKey(id)) {
			buildUnBuiltVisibilities(id);
		}

		if (superReferencesToBuild.containsKey(id)) {
			buildUnBuiltSuperReferences(id);
		}

		if (instancesToBuild.containsKey(id)) {
			buildUnBuiltInstances(id);
		}

		if (edgesToBuild.containsKey(id)) {
			buildUnBuiltEdges(id);
		}

		if (semanticRoleEdgesToBuild.containsKey(id)) {
			buildUnBuiltSemanticRoleEdges(id);
		}

		if (variablesToBuild.containsKey(id)) {
			buildUnBuiltVariables(id);
		}

		if (valuesToBuild.containsKey(id)) {
			buildUnBuiltValues(id);
		}
	}

	private void addToReferencesToBuild(final String missingInstanceId,
			final SuperSetReferenceType superReference) {
		superReferencesToBuild.put(missingInstanceId, superReference);
	}

	private void addToValuesToBuild(final String missingInstanceId,
			final String hostInstanceId, final ValueType value) {
		valuesToBuild.put(missingInstanceId, new MapValue(value.getValuePair(),
				hostInstanceId));
	}

	private void addToVariablesToBuild(final String missingInstanceId,
			final String hostInstanceId, final VariableType variable) {
		variablesToBuild.put(missingInstanceId,
				new MapValue(variable.getVariablePair(), hostInstanceId));
	}

	private void addToVisibilitiesToBuild(final String missingInstanceId,
			final VisibilityType visibility) {
		visibilitiesToBuild.put(missingInstanceId, visibility);
	}

	private boolean buildEdge(final EdgeType edge, final boolean isRetry) {
		if (isBuiltInstance(edge.getSemanticIdentity()
				.getUniqueRepresentationReference())) {
			return true;
		} else if (isBuiltInstance(edge.getMetaElement())
				&& (isBuiltInstance(edge.getEdgeEnd().get(0).getInstanceId()))
				&& (isBuiltInstance(edge.getEdgeEnd().get(1).getInstanceId()))) {
			final EdgeEnd ee1 = edge.getEdgeEnd().get(0);
			final EdgeEnd ee2 = edge.getEdgeEnd().get(1);
			final Set sourceInstance = getBuiltInstance(ee1.getInstanceId());
			final Set targetInstance = getBuiltInstance(ee2.getInstanceId());

			if (edge.getMetaElement().equals(
					semanticRole_to_equivalenceClass.identity()
					.uniqueRepresentationReference().toString())) {
				final Identity edgeId = Reconstitution.reconstituteIdentity(edge
						.getSemanticIdentity().getName(), edge
						.getSemanticIdentity().getPluralName(),
						UUID.fromString(edge.getSemanticIdentity()
								.getIdentifier()), UUID.fromString(edge
										.getSemanticIdentity()
										.getUniqueRepresentationReference()));
				final Identity ee1Id = Reconstitution.reconstituteIdentity(ee1
						.getSemanticIdentity().getName(), ee1
						.getSemanticIdentity().getPluralName(), UUID
						.fromString(ee1.getSemanticIdentity().getIdentifier()),
						UUID.fromString(ee1.getSemanticIdentity()
								.getIdentifier()));
				final Identity ee2Id = Reconstitution.reconstituteIdentity(ee2
						.getSemanticIdentity().getName(), ee2
						.getSemanticIdentity().getPluralName(), UUID
						.fromString(ee2.getSemanticIdentity().getIdentifier()),
						UUID.fromString(ee2.getSemanticIdentity()
								.getIdentifier()));
				final Set ee1I = Reconstitution.getSetFromLocalMemory(ee1Id);
				final Set ee2I = Reconstitution.getSetFromLocalMemory(ee1Id);
				final Identity idRef = S23MSemanticDomains.referencingSemanticRole.identity();
				final Set i = Reconstitution.getSetFromLocalMemory(idRef);

				final Set semanticRoleEdge = Reconstitution.reconstituteLink(
						semanticRole_to_equivalenceClass.identity(),
						edgeId, ee1Id, sourceInstance.identity(),
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE.identity(),
						S23MSemanticDomains.maxCardinality_NOTAPPLICABLE.identity(),
						S23MSemanticDomains.isNavigable_FALSE.identity(),
						S23MSemanticDomains.isContainer_FALSE.identity(), ee2Id,
						targetInstance.identity(),
						S23MSemanticDomains.minCardinality_NOTAPPLICABLE.identity(),
						S23MSemanticDomains.maxCardinality_NOTAPPLICABLE.identity(),
						S23MSemanticDomains.isNavigable_FALSE.identity(),
						S23MSemanticDomains.isContainer_FALSE.identity());

				if (InstanceBuilder.hasSemanticError(semanticRoleEdge, edgeId)) {
					System.err.println("Semantic error");
				}
				addToInstanceMap(semanticRoleEdge.identity()
						.uniqueRepresentationReference().toString(),
						semanticRoleEdge);
				System.err.println(">> Created Semantic Role Edge "
						+ edge.getSemanticIdentity()
						.getUniqueRepresentationReference());
				return true;
			} else {
				final Set newEdge = org.s23m.cell.api.serializerinterface.Reconstitution.reconstituteLink
				(getBuiltInstance(edge.getMetaElement()).identity(),
						InstanceBuilder.buildIdentity(edge.getSemanticIdentity()),
						InstanceBuilder.buildIdentity(ee1
								.getSemanticIdentity()),
								sourceInstance.identity(), InstanceBuilder
								.createMinCardinality(ee1.getMinCardinality()).identity(),
								InstanceBuilder.createMaxCardinality(ee1.getMaxCardinality()).identity(),
								InstanceBuilder.createNavigability(ee1
										.isIsNavigable()).identity(),
										InstanceBuilder.createContainer(ee1.isIsContainer()).identity(),
										InstanceBuilder.buildIdentity(ee2.getSemanticIdentity()),
										targetInstance.identity(),
										InstanceBuilder.createMinCardinality(ee2.getMinCardinality()).identity(),
										InstanceBuilder.createMaxCardinality(ee2.getMaxCardinality()).identity(),
										InstanceBuilder.createNavigability(ee2.isIsNavigable()).identity(),
										InstanceBuilder.createContainer(ee2.isIsContainer()).identity());

				if (InstanceBuilder.hasSemanticError(newEdge, InstanceBuilder.buildIdentity(edge.getSemanticIdentity()))) {
					return false;
				} else {
					addToInstanceMap(newEdge.identity()
							.uniqueRepresentationReference().toString(),
							newEdge);
					return true;
				}
			}
		} else {
			return false;
		}
	}

	private boolean buildSuperReference(final SuperSetReferenceType sRef) {
		if (isBuiltInstance(sRef.getId())) {
			return true;
		} else if (isBuiltInstance(sRef.getSubSetInstance())
				&& isBuiltInstance(sRef.getSuperSetInstance())) {
			final Identity si = Reconstitution.reconstituteIdentity(sRef
					.getSemanticIdentity().getName(), sRef
					.getSemanticIdentity().getPluralName(), UUID
					.fromString(sRef.getSemanticIdentity().getIdentifier()),
					UUID.fromString(sRef.getSemanticIdentity()
							.getUniqueRepresentationReference()));
			final Set set = Reconstitution.reconstituteLink(
					S23MKernel.coreGraphs.superSetReference.identity(), si,
					getBuiltInstance(sRef.getSubSetInstance()).identity(),
					getBuiltInstance(sRef.getSuperSetInstance()).identity());
			if (InstanceBuilder.hasSemanticError(set, si)) {
				semanticErrors.add("SSR errors: "+si.name()+","+set.identity().name());
			}
			addToInstanceMap(sRef.getId(), set);
			return true;
		} else {
			return false;
		}
	}

	private <T> void buildUnBuiltAttributes(final String missingInstanceId,
			final Map<String, List<MapValue>> toBuildMap, final T attributeClass) {
		final List<MapValue> lst = toBuildMap.get(missingInstanceId);
		final Iterator<MapValue> itr = lst.iterator();
		while (itr.hasNext()) {
			final MapValue value = itr.next();
			final String elementId = value.getOrderedPair().getElement()
			.getIdentifier();
			final String metaElementId = value.getOrderedPair()
			.getMetaElement().getIdentifier();
			if (isBuiltInstance(elementId) && isBuiltInstance(metaElementId)) {
				final Set hostInstance = getBuiltInstance(value
						.getHostInstanceId());
				if (attributeClass.equals(ValueType.class)) {
					hostInstance.addToValues(getBuiltInstance(elementId));
				} else {
					hostInstance.addToVariables(getBuiltInstance(elementId));
				}
			}
		}
		if (toBuildMap.get(missingInstanceId).isEmpty()) {
			toBuildMap.remove(missingInstanceId);
		}
	}

	private void buildUnBuiltEdges(final String id) {
		final List<EdgeType> edgesList = (List<EdgeType>) edgesToBuild.getCollection(id);
		final List<EdgeType> toRemove = new ArrayList<EdgeType>();

		for (final EdgeType edge : edgesList) {
			if (buildEdge(edge, false)) {
				toRemove.add(edge);
			}
		}

		for (final EdgeType edgeToRemove : toRemove) {
			edgesToBuild.remove(id, edgeToRemove);
		}
	}

	private void buildUnBuiltSemanticRoleEdges(final String id) {
		final List<EdgeType> edgesList = (List<EdgeType>) semanticRoleEdgesToBuild.getCollection(id);
		final List<EdgeType> toRemove = new ArrayList<EdgeType>();

		for (final EdgeType edge : edgesList) {
			if (buildEdge(edge, false)) {
				toRemove.add(edge);
			}
		}

		for (final EdgeType edgeToRemove : toRemove) {
			semanticRoleEdgesToBuild.remove(id, edgeToRemove);
		}
	}

	private boolean buildUnBuiltInstance(final Instance instance) {
		if (!isBuiltInstance(instance.getSemanticIdentity())
				&& existMetaInstances(instance)) {
			final Set metaElement = getBuiltInstance(instance.getMetaElement());
			final Set artifact = getBuiltInstance(instance.getArtifact());
			final String name = instance.getSemanticIdentity().getName();
			final String pluralName = instance.getSemanticIdentity()
			.getPluralName();
			final String id = instance.getSemanticIdentity().getIdentifier();
			final String urr = instance.getSemanticIdentity()
			.getUniqueRepresentationReference();
			final Identity reconstitutedId = Reconstitution
			.reconstituteIdentity(name, pluralName,
					UUID.fromString(id), UUID.fromString(urr));
			if (metaElement != null && artifact != null) {
				final Set builtInstance = InstanceBuilder.build(instance,
						metaElement, artifact, reconstitutedId);
				try {
					if (builtInstance
							.identity()
							.uniqueRepresentationReference()
							.toString()
							.equals(instance.getSemanticIdentity()
									.getUniqueRepresentationReference())) {
						addToInstanceMap(instance.getSemanticIdentity()
								.getUniqueRepresentationReference(),
								builtInstance);
						return true;
					} else {
						semanticErrors.add("Vertex Error: "+instance.getSemanticIdentity().getName()
								+ ","+ builtInstance.identity().name());
						if (builtInstance.identity().name().trim().startsWith(
						"addConcrete(metaproperty, semanticIdentity)")) {
							final Set mElement = getBuiltInstance(instance
									.getMetaElement());
							Instantiation.arrow(S23MKernel.coreGraphs.superSetReference, mElement,
									S23MKernel.coreGraphs.vertex);
							buildUnBuiltInstance(instance);
						}
						return false;
					}
				} catch (final Exception ex) {
					return false;
				}
			}
		}
		return false;
	}

	public List<String> getSemanticErrors() {
		return semanticErrors;
	}

	@SuppressWarnings("unchecked")
	private void buildUnBuiltInstances(final String id) {
		if (instancesToBuild.containsKey(id)) {
			final List<Instance> instancesToRemove = new ArrayList<Instance>();
			final List<Instance> instances = (List<Instance>) instancesToBuild.getCollection(id);
			for (final Instance i : instances) {
				if (buildUnBuiltInstance(i)) {
					instancesToRemove.add(i);
				}
			}

			for (final Instance instanceToRemove : instancesToRemove) {
				instancesToBuild.remove(id, instanceToRemove);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void buildUnBuiltSuperReferences(final String id) {
		final List<SuperSetReferenceType> superRefList = (List<SuperSetReferenceType>) superReferencesToBuild
		.getCollection(id);
		final List<SuperSetReferenceType> superRefsToRemove = new ArrayList<SuperSetReferenceType>();
		for (final SuperSetReferenceType sRef : superRefList) {
			if (buildSuperReference(sRef)) {
				superRefsToRemove.add(sRef);
			}
		}

		for (final SuperSetReferenceType sRefToRemove : superRefsToRemove) {
			superReferencesToBuild.remove(id, sRefToRemove);
		}
	}

	@SuppressWarnings("unchecked")
	private void buildUnBuiltValues(final String id) {
		buildUnBuiltAttributes(id, valuesToBuild, ValueType.class);
	}

	@SuppressWarnings("unchecked")
	private void buildUnBuiltVariables(final String id) {
		buildUnBuiltAttributes(id, variablesToBuild, VariableType.class);
	}

	@SuppressWarnings("unchecked")
	private void buildUnBuiltVisibilities(final String id) {
		if (visibilitiesToBuild.containsKey(id)) {
			final List<VisibilityType> visibilityList = (List<VisibilityType>) visibilitiesToBuild
			.getCollection(id);
			final List<VisibilityType> visibilitiesToRemove = new ArrayList<VisibilityType>();
			for (final VisibilityType visibility : visibilityList) {
				if (buildVisibility(visibility, false)) {
					visibilitiesToRemove.add(visibility);
				}
			}

			for (final VisibilityType v : visibilitiesToRemove) {
				visibilitiesToBuild.remove(id, v);
			}
		}
	}

	private boolean buildVisibility(final VisibilityType visibility,
			final boolean isRetry) {
		if (isBuiltInstance(visibility.getId())) {
			return true;
		} else if (isBuiltInstance(visibility.getSourceInstance())
				&& isBuiltInstance(visibility.getTargetInstance())) {
			final Identity si = Reconstitution.reconstituteIdentity(visibility
					.getSemanticIdentity().getName(), visibility
					.getSemanticIdentity().getPluralName(), UUID
					.fromString(visibility.getSemanticIdentity()
							.getIdentifier()), UUID.fromString(visibility
									.getSemanticIdentity().getUniqueRepresentationReference()));
			final Set set = Reconstitution
			.reconstituteLink(S23MKernel.coreGraphs.visibility.identity(), si,
					getBuiltInstance(visibility.getSourceInstance())
					.identity(),
					getBuiltInstance(visibility.getTargetInstance())
					.identity());
			if (InstanceBuilder.hasSemanticError(set, si)) {
				semanticErrors.add("Visibility Error: "+visibility.getSemanticIdentity().getName()+","+set.identity().name());
			}
			addToInstanceMap(visibility.getId(), set);
			return true;
		} else {
			return false;
		}
	}

	private <T> boolean compareId(final T instance, final String id) {
		if (instance instanceof SuperSetReferenceType) {
			return ((SuperSetReferenceType) instance).getId().equals(id);
		} else if (instance instanceof VisibilityType) {
			return ((VisibilityType) instance).getId().equals(id);
		} else if (instance instanceof EdgeType) {
			return ((EdgeType) instance).getSemanticIdentity()
			.getUniqueRepresentationReference().equals(id);
		} else {
			return false;
		}
	}

	protected boolean existEdgeMetaInstances(final EdgeType edge) {
		final EdgeEnd firstEdge = edge.getEdgeEnd().get(0);
		final EdgeEnd secondEdge = edge.getEdgeEnd().get(1);
		return (isBuiltInstance(edge.getMetaElement())
				&& isBuiltInstance(firstEdge.getInstanceId()) && isBuiltInstance(secondEdge
						.getInstanceId()));
	}

	protected boolean existMetaInstances(final InstanceType instance) {
		final String metaInstance = instance.getMetaElement();
		if (fetchInstanceFromMemory(metaInstance, metaInstance) != null) {
			return true;
		} else {
			return false;
		}
	}

	protected Set getBuiltInstance(final String id) {
		return fetchInstanceFromMemory(id, id);
	}

	public Set fetchInstanceFromMemory(final String uuid, final String urr) {
		final Identity identity = Reconstitution.reconstituteIdentity("", "",
				UUID.fromString(uuid), UUID.fromString(urr));
		final Set set = Reconstitution.getSetFromLocalMemory(identity);
		if (!set.identity().name()
				.equals(S23MSemanticDomains.semanticErr_ThisSetIsNotAvailableInMemory.identity().name())) {
			return set;
		} else {
			// try in-memory map for non-Root instances
			if (innerShellInstances.containsKey(urr)) {
				return innerShellInstances.get(urr);
			} else {
				return null;
			}
		}
	}

	public boolean isBuiltInstance(final SemanticIdType semanticIdentity) {
		if (fetchInstanceFromMemory(semanticIdentity.getIdentifier(),
				semanticIdentity.getUniqueRepresentationReference()) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBuiltInstance(final String urr) {
		if (fetchInstanceFromMemory(urr, urr) != null) {
			return true;
		} else {
			return false;
		}
	}

	private void putToInstancesToBuild(final String missingSetId,
			final Instance instance) {
		if (!instancesToBuild.containsValue(missingSetId, instance)) {
			instancesToBuild.put(missingSetId, instance);
		}
	}

	public void removeFromBuiltInstances(final String uuid) {
		if (isBuiltInstance(uuid)) {
			builtIntances.remove(uuid);
		}
	}

	private <T> void removeInstancesFromToBeBuiltList(
			final Map<String, List<T>> toBeBuiltMap,
			final List<String> idsToRemove) {
		for (final String id : idsToRemove) {
			final List<T> toRemoveList = new ArrayList<T>();
			if (toBeBuiltMap.containsKey(id)) {
				final List<T> todoList = toBeBuiltMap.get(id);
				for (final T i : todoList) {
					if (compareId(i, id)) {
						toRemoveList.add(i);
					}
				}
				todoList.removeAll(toRemoveList);
			}
		}
	}

	public void reset() {
		builtIntances.clear(); // clear all instances and reinsert kernel
		// instances
		InstanceBuilder.addKernelInstances(builtIntances);
		instancesToBuild.clear();
		edgesToBuild.clear();
		superReferencesToBuild.clear();
		valuesToBuild.clear();
		variablesToBuild.clear();
		visibilitiesToBuild.clear();
	}

	public boolean isInnershellInstance(final String urr) {
		return innerShellInstances.containsKey(urr);
	}

}
