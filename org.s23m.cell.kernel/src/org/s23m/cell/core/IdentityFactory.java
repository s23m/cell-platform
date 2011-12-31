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

import static org.s23m.cell.core.F_Instantiation.identityFactory;

import java.util.UUID;

import org.apache.commons.collections.map.ListOrderedMap;
import org.s23m.cell.Identity;
import org.s23m.cell.api.Instantiation;

/**
 * {@link IdentityFactory} is a factory for Semantic Identities that connects
 * to the basic implementation of SemanticIdentity delivered with Gmodel.
 * 
 * The implementation can be overridden as needed by an alternative class that implements
 * the FundamentalSemanticIdentities interface.
 */
public class IdentityFactory implements KernelIdentities {
	private final ListOrderedMap inMemoryIdentities = new ListOrderedMap();
	private final UUIDReservoirForKernelGraph uUIDReservoir = new UUIDReservoirForKernelGraph();


	public IdentityFactory() {
		//	final UUIDReservoirForKernelGraph uUIDReservoir = new UUIDReservoirForKernelGraph();
		uUIDReservoir.fill();
		IdentityImpl.initialize(uUIDReservoir);
		orderedPair();
		graph();
	}

	public final	Identity abstractSemanticRole() {return identityFactory.createIdentityInKernel("abstract semantic role", "abstract semantic roles", SemanticIdentityRegistry.abstractSemanticRole.ordinal());}
	public final	Identity addAbstract() {return createIdentityInKernel("addAbstract" , "addAbstract", SemanticIdentityRegistry.addAbstract.ordinal());}
	public final	Identity addConcrete() {return createIdentityInKernel("addConcrete" , "addConcrete", SemanticIdentityRegistry.addConcrete.ordinal());}
	public final	Identity addElement() {return createIdentityInKernel("addElement" , "addElement", SemanticIdentityRegistry.addElement.ordinal());}
	public final	Identity addToCommands() {return createIdentityInKernel("addToCommands" , "addToCommands", SemanticIdentityRegistry.addToCommands.ordinal());}
	public final	Identity addToQueries() {return createIdentityInKernel("addToQueries" , "addToQueries", SemanticIdentityRegistry.addToQueries.ordinal());}
	public final	Identity addToValues() {return createIdentityInKernel("addToValues" , "addToValues", SemanticIdentityRegistry.addToValues.ordinal());}
	public final	Identity addToVariables() {return createIdentityInKernel("addToVariables" , "addToVariables", SemanticIdentityRegistry.addToVariables.ordinal());}
	public final 	Identity allowableEdgeCategories() {return createIdentityInKernel("allowableEdgeCategories" , "allowableEdgeCategories", SemanticIdentityRegistry.allowableEdgeCategories.ordinal());}
	public final 	Identity anonymous() {return createAnonymousIdentity();}
	public final 	Identity anonymousInKernel() {return createAnonymousIdentityInKernel();}
	public final	Identity asList() {return createIdentityInKernel("asList" , "asList", SemanticIdentityRegistry.asList.ordinal());}
	public final	Identity assignNewName() {return createIdentityInKernel("assignNewName" , "assignNewName", SemanticIdentityRegistry.assignNewName.ordinal());}
	public final	Identity assignNewPayload() {return createIdentityInKernel("assignNewPayload" , "assignNewPayload", SemanticIdentityRegistry.assignNewPayload.ordinal());}
	public final	Identity assignNewPluralName() {return createIdentityInKernel("assignNewPluralName" , "assignNewPluralName", SemanticIdentityRegistry.assignNewPluralName.ordinal());}

	public final	Identity booleanValue() {return createIdentityInKernel("boolean value" , "boolean values", SemanticIdentityRegistry.booleanValue.ordinal());}

