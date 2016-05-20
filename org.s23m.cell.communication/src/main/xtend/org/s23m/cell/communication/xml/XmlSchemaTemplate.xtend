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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.model.schema.Schema

import static java.util.Arrays.*
import static org.s23m.cell.communication.xml.model.schema.Cardinality.*
import static org.s23m.cell.communication.xml.model.schema.DataType.*

import static extension org.s23m.cell.communication.xml.SchemaBuilder.*

class XmlSchemaTemplate {
	
	def Schema createSchemaModel(XmlSchemaTerminology terminology) {
		val semanticIdentity = terminology.semanticIdentity
		val model = terminology.model
		val category = terminology.category
		val isAbstract = terminology.isAbstract
		val maxCardinality = terminology.maximumCardinality
		val minCardinality = terminology.minimumCardinality
		val isContainer = terminology.isContainer
		val isNavigable = terminology.isNavigable
		val from = terminology.from
		val to = terminology.to		
		val graph = terminology.graph 
		val vertex = terminology.vertex
		val visibility = terminology.visibility
		val edge = terminology.edge
		val edgeEnd = terminology.edgeEnd
		val superSetReference = terminology.superSetReference
		val command = terminology.command
		val query = terminology.query
		val function = terminology.function
		val artifactSet = terminology.artifactSet
		val structure = terminology.structure 
		
		val builder = new SchemaBuilder(artifactSet)
	
		val uuid = builder.simpleType(terminology.uuid, STRING)
		
		val identityReference = builder.complexType(terminology.identityReference, asList(
			builder.optionalAttribute(terminology.name, STRING),
			builder.mandatoryAttribute(terminology.uniqueRepresentationReference, uuid),
			builder.mandatoryAttribute(terminology.identifier, uuid)
		))
		
		val isAbstractElement = builder.element(isAbstract, identityReference)
		
		val maxCardinalityElement = builder.element(maxCardinality, identityReference)
		
		val fromElement = builder.element(from, identityReference)
	
		val toElement = builder.element(to, identityReference)
		
		val categoryComplexType = builder.complexType(category, [
			children += builder.element(semanticIdentity, identityReference)
			children += builder.element(category, identityReference)
		])
		
		val vertexComplexType = builder.complexType(vertex, withExtension(categoryComplexType, [
			children += builder.element(isAbstractElement)
			children += builder.element(maxCardinalityElement)
		]))
		
		val visibilityComplexType = builder.complexType(visibility, withExtension(categoryComplexType, [
			children += builder.element(isAbstractElement)
			children += builder.element(fromElement)
			children += builder.element(toElement)
		]))
		
		val superSetReferenceComplexType = builder.complexType(superSetReference, withExtension(categoryComplexType, [
			children += builder.element(isAbstractElement)
			children += builder.element(fromElement)
			children += builder.element(toElement)
		]))
		
		val edgeEndComplexType = builder.complexType(edgeEnd, withExtension(categoryComplexType, [
			children += builder.element(isAbstractElement)
			children += builder.element(minCardinality, identityReference)		
			children += builder.element(maxCardinalityElement)
			children += builder.element(isContainer, identityReference)
			children += builder.element(isNavigable, identityReference)		
		]))
		
		val edgeComplexType = builder.complexType(edge, withExtension(categoryComplexType, [
			children += builder.element(isAbstractElement)
			children += builder.element(from, edgeEndComplexType)
			children += builder.element(to, edgeEndComplexType)
		]))
		
		/* Artifact functionality */

		val parameter = terminology.parameter

		val parameterComplexType = builder.complexType(parameter, withExtension(categoryComplexType))
		
		val functionComplexType = builder.complexType(function, withExtension(categoryComplexType, [
			children += builder.element(parameter, parameterComplexType, ZERO_TO_MANY)
		]))

		val commandComplexType = builder.complexType(command, withExtension(functionComplexType))

		val queryComplexType = builder.complexType(query, withExtension(functionComplexType))

		/* Encoding of model artifacts */
	
		val graphComplexType = builder.complexType(graph, withExtension(categoryComplexType, [
			children += builder.element(terminology.container(), identityReference)
			children += builder.element(isAbstractElement)
			children += builder.element(vertex, vertexComplexType, ZERO_TO_MANY)
			children += builder.element(visibility, visibilityComplexType, ZERO_TO_MANY)
			children += builder.element(edge, edgeComplexType, ZERO_TO_MANY)
			children += builder.element(superSetReference, superSetReferenceComplexType, ZERO_TO_MANY)
			children += builder.element(command, commandComplexType, ZERO_TO_MANY)
			children += builder.element(query, queryComplexType, ZERO_TO_MANY)
		]))
		
		val identityComplexType = builder.complexType(terminology.identity,
			asList(
				builder.mandatoryAttribute(terminology.identifier, uuid),
				builder.mandatoryAttribute(terminology.name, STRING),
				builder.mandatoryAttribute(terminology.pluralName, STRING),			
				builder.mandatoryAttribute(terminology.codeName, STRING),
				builder.mandatoryAttribute(terminology.pluralCodeName, STRING)
			), [
				children += builder.element(terminology.payload, STRING)
			]
		)

		val structureComplexType = builder.complexType(structure, withExtension(graphComplexType))
		
		val modelComplexType = builder.complexType(model, withExtension(structureComplexType))
		
		/* Encoding of semantic domain artifacts */
		val semanticDomainComplexType = builder.complexType(terminology.semanticDomain, withExtension(structureComplexType, [
			children += builder.element(terminology.identity, identityComplexType, ZERO_TO_MANY)
		]))
		
		val languageElement = builder.element(terminology.language, identityReference)
		
		val modelElement = builder.element(model, modelComplexType)
		
		val artifactSetComplexType = builder.complexType(artifactSet, [
			children += builder.element(languageElement)
			children += builder.element(modelElement, ZERO_TO_MANY)
			children += builder.element(terminology.semanticDomain, semanticDomainComplexType, ZERO_TO_MANY)
		])
		
		/* Root element */
		
		builder.element(artifactSet, artifactSetComplexType)
		
		builder.build
	}
}
