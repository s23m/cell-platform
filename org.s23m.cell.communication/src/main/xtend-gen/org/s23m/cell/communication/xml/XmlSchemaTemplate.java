package org.s23m.cell.communication.xml;

import java.util.LinkedHashMap;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.s23m.cell.communication.xml.Extensions;
import org.s23m.cell.communication.xml.SchemaBuilder;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Node;
import org.s23m.cell.communication.xml.schema.Cardinality;
import org.s23m.cell.communication.xml.schema.ComplexType;
import org.s23m.cell.communication.xml.schema.DataType;
import org.s23m.cell.communication.xml.schema.Element;
import org.s23m.cell.communication.xml.schema.ElementReference;
import org.s23m.cell.communication.xml.schema.Extension;
import org.s23m.cell.communication.xml.schema.Schema;
import org.s23m.cell.communication.xml.schema.Sequence;
import org.s23m.cell.communication.xml.schema.SimpleType;

@SuppressWarnings("all")
public class XmlSchemaTemplate {
  /**
   * Schema constants
   */
  private static String XSD = "xsd";
  
  private static String XSD_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  
  /**
   * Namespace-related constants
   */
  private static String S23M = "s23m";
  
  private static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012";
  
  public Schema createSchemaModel(final XmlSchemaTerminology terminology) {
    Schema _xblockexpression = null;
    {
      String _semanticIdentity = terminology.semanticIdentity();
      final String semanticIdentity = _semanticIdentity;
      String _model = terminology.model();
      final String model = _model;
      String _category = terminology.category();
      final String category = _category;
      String _isAbstract = terminology.isAbstract();
      final String isAbstract = _isAbstract;
      String _maxCardinality = terminology.maxCardinality();
      final String maxCardinality = _maxCardinality;
      String _minCardinality = terminology.minCardinality();
      final String minCardinality = _minCardinality;
      String _isContainer = terminology.isContainer();
      final String isContainer = _isContainer;
      String _isNavigable = terminology.isNavigable();
      final String isNavigable = _isNavigable;
      String _from = terminology.from();
      final String from = _from;
      String _to = terminology.to();
      final String to = _to;
      String _graph = terminology.graph();
      final String graph = _graph;
      String _vertex = terminology.vertex();
      final String vertex = _vertex;
      String _visibility = terminology.visibility();
      final String visibility = _visibility;
      String _edge = terminology.edge();
      final String edge = _edge;
      String _edgeEnd = terminology.edgeEnd();
      final String edgeEnd = _edgeEnd;
      String _superSetReference = terminology.superSetReference();
      final String superSetReference = _superSetReference;
      String _command = terminology.command();
      final String command = _command;
      String _query = terminology.query();
      final String query = _query;
      String _function = terminology.function();
      final String function = _function;
      String _artifactSet = terminology.artifactSet();
      final String artifactSet = _artifactSet;
      final Procedure1<Schema> _function_1 = new Procedure1<Schema>() {
          public void apply(final Schema it) {
            LinkedHashMap<String,String> _attributes = it.getAttributes();
            String _xmlns = XmlSchemaTemplate.xmlns(XmlSchemaTemplate.XSD);
            Pair<String,String> _operator_mappedTo = ObjectExtensions.<String, String>operator_mappedTo(_xmlns, XmlSchemaTemplate.XSD_SCHEMA);
            String _xmlns_1 = XmlSchemaTemplate.xmlns(XmlSchemaTemplate.S23M);
            Pair<String,String> _operator_mappedTo_1 = ObjectExtensions.<String, String>operator_mappedTo(_xmlns_1, XmlSchemaTemplate.S23M_SCHEMA);
            Pair<String,String> _operator_mappedTo_2 = ObjectExtensions.<String, String>operator_mappedTo("targetNamespace", XmlSchemaTemplate.S23M_SCHEMA);
            Pair<String,String> _operator_mappedTo_3 = ObjectExtensions.<String, String>operator_mappedTo("elementFormDefault", "qualified");
            Pair<String,String> _operator_mappedTo_4 = ObjectExtensions.<String, String>operator_mappedTo("attributeFormDefault", "unqualified");
            LinkedHashMap<String,String> _newLinkedHashMap = CollectionLiterals.<String, String>newLinkedHashMap(_operator_mappedTo, _operator_mappedTo_1, _operator_mappedTo_2, _operator_mappedTo_3, _operator_mappedTo_4);
            Extensions.<String, String>operator_add(_attributes, _newLinkedHashMap);
          }
        };
      SchemaBuilder _schemaBuilder = new SchemaBuilder(artifactSet, _function_1);
      final SchemaBuilder builder = _schemaBuilder;
      String _uuid = terminology.uuid();
      SimpleType _simpleType = builder.simpleType(_uuid, DataType.STRING);
      final SimpleType uuid = _simpleType;
      String _identityReference = terminology.identityReference();
      final Procedure1<Sequence> _function_2 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _uniqueRepresentationReference = terminology.uniqueRepresentationReference();
              Element _element = builder.element(_uniqueRepresentationReference, uuid);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _identifier = terminology.identifier();
              Element _element_1 = builder.element(_identifier, uuid);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
            }
          }
        };
      ComplexType _complexType = builder.complexType(_identityReference, _function_2);
      final ComplexType identityReference = _complexType;
      Element _element = builder.element(semanticIdentity, identityReference);
      final Element semanticIdentityElement = _element;
      Element _element_1 = builder.element(isAbstract, identityReference);
      final Element isAbstractElement = _element_1;
      Element _element_2 = builder.element(minCardinality, identityReference);
      final Element minCardinalityElement = _element_2;
      Element _element_3 = builder.element(maxCardinality, identityReference);
      final Element maxCardinalityElement = _element_3;
      Element _element_4 = builder.element(isContainer, identityReference);
      final Element isContainerElement = _element_4;
      Element _element_5 = builder.element(isNavigable, identityReference);
      final Element isNavigableElement = _element_5;
      Element _element_6 = builder.element(from, identityReference);
      final Element fromElement = _element_6;
      Element _element_7 = builder.element(to, identityReference);
      final Element toElement = _element_7;
      Element _element_8 = builder.element(category, identityReference);
      final Element categoryElement = _element_8;
      final Procedure1<Sequence> _function_3 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              CollectionExtensions.<Element>operator_add(_children, semanticIdentityElement);
              List<Node> _children_1 = it.getChildren();
              CollectionExtensions.<Element>operator_add(_children_1, categoryElement);
            }
          }
        };
      ComplexType _complexType_1 = builder.complexType(category, _function_3);
      final ComplexType categoryComplexType = _complexType_1;
      final Procedure1<Sequence> _function_4 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = builder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = builder.element(maxCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
            }
          }
        };
      Extension _withExtension = SchemaBuilder.withExtension(categoryComplexType, _function_4);
      ComplexType _complexType_2 = builder.complexType(vertex, _withExtension);
      final ComplexType vertexComplexType = _complexType_2;
      final Procedure1<Sequence> _function_5 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = builder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = builder.element(fromElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = builder.element(toElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_1 = SchemaBuilder.withExtension(categoryComplexType, _function_5);
      ComplexType _complexType_3 = builder.complexType(visibility, _withExtension_1);
      final ComplexType visibilityComplexType = _complexType_3;
      final Procedure1<Sequence> _function_6 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = builder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = builder.element(fromElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = builder.element(toElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_2 = SchemaBuilder.withExtension(categoryComplexType, _function_6);
      ComplexType _complexType_4 = builder.complexType(superSetReference, _withExtension_2);
      final ComplexType superSetReferenceComplexType = _complexType_4;
      final Procedure1<Sequence> _function_7 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = builder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = builder.element(minCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = builder.element(maxCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              ElementReference _element_3 = builder.element(isContainerElement);
              CollectionExtensions.<ElementReference>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              ElementReference _element_4 = builder.element(isNavigableElement);
              CollectionExtensions.<ElementReference>operator_add(_children_4, _element_4);
            }
          }
        };
      Extension _withExtension_3 = SchemaBuilder.withExtension(categoryComplexType, _function_7);
      ComplexType _complexType_5 = builder.complexType(edgeEnd, _withExtension_3);
      final ComplexType edgeEndComplexType = _complexType_5;
      final Procedure1<Sequence> _function_8 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = builder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              Element _element_1 = builder.element(from, edgeEndComplexType);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              Element _element_2 = builder.element(to, edgeEndComplexType);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_4 = SchemaBuilder.withExtension(categoryComplexType, _function_8);
      ComplexType _complexType_6 = builder.complexType(edge, _withExtension_4);
      final ComplexType edgeComplexType = _complexType_6;
      String _parameter = terminology.parameter();
      final String parameter = _parameter;
      Extension _withExtension_5 = SchemaBuilder.withExtension(categoryComplexType);
      ComplexType _complexType_7 = builder.complexType(parameter, _withExtension_5);
      final ComplexType parameterComplexType = _complexType_7;
      final Procedure1<Sequence> _function_9 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            List<Node> _children = it.getChildren();
            Element _element = builder.element(parameter, parameterComplexType, Cardinality.ZERO_TO_MANY);
            CollectionExtensions.<Element>operator_add(_children, _element);
          }
        };
      Extension _withExtension_6 = SchemaBuilder.withExtension(categoryComplexType, _function_9);
      ComplexType _complexType_8 = builder.complexType(function, _withExtension_6);
      final ComplexType functionComplexType = _complexType_8;
      Extension _withExtension_7 = SchemaBuilder.withExtension(functionComplexType);
      ComplexType _complexType_9 = builder.complexType(command, _withExtension_7);
      final ComplexType commandComplexType = _complexType_9;
      Extension _withExtension_8 = SchemaBuilder.withExtension(functionComplexType);
      ComplexType _complexType_10 = builder.complexType(query, _withExtension_8);
      final ComplexType queryComplexType = _complexType_10;
      final Procedure1<Sequence> _function_10 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _container = terminology.container();
              Element _element = builder.element(_container, identityReference);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = builder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              Element _element_2 = builder.element(vertex, vertexComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              Element _element_3 = builder.element(visibility, visibilityComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              Element _element_4 = builder.element(edge, edgeComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_4, _element_4);
              List<Node> _children_5 = it.getChildren();
              Element _element_5 = builder.element(superSetReference, superSetReferenceComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_5, _element_5);
              List<Node> _children_6 = it.getChildren();
              Element _element_6 = builder.element(command, commandComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_6, _element_6);
              List<Node> _children_7 = it.getChildren();
              Element _element_7 = builder.element(query, queryComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_7, _element_7);
            }
          }
        };
      Extension _withExtension_9 = SchemaBuilder.withExtension(categoryComplexType, _function_10);
      ComplexType _complexType_11 = builder.complexType(graph, _withExtension_9);
      final ComplexType graphComplexType = _complexType_11;
      Extension _withExtension_10 = SchemaBuilder.withExtension(graphComplexType);
      ComplexType _complexType_12 = builder.complexType(model, _withExtension_10);
      final ComplexType modelComplexType = _complexType_12;
      Element _element_9 = builder.element(model, modelComplexType);
      final Element modelElement = _element_9;
      String _identity = terminology.identity();
      final Procedure1<Sequence> _function_11 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _identifier = terminology.identifier();
              Element _element = builder.element(_identifier, uuid);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _name = terminology.name();
              Element _element_1 = builder.element(_name, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              String _pluralName = terminology.pluralName();
              Element _element_2 = builder.element(_pluralName, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              String _payload = terminology.payload();
              Element _element_3 = builder.element(_payload, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              String _technicalName = terminology.technicalName();
              Element _element_4 = builder.element(_technicalName, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_4, _element_4);
            }
          }
        };
      ComplexType _complexType_13 = builder.complexType(_identity, _function_11);
      final ComplexType identityComplexType = _complexType_13;
      String _semanticDomain = terminology.semanticDomain();
      final Procedure1<Sequence> _function_12 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = builder.element(modelElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _identity = terminology.identity();
              Element _element_1 = builder.element(_identity, identityComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
            }
          }
        };
      ComplexType _complexType_14 = builder.complexType(_semanticDomain, _function_12);
      final ComplexType semanticDomainComplexType = _complexType_14;
      final Procedure1<Sequence> _function_13 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _languageIdentifier = terminology.languageIdentifier();
              Element _element = builder.element(_languageIdentifier, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = builder.element(modelElement, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              String _semanticDomain = terminology.semanticDomain();
              Element _element_2 = builder.element(_semanticDomain, semanticDomainComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
            }
          }
        };
      ComplexType _complexType_15 = builder.complexType(artifactSet, _function_13);
      final ComplexType artifactSetComplexType = _complexType_15;
      builder.element(artifactSet, artifactSetComplexType);
      Schema _build = builder.build();
      _xblockexpression = (_build);
    }
    return _xblockexpression;
  }
  
  private static String xmlns(final String name) {
    String _qualifiedName = XmlSchemaTemplate.qualifiedName("xmlns", name);
    return _qualifiedName;
  }
  
  private static String qualifiedName(final String namespacePrefix, final String name) {
    String _operator_plus = StringExtensions.operator_plus(namespacePrefix, ":");
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, name);
    return _operator_plus_1;
  }
}
