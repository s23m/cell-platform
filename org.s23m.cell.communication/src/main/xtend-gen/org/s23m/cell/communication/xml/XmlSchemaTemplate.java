/**
 * BEGIN LICENSE BLOCK
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
 * ***** END LICENSE BLOCK
 */
package org.s23m.cell.communication.xml;

import java.util.Arrays;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.s23m.cell.communication.xml.SchemaBuilder;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schema.Attribute;
import org.s23m.cell.communication.xml.model.schema.Cardinality;
import org.s23m.cell.communication.xml.model.schema.ComplexType;
import org.s23m.cell.communication.xml.model.schema.ConstrainedSimpleType;
import org.s23m.cell.communication.xml.model.schema.DataType;
import org.s23m.cell.communication.xml.model.schema.Element;
import org.s23m.cell.communication.xml.model.schema.ElementReference;
import org.s23m.cell.communication.xml.model.schema.Extension;
import org.s23m.cell.communication.xml.model.schema.Schema;
import org.s23m.cell.communication.xml.model.schema.Sequence;

@SuppressWarnings("all")
public class XmlSchemaTemplate {
  public Schema createSchemaModel(final XmlSchemaTerminology terminology) {
    Schema _xblockexpression = null;
    {
      final String semanticIdentity = terminology.semanticIdentity();
      final String model = terminology.model();
      final String category = terminology.category();
      final String isAbstract = terminology.isAbstract();
      final String maxCardinality = terminology.maximumCardinality();
      final String minCardinality = terminology.minimumCardinality();
      final String isContainer = terminology.isContainer();
      final String isNavigable = terminology.isNavigable();
      final String from = terminology.from();
      final String to = terminology.to();
      final String graph = terminology.graph();
      final String vertex = terminology.vertex();
      final String visibility = terminology.visibility();
      final String edge = terminology.edge();
      final String edgeEnd = terminology.edgeEnd();
      final String superSetReference = terminology.superSetReference();
      final String command = terminology.command();
      final String query = terminology.query();
      final String function = terminology.function();
      final String artifactSet = terminology.artifactSet();
      final String structure = terminology.structure();
      final SchemaBuilder builder = new SchemaBuilder(artifactSet);
      String _uuid = terminology.uuid();
      final ConstrainedSimpleType uuid = builder.simpleType(_uuid, DataType.STRING);
      String _identityReference = terminology.identityReference();
      String _name = terminology.name();
      Attribute _optionalAttribute = builder.optionalAttribute(_name, DataType.STRING);
      String _uniqueRepresentationReference = terminology.uniqueRepresentationReference();
      Attribute _mandatoryAttribute = builder.mandatoryAttribute(_uniqueRepresentationReference, uuid);
      String _identifier = terminology.identifier();
      Attribute _mandatoryAttribute_1 = builder.mandatoryAttribute(_identifier, uuid);
      List<Attribute> _asList = Arrays.<Attribute>asList(_optionalAttribute, _mandatoryAttribute, _mandatoryAttribute_1);
      final ComplexType identityReference = builder.complexType(_identityReference, _asList);
      final Element isAbstractElement = builder.element(isAbstract, identityReference);
      final Element maxCardinalityElement = builder.element(maxCardinality, identityReference);
      final Element fromElement = builder.element(from, identityReference);
      final Element toElement = builder.element(to, identityReference);
      final Procedure1<Sequence> _function = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        Element _element = builder.element(semanticIdentity, identityReference);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        Element _element_1 = builder.element(category, identityReference);
        _children_1.add(_element_1);
      };
      final ComplexType categoryComplexType = builder.complexType(category, _function);
      final Procedure1<Sequence> _function_1 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        ElementReference _element = builder.element(isAbstractElement);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        ElementReference _element_1 = builder.element(maxCardinalityElement);
        _children_1.add(_element_1);
      };
      Extension _withExtension = SchemaBuilder.withExtension(categoryComplexType, _function_1);
      final ComplexType vertexComplexType = builder.complexType(vertex, _withExtension);
      final Procedure1<Sequence> _function_2 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        ElementReference _element = builder.element(isAbstractElement);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        ElementReference _element_1 = builder.element(fromElement);
        _children_1.add(_element_1);
        List<Node> _children_2 = it.getChildren();
        ElementReference _element_2 = builder.element(toElement);
        _children_2.add(_element_2);
      };
      Extension _withExtension_1 = SchemaBuilder.withExtension(categoryComplexType, _function_2);
      final ComplexType visibilityComplexType = builder.complexType(visibility, _withExtension_1);
      final Procedure1<Sequence> _function_3 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        ElementReference _element = builder.element(isAbstractElement);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        ElementReference _element_1 = builder.element(fromElement);
        _children_1.add(_element_1);
        List<Node> _children_2 = it.getChildren();
        ElementReference _element_2 = builder.element(toElement);
        _children_2.add(_element_2);
      };
      Extension _withExtension_2 = SchemaBuilder.withExtension(categoryComplexType, _function_3);
      final ComplexType superSetReferenceComplexType = builder.complexType(superSetReference, _withExtension_2);
      final Procedure1<Sequence> _function_4 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        ElementReference _element = builder.element(isAbstractElement);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        Element _element_1 = builder.element(minCardinality, identityReference);
        _children_1.add(_element_1);
        List<Node> _children_2 = it.getChildren();
        ElementReference _element_2 = builder.element(maxCardinalityElement);
        _children_2.add(_element_2);
        List<Node> _children_3 = it.getChildren();
        Element _element_3 = builder.element(isContainer, identityReference);
        _children_3.add(_element_3);
        List<Node> _children_4 = it.getChildren();
        Element _element_4 = builder.element(isNavigable, identityReference);
        _children_4.add(_element_4);
      };
      Extension _withExtension_3 = SchemaBuilder.withExtension(categoryComplexType, _function_4);
      final ComplexType edgeEndComplexType = builder.complexType(edgeEnd, _withExtension_3);
      final Procedure1<Sequence> _function_5 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        ElementReference _element = builder.element(isAbstractElement);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        Element _element_1 = builder.element(from, edgeEndComplexType);
        _children_1.add(_element_1);
        List<Node> _children_2 = it.getChildren();
        Element _element_2 = builder.element(to, edgeEndComplexType);
        _children_2.add(_element_2);
      };
      Extension _withExtension_4 = SchemaBuilder.withExtension(categoryComplexType, _function_5);
      final ComplexType edgeComplexType = builder.complexType(edge, _withExtension_4);
      final String parameter = terminology.parameter();
      Extension _withExtension_5 = SchemaBuilder.withExtension(categoryComplexType);
      final ComplexType parameterComplexType = builder.complexType(parameter, _withExtension_5);
      final Procedure1<Sequence> _function_6 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        Element _element = builder.element(parameter, parameterComplexType, Cardinality.ZERO_TO_MANY);
        _children.add(_element);
      };
      Extension _withExtension_6 = SchemaBuilder.withExtension(categoryComplexType, _function_6);
      final ComplexType functionComplexType = builder.complexType(function, _withExtension_6);
      Extension _withExtension_7 = SchemaBuilder.withExtension(functionComplexType);
      final ComplexType commandComplexType = builder.complexType(command, _withExtension_7);
      Extension _withExtension_8 = SchemaBuilder.withExtension(functionComplexType);
      final ComplexType queryComplexType = builder.complexType(query, _withExtension_8);
      final Procedure1<Sequence> _function_7 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        String _container = terminology.container();
        Element _element = builder.element(_container, identityReference);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        ElementReference _element_1 = builder.element(isAbstractElement);
        _children_1.add(_element_1);
        List<Node> _children_2 = it.getChildren();
        Element _element_2 = builder.element(vertex, vertexComplexType, Cardinality.ZERO_TO_MANY);
        _children_2.add(_element_2);
        List<Node> _children_3 = it.getChildren();
        Element _element_3 = builder.element(visibility, visibilityComplexType, Cardinality.ZERO_TO_MANY);
        _children_3.add(_element_3);
        List<Node> _children_4 = it.getChildren();
        Element _element_4 = builder.element(edge, edgeComplexType, Cardinality.ZERO_TO_MANY);
        _children_4.add(_element_4);
        List<Node> _children_5 = it.getChildren();
        Element _element_5 = builder.element(superSetReference, superSetReferenceComplexType, Cardinality.ZERO_TO_MANY);
        _children_5.add(_element_5);
        List<Node> _children_6 = it.getChildren();
        Element _element_6 = builder.element(command, commandComplexType, Cardinality.ZERO_TO_MANY);
        _children_6.add(_element_6);
        List<Node> _children_7 = it.getChildren();
        Element _element_7 = builder.element(query, queryComplexType, Cardinality.ZERO_TO_MANY);
        _children_7.add(_element_7);
      };
      Extension _withExtension_9 = SchemaBuilder.withExtension(categoryComplexType, _function_7);
      final ComplexType graphComplexType = builder.complexType(graph, _withExtension_9);
      String _identity = terminology.identity();
      String _identifier_1 = terminology.identifier();
      Attribute _mandatoryAttribute_2 = builder.mandatoryAttribute(_identifier_1, uuid);
      String _name_1 = terminology.name();
      Attribute _mandatoryAttribute_3 = builder.mandatoryAttribute(_name_1, DataType.STRING);
      String _pluralName = terminology.pluralName();
      Attribute _mandatoryAttribute_4 = builder.mandatoryAttribute(_pluralName, DataType.STRING);
      String _codeName = terminology.codeName();
      Attribute _mandatoryAttribute_5 = builder.mandatoryAttribute(_codeName, DataType.STRING);
      String _pluralCodeName = terminology.pluralCodeName();
      Attribute _mandatoryAttribute_6 = builder.mandatoryAttribute(_pluralCodeName, DataType.STRING);
      List<Attribute> _asList_1 = Arrays.<Attribute>asList(_mandatoryAttribute_2, _mandatoryAttribute_3, _mandatoryAttribute_4, _mandatoryAttribute_5, _mandatoryAttribute_6);
      final Procedure1<Sequence> _function_8 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        String _payload = terminology.payload();
        Element _element = builder.element(_payload, DataType.STRING);
        _children.add(_element);
      };
      final ComplexType identityComplexType = builder.complexType(_identity, _asList_1, _function_8);
      Extension _withExtension_10 = SchemaBuilder.withExtension(graphComplexType);
      final ComplexType structureComplexType = builder.complexType(structure, _withExtension_10);
      Extension _withExtension_11 = SchemaBuilder.withExtension(structureComplexType);
      final ComplexType modelComplexType = builder.complexType(model, _withExtension_11);
      String _semanticDomain = terminology.semanticDomain();
      final Procedure1<Sequence> _function_9 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        String _identity_1 = terminology.identity();
        Element _element = builder.element(_identity_1, identityComplexType, Cardinality.ZERO_TO_MANY);
        _children.add(_element);
      };
      Extension _withExtension_12 = SchemaBuilder.withExtension(structureComplexType, _function_9);
      final ComplexType semanticDomainComplexType = builder.complexType(_semanticDomain, _withExtension_12);
      String _language = terminology.language();
      final Element languageElement = builder.element(_language, identityReference);
      final Element modelElement = builder.element(model, modelComplexType);
      final Procedure1<Sequence> _function_10 = (Sequence it) -> {
        List<Node> _children = it.getChildren();
        ElementReference _element = builder.element(languageElement);
        _children.add(_element);
        List<Node> _children_1 = it.getChildren();
        ElementReference _element_1 = builder.element(modelElement, Cardinality.ZERO_TO_MANY);
        _children_1.add(_element_1);
        List<Node> _children_2 = it.getChildren();
        String _semanticDomain_1 = terminology.semanticDomain();
        Element _element_2 = builder.element(_semanticDomain_1, semanticDomainComplexType, Cardinality.ZERO_TO_MANY);
        _children_2.add(_element_2);
      };
      final ComplexType artifactSetComplexType = builder.complexType(artifactSet, _function_10);
      builder.element(artifactSet, artifactSetComplexType);
      _xblockexpression = builder.build();
    }
    return _xblockexpression;
  }
}
