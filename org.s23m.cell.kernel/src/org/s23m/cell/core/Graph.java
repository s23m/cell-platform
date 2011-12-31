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
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import java.util.List;
import java.util.ListIterator;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.VisitorFunction;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.models2.Visualization;

public class Graph extends OrderedPair implements Set {

	/* Reify the Gmodel Graph concept */
	protected static final Graph graph = new Graph();
	protected static final OrderedSet inMemorySets = new OrderedSet();
	protected static final OrderedSet inMemorySemanticIdentities = new OrderedSet();
	protected static final OrderedSet changedSets = new OrderedSet();

	private boolean orderedSetsInitialized;
	private boolean executableFunctionsInitialized;

	private OrderedSet edgeFlavored;
	private OrderedSet allContainedLinkInstances;
	private OrderedSet superSetReferenceFlavored;
	private OrderedSet vertexFlavored;
	private OrderedSet visibilityFlavored;
	private OrderedSet allContainedInstances;

	private OrderedSet variables;
	private OrderedSet values;
	private OrderedSet commands;
	private OrderedSet queries;
	private OrderedSet executableCommands;
	private OrderedSet executableQueries;



	/* modification state */
	private boolean containsDecommissionedSets;
	private boolean containsNewSets;
	private boolean hasDecommissionedPayload;
	private boolean hasNewPayload;
	private boolean isDecommissioned;
	private boolean isNewInstance;
	private boolean hasNewName;
	private boolean hasNewPluralName;


