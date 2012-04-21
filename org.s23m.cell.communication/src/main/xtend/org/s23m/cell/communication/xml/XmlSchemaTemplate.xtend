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
 * The Original Code is Cell.
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

import static java.util.Arrays.*
import static extension org.eclipse.xtext.xbase.lib.IterableExtensions.*
import static extension java.lang.String.*

import static extension org.s23m.cell.communication.xml.Extensions.*

import static extension org.s23m.cell.communication.xml.SchemaBuilder.*
import org.s23m.cell.communication.xml.schema.Cardinality
import org.s23m.cell.communication.xml.schema.DataType
import org.s23m.cell.communication.xml.schema.Schema

class XmlSchemaTemplate {
	
	/* Schema constants */
	
	static String XSD = "xsd"
	
	static String XSD_SCHEMA = "http://www.w3.org/2001/XMLSchema"
	
	/* Namespace-related constants */
	
	static String S23M = "s23m"
	
	static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012"
	
	def Schema createSchemaModel(XmlSchemaTerminology terminology) {
		val semanticIdentity = terminology.semanticIdentity
		val model = terminology.model
		val category = terminology.category
		val isAbstract = terminology.isAbstract
		val maxCardinality = terminology.maxCardinality
		val minCardinality = terminology.minCardinality
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
		
		val builder = new SchemaBuilder([
			attributes += newLinkedHashMap(
				xmlns(XSD) -> XSD_SCHEMA,
				xmlns(S23M) -> S23M_SCHEMA,
				"targetNamespace" -> S23M_SCHEMA,
				"elementFormDefault" -> "qualified",
				"attributeFormDefault" -> "unqualified"
			)
		])
	
		val uuid = builder.simpleType(terminology.uuid, DataType::STRING)
		
		val identityReference = builder.complexType(terminology.identityReference, [
			children += builder.element(terminology.uniqueRepresentationReference, uuid)
			children += builder.element(terminology.identifier, uuid)
		])
				
		val semanticIdentityElement = builder.element(semanticIdentity, identityReference)
		
		val isAbstractElement = builder.element(isAbstract, identityReference)
		
		val minCardinalityElement = builder.element(minCardinality, identityReference)
		
		val maxCardinalityElement = builder.element(maxCardinality, identityReference)
		
		val isContainerElement = builder.element(isContainer, identityReference)
		
		val isNavigableElement = builder.element(isNavigable, identityReference)
		
		val fromElement = builder.element(from, identityReference)
	
		val toElement = builder.element(to, identityReference)
		
		val categoryElement = builder.element(category, identityReference)
	
		val categoryComplexType = builder.complexType(category, [
			children += semanticIdentityElement
			children += categoryElement
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
			children += builder.element(minCardinalityElement)
			children += builder.element(maxCardinalityElement)
			children += builder.element(isContainerElement)
			children += builder.element(isNavigableElement)
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
			children += builder.element(parameter, parameterComplexType, Cardinality::ZERO_TO_MANY)
		]))

		val commandComplexType = builder.complexType(command, withExtension(functionComplexType))

		val queryComplexType = builder.complexType(query, withExtension(functionComplexType))

		/* Encoding of model artifacts */
	
		val graphComplexType = builder.complexType(graph, withExtension(categoryComplexType, [
			children += builder.element(terminology.container, identityReference)
			children += builder.element(isAbstractElement)
			children += builder.element(vertex, vertexComplexType, Cardinality::ZERO_TO_MANY)
			children += builder.element(visibility, visibilityComplexType, Cardinality::ZERO_TO_MANY)
			children += builder.element(edge, edgeComplexType, Cardinality::ZERO_TO_MANY)
			children += builder.element(superSetReference, superSetReferenceComplexType, Cardinality::ZERO_TO_MANY)
			children += builder.element(command, commandComplexType, Cardinality::ZERO_TO_MANY)
			children += builder.element(query, queryComplexType, Cardinality::ZERO_TO_MANY)
		]))
		
		val modelComplexType = builder.complexType(model, withExtension(graphComplexType))
		
		val modelElement = builder.element(model, modelComplexType)
		
		val identityComplexType = builder.complexType(terminology.identity, [
			children += builder.element(terminology.identifier, uuid)
			children += builder.element(terminology.name, DataType::STRING)
			children += builder.element(terminology.pluralName, DataType::STRING)
			children += builder.element(terminology.payload, DataType::STRING)
			children += builder.element(terminology.technicalName, DataType::STRING)
		])

		/* Encoding of semantic domain artifacts */
		val semanticDomainComplexType = builder.complexType(terminology.semanticDomain, [
			children += builder.element(modelElement)
			children += builder.element(terminology.identity, identityComplexType, Cardinality::ZERO_TO_MANY)
		])

		val artifactSetComplexType = builder.complexType(terminology.artifactSet, [
			children += builder.element(modelElement, Cardinality::ZERO_TO_MANY)
			children += builder.element(terminology.semanticDomain, semanticDomainComplexType, Cardinality::ZERO_TO_MANY)
		])
		
		/* Root element */
		
		builder.element(terminology.artifactSet, artifactSetComplexType)
		
		builder.build
	}
	
	def private static String xmlns(String name) {
		qualifiedName("xmlns", name)
	}
	
	def private static String qualifiedName(String namespacePrefix, String name) {
		namespacePrefix + ":" + name
	}
}