	public final	Identity category() {return createIdentityInKernel("category" , "category", SemanticIdentityRegistry.category.ordinal());}
	public final	Identity command() {return createIdentityInKernel("command" , "commands", SemanticIdentityRegistry.command.ordinal());}
	public final	Identity commandFunction() {return createIdentityInKernel("command function" , "command functions", SemanticIdentityRegistry.commandFunction.ordinal());}
	public final	Identity commands() {return createIdentityInKernel("commands" , "commands", SemanticIdentityRegistry.commands.ordinal());}
	public final	Identity complement() {return createIdentityInKernel("complement" , "complement", SemanticIdentityRegistry.complement.ordinal());}
	public final	Identity completion() {return createIdentityInKernel("completion" , "completion", SemanticIdentityRegistry.completion.ordinal());}
	public final	Identity completion_successful() {return createIdentityInKernel("successful" , "successful", SemanticIdentityRegistry.completion_successful.ordinal());}
	public final	Identity container() {return createIdentityInKernel("container" , "container", SemanticIdentityRegistry.container.ordinal());}
	public final	Identity containerCategory() {return createIdentityInKernel("containerCategory" , "containerCategory", SemanticIdentityRegistry.containerCategory.ordinal());}
	public final	Identity contains() {return createIdentityInKernel("contains" , "contains", SemanticIdentityRegistry.contains.ordinal());}
	public final	Identity containsAll() {return createIdentityInKernel("containsAll" , "containsAll", SemanticIdentityRegistry.containsAll.ordinal());}
	public final	Identity containsAllRepresentations() {return createIdentityInKernel("containsAllRepresentations" , "containsAllRepresentations", SemanticIdentityRegistry.containsAllRepresentations.ordinal());}
	public final	Identity containsEdgeFromOrTo() {return createIdentityInKernel("containsEdgeFromOrTo" , "containsEdgeFromOrTo", SemanticIdentityRegistry.containsEdgeFromOrTo.ordinal());}
	public final	Identity containsRepresentations() {return createIdentityInKernel("containsRepresentations" , "containsRepresentations", SemanticIdentityRegistry.containsRepresentations.ordinal());}
	public 			Identity createAnonymousIdentity() {
		return deduplicate(new IdentityImpl("ANONYMOUS", "ANONYMOUS", SemanticIdentityRegistry.anonymous.ordinal()));
	}
	public Identity createAnonymousIdentity(final boolean isKernelIdentity) {
		if (isKernelIdentity) {
			return createAnonymousIdentityInKernel();
		} else {
			return createAnonymousIdentity();
		}
	}
	public Identity createAnonymousIdentityInKernel() {
		return deduplicate(new IdentityImpl("ANONYMOUS IN KERNEL", "ANONYMOUS IN KERNEL", SemanticIdentityRegistry.anonymousInKernel.ordinal()));
	}
	public Identity createIdentity(final String name) {
		return deduplicate(new IdentityImpl(name, "set of ".concat(name), Instantiation.indexIsNotAvailable));
	}
	public Identity createIdentity(final String name, final String pluralName) {
		return deduplicate(new IdentityImpl(name, pluralName, Instantiation.indexIsNotAvailable));
	}
	public Identity createIdentity(final String name, final String pluralName, final int index) {
		return deduplicate(new IdentityImpl(name, pluralName, index));
	}
	public Identity createIdentityInKernel(final String name, final String pluralName, final int index) {
		return createIdentity(name, pluralName, index);
	}
	public Identity createIdentityReification() {
		return semanticIdentity();
	}

	public final	Identity decommission() {return createIdentityInKernel("decommission" , "decommission", SemanticIdentityRegistry.decommission.ordinal());}
	private Identity deduplicate(final Identity id) {
		if (this.inMemoryIdentities.containsKey(id.uniqueRepresentationReference().toString())) {
			return (Identity) this.inMemoryIdentities.get(id.uniqueRepresentationReference().toString());
		} else
		{
			this.inMemoryIdentities.put(id.uniqueRepresentationReference().toString(), id);
			return id;
		}
	}
	public final	Identity directSuperSetOf() {return createIdentityInKernel("directSuperSetOf" , "directSuperSetOf", SemanticIdentityRegistry.directSuperSetOf.ordinal());}
	public final    Identity disjunctSemanticIdentitySet() {return createIdentityInKernel("disjunct semantic identity set","disjunct semantic identity sets", SemanticIdentityRegistry.disjunctSemanticIdentitySet.ordinal());}

	public final 	Identity edge() {return createIdentityInKernel("edge" , "edges", SemanticIdentityRegistry.edge.ordinal());}
	public final 	Identity edgeEnd() {return createIdentityInKernel("edge end" , "edge ends", SemanticIdentityRegistry.edgeEnd.ordinal());}
	public final	Identity edgeEnds() {return createIdentityInKernel("edgeEnds" , "edgeEnds", SemanticIdentityRegistry.edgeEnds.ordinal());}
	public final 	Identity edgeTrace() {return createIdentityInKernel("edge trace" , "edge traces", SemanticIdentityRegistry.edgeTrace.ordinal());}
	public final	Identity element() {return identityFactory.createIdentityInKernel("element", "elements", SemanticIdentityRegistry.element.ordinal());}
	public final 	Identity elementAdded() {return createIdentityInKernel("elementAdded" , "elementAdded", SemanticIdentityRegistry.elementAdded.ordinal());}
	public final 	Identity elementRemoved() {return createIdentityInKernel("elementRemoved" , "elementRemoved", SemanticIdentityRegistry.elementRemoved.ordinal());}
	public final    Identity elements() {return createIdentityInKernel("elements" , "elements", SemanticIdentityRegistry.elements.ordinal());}
	public final 	Identity elementsOfSemanticIdentitySet() {return createIdentityInKernel("elementsOfSemanticIdentitySet" , "elementsOfSemanticIdentitySet", SemanticIdentityRegistry.elementsOfSemanticIdentitySet.ordinal());}
	public final    Identity equivalenceClass() {return createIdentityInKernel("equivalence class","equivalence classes", SemanticIdentityRegistry.equivalenceClass.ordinal());}
	public final 	Identity event() {return createIdentityInKernel("event" , "event", SemanticIdentityRegistry.event.ordinal());}
	public final	Identity executableCommands() {return createIdentityInKernel("executableCommands" , "executableCommands", SemanticIdentityRegistry.executableCommands.ordinal());}
	public final	Identity executableQueries() {return createIdentityInKernel("executableQueries" , "executableQueries", SemanticIdentityRegistry.executableQueries.ordinal());}
	public final 	Identity extractFirst() {return createIdentityInKernel("extractFirst" , "extractFirst", SemanticIdentityRegistry.extractFirst.ordinal());}
	public final 	Identity extractSecond() {return createIdentityInKernel("extractSecond" , "extractSecond", SemanticIdentityRegistry.extractSecond.ordinal());}
	public final	Identity extractLast() {return createIdentityInKernel("extractLast" , "extractLast", SemanticIdentityRegistry.extractLast.ordinal());}
	public final 	Identity extractUniqueMatch() {return createIdentityInKernel("extractUniqueMatch" , "extractUniqueMatch", SemanticIdentityRegistry.extractUniqueMatch.ordinal());}


	public final	Identity filter() {return createIdentityInKernel("filter" , "filter", SemanticIdentityRegistry.filter.ordinal());}
	public final	Identity filterFlavor() {return createIdentityInKernel("filterFlavor" , "filterFlavor", SemanticIdentityRegistry.filterFlavor.ordinal());}
	public final	Identity filterInstances() {return createIdentityInKernel("filterInstances" , "filterInstances", SemanticIdentityRegistry.filterInstances.ordinal());}
	public final	Identity filterLinks() {return createIdentityInKernel("filterLinks" , "filterLinks", SemanticIdentityRegistry.filterLinks.ordinal());}
	public final 	Identity filterPolymorphic() {return createIdentityInKernel("filterPolymorphic" , "filterPolymorphic", SemanticIdentityRegistry.filterPolymorphic.ordinal());}
	public final	Identity flavor() {return createIdentityInKernel("flavor" , "flavor", SemanticIdentityRegistry.flavor.ordinal());}
	public final	Identity flavorCommandFunction() {return createIdentityInKernel("flavor command function" , "flavor command functions", SemanticIdentityRegistry.flavorCommandFunction.ordinal());}
	public final	Identity flavorQueryFunction() {return createIdentityInKernel("flavor query function" , "flavor query functions", SemanticIdentityRegistry.flavorQueryFunction.ordinal());}
	public final	Identity from() {return createIdentityInKernel("from" , "from", SemanticIdentityRegistry.from.ordinal());}
	public final	Identity fromEdgeEnd() {return createIdentityInKernel("fromEdgeEnd" , "fromEdgeEnd", SemanticIdentityRegistry.fromEdgeEnd.ordinal());}
	public final	Identity function() {return createIdentityInKernel("function" , "functions", SemanticIdentityRegistry.function.ordinal());}

	public final	Identity get() {return createIdentityInKernel("get" , "get", SemanticIdentityRegistry.get.ordinal());}
	public final 	Identity graph() {return createIdentityInKernel("graph" , "graphs", SemanticIdentityRegistry.graph.ordinal());}

	public final	Identity hasVisibilityOf() {return createIdentityInKernel("hasVisibilityOf" , "hasVisibilityOf", SemanticIdentityRegistry.hasVisibilityOf.ordinal());}

	public final	Identity identifier() {return createIdentityInKernel("identifier" , "identifier", SemanticIdentityRegistry.identifier.ordinal());}
	public final	Identity identity() {return createIdentityInKernel("identity" , "identity", SemanticIdentityRegistry.identity.ordinal());}
	public final	Identity indexOf() {return createIdentityInKernel("indexOf" , "indexOf", SemanticIdentityRegistry.indexOf.ordinal());}
	public final 	Identity indexOfIdentifier() {return createIdentityInKernel("indexOfIdentifier" , "indexOfIdentifier", SemanticIdentityRegistry.indexOfIdentifier.ordinal());}
	// the number of identities that have been loaded into memory
	public int inMemoryComplexity() {
		return this.inMemoryIdentities.size();
	}
	public final	Identity instantiateAbstract() {return createIdentityInKernel("instantiateAbstract" , "instantiateAbstract", SemanticIdentityRegistry.instantiateAbstract.ordinal());}
	public final	Identity instantiateConcrete() {return createIdentityInKernel("instantiateConcrete" , "instantiateConcrete", SemanticIdentityRegistry.instantiateConcrete.ordinal());}
	public final	Identity intersection() {return createIdentityInKernel("intersection" , "intersection", SemanticIdentityRegistry.intersection.ordinal());}
	public final	Identity iqLogicValue() {return createIdentityInKernel("iq-logic value" , "iq-logic values", SemanticIdentityRegistry.iqLogicValue.ordinal());}
	public final	Identity is_FALSE() {return createIdentityInKernel("FALSE" , "FALSEs", SemanticIdentityRegistry.is_FALSE.ordinal());}
	public final	Identity is_NOTAPPLICABLE() {return createIdentityInKernel("N/A" , "N/As", SemanticIdentityRegistry.is_NOTAPPLICABLE.ordinal());}
	public final	Identity is_TRUE() {return createIdentityInKernel("TRUE" , "TRUEs", SemanticIdentityRegistry.is_TRUE.ordinal());}
	public final	Identity is_UNKNOWN() {return createIdentityInKernel("UNKNOWN" , "UNKNOWNs", SemanticIdentityRegistry.is_UNKNOWN.ordinal());}
	public final	Identity isAbstract() {return createIdentityInKernel("is abstract" , "are abstract", SemanticIdentityRegistry.isAbstract.ordinal());}
	public final	Identity isAbstract_FALSE() {return createIdentityInKernel("isAbs FALSE" , "FALSEs", SemanticIdentityRegistry.isAbstract_FALSE.ordinal());}
	public final	Identity isAbstract_TRUE() {return createIdentityInKernel("isAbs TRUE" , "TRUEs", SemanticIdentityRegistry.isAbstract_TRUE.ordinal());}
	public final	Identity isALink() {return createIdentityInKernel("isALink" , "isALink", SemanticIdentityRegistry.isALink.ordinal());}
	public final	Identity isASemanticIdentity() {return createIdentityInKernel("isASemanticIdentity" , "isASemanticIdentity", SemanticIdentityRegistry.isASemantikIdentity.ordinal());}
	public final	Identity isContainer() {return createIdentityInKernel("is container" , "is container", SemanticIdentityRegistry.isContainer.ordinal());}
	public final	Identity isContainer_FALSE() {return createIdentityInKernel("isCont FALSE" , "FALSEs", SemanticIdentityRegistry.isContainer_FALSE.ordinal());}
	public final	Identity isContainer_NOTAPPLICABLE() {return createIdentityInKernel("isCont N/A" , "N/As", SemanticIdentityRegistry.isContainer_NOTAPPLICABLE.ordinal());}
	public final	Identity isContainer_TRUE() {return createIdentityInKernel("isCont TRUE" , "TRUEs", SemanticIdentityRegistry.isContainer_TRUE.ordinal());}
	public final	Identity isContainer_UNKNOWN() {return createIdentityInKernel("isCont UNKNOWN" , "UNKNOWNs", SemanticIdentityRegistry.isContainer_UNKNOWN.ordinal());}
	public final 	Identity isElementOf() {return createIdentityInKernel("isElementOf" , "isElementOf", SemanticIdentityRegistry.isElementOf.ordinal());}
	public final	Identity isEmpty() {return createIdentityInKernel("isEmpty" , "isEmpty", SemanticIdentityRegistry.isEmpty.ordinal());}
	public final	Identity isEqualTo() {return createIdentityInKernel("isEqualTo" , "isEqualTo", SemanticIdentityRegistry.isEqualTo.ordinal());}
	public final	Identity isEqualToRepresentation() {return createIdentityInKernel("isEqualToRepresentation" , "isEqualToRepresentation", SemanticIdentityRegistry.isEqualToRepresentation.ordinal());}
	public final	Identity isExternal() {return createIdentityInKernel("isExternal" , "isExternal", SemanticIdentityRegistry.isExternal.ordinal());}
	public final	Identity isInformation() {return createIdentityInKernel("isInformation" , "isInformation", SemanticIdentityRegistry.isInformation.ordinal());}
	public final	Identity isLocalSuperSetOf() {return createIdentityInKernel("isLocalSuperSetOf" , "isLocalSuperSetOf", SemanticIdentityRegistry.isLocalSuperSetOf.ordinal());}
	public final	Identity isNavigable() {return createIdentityInKernel("is navigable" , "are navigable", SemanticIdentityRegistry.isNavigable.ordinal());}
	public final	Identity isNavigable_FALSE() {return createIdentityInKernel("isNav FALSE" , "FALSEs", SemanticIdentityRegistry.isNavigable_FALSE.ordinal());}
	public final	Identity isNavigable_NOTAPPLICABLE() {return createIdentityInKernel("isNav N/A" , "N/As", SemanticIdentityRegistry.isNavigable_NOTAPPLICABLE.ordinal());}
	public final	Identity isNavigable_TRUE() {return createIdentityInKernel("isNav TRUE" , "TRUEs", SemanticIdentityRegistry.isNavigable_TRUE.ordinal());}
	public final	Identity isNavigable_UNKNOWN() {return createIdentityInKernel("isNav UNKNOWN" , "UNKNOWNs", SemanticIdentityRegistry.isNavigable_UNKNOWN.ordinal());}
	public final	Identity isSuperSetOf() {return createIdentityInKernel("isSuperSetOf" , "isSuperSetOf", SemanticIdentityRegistry.isSuperSetOf.ordinal());}