	protected Graph(final Identity semanticIdentity, final Set categoryOfElement) {
		super(semanticIdentity, categoryOfElement);
	}
	protected Graph(final Identity semanticIdentity) {
		super(semanticIdentity);
	}
	private Graph() {
		super(identityFactory.graph());
		this.addToValues(coreSets.orderedPair);
		this.addToVariables(coreSets.maxCardinality);
		this.addToVariables(coreSets.isAbstract);
		this.addToValues(coreSets.isAbstract_TRUE);
		this.addFlavorQueries();
		this.addFlavorCommands();
		this.containsDecommissionedSets = false;
		this.containsNewSets = false;
		this.isDecommissioned = false;
		if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			this.isNewInstance = true;
		} else {
			this.isNewInstance = false;
		}
		this.hasNewName = false;
		this.hasNewPluralName =false;
		this.hasNewPayload = false;
		this.hasDecommissionedPayload = false;
	}
	public static void addSetToInMemorySets(final Set e) {
		if (!F_SemanticStateOfInMemoryModel.isDebugModeOn()) {
			Graph.inMemorySets.add(e);
			if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
				if (SemanticDomain.semanticdomain.isEqualTo(e.container().category())) {
					Graph.inMemorySemanticIdentities.add(e);
				}
			}
		} else {
			if (Graph.inMemorySets.containsRepresentation(e)) {
				F_Instantiation.raiseError(identityFactory.semanticErr_ASetWithThisIdentityAndRepresentationIsAlreadyLoaded(), coreSets.semanticErr);
			}
			else {
				Graph.inMemorySets.add(e);
				if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
					if (SemanticDomain.semanticdomain.isEqualTo(e.container().category())) {
						Graph.inMemorySemanticIdentities.add(e);
					}
				}
			}
		}

	}
	public static void addSetToChangedSets(final Set e) {
		Graph.changedSets.add(e);
	}
	@Override
	public String toString() {
		return this.localVisualRecognitionText();
	}

	@Override
	public Graph category() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {
			/*
			 * orderedPairCategory() duplicates category() but is essential to break recursive loop in Graph
			 * This is the only class/method in Gmodel where orderedPairCategory() must be used
			 * All other code must only use category()
			 */
			if (this.orderedPairCategory().isEqualTo(coreSets.semanticErr)) {return ((Graph) GmodelSemanticDomains.semanticErr);}
			if (this.orderedPairCategory().isEqualTo(coreSets.kernelDefect)) {return ((Graph) GmodelSemanticDomains.kernelDefect);}
		}
		return ((Graph) category);
	}

	@Override
	public Set variables() {
		this.ensureInitializedOrderedSets();
		return variables;
	}
	@Override
	public Set addToVariables(final Set set) {
		this.ensureInitializedOrderedSets();
		if (this.identity().isPartOfKernel()) {
			if (!this.variables.containsSemanticMatch(set)) {
				this.variables.add(set);
				if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
					this.containsNewSets = true;
					Graph.addSetToChangedSets(this);
				}
			}
			return coreSets.successful;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set values() {
		this.ensureInitializedOrderedSets();
		return values;
	}

	private void setValue(final Set value) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {
			if (!this.values.containsSemanticMatch(value)) {
				this.values.add(value);
				if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
					this.containsNewSets = true;
					Graph.addSetToChangedSets(this);
				}
			}
		} else {
			this.values.add(value);
			if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
				this.containsNewSets = true;
				Graph.addSetToChangedSets(this);
			}
		}
	}

	@Override
	public Set addToValues(final Set anElement) {
		// a value can only be set once, and can never be modified!
		this.ensureInitializedOrderedSets();
		if ((this.identity().isPartOfKernel() && !this.values.containsSemanticMatch(anElement))
				|| (
						(this.category().flavor().variables().containsSemanticMatch(((OrderedPair)anElement).category())
								|| this.category().variables().containsSemanticMatch(((OrderedPair)anElement).category())
								|| anElement.category().isEqualTo(coreSets.isAbstract)
								|| anElement.category().isEqualTo(coreSets.maxCardinality)
						)
						&& (!this.values.containsSemanticMatch(anElement))
				)
		) {
			this.setValue(anElement);
			return coreSets.successful;
		} else {
			if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {
				final Set theSemanticIdentity = anElement.semanticIdentity();
				for (final Set variable : this.category().flavor().variables()) {
					if (theSemanticIdentity.isElementOf(variable.semanticIdentity()).isEqualTo(coreSets.is_TRUE)) {
						this.setValue(theSemanticIdentity);
						return GmodelSemanticDomains.successful;
					}
				}
				for (final Set variable : this.category().variables()) {
					if (theSemanticIdentity.isElementOf(GmodelSemanticDomains.isAbstract).isEqualTo(coreSets.is_TRUE)
							|| theSemanticIdentity.isElementOf(GmodelSemanticDomains.maxCardinality).isEqualTo(coreSets.is_TRUE)
							|| theSemanticIdentity.isElementOf(variable.semanticIdentity()).isEqualTo(coreSets.is_TRUE)) {
						this.setValue(theSemanticIdentity);
						return GmodelSemanticDomains.successful;
					}
				}
				return F_InstantiationImpl.raiseError(coreSets.semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance.identity(), coreSets.semanticErr);
			} else {
				return F_InstantiationImpl.raiseError(coreSets.semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance.identity(), coreSets.semanticErr);
			}
		}
	}

	@Override
	public Set value(final Set variable) {
		this.ensureInitializedOrderedSets();
		for (final Set value : values) {
			if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {
				if (value.semanticIdentity().isElementOf(variable.semanticIdentity()).isEqualTo(coreSets.is_TRUE)) {
					return value;
				}
			} else {
				if (value.category().isEqualTo(variable)) {
					return value;
				}
			}
		}
		// logic for max cardinality constraint for physical containment of graphs
		if (variable.isEqualTo(coreSets.maxCardinality)) {
			return coreSets.maxCardinality_n;
		} else {
			return coreSets.semanticErr_ValueIsNotAssigned;
		}
	}

	@Override
	public Set commands() {
		this.ensureInitializedOrderedSets();
		return commands;
	}
	@Override
	public Set executableCommands() {
		this.ensureInitializedOrderedSets();
		this.ensureInitializedExecutableFunctions();
		return executableCommands;
	}


	@Override
	public Set addToCommands(final Set anElement) {
		this.ensureInitializedOrderedSets();
		if (!this.commands.containsSemanticMatch(anElement)) {
			this.commands.add(anElement);
			if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
				this.containsNewSets = true;
				Graph.addSetToChangedSets(this);
			}
		}
		return coreSets.successful;
	}


	@Override
	public Set removeFromCommands(final Set anElement) {
		this.ensureInitializedOrderedSets();
		if (!(this.container().isEqualTo(Graph.graph))) {
			this.commands.remove(anElement);
			if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
				this.containsNewSets = true;
				Graph.addSetToChangedSets(this);
			}
			return coreSets.successful;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_GraphGraphCantBeModified.identity(), coreSets.semanticErr);
		}
	}
	@Override
	public Set queries() {
		this.ensureInitializedOrderedSets();
		return queries;
	}

	private void ensureInitializedExecutableFunctions() {
		if (!executableFunctionsInitialized) {
			if (!isEqualTo(Graph.graph)) {
				// ensure that all flavor-level queries are included in the result
				addFlavorQueries();
				addFlavorCommands();
			}
			final Set superClasses = this.category().container().filterPolymorphic(this.category().container().localRootSuperSetOf(this.category()));
			for (final Set superClass : superClasses) {
				executableQueries = ((OrderedSet) executableQueries.union(superClass.queries()));
				executableCommands = ((OrderedSet) executableCommands.union(superClass.commands()));
			}
			executableFunctionsInitialized = true;
		}
	}
	@Override
	public Set executableQueries() {
		this.ensureInitializedOrderedSets();
		this.ensureInitializedExecutableFunctions();
		return executableQueries;
	}

	@Override
	public Set addToQueries(final Set anElement) {
		this.ensureInitializedOrderedSets();
		if (!this.queries.containsSemanticMatch(anElement)) {
			this.queries.add(anElement);
			if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
				this.containsNewSets = true;
				Graph.addSetToChangedSets(this);
			}
		}
		return coreSets.successful;
	}

	protected Set addToExecutableQueries(final Set anElement) {
		this.ensureInitializedOrderedSets();
		if (!this.executableQueries.containsSemanticMatch(anElement)) {
			this.executableQueries.add(anElement);
		}
		return coreSets.successful;
	}
	protected Set addToExecutableCommands(final Set anElement) {
		this.ensureInitializedOrderedSets();
		if (!this.executableCommands.containsSemanticMatch(anElement)) {
			this.executableCommands.add(anElement);
		}
		return coreSets.successful;
	}

	@Override
	public Set removeFromQueries(final Set anElement) {
		this.ensureInitializedOrderedSets();
		if (!(this.container().isEqualTo(Graph.graph))) {
			this.queries.remove(anElement);
			if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
				this.containsNewSets = true;
				Graph.addSetToChangedSets(this);
			}
			return coreSets.successful;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_GraphGraphCantBeModified.identity(), coreSets.semanticErr);
		}
	}

	private void ensureInitializedOrderedSets() {
		if (!orderedSetsInitialized) {
			values = new OrderedSet(identityFactory.createAnonymousIdentity());
			variables = new OrderedSet(identityFactory.createAnonymousIdentity());
			allContainedInstances = new OrderedSet(identityFactory.createAnonymousIdentity());
			vertexFlavored = new OrderedSet(identityFactory.createAnonymousIdentity());
			edgeFlavored = new OrderedSet(identityFactory.createAnonymousIdentity());
			allContainedLinkInstances = new OrderedSet(identityFactory.createAnonymousIdentity());
			superSetReferenceFlavored = new OrderedSet(identityFactory.createAnonymousIdentity());
			visibilityFlavored = new OrderedSet(identityFactory.createAnonymousIdentity());
			commands = new OrderedSet(identityFactory.createAnonymousIdentity());
			queries = new OrderedSet(identityFactory.createAnonymousIdentity());
			executableCommands = new OrderedSet(identityFactory.createAnonymousIdentity());
			executableQueries = new OrderedSet(identityFactory.createAnonymousIdentity());

			orderedSetsInitialized = true;
		}
	}

	protected Set getVertices() {
		this.ensureInitializedOrderedSets();
		return vertexFlavored;
	}

	protected void addToVertices(final Vertex anElement) {
		this.ensureInitializedOrderedSets();
		this.vertexFlavored.add(anElement);
		this.allContainedInstances.add(anElement);
	}

	protected void removeFromVertices(final Vertex anElement) {
		this.ensureInitializedOrderedSets();
		this.vertexFlavored.remove(anElement);
		this.allContainedInstances.remove(anElement);
	}

	protected Set getEdges() {
		this.ensureInitializedOrderedSets();
		return edgeFlavored;
	}

	protected void addToEdges(final Edge anElement) {
		this.ensureInitializedOrderedSets();
		this.edgeFlavored.add(anElement);
		this.allContainedLinkInstances.add(anElement);
		this.allContainedInstances.add(anElement);
	}

	protected void removeFromEdges(final Edge anElement) {
		this.ensureInitializedOrderedSets();
		this.edgeFlavored.remove(anElement);
		this.allContainedLinkInstances.remove(anElement);
		this.allContainedInstances.remove(anElement);
	}

	protected Set getSuperSetReferences() {
		this.ensureInitializedOrderedSets();
		return superSetReferenceFlavored;
	}

	protected void addToSuperSetReferences(final SuperSetReference anElement) {
		this.ensureInitializedOrderedSets();
		this.superSetReferenceFlavored.add(anElement);
		this.allContainedLinkInstances.add(anElement);
		this.allContainedInstances.add(anElement);
	}

	protected void removeFromGeneralizationReferences(final SuperSetReference anElement) {
		this.ensureInitializedOrderedSets();
		this.superSetReferenceFlavored.remove(anElement);
		this.allContainedLinkInstances.remove(anElement);
		this.allContainedInstances.remove(anElement);
	}

	protected Set getVisibilities() {
		this.ensureInitializedOrderedSets();
		return visibilityFlavored;
	}

	protected void addToVisibilities(final Visibility anElement) {
		this.ensureInitializedOrderedSets();
		this.visibilityFlavored.add(anElement);
		this.allContainedLinkInstances.add(anElement);
		this.allContainedInstances.add(anElement);
	}

	protected void removeFromVisibilities(final Visibility anElement) {
		this.ensureInitializedOrderedSets();
		this.visibilityFlavored.remove(anElement);
		this.allContainedLinkInstances.remove(anElement);
		this.allContainedInstances.remove(anElement);
	}

	@Override
	public Set container() {
		if (this.flavor().isEqualTo(Vertex.vertex)) {
			return ((Vertex) this).container();
		}
		else if (this.flavor().isEqualTo(Edge.edge)) {
			return ((Edge) this).container();
		}
		else if (this.flavor().isEqualTo(Visibility.visibility)) {
			return ((Visibility) this).container();
		}
		else if (this.flavor().isEqualTo(SuperSetReference.superSetReference)) {
			return ((SuperSetReference) this).container();
		}
		else if (this.flavor().isEqualTo(Link.link)) {
			return ((Link) this).container();
		}
		else if (this.isEqualTo(Graph.graph)) {
			return Graph.graph;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.kernelDefect_KernelHasReachedAnIllegalState.identity(), coreSets.kernelDefect);
		}
	}

	@Override
	public Set isSuperSetOf(final Set orderedPair) {
		if (this.isEqualToRepresentation(orderedPair)) {return coreSets.is_TRUE;}
		// only for performance optimisation
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {
			if (this.isEqualTo(GmodelSemanticDomains.semanticIdentity)
					&& (((OrderedPair)orderedPair.category()).isASemanticIdentity())) {return GmodelSemanticDomains.is_TRUE;}
		}

		final Set a = this;
		final Set aSuper = a.container().directSuperSetOf(a);
		final boolean aHasTrueSuper = !aSuper.isEqualToRepresentation(a);

		final Set b = orderedPair;
		final Set bSuper = b.container().directSuperSetOf(b);
		final boolean bHasTrueSuper = !bSuper.isEqualToRepresentation(b);

		if (aHasTrueSuper && aSuper.isEqualToRepresentation(b)) {return coreSets.is_FALSE;}
		if (bHasTrueSuper && bSuper.isEqualToRepresentation(a)) {return coreSets.is_TRUE;}
		if (bHasTrueSuper) {return a.isSuperSetOf(bSuper);}
		if (aHasTrueSuper) {return aSuper.isSuperSetOf(b);}
		return coreSets.is_FALSE;

	}

	@Override
	public Set localRootSuperSetOf(final Set orderedPair) {
		Set temp = this.directSuperSetOf(orderedPair);
		if (!(temp.isEqualToRepresentation(orderedPair))) {
			if (this.allContainedInstances.containsRepresentation(temp)) {
				temp = this.localRootSuperSetOf(temp);} else {
					return temp;
				}
		}
		return temp;
	}

	@Override
	public Set isLocalSuperSetOf(final Set orderedPair) {
		if (this.isEqualToRepresentation(orderedPair)) {
			return coreSets.is_TRUE;
		}
		else {
			if (this.allContainedInstances.containsRepresentation(orderedPair)) {
				Set temp = this.directSuperSetOf(orderedPair);
				while ((!(temp.isEqualToRepresentation(this))) && (!(temp.isEqualToRepresentation(orderedPair)))) {
					temp = this.directSuperSetOf(temp);
				}
				if (temp.isEqualToRepresentation(this)) {
					return coreSets.is_TRUE;
				} else {
					return coreSets.is_FALSE;
				}
			} else {
				return coreSets.is_FALSE;
			}
		}
	}

	@Override
	public Set directSuperSetOf(final Set orderedPair) {
		if (this.allContainedInstances.containsRepresentation(orderedPair)) {
			for (final Set superSetReference : this.getSuperSetReferences()) {
				if (superSetReference.from().isEqualToRepresentation(orderedPair)) {
					return ((SuperSetReference)superSetReference).getSuperSet();
				}
			}
			return orderedPair;
		} else {
			if (this.isEqualTo(graph) && (
					(orderedPair.isEqualTo(graph)
							||	(orderedPair.isEqualTo(Link.link))
							||	(orderedPair.isEqualTo(Vertex.vertex))
					))) {
				return graph;
			} else {
				if (this.isEqualTo(graph) && (
						(orderedPair.isEqualTo(Edge.edge)
								||	(orderedPair.isEqualTo(SuperSetReference.superSetReference))
								||	(orderedPair.isEqualTo(Visibility.visibility))

						))) {
					return graph;
				}
			}}
		return orderedPair;
		// TODO : verify that there is no use case where the error condition below adds value
		// return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	@Override
	public Set visibleArtifactsForSubGraph(final Set subGraph) {
		final OrderedSet visibleGraphs = new OrderedSet(identityFactory.createAnonymousIdentity());
		final OrderedSet visibleChildren = new OrderedSet(identityFactory.createAnonymousIdentity());

		// add the universally visible core instances
		visibleGraphs.add(coreGraphs.vertex);
		visibleGraphs.add(coreGraphs.graph);
		for (final Set coreInstance : coreGraphs.vertex.filterFlavor(coreGraphs.vertex)) {
			visibleGraphs.add(coreInstance);
		}

		for (final Set visibleSet : this.getVisibilities()) {
			final Visibility iVisibility = (Visibility) visibleSet;
			if (iVisibility.from().isEqualTo(subGraph)) {
				visibleGraphs.add(((iVisibility.to())));
				// collect all the GraphFlavored content of the toSubGraph
				for (final Set visibleChild : iVisibility.to().filterInstances()) {
					visibleChildren.add(visibleChild);
				}
			}
		}

		// also add all the GraphFlavored content of the toSubGraph to the visibility
		for (final Set visibleChild : visibleChildren) {
			visibleGraphs.add(visibleChild);
		}

		return visibleGraphs;
	}

	@Override
	public Set hasVisibilityOf(final Set target) {
		if (	(this.container().isEqualTo(target.container()))
				|| (this.isEqualTo(target.container()))
				|| (this.category().identity().isPartOfUniversalArtifactConcept())
		)	{
			return coreSets.is_TRUE;
		} else {
			final Set viz = this.container().container().visibleArtifactsForSubGraph(this.container());
			if (viz.containsSemanticMatch(target)) {
				return coreSets.is_TRUE;
			} else {
				if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
					if (target.category().container().isEqualTo(SemanticDomain.semanticdomain) && viz.containsSemanticMatch(target.container())
							|| (this.isEqualTo(Visualization.symbol))	) {
						return coreSets.is_TRUE;
					}
				}
				return coreSets.is_FALSE;
			}
		}
	}

	private Set getCategoryOfVisibility(final Graph target) {
		if (	(this.container().isEqualTo(target.container()))
				||	(this.isEqualTo(target.container())))	{
			return Visibility.visibility;
		} else {
			final Set viz = this.container().container().filterFlavor(coreGraphs.visibility);
			for (final Set visibility : viz) {
				if (   visibility.from().isEqualTo(this.container())
						&& (visibility.to().isEqualTo(target.container())
								|| (visibility.to().isEqualTo(target)	))
				) {
					return visibility;
				}
			}
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_TargetIsNotWithinVisibility.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set filterInstances() {
		this.ensureInitializedOrderedSets();
		return this.allContainedInstances;
	}

	@Override
	public Set filterLinks() {
		this.ensureInitializedOrderedSets();
		return this.allContainedLinkInstances;
	}

	@Override
	public Set filterLinks(final Set flavorOrCategory, final Set fromSet, final Set toSet) {
		if (flavorOrCategory.isInformation().is_TRUE()) {
			if (fromSet.isInformation().is_FALSE()
					&& toSet.isInformation().is_FALSE()) {
				if (((OrderedPair)flavorOrCategory).isLinkFlavor()) {
					return this.filterFlavor(flavorOrCategory);
				} else {
					return this.filterLinks().filter(flavorOrCategory);
				}
			}
			if (((OrderedPair)flavorOrCategory).isLinkFlavor()) {
				return this.filterFlavor(flavorOrCategory).filterByLinkedFromAndTo(fromSet, toSet);
			} else {
				return this.filterLinks().filter(flavorOrCategory).filterByLinkedFromAndTo(fromSet, toSet);
			}
		} else {
			return this.filterLinks().filterByLinkedFromAndTo(fromSet, toSet);
		}
	}

	@Override
	public Set filterFlavor(final Set flavor) {
		this.ensureInitializedOrderedSets();
		if (flavor.isEqualTo(coreGraphs.vertex)) {
			return this.vertexFlavored;
		} else if (flavor.isEqualTo(coreGraphs.link) && this.isEqualTo(Graph.graph)) {
			return Link.link;
		} else if (flavor.isEqualTo(coreGraphs.link)) {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		} else if (flavor.isEqualTo(coreGraphs.edge)) {
			return this.edgeFlavored;
		} else if (flavor.isEqualTo(coreGraphs.visibility)) {
			return this.visibilityFlavored;
		} else if (flavor.isEqualTo(coreGraphs.superSetReference)) {
			return this.superSetReferenceFlavored;
		} else if (flavor.isEqualTo(coreGraphs.edgeEnd)) {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		} else if (flavor.isEqualTo(coreGraphs.graph) && this.isEqualTo(Graph.graph)) {
			return this.allContainedInstances;
		} else if (flavor.isEqualTo(coreGraphs.graph)) {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		} else {
			return F_InstantiationImpl.raiseError(coreSets.kernelDefect_KernelHasReachedAnIllegalState.identity(), coreSets.kernelDefect);
		}
	}

	@Override
	public Set filter(final Set category) {
		this.ensureInitializedOrderedSets();
		final OrderedSet resultSet = new OrderedSet(identityFactory.createAnonymousIdentity());
		for (final Set instance : this.allContainedInstances) {
			if (instance.category().isEqualTo(category)) {
				resultSet.add(instance);
			}
		}
		return resultSet;
	}

	@Override
	public Set filterPolymorphic(final Set category) {
		this.ensureInitializedOrderedSets();
		final OrderedSet resultSet = new OrderedSet(identityFactory.createAnonymousIdentity());
		for (final Set instance : this.allContainedInstances) {
			if (category.isSuperSetOf(instance.category()).isEqualTo(coreSets.is_TRUE)
					&& category.isSuperSetOf(instance).isEqualTo(coreSets.is_FALSE)) {
				resultSet.add(instance);
			}
		}
		return resultSet;
	}

	@Override
	public Set categoryOfVisibility(final Set target) {
		return this.getCategoryOfVisibility((Graph)target);
	}

	@Override
	public Set containsEdgeTo (final Set anElement) {
		for (final Set edge : this.getEdges()) {
			if (	(edge.from().isEqualTo(anElement))
					|| 	(edge.to().isEqualTo(anElement)) ) {
				return coreSets.is_TRUE;
			}
		}
		return coreSets.is_FALSE;
	}

	@Override
	public Set from() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex.identity(), coreSets.semanticErr);
	}

	@Override
	public Set to() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex.identity(), coreSets.semanticErr);
	}

	@Override
	public Set fromEdgeEnd() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles.identity(), coreSets.semanticErr);
	}

	@Override
	public Set toEdgeEnd() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles.identity(), coreSets.semanticErr);
	}

	@Override
	public Set edgeEnds() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles.identity(), coreSets.semanticErr);
	}

	@Override
	public Set flavor() {
		return Graph.graph;
	}

	public Set addConcrete(final Set category, final Identity semanticIdentity) {
		if (category.flavor().isEqualTo(coreGraphs.vertex)) {
			return (F_Instantiation.addConcreteVertex(this, semanticIdentity, category));
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set addConcrete(final Set category, final Set semanticIdentity) {
		if (category.flavor().isEqualTo(coreGraphs.vertex)) {
			return (F_Instantiation.addConcreteVertex(this, identityFactory.reuseSemanticIdentity(semanticIdentity.identity()), category));
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor.identity(), coreSets.semanticErr);
		}
	}

	public Set addAbstract(final Set category, final Identity semanticIdentity) {
		if (category.flavor().isEqualTo(coreGraphs.vertex)) {
			return (F_Instantiation.addAbstractVertex(this, semanticIdentity, category));
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set addAbstract(final Set category, final Set semanticIdentity) {
		if (category.flavor().isEqualTo(coreGraphs.vertex)) {
			return (F_Instantiation.addAbstractVertex(this, identityFactory.reuseSemanticIdentity(semanticIdentity.identity()), category));
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set decommission() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {
			final Set dependentSets = Query.findDependentSets(this);
			if (dependentSets.size() == 0) {
				this.isDecommissioned = true;
				this.isNewInstance = false;
				Graph.addSetToChangedSets(this);
				((Graph) this.container()).setContainsDecommissionedSets(true);
				Graph.addSetToChangedSets(this.container());
				return coreSets.successful;
			} else {
				return dependentSets;
			}
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	public Set delete(final Set set) {
		if (set.category().flavor().isEqualTo(coreGraphs.vertex)) {
			this.removeFromVertices((Vertex)set);
			return set;
		} else {
			if (set.category().flavor().isEqualTo(coreGraphs.edge)) {
				this.removeFromEdges((Edge)set);
				return set;
			} else {

				if (set.category().flavor().isEqualTo(coreGraphs.superSetReference)) {
					this.removeFromGeneralizationReferences((SuperSetReference)set);
					return set;
				} else {
					if (set.category().flavor().isEqualTo(coreGraphs.visibility)) {
						this.removeFromVisibilities((Visibility)set);
						return set;
					} else {
						return F_InstantiationImpl.raiseError(coreSets.semanticErr_GraphsCantBeDecommissioned.identity(), coreSets.semanticErr);
					}
				}

			}
		}
	}

	protected void setContainsNewSets(final boolean value) {
		this.containsNewSets = value;
	}

	protected void setContainsDecommissionedSets(final boolean value) {
		this.containsDecommissionedSets = value;
	}

	protected void setHasNewPayload(final boolean value) {
		this.hasNewPayload = value;
	}

	protected void setHasDecommissionedPayload(final boolean value) {
		this.hasDecommissionedPayload = value;
	}

	@Override
	public Set isExternal() {
		if (  	this.flavor().isEqualTo(coreGraphs.edge)
				||	this.flavor().isEqualTo(coreGraphs.superSetReference)
				||	this.flavor().isEqualTo(coreGraphs.visibility)
		) {
			return F_InstantiationImpl.raiseError(coreSets.kernelDefect_KernelHasReachedAnIllegalState.identity(), coreSets.kernelDefect); // result must be overriden by specialization!
		} else {
			return coreSets.is_FALSE;
		}
	}

	/**
	 * GraphFlavor queries
	 */
	protected void addFlavorQueries() {
		this.addToExecutableQueries(coreSets.identity);
		this.addToExecutableQueries(coreSets.artifact);
		this.addToExecutableQueries(coreSets.filter);
		this.addToExecutableQueries(coreSets.containsEdgeFromOrTo);
		this.addToExecutableQueries(coreSets.filterFlavor);
		this.addToExecutableQueries(coreSets.hasVisibilityOf);
		this.addToExecutableQueries(coreSets.filterInstances);
		this.addToExecutableQueries(coreSets.isLocalSuperSetOf);
		this.addToExecutableQueries(coreSets.isSuperSetOf);
		this.addToExecutableQueries(coreSets.filterLinks);
		this.addToExecutableQueries(coreSets.localRootSuperSetOf);
		this.addToExecutableQueries(coreSets.directSuperSetOf);
		this.addToExecutableQueries(coreSets.category);
		this.addToExecutableQueries(coreSets.containerCategory);
		this.addToExecutableQueries(coreSets.variables);
		this.addToExecutableQueries(coreSets.value);
		this.addToExecutableQueries(coreSets.values);
		this.addToExecutableQueries(coreSets.visibleArtifactsForSubGraph);
		this.addToExecutableQueries(coreSets.allowableEdgeCategories);
		this.addToExecutableQueries(coreSets.filterPolymorphic);
		this.addToExecutableQueries(coreSets.semanticIdentity);
		this.addToExecutableQueries(coreSets.commands);
		this.addToExecutableQueries(coreSets.queries);
		this.addToExecutableQueries(coreSets.executableCommands);
		this.addToExecutableQueries(coreSets.executableQueries);
	}

	/**
	 * GraphFlavor commands
	 */
	protected void addFlavorCommands() {
		this.addToExecutableCommands(coreSets.addAbstract);
		this.addToExecutableCommands(coreSets.addConcrete);
	}

	@Override
	public Set containsDecommissionedSets() {
		if (containsDecommissionedSets) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}

	@Override
	public Set containsNewSets() {
		if (containsNewSets) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}

	@Override
	public Set hasNewPayload() {
		if (hasNewPayload) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}

	@Override
	public Set hasDecommissionedPayload() {
		if (hasDecommissionedPayload) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}

	@Override
	public Set hasNewName() {
		if (SemanticDomain.semanticIdentity.isSuperSetOf(this.category()).isEqualTo(coreSets.is_TRUE)) {
			if (hasNewName) {
				return coreSets.is_TRUE;
			} else {
				return coreSets.is_FALSE;
			}
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set hasNewPluralName() {
		if (SemanticDomain.semanticIdentity.isSuperSetOf(this.category()).isEqualTo(coreSets.is_TRUE)) {
			if (hasNewPluralName) {
				return coreSets.is_TRUE;
			} else {
				return coreSets.is_FALSE;
			}
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set isDecommissioned() {
		if (isDecommissioned) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}

	@Override
	public Set isNewInstance() {
		if (isNewInstance) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}

	@Override
	public Set assignNewName(final String newName) {
		if (SemanticDomain.semanticIdentity.isSuperSetOf(this.category()).isEqualTo(coreSets.is_TRUE)) {
			((IdentityImpl) this.identity()).assignNewName(newName);
			if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
				this.hasNewName = true;
				Graph.addSetToChangedSets(this);
			}
			return coreSets.successful;}
		else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set assignNewPluralName(final String newPluralName) {
		if (SemanticDomain.semanticIdentity.isSuperSetOf(this.category()).isEqualTo(coreSets.is_TRUE)) {
			((IdentityImpl) this.identity()).assignNewPluralName(newPluralName);
			if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
				this.hasNewPluralName = true;
				Graph.addSetToChangedSets(this);
			}
			return coreSets.successful;}
		else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set assignNewPayload(final String newPayload) {
		this.identity().setPayload(newPayload);
		if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			this.setHasNewPayload(true);
			this.setHasDecommissionedPayload(false);
			Graph.addSetToChangedSets(this);
		}
		return coreSets.successful;
	}

	@Override
	public Set decommissionPayload() {
		this.identity().setPayload(null);
		if (F_SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			this.setHasDecommissionedPayload(true);
			this.setHasNewPayload(false);
			Graph.addSetToChangedSets(this);
		}
		return coreSets.successful;
	}

	public Set clearModificationState() {
		isNewInstance = false;
		isDecommissioned = false;
		containsNewSets = false;
		containsDecommissionedSets = false;
		hasNewName = false;
		hasNewPluralName = false;
		hasNewPayload = false;
		hasDecommissionedPayload = false;
		return coreSets.successful;
	}

	@Override
	public Set allowableEdgeCategories(final Set orderedPair) {
		final OrderedSet result = new OrderedSet(identityFactory.createAnonymousIdentity());
		for (final Set edge : this.category().container().filterFlavor(Edge.edge)) {
			if (((edge.from().isSuperSetOf(this.category()).isEqualTo(coreSets.is_TRUE)))
					&& (LinkConstraints.isAllowableEdgeFlavoredLinkCategory(edge, this, orderedPair).isEqualTo(coreSets.is_TRUE))) {
				result.add(edge);
			}
			if (this.category().container().isEqualTo(graph)) {
				result.add(Edge.edge);
			}
		}
		return result;
	}

	@Override
	public Set processEvent(final Set event) {
		return event;
	}

	@Override
	public Set unionOfconnectingLinks(final Set instance) {
		final OrderedSet result = new OrderedSet(identityFactory.createAnonymousIdentity());
		for (final Set link : instance.container().filterLinks()) {
			if ((link.from().isEqualToRepresentation(instance) && (link.to().isEqualToRepresentation(this)))
					|| ((link.from().isEqualToRepresentation(this) && link.to().isEqualToRepresentation(instance)))) {
				result.add(link);
			}
		}
		for (final Set link : this.container().filterLinks()) {
			if ((link.from().isEqualToRepresentation(instance) && (link.to().isEqualToRepresentation(this)))
					|| ((link.from().isEqualToRepresentation(this) && link.to().isEqualToRepresentation(instance)))) {
				result.add(link);
			}
		}
		return result;
	}

	@Override
	public String localVisualRecognitionText() {
		return this.identity().name() + " : " + this.category().identity().name();
	}
	@Override
	public String visualRecognitionText() {
		if (this.category().isEqualTo(this)) {
			return this.identity().name();
		} else {
			return this.identity().name() + "." + this.container().visualRecognitionText();
		}
	}
	@Override
	public String fullVisualRecognitionText() {
		return this.visualRecognitionText() + " : " + this.category().visualRecognitionText();
	}

	/**
	 * Support for Information Quality Logic
	 */

	@Override
	public Set not() {
		return F_IqLogic.not(this);
	}

	@Override
	public Set and(final Set b) {
		return F_IqLogic.and(this, b);
	}
	@Override
	public Set or(final Set b) {
		return F_IqLogic.or(this, b);
	}

	@Override
	public Set isQuality() {
		return F_IqLogic.isQuality(this);
	}

	@Override
	public Set isInformation() {
		return F_IqLogic.isInformation(this);
	}
	@Override
	public  boolean is_NOTAPPLICABLE() {
		return this.isEqualTo(GmodelSemanticDomains.is_NOTAPPLICABLE);
	}
	@Override
	public  boolean is_FALSE() {
		return this.isEqualTo(GmodelSemanticDomains.is_FALSE);
	}
	@Override
	public  boolean is_UNKNOWN() {
		return this.isEqualTo(GmodelSemanticDomains.is_UNKNOWN);
	}
	@Override
	public  boolean is_TRUE() {
		return this.isEqualTo(GmodelSemanticDomains.is_TRUE);
	}
	@Override
	public Set includesValue(final Set value, final Set equivalenceClass) {
		return F_IqLogic.includesValue(this, value, equivalenceClass);
	}
	@Override
	public Set isEqualTo(final Set set, final Set equivalenceClass) {
		return F_IqLogic.isEqualTo(set, equivalenceClass);
	}

	/**
	 * support of Set Algebra
	 **/


	/**
	 * the union of this and semanticIdentity
	 */
	@Override
	public Set union(final Set semanticIdentity) {
		return F_SetAlgebra.union(this, semanticIdentity);
	}
	/**
	 * the intersection of this and semanticIdentity
	 */
	@Override
	public Set intersection(final Set set) {
		return F_SetAlgebra.intersection(this, set);
	}
	/**
	 * the complement of this and semanticIdentity
	 */
	@Override
	public Set complement(final Set set) {
		return F_SetAlgebra.complement(this, set);
	}
	@Override
	public Set isElementOf(final Set semanticIdentity){
		return F_SetAlgebra.isElementOf(this, semanticIdentity);
	}
	@Override
	public Set transformToOrderedSetOfSemanticIdentities() {
		return F_SetAlgebra.transformToOrderedSetOfSemanticIdentities(this.filterInstances());
	}

	/**
	 * Establish symmetry between graphs and ordered set
	 * Delegate to the instanceSet
	 */

	@Override
	public Set filterByLinkedTo(final Set toSet) {
		return this.filterLinks().filterByLinkedTo(toSet);
	}
	@Override
	public Set filterByLinkedFrom(final Set fromSet) {
		return this.filterLinks().filterByLinkedFrom(fromSet);
	}
	@Override
	public Set filterByLinkedFromAndTo(final Set fromSet, final Set toSet) {
		return this.filterLinks().filterByLinkedFromAndTo(toSet, fromSet);
	}
	@Override
	public Set filterByLinkedToVia(final Set toEdgeEnd) {
		return this.filterLinks().filterByLinkedToVia(toEdgeEnd);
	}
	@Override
	public Set filterByLinkedFromVia(final Set fromEdgeEnd) {
		return this.filterLinks().filterByLinkedFromVia(fromEdgeEnd);
	}
	@Override
	public Set filterByLinkedFromAndToVia(final Set fromEdgeEnd, final Set toEdgeEnd) {
		return this.filterLinks().filterByLinkedFromAndToVia(fromEdgeEnd, toEdgeEnd );
	}
	@Override
	public Set filterFrom() {
		return this.filterLinks().filterFrom();
	}
	@Override
	public Set filterTo() {
		return this.filterLinks().filterTo();
	}
	@Override
	public Set filterFromAndTo() {
		return this.filterLinks().filterFromAndTo();
	}
	@Override
	public Set filterByLinkedToSemanticRole(final Set toSetReferencedSemanticRole) {
		return this.filterLinks().filterByLinkedToSemanticRole(toSetReferencedSemanticRole);
	}
	@Override
	public Set filterByLinkedFromSemanticRole(final Set fromSetReferencedSemanticRole) {
		return this.filterLinks().filterByLinkedFromSemanticRole(fromSetReferencedSemanticRole);
	}
	@Override
	public Set filterByLinkedFromAndToSemanticRole(final Set fromSetReferencedSemanticRole, final Set toSetReferencedSemanticRole) {
		return this.filterLinks().filterByLinkedFromAndToSemanticRole(fromSetReferencedSemanticRole, toSetReferencedSemanticRole);
	}
	@Override
	public Set filterBySemanticIdentity(final Set set) {
		return this.filterInstances().filterBySemanticIdentity(set);
	}
	@Override
	public Set filterByEquivalenceClass(final Set set) {
		return this.filterInstances().filterByEquivalenceClass(set);
	}
	@Override
	public Set extractUniqueMatch(final Set set) {
		return this.filterInstances().extractUniqueMatch(set);
	}
	@Override
	public Set extractUniqueMatch(final Identity identity) {
		return this.filterInstances().extractUniqueMatch(identity);
	}
	@Override
	public Set extractUniqueMatch(final String uuidAsString) {
		return this.filterInstances().extractUniqueMatch(uuidAsString);
	}
	@Override
	public Set extractFirst() {
		return this.filterInstances().extractFirst();
	}
	@Override
	public Set extractSecond() {
		return this.filterInstances().extractSecond();
	}
	@Override
	public Set extractLast() {
		return this.filterInstances().extractLast();
	}
	@Override
	public Set extractNext(final Set element) {
		return this.filterInstances().extractNext(element);
	}
	@Override
	public Set extractPrevious(final Set element) {
		return this.filterInstances().extractPrevious(element);
	}
	/**
	 * See {@link java.util.List#contains(Object)}
	 */
	@Override
	public boolean containsSemanticMatch(final Set set) {
		return this.filterInstances().containsSemanticMatch(set);
	}
	@Override
	public boolean containsRepresentation(final Set set) {
		return this.filterInstances().containsSemanticMatch(set);
	}
	@Override
	public boolean containsSemanticMatchesForAll(final Set set) {
		return this.filterInstances().containsSemanticMatchesForAll(set);
	}
	@Override
	public boolean containsAllRepresentations(final Set set) {
		return this.filterInstances().containsAllRepresentations(set);
	}
	@Override
	public boolean isEmpty() {
		return this.filterInstances().isEmpty();
	}
	@Override
	public ListIterator<Set> listIterator() {
		return this.filterInstances().listIterator();
	}
	@Override
	public ListIterator<Set> listIterator(final int index) {
		return this.filterInstances().listIterator(index);
	}
	@Override
	public int size() {
		return this.filterInstances().size();
	}
	@Override
	public Set[] toArray() {
		return this.filterInstances().toArray();
	}
	@Override
	public Set[] toArray(final Set[] a) {
		return this.filterInstances().toArray(a);
	}
	@Override
	public List<Set> asList() {
		return this.filterInstances().asList();
	}
	@Override
	public Set and() {
		return this.filterInstances().and();
	}
	@Override
	public Set or() {
		return this.filterInstances().or();
	}
	public  Set initializeWalk(final VisitorFunction visitorFunction) {
		return visitorFunction.initialize(this);
	}

	public 	Set walkDownThenRight(final VisitorFunction visitorFunction) {
		final Set content = this.filterInstances();
		for (final Set element : content) {
			element.walkDownThenRight(visitorFunction) ;
		}
		return visitorFunction.compute(this);
	}

	public 	Set walkDownThenLeft(final VisitorFunction visitorFunction) {
		final Set content = this.filterInstances();
		Set element = content.extractLast();
		for (int i = 0; i < content.size()  ; i++) {
			element.walkDownThenLeft(visitorFunction) ;
			element = content.extractPrevious(element);
		}
		return visitorFunction.compute(this);
	}

	public 	Set walkRightThenDown(final VisitorFunction visitorFunction) {
		final Set content = this.filterInstances();
		for (final Set element : content) {
			visitorFunction.compute(element);
		}
		return this.walkRightThenDown(visitorFunction) ;
	}
	public 	Set walkLeftThenDown(final VisitorFunction visitorFunction) {
		final Set content = this.filterInstances();
		Set element = content.extractLast();
		visitorFunction.compute(element);
		for (int i = 0; i < content.size()  ; i++) {
			element = content.extractPrevious(element);
			visitorFunction.compute(element);
		}
		this.walkLeftThenDown(visitorFunction) ;
		return visitorFunction.compute(this);
	}
}