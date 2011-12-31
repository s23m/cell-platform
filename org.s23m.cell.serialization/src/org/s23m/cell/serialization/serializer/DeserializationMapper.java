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

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.api.models.SemanticDomain.semanticRole_to_equivalenceClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.naming.OperationNotSupportedException;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.serializerinterface.Reconstitution;
import org.s23m.cell.serialization.EdgeType;
import org.s23m.cell.serialization.EdgeType.EdgeEnd;
import org.s23m.cell.serialization.Gmodel;
import org.s23m.cell.serialization.Gmodel.Instance;
import org.s23m.cell.serialization.InstanceType;
import org.s23m.cell.serialization.SemanticIdType;
import org.s23m.cell.serialization.SuperSetReferenceType;
import org.s23m.cell.serialization.ValueType;
import org.s23m.cell.serialization.VariableType;
import org.s23m.cell.serialization.VisibilityType;

public class DeserializationMapper {

	private static final int ROOT_INDEX = 0;

	private final InstanceMap instanceMap;

	public DeserializationMapper() {
		this.instanceMap = InstanceMap.getInstance();
	}

	private void createEdge(final EdgeType edge) {

		final Identity edgeId = Reconstitution.reconstituteIdentity(edge.getSemanticIdentity().getName(), edge.getSemanticIdentity().getPluralName(),
				UUID.fromString(edge.getSemanticIdentity().getIdentifier()), UUID.fromString(edge.getSemanticIdentity().getUniqueRepresentationReference()));
		final EdgeEnd ee1 = edge.getEdgeEnd().get(0);
		final EdgeEnd ee2 = edge.getEdgeEnd().get(1);

		if (instanceMap.getBuiltInstance(edge.getMetaElement()) == null ||
				instanceMap.getBuiltInstance(ee1.getInstanceId()) == null ||
				instanceMap.getBuiltInstance(ee2.getInstanceId()) == null) {
			if(isSemanticRoleEdgeType(edge)) {
				instanceMap.addToBeBuiltSemanticRoleEdges(edge);
			} else {
				instanceMap.addToBeBuiltEdges(edge);
			}
			System.err.println("!Missing edge "+edge.getSemanticIdentity().getUniqueRepresentationReference());
		}
		else {
			// Jorn: no need for a special case
			// TODO: Chul to confirm
			// if (isSemanticRoleEdgeType(edge)) {

			/*		final Set semanticRoleEdge = Reconstitution.reconstituteLink(semanticRole_to_equivalenceClass.identity(),
						edgeId,
						org.s23m.cell.impl.F_SemanticStateOfInMemoryModel.reuseSemanticIdentity(F_SemanticStateOfInMemoryModel.coreSets.referencingSemanticRole.identity(),
						instanceMap.getBuiltInstance(ee1.getInstanceId()).identity(),
						coreSets.minCardinality_NOTAPPLICABLE.identity(),
						coreSets.maxCardinality_NOTAPPLICABLE.identity(),
						coreSets.maxCardinality_NOTAPPLICABLE.identity(),
						coreSets.isContainer_FALSE.identity(),
						org.s23m.cell.impl.F_SemanticStateOfInMemoryModel.reuseSemanticIdentity(F_SemanticStateOfInMemoryModel.coreSets.referencingSemanticRole.identity(),
						instanceMap.getBuiltInstance(ee2.getInstanceId()).identity(),
						coreSets.minCardinality_NOTAPPLICABLE.identity(),
						coreSets.maxCardinality_NOTAPPLICABLE.identity(),
						coreSets.maxCardinality_NOTAPPLICABLE.identity(),
						coreSets.isContainer_FALSE.identity());
				if (InstanceBuilder.hasSemanticError(semanticRoleEdge, edgeId)) {
					System.err.println("Got semantic error");
					instanceMap.addToBeBuiltEdges(edge);
				} else {
					System.err.println("Created Semantic Role Edge "+semanticRoleEdge.identity().name());
					instanceMap.addToInstanceMap(edge.getSemanticIdentity().getUniqueRepresentationReference(), semanticRoleEdge);
				} */

			// Jorn: no need for a special case
			// TODO: Chul to confirm
			// } else {
			final Set newEdge = org.s23m.cell.api.serializerinterface.Reconstitution.reconstituteLink(
					instanceMap.getBuiltInstance(edge.getMetaElement()).identity(),
					InstanceBuilder.buildIdentity(edge.getSemanticIdentity()),
					InstanceBuilder.buildIdentity(ee1.getSemanticIdentity()),
					instanceMap.getBuiltInstance(ee1.getInstanceId()).identity(),
					InstanceBuilder.createMinCardinality(ee1.getMinCardinality()).identity(),
					InstanceBuilder.createMaxCardinality(ee1.getMaxCardinality()).identity(),
					InstanceBuilder.createNavigability(ee1.isIsNavigable()).identity(),
					InstanceBuilder.createContainer(ee1.isIsContainer()).identity(),
					InstanceBuilder.buildIdentity(ee2.getSemanticIdentity()),
					instanceMap.getBuiltInstance(ee2.getInstanceId()).identity(),
					InstanceBuilder.createMinCardinality(ee2.getMinCardinality()).identity(),
					InstanceBuilder.createMaxCardinality(ee2.getMaxCardinality()).identity(),
					InstanceBuilder.createNavigability(ee2.isIsNavigable()).identity(),
					InstanceBuilder.createContainer(ee2.isIsContainer()).identity());
			if (InstanceBuilder.hasSemanticError(newEdge, InstanceBuilder.buildIdentity(edge.getSemanticIdentity()))) {
				System.err.println("Got semantic error "+newEdge.identity());
				instanceMap.addToBeBuiltEdges(edge);
			} else {
				System.err.println("Created Edge "+newEdge.identity().name());
				instanceMap.addToInstanceMap(edge.getSemanticIdentity().getUniqueRepresentationReference(), newEdge);
			}
			// Jorn: no need for a special case
			// TODO: Chul to confirm
			//}
		}
	}