	// the size of the kernel in terms of the number of UUID that have been assigned
	public int kernelComplexity() {
		int complexity = 0;
		for (int i=0; i < this.inMemoryIdentities.size(); i++) {
			final Identity iIdentity = (Identity) this.inMemoryIdentities.getValue(i);
			if (uUIDReservoir.isPartOfKernel(iIdentity.uniqueRepresentationReference())) {
				complexity = complexity+1;
			}
		}
		return complexity;
	}
	public final	Identity kernelDefect() {return createIdentityInKernel("kernel defect - this should never happen" , "kernelDefects", SemanticIdentityRegistry.kernelDefect.ordinal());}
	public final	Identity kernelDefect_KernelHasReachedAnIllegalState() {return createIdentityInKernel("kernel CONSTRAINTVIOLATION" , "the Gmodel kernel has reached an illegal state", SemanticIdentityRegistry.kernelDefect_KernelHasReachedAnIllegalState.ordinal());}

	public final	Identity lastIndexOf() {return createIdentityInKernel("lastIndexOf" , "lastIndexOf", SemanticIdentityRegistry.lastIndexOf.ordinal());}
	public final    Identity link() {return createIdentityInKernel("link" , "links", SemanticIdentityRegistry.link.ordinal());}
	public final	Identity listIterator() {return createIdentityInKernel("listIterator" , "listIterator", SemanticIdentityRegistry.listIterator.ordinal());}
	public final	Identity listIteratorInt() {return createIdentityInKernel("listIterator(int)" , "listIterator(int)", SemanticIdentityRegistry.listIteratorInt.ordinal());}
	public final	Identity localRootSuperSetOf() {return createIdentityInKernel("localRootSuperSetOf" , "localRootSuperSetOf", SemanticIdentityRegistry.localRootSuperSetOf.ordinal());}

	public final	Identity maxCardinality() {return createIdentityInKernel("max cardinality" , "max cardinalities", SemanticIdentityRegistry.maxCardinality.ordinal());}
	public final	Identity maxCardinality_0() {return createIdentityInKernel("0]" , "0s]", SemanticIdentityRegistry.maxCardinality_0.ordinal());}
	public final	Identity maxCardinality_1() {return createIdentityInKernel("1]" , "1s]", SemanticIdentityRegistry.maxCardinality_1.ordinal());}
	public final	Identity maxCardinality_2() {return createIdentityInKernel("2]" , "2s]", SemanticIdentityRegistry.maxCardinality_2.ordinal());}
	public final	Identity maxCardinality_n() {return createIdentityInKernel("*]" , "*s]", SemanticIdentityRegistry.maxCardinality_n.ordinal());}
	public final	Identity maxCardinality_NOTAPPLICABLE() {return createIdentityInKernel("max N/A" , "N/As", SemanticIdentityRegistry.maxCardinality_NOTAPPLICABLE.ordinal());}
	public final	Identity maxCardinality_UNKNOWN() {return createIdentityInKernel("max UNKNOWN" , "UNKNOWNs", SemanticIdentityRegistry.maxCardinality_UNKNOWN.ordinal());}
	public final	Identity maxSearchSpaceDepth() {return createIdentityInKernel("maxSearchSpaceDepth" , "maxSearchSpaceDepth", SemanticIdentityRegistry.maxSearchSpaceDepth.ordinal());}
	public final	Identity minCardinality() {return createIdentityInKernel("min cardinality" , "min cardinalities", SemanticIdentityRegistry.minCardinality.ordinal());}
	public final	Identity minCardinality_0() {return createIdentityInKernel("[0" , "[0s", SemanticIdentityRegistry.minCardinality_0.ordinal());}
	public final	Identity minCardinality_1() {return createIdentityInKernel("[1" , "[1s", SemanticIdentityRegistry.minCardinality_1.ordinal());}
	public final	Identity minCardinality_2() {return createIdentityInKernel("[2" , "[2s", SemanticIdentityRegistry.minCardinality_2.ordinal());}
	public final	Identity minCardinality_n() {return createIdentityInKernel("[*" , "[*s", SemanticIdentityRegistry.minCardinality_n.ordinal());}
	public final	Identity minCardinality_NOTAPPLICABLE() {return createIdentityInKernel("min N/A" , "N/As", SemanticIdentityRegistry.minCardinality_NOTAPPLICABLE.ordinal());}
	public final	Identity minCardinality_UNKNOWN() {return createIdentityInKernel("min UNKNOWN" , "UNKNOWNs", SemanticIdentityRegistry.minCardinality_UNKNOWN.ordinal());}

	public final	Identity name() {return createIdentityInKernel("name" , "name", SemanticIdentityRegistry.name.ordinal());}

	public final 	Identity orderedPair() {return createIdentityInKernel("ordered pair" , "ordered pairs", SemanticIdentityRegistry.orderedPair.ordinal());}
	public final 	Identity orderedSet() {return createIdentityInKernel("ordered set" , "ordered sets", SemanticIdentityRegistry.orderedSet.ordinal());}

	public final	Identity parameter() {return createIdentityInKernel("parameter" , "parameters", SemanticIdentityRegistry.parameter.ordinal());}
	public final	Identity pluralName() {return createIdentityInKernel("pluralName" , "pluralName", SemanticIdentityRegistry.pluralName.ordinal());}
	public final	Identity queries() {return createIdentityInKernel("queries" , "queries", SemanticIdentityRegistry.queries.ordinal());}
	public final	Identity query() {return createIdentityInKernel("query" , "queries", SemanticIdentityRegistry.query.ordinal());}
	public final	Identity queryFunction() {return createIdentityInKernel("query function" , "querie functions", SemanticIdentityRegistry.queryFunction.ordinal());}
	// only for use in the context of deserialization!
	public Identity reconstituteSemanticIdentity(final String name, final String pluralName, final UUID identifier, final UUID uniqueRepresentationReference) {
		return new IdentityImpl(name, pluralName, identifier, uniqueRepresentationReference);
	}
	public final	Identity referencedSemanticRole() {return identityFactory.createIdentityInKernel("referenced semantic role", "referenced semantic roles", SemanticIdentityRegistry.referencedSemanticRole.ordinal());}
	public final	Identity referencingSemanticRole() {return identityFactory.createIdentityInKernel("referencing semantic role", "referencing semantic roles", SemanticIdentityRegistry.referencingSemanticRole.ordinal());}
	public final	Identity removeElement() {return createIdentityInKernel("removeElement" , "removeElement", SemanticIdentityRegistry.removeElement.ordinal());}
	public final	Identity removeFromCommands() {return createIdentityInKernel("removeFromCommands" , "removeFromCommands", SemanticIdentityRegistry.removeFromCommands.ordinal());}
	public final	Identity removeFromQueries() {return createIdentityInKernel("removeFromQueries" , "removeFromQueries", SemanticIdentityRegistry.removeFromQueries.ordinal());}
	public final	Identity removeFromValues() {return createIdentityInKernel("removeFromValues" , "removeFromValues", SemanticIdentityRegistry.removeFromValues.ordinal());}
	public final	Identity removeFromVariables() {return createIdentityInKernel("removeFromVariables" , "removeFromVariables", SemanticIdentityRegistry.removeFromVariables.ordinal());}
	// to reuse an existing SemanticIdentity
	public Identity reuseSemanticIdentity(final Identity semanticIdentity) {
		return new IdentityImpl(semanticIdentity);
	}