	private boolean isSemanticRoleEdgeType(final EdgeType edge) {
		return edge.getMetaElement().equals(semanticRole_to_equivalenceClass.identity().uniqueRepresentationReference().toString());
	}

	private void createSuperReference(final SuperSetReferenceType superRef) {
		if (!instanceMap.isBuiltInstance(superRef.getId())) {
			if (instanceMap.isBuiltInstance(superRef.getSubSetInstance())
					&& instanceMap.isBuiltInstance(superRef.getSuperSetInstance())) {
				final SemanticIdType sId = superRef.getSemanticIdentity();
				final Identity id = Reconstitution.reconstituteIdentity(sId.getName(), sId.getPluralName(), UUID.fromString(sId.getIdentifier()), UUID.fromString(sId.getUniqueRepresentationReference()));
				final Identity subSetId = Reconstitution.reconstituteIdentity("", "", UUID.fromString(superRef.getSubSetInstance()), UUID.fromString(superRef.getSubSetInstance()));
				final Identity superSetId = Reconstitution.reconstituteIdentity("", "", UUID.fromString(superRef.getSuperSetInstance()), UUID.fromString(superRef.getSuperSetInstance()));
				final Set sRef = Reconstitution.reconstituteLink(coreGraphs.superSetReference.identity(),
						id,
						subSetId,
						superSetId);
				if (InstanceBuilder.hasSemanticError(sRef, id)) {
					System.err.println("Got semantic error "+sRef.identity());
					instanceMap.addToBeBuiltSuperReferences(superRef);
				}
				instanceMap.addToInstanceMap(superRef.getId(), sRef);
			} else {
				instanceMap.addToBeBuiltSuperReferences(superRef);
			}
		}
	}

	private void createVisibility(final VisibilityType v) {
		if (!instanceMap.isBuiltInstance(v.getId())) {
			if (instanceMap.isBuiltInstance(v.getSourceInstance())
					&& instanceMap.isBuiltInstance(v.getTargetInstance())) {
				final SemanticIdType sId =  v.getSemanticIdentity();
				final Identity id = Reconstitution.reconstituteIdentity(sId.getName(), sId.getPluralName(), UUID.fromString(sId.getIdentifier()), UUID.fromString(sId.getUniqueRepresentationReference()));
				final Identity sourceId = Reconstitution.reconstituteIdentity("", "", UUID.fromString(v.getSourceInstance()), UUID.fromString(v.getSourceInstance()));
				final Identity targeId = Reconstitution.reconstituteIdentity("", "", UUID.fromString(v.getTargetInstance()), UUID.fromString(v.getTargetInstance()));
				final Set vLink = Reconstitution.reconstituteLink(coreGraphs.visibility.identity(),
						id,
						sourceId,
						targeId);
				if (InstanceBuilder.hasSemanticError(vLink, id)) {
					System.err.println("Got semantic error "+vLink.identity());
					instanceMap.addToBeBuiltVisibilities(v);
				} else {
					instanceMap.addToInstanceMap(v.getId(), vLink);
				}
			} else {
				instanceMap.addToBeBuiltVisibilities(v);
			}
		}
	}

	protected void deserializeRootModels(final Map<String, Gmodel> artifacts) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("");
	}

	/**
	 * Deserialize all instances of the map
	 * @param artifacts map
	 */
	protected void deserializeInstances(final Map<String, Gmodel> artifacts) {
		//create a depth first list of the containment tree instances
		if (artifacts.containsKey(Root.models.identity().uniqueRepresentationReference().toString())) {
			final List<Gmodel> instances = new ArrayList<Gmodel>();
			createDepthFirstTreeList(instances, artifacts, Root.models.identity().uniqueRepresentationReference().toString());
			for (final Gmodel modelsArtifact : instances) {
				final Instance model = modelsArtifact.getInstance().get(ROOT_INDEX);
				final String name = model.getSemanticIdentity().getName();

				deserializeModelsInstances(model, artifacts, false);
			}
		} else {
			for (final Entry<String, Gmodel> e : artifacts.entrySet()) {
				final Gmodel modelsArtifact = e.getValue();
				final Instance model = modelsArtifact.getInstance().get(ROOT_INDEX);
				final String name = model.getSemanticIdentity().getName();

				deserializeModelsInstances(model, artifacts, false);
			}
		}

		final List<String> errors = InstanceMap.getInstance().getSemanticErrors();
		for(final String s : errors) {
			System.err.println("Error: "+s);
		}
	}

	private void createDepthFirstTreeList(final List<Gmodel> instances, final Map<String, Gmodel> artifacts, final String rootId) {
		if (artifacts.containsKey(rootId)) {
			final Gmodel instance = artifacts.get(rootId);
			instances.add(instance);
			final Instance model = instance.getInstance().get(ROOT_INDEX);
			for (final InstanceType containedInstance : model.getInstance()) {
				createDepthFirstTreeList(instances, artifacts, containedInstance.getSemanticIdentity().getUniqueRepresentationReference());
			}
		}
	}

	private void deserializeModelsInstances(final Instance instance, final Map<String, Gmodel> artifacts, final boolean isRecursive) {

		createLinks(instance);
		if (!instanceMap.isBuiltInstance(instance.getSemanticIdentity()) &&
				instanceMap.isBuiltInstance(instance.getArtifact()) &&
				instanceMap.existMetaInstances(instance)) {
			final Set metaElement = instanceMap.getBuiltInstance(instance
					.getMetaElement());
			final Set artifact = instanceMap.getBuiltInstance(instance
					.getArtifact());
			final String name = instance.getSemanticIdentity().getName();
			final String pluralName = instance.getSemanticIdentity().getPluralName();
			final String id = instance.getSemanticIdentity().getIdentifier();
			final String urr = instance.getSemanticIdentity().getUniqueRepresentationReference();
			final Identity reconstitutedId = Reconstitution.reconstituteIdentity(name, pluralName, UUID.fromString(id), UUID.fromString(urr));
			final String payLoad = (instance.getSemanticIdentity().getPayload() == null) ? "" : instance.getSemanticIdentity().getPayload();
			if (!payLoad.equals("")) {
				reconstitutedId.setPayload(payLoad);
			}

			final Set builtInstance = InstanceBuilder.build(instance, metaElement, artifact, reconstitutedId);

			if (builtInstance != null && !InstanceBuilder.hasSemanticError(builtInstance, reconstitutedId)) {
				instanceMap.addToInstanceMap(instance.getSemanticIdentity().getUniqueRepresentationReference(), builtInstance);
			} else {
				System.err.println(">> Semantic Error, failed to build "+builtInstance.identity());
			}
			createVariables(instance, builtInstance);
			createValues(instance, builtInstance);
		} else {
			instanceMap.addToBeBuiltInstances(instance); //add to the to-be-built list
		}
		if (isRecursive) {
			for (final InstanceType i : instance.getInstance()) {
				final String uuid = i.getSemanticIdentity().getUniqueRepresentationReference();
				deserializeModelsInstances(artifacts.get(uuid).getInstance().get(ROOT_INDEX), artifacts, isRecursive);
			}
		}
	}

	private void createValues(final Instance instance, final Set builtInstance) {
		if (instance.getValues() != null) {
			for (final ValueType value : instance.getValues().getValue()) {
				final String valId = value.getValuePair().getElement()
				.getUniqueRepresentationReference();
				if (!isAbstractValuePair(value)) {
					if (instanceMap.isBuiltInstance(valId)) {
						builtInstance.addToValues(instanceMap
								.getBuiltInstance(valId));
					} else {
						instanceMap.addToBeBuiltValues(instance.getId(), value);
					}
				}
			}
		}
	}

	private void createVariables(final Instance instance,
			final Set builtInstance) {
		if (instance.getVariables() != null) {
			for (final VariableType var : instance.getVariables().getVariable()) {
				final String varId = var.getVariablePair().getElement()
				.getUniqueRepresentationReference();
				if (instanceMap.isBuiltInstance(varId)) {
					builtInstance.addToVariables(instanceMap
							.getBuiltInstance(varId));
				} else {
					instanceMap.addToBeBuiltVariables(instance.getId(), var);
				}
			}
		}
	}

	private void createLinks(final Instance instance) {
		if (instance.getLink() != null) {
			for (final Object obj : instance.getLink().getVisibilityAndEdgeAndEdgeTrace()) {
				if (obj instanceof VisibilityType) {
					createVisibility((VisibilityType) obj);
				} else if (obj instanceof SuperSetReferenceType) {
					final SuperSetReferenceType superRef = (SuperSetReferenceType) obj;
					createSuperReference(superRef);
				} else if (obj instanceof EdgeType) {
					final EdgeType edge = (EdgeType) obj;
					if (!instanceMap.isBuiltInstance(edge.getSemanticIdentity())) {
						createEdge(edge);
					}
				}
			}
		}
	}

	protected  void deserializeSemanticDomains(final Map<String, Gmodel> artifacts) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("Seperate method to deserialize semantic domain instance is not supported.");
	}

	private boolean isAbstractValuePair(final ValueType value) {
		return value.getValuePair().getMetaElement().getName().equals(
				GmodelSemanticDomains.isAbstract.identity().name());
	}

}