	public final    Identity semanticDomain() {return createIdentityInKernel("semantic domain" , "semantic domain", SemanticIdentityRegistry.semanticDomain.ordinal());}
	public final	Identity semanticErr() {return createIdentityInKernel("semantic error - attempt to violate Gmodel semantics" , "semantic errors", SemanticIdentityRegistry.semanticErr.ordinal());}
	public final	Identity semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor() {return createIdentityInKernel("addAbstract(category, semanticIdentity) is only valid for abstract VertexFlavored Instances" , "addConcrete(category, semanticIdentity) is only valid for abstract VertexFlavored Instances", SemanticIdentityRegistry.semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor.ordinal());}
	public final	Identity semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor() {return createIdentityInKernel("addConcrete(category, semanticIdentity) is only valid for concrete VertexFlavored Instances" , "addConcrete(category, semanticIdentity) is only valid for concrete VertexFlavored Instances", SemanticIdentityRegistry.semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor.ordinal());}
	public final    Identity semanticErr_ASetWithThisIdentityAndRepresentationIsAlreadyLoaded() {return createIdentityInKernel("attempt to ceate a second copy of a set in memory","a set with this identity and representation is already loaded", SemanticIdentityRegistry.semanticErr_ASetWithThisIdentityAndRepresentationIsAlreadyLoaded.ordinal());}
	public final	Identity semanticErr_GraphGraphCantBeModified() {return createIdentityInKernel("Graph.graph is the representation of the Gmodel kernel and can't be modified" , "Graph.graph is the representation of the Gmodel kernel and can't be modified", SemanticIdentityRegistry.semanticErr_GraphGraphCantBeModified.ordinal());}
	public final	Identity semanticErr_GraphsCantBeDecommissioned() {return createIdentityInKernel("Graphs are transient and can't be decommissioned" , "Graphs are transient and can't be decommissioned", SemanticIdentityRegistry.semanticErr_GraphsCantBeDecommissioned.ordinal());}
	public final	Identity semanticErr_LinkIsNotApplicable() {return createIdentityInKernel("link(isInformation, fromInstance, toInstance) is NOTAPPLICABLE" , "link(isInformation, fromInstance, toInstance) is only applicable when the isInformation is of visibility, edgeTrace, or generalizationReference flavor", SemanticIdentityRegistry.semanticErr_LinkIsNotApplicable.ordinal());}
	public final	Identity semanticErr_maxFromCardinalityIsIllegal() {return createIdentityInKernel("maximumFromCardinality is illegal" , "maximumFromCardinality is illegal", SemanticIdentityRegistry.semanticErr_maxFromCardinalityIsIllegal.ordinal());}
	public final	Identity semanticErr_maxFromCardinalityIsOne() {return createIdentityInKernel("maximumFromCardinality is 1" , "maximumFromCardinality is 1", SemanticIdentityRegistry.semanticErr_maxFromCardinalityIsOne.ordinal());}
	public final	Identity semanticErr_maxFromCardinalityIsTwo() {return createIdentityInKernel("maximumFromCardinality is 2" , "maximumFromCardinality is 2", SemanticIdentityRegistry.semanticErr_maxFromCardinalityIsTwo.ordinal());}
	public final	Identity semanticErr_maxToCardinalityIsIllegal() {return createIdentityInKernel("maximumToCardinality is illegal" , "maximumToCardinality is illegal", SemanticIdentityRegistry.semanticErr_maxToCardinalityIsIllegal.ordinal());}
	public final	Identity semanticErr_maxToCardinalityIsOne() {return createIdentityInKernel("maximumToCardinality is 1" , "maximumToCardinality is 1", SemanticIdentityRegistry.semanticErr_maxToCardinalityIsOne.ordinal());}
	public final	Identity semanticErr_maxToCardinalityIsTwo() {return createIdentityInKernel("maximumToCardinality is 2" , "maximumToCardinality is 2", SemanticIdentityRegistry.semanticErr_maxToCardinalityIsTwo.ordinal());}
	public final	Identity semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeEnds instances have an edgeEndVertex", SemanticIdentityRegistry.semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex.ordinal());}
	public final	Identity semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeEnds instances have an isContainer value", SemanticIdentityRegistry.semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer.ordinal());}
	public final	Identity semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeEnds instances have an isNavigable value", SemanticIdentityRegistry.semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable.ordinal());}
	public final	Identity semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeEnds instances have a maxCardinality value", SemanticIdentityRegistry.semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality.ordinal());}
	public final	Identity semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeEnds instances have a minCardinality value", SemanticIdentityRegistry.semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality.ordinal());}
	public final	Identity semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeFlavored instances have connected to/fromInstances", SemanticIdentityRegistry.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances.ordinal());}
	public final	Identity semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeFlavored instances have connected to/fromRoles", SemanticIdentityRegistry.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles.ordinal());}
	public final	Identity semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeFlavored instances have edgeEndFlavors", SemanticIdentityRegistry.semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors.ordinal());}
	public final	Identity semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeTraceFlavored instances have an abstraction", SemanticIdentityRegistry.semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction.ordinal());}
	public final	Identity semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only edgeTraceFlavored instances have a detail", SemanticIdentityRegistry.semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail.ordinal());}
	public final	Identity semanticErr_OnlyInstancesHaveIsAbstract() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only instances have an isAbstract value", SemanticIdentityRegistry.semanticErr_OnlyInstancesHaveIsAbstract.ordinal());}
	public final	Identity semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only generalizationReferenceFlavored instances have a specialization", SemanticIdentityRegistry.semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet.ordinal());}
	public final	Identity semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only generalizationReferenceFlavored instances have a generalization", SemanticIdentityRegistry.semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet.ordinal());}
	public final    Identity semanticErr_OnlyTransportContainerCanHaveContentElements() {return createIdentityInKernel("only transport container can have content elements" , "only transport container can have content elements", SemanticIdentityRegistry.semanticErr_OnlyTransportContainerCanHaveContentElements.ordinal());}
	public final	Identity semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only visibilityFlavored instances have a fromSubGraph", SemanticIdentityRegistry.semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph.ordinal());}
	public final	Identity semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph() {return createIdentityInKernel("operation is NOTAPPLICABLE" , "Only visibilityFlavored instances have a toSubGraph", SemanticIdentityRegistry.semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph.ordinal());}
	public final	Identity semanticErr_operationIsIllegalOnThisInstance() {return createIdentityInKernel("operation is illegal on this instance" , "operation is illegal on this instance", SemanticIdentityRegistry.semanticErr_operationIsIllegalOnThisInstance.ordinal());}
	public final    Identity semanticErr_operationIsNotYetImplemented() {return createIdentityInKernel("operation is not yet implemented" , "operation is not yet implemented", SemanticIdentityRegistry.semanticErr_operationIsNotYetImplemented.ordinal());}
	public final	Identity semanticErr_TargetIsNotWithinVisibility() {return createIdentityInKernel("target of link is NOTWITHINVISIBILITY" , "the target of an AbstractEdgeFlavored link must be within the range of visibilities defined in the category of the source", SemanticIdentityRegistry.semanticErr_TargetIsNotWithinVisibility.ordinal());}
	public final    Identity semanticErr_ThisSetIsNotAvailableInMemory() {return createIdentityInKernel("this set is not available in memory","this set has not been loaded", SemanticIdentityRegistry.semanticErr_ThisSetIsNotAvailableInMemory.ordinal());}
	public final	Identity semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance() {return createIdentityInKernel("the Value is not an instance of a Variable of the isInformation of this Set" , "the Value is not an instance of a Variable of the isInformation of this Set", SemanticIdentityRegistry.semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance.ordinal());}
	public final	Identity semanticErr_ValueIsNotAssigned() {return createIdentityInKernel("property is NOTASSIGNED" , "Property has not been assigned", SemanticIdentityRegistry.semanticErr_ValueIsNotAssigned.ordinal());}
	public final	Identity semanticErr_VariableCantBeRemovedArtifactStillHasInstances() {return createIdentityInKernel("Variable can't be removed, container still has instances" , "Variable can't be removed, container still has instances", SemanticIdentityRegistry.semanticErr_VariableCantBeRemovedArtifactStillHasInstances.ordinal());}
	public final    Identity semanticIdentity() {return createIdentityInKernel("semantic identity","semantic identities", SemanticIdentityRegistry.semanticIdentity.ordinal());}
	public final    Identity semanticIdentitySet() {return createIdentityInKernel("semantic identity set","semantic identity sets", SemanticIdentityRegistry.semanticIdentitySet.ordinal());}
	public final    Identity semanticRole() {return createIdentityInKernel("semantic role","semantic roles", SemanticIdentityRegistry.semanticRole.ordinal());}
	public final	Identity set() {return identityFactory.createIdentityInKernel("set", "sets", SemanticIdentityRegistry.set.ordinal());}
	public final	Identity setMaintenanceCommand() {return createIdentityInKernel("setMaintenanceCommand" , "setMaintenanceCommand", SemanticIdentityRegistry.setMaintenanceCommand.ordinal());}
	public final	Identity setValue() {return createIdentityInKernel("setValue" , "setValue", SemanticIdentityRegistry.setPropertyValue.ordinal());}
	public final	Identity size() {return createIdentityInKernel("size" , "size", SemanticIdentityRegistry.size.ordinal());}
	public final	Identity subGraph() {return createIdentityInKernel("subGraph" , "subGraphs", SemanticIdentityRegistry.subGraph.ordinal());}
	public final 	Identity superSetReference() {return createIdentityInKernel("super set reference" , "super set references", SemanticIdentityRegistry.superSetReference.ordinal());}

	public final	Identity target() {return createIdentityInKernel("target" , "targets", SemanticIdentityRegistry.target.ordinal());}
	public final	Identity technicalName() {return createIdentityInKernel("technicalName" , "technicalName", SemanticIdentityRegistry.technicalName.ordinal());}
	public final	Identity to() {return createIdentityInKernel("to" , "to", SemanticIdentityRegistry.to.ordinal());}
	public final	Identity toArray() {return createIdentityInKernel("toArray" , "toArray", SemanticIdentityRegistry.toArray.ordinal());}
	public final	Identity toArrayInstance() {return createIdentityInKernel("toArray(Set)" , "toArray(Set)", SemanticIdentityRegistry.toArrayInstance.ordinal());}
	public final	Identity toEdgeEnd() {return createIdentityInKernel("toEdgeEnd" , "toEdgeEnd", SemanticIdentityRegistry.toEdgeEnd.ordinal());}

	public final	Identity union() {return createIdentityInKernel("union" , "union", SemanticIdentityRegistry.union.ordinal());}

	public final	Identity value() {return createIdentityInKernel("value" , "value", SemanticIdentityRegistry.value.ordinal());}
	public final	Identity values() {return createIdentityInKernel("values" , "values", SemanticIdentityRegistry.values.ordinal());}
	public final    Identity variabilityDimension() {return createIdentityInKernel("variability dimension", "variability dimensions", SemanticIdentityRegistry.variabilityDimension.ordinal());}
	public final	Identity variables() {return createIdentityInKernel("variables" , "variables", SemanticIdentityRegistry.variables.ordinal());}
	public final    Identity variantDisjunctSemanticIdentitySet() {return createIdentityInKernel("variant disjunct semantic identity set","variant disjunct semantic identity sets", SemanticIdentityRegistry.variantDisjunctSemanticIdentitySet.ordinal());}
	public final    Identity variantIdentifier() {return createIdentityInKernel("variant identifier", "variant identifiers", SemanticIdentityRegistry.variantIdentifier.ordinal());}
	public final 	Identity vertex() {return createIdentityInKernel("vertex" , "vertices", SemanticIdentityRegistry.vertex.ordinal());}
	public final 	Identity visibility() {return createIdentityInKernel("visibility" , "visibilitis", SemanticIdentityRegistry.visibility.ordinal());}
	public final	Identity visibleArtifactsForSubGraph() {return createIdentityInKernel("visibleArtifactsForSubGraph" , "visibleArtifactsForSubGraph", SemanticIdentityRegistry.visibleArtifactsForSubGraph.ordinal());}

}
