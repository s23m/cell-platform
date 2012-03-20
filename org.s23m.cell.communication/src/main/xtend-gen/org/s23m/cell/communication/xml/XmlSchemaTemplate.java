package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.s23m.cell.communication.xml.Extensions;
import org.s23m.cell.communication.xml.SchemaBuilder;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
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
  
  private static String XSD_STRING = new Function0<String>() {
    public String apply() {
      String _xsd = XmlSchemaTemplate.xsd("string");
      return _xsd;
    }
  }.apply();
  
  /**
   * Namespace-related constants
   */
  private static String S23M = "s23m";
  
  private static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012";
  
  private static String SCHEMA_ATTRIBUTES = new Function0<String>() {
    public String apply() {
      String _operator_plus = StringExtensions.operator_plus("xmlns:", XmlSchemaTemplate.XSD);
      String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, "=\"http://www.w3.org/2001/XMLSchema\" ");
      String _operator_plus_2 = StringExtensions.operator_plus(_operator_plus_1, "xmlns:");
      String _operator_plus_3 = StringExtensions.operator_plus(_operator_plus_2, XmlSchemaTemplate.S23M);
      String _operator_plus_4 = StringExtensions.operator_plus(_operator_plus_3, "=\"");
      String _operator_plus_5 = StringExtensions.operator_plus(_operator_plus_4, XmlSchemaTemplate.S23M_SCHEMA);
      String _operator_plus_6 = StringExtensions.operator_plus(_operator_plus_5, "\" ");
      String _operator_plus_7 = StringExtensions.operator_plus(_operator_plus_6, "targetNamespace=\"");
      String _operator_plus_8 = StringExtensions.operator_plus(_operator_plus_7, XmlSchemaTemplate.S23M_SCHEMA);
      String _operator_plus_9 = StringExtensions.operator_plus(_operator_plus_8, "\" ");
      String _operator_plus_10 = StringExtensions.operator_plus(_operator_plus_9, "elementFormDefault=\"qualified\" ");
      String _operator_plus_11 = StringExtensions.operator_plus(_operator_plus_10, "attributeFormDefault=\"unqualified\"");
      return _operator_plus_11;
    }
  }.apply();
  
  private static Namespace NS_S23M = new Function0<Namespace>() {
    public Namespace apply() {
      Namespace _namespace = new Namespace(XmlSchemaTemplate.S23M, XmlSchemaTemplate.S23M_SCHEMA);
      return _namespace;
    }
  }.apply();
  
  private XmlSchemaTerminology terminology;
  
  private String semanticIdentity;
  
  private String identityReference;
  
  private String identityReferenceQualified;
  
  private String model;
  
  private String category;
  
  private String uuid;
  
  private String uuidQualified;
  
  private String isAbstract;
  
  private String maxCardinality;
  
  private String minCardinality;
  
  private String isContainer;
  
  private String isNavigable;
  
  private String from;
  
  private String to;
  
  private String graph;
  
  private String vertex;
  
  private String visibility;
  
  private String edge;
  
  private String edgeEnd;
  
  private String superSetReference;
  
  private String command;
  
  private String query;
  
  private String function;
  
  public XmlSchemaTemplate(final XmlSchemaTerminology terminology) {
      this.terminology = terminology;
      String _semanticIdentity = terminology.semanticIdentity();
      this.semanticIdentity = _semanticIdentity;
      String _identityReference = terminology.identityReference();
      this.identityReference = _identityReference;
      String _s23m = XmlSchemaTemplate.s23m(this.identityReference);
      this.identityReferenceQualified = _s23m;
      String _model = terminology.model();
      this.model = _model;
      String _category = terminology.category();
      this.category = _category;
      String _uuid = terminology.uuid();
      this.uuid = _uuid;
      String _s23m_1 = XmlSchemaTemplate.s23m(this.uuid);
      this.uuidQualified = _s23m_1;
      String _isAbstract = terminology.isAbstract();
      this.isAbstract = _isAbstract;
      String _maxCardinality = terminology.maxCardinality();
      this.maxCardinality = _maxCardinality;
      String _minCardinality = terminology.minCardinality();
      this.minCardinality = _minCardinality;
      String _isContainer = terminology.isContainer();
      this.isContainer = _isContainer;
      String _isNavigable = terminology.isNavigable();
      this.isNavigable = _isNavigable;
      String _from = terminology.from();
      this.from = _from;
      String _to = terminology.to();
      this.to = _to;
      String _graph = terminology.graph();
      this.graph = _graph;
      String _vertex = terminology.vertex();
      this.vertex = _vertex;
      String _visibility = terminology.visibility();
      this.visibility = _visibility;
      String _edge = terminology.edge();
      this.edge = _edge;
      String _edgeEnd = terminology.edgeEnd();
      this.edgeEnd = _edgeEnd;
      String _superSetReference = terminology.superSetReference();
      this.superSetReference = _superSetReference;
      String _command = terminology.command();
      this.command = _command;
      String _query = terminology.query();
      this.query = _query;
      String _function = terminology.function();
      this.function = _function;
  }
  
  public String createSchemaModel() {
    String _xblockexpression = null;
    {
      final Procedure1<Schema> _function = new Procedure1<Schema>() {
          public void apply(final Schema it) {
            {
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
              List<Node> _children = it.getChildren();
              List<Element> _createReusedElements = XmlSchemaTemplate.this.createReusedElements();
              _children.addAll(_createReusedElements);
            }
          }
        };
      Schema _schema = SchemaBuilder.schema(_function);
      final Schema schema = _schema;
      LinkedHashMap<String,String> _attributes = schema.getAttributes();
      String _string = _attributes.toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
  
  private List<Element> createReusedElements() {
    ArrayList<Element> _xblockexpression = null;
    {
      SimpleType _simpleType = SchemaBuilder.simpleType(this.uuid, DataType.STRING);
      final SimpleType uuid = _simpleType;
      final Procedure1<Sequence> _function = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _uniqueRepresentationReference = XmlSchemaTemplate.this.terminology.uniqueRepresentationReference();
              Element _element = SchemaBuilder.element(_uniqueRepresentationReference, uuid);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _identifier = XmlSchemaTemplate.this.terminology.identifier();
              Element _element_1 = SchemaBuilder.element(_identifier, uuid);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
            }
          }
        };
      ComplexType _complexType = SchemaBuilder.complexType(this.identityReference, _function);
      final ComplexType identityReference = _complexType;
      Element _element = SchemaBuilder.element(this.semanticIdentity, identityReference);
      final Element semanticIdentityElement = _element;
      Element _element_1 = SchemaBuilder.element(this.isAbstract, identityReference);
      final Element isAbstractElement = _element_1;
      Element _element_2 = SchemaBuilder.element(this.minCardinality, identityReference);
      final Element minCardinalityElement = _element_2;
      Element _element_3 = SchemaBuilder.element(this.maxCardinality, identityReference);
      final Element maxCardinalityElement = _element_3;
      Element _element_4 = SchemaBuilder.element(this.isContainer, identityReference);
      final Element isContainerElement = _element_4;
      Element _element_5 = SchemaBuilder.element(this.isNavigable, identityReference);
      final Element isNavigableElement = _element_5;
      Element _element_6 = SchemaBuilder.element(this.from, identityReference);
      final Element fromElement = _element_6;
      Element _element_7 = SchemaBuilder.element(this.to, identityReference);
      final Element toElement = _element_7;
      Element _element_8 = SchemaBuilder.element(this.category, identityReference);
      final Element categoryElement = _element_8;
      final Procedure1<Sequence> _function_1 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              CollectionExtensions.<Element>operator_add(_children, semanticIdentityElement);
              List<Node> _children_1 = it.getChildren();
              CollectionExtensions.<Element>operator_add(_children_1, categoryElement);
            }
          }
        };
      ComplexType _complexType_1 = SchemaBuilder.complexType(this.category, _function_1);
      final ComplexType categoryComplexType = _complexType_1;
      final Procedure1<Sequence> _function_2 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(maxCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
            }
          }
        };
      Extension _withExtension = SchemaBuilder.withExtension(categoryComplexType, _function_2);
      ComplexType _complexType_2 = SchemaBuilder.complexType(this.vertex, _withExtension);
      final ComplexType vertexComplexType = _complexType_2;
      final Procedure1<Sequence> _function_3 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(fromElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = SchemaBuilder.element(toElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_1 = SchemaBuilder.withExtension(categoryComplexType, _function_3);
      ComplexType _complexType_3 = SchemaBuilder.complexType(this.visibility, _withExtension_1);
      final ComplexType visibilityComplexType = _complexType_3;
      final Procedure1<Sequence> _function_4 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(fromElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = SchemaBuilder.element(toElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_2 = SchemaBuilder.withExtension(categoryComplexType, _function_4);
      ComplexType _complexType_4 = SchemaBuilder.complexType(this.superSetReference, _withExtension_2);
      final ComplexType superSetReferenceComplexType = _complexType_4;
      final Procedure1<Sequence> _function_5 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(minCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = SchemaBuilder.element(maxCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              ElementReference _element_3 = SchemaBuilder.element(isContainerElement);
              CollectionExtensions.<ElementReference>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              ElementReference _element_4 = SchemaBuilder.element(isNavigableElement);
              CollectionExtensions.<ElementReference>operator_add(_children_4, _element_4);
            }
          }
        };
      Extension _withExtension_3 = SchemaBuilder.withExtension(categoryComplexType, _function_5);
      ComplexType _complexType_5 = SchemaBuilder.complexType(this.edgeEnd, _withExtension_3);
      final ComplexType edgeEndComplexType = _complexType_5;
      final Procedure1<Sequence> _function_6 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              Element _element_1 = SchemaBuilder.element(XmlSchemaTemplate.this.from, edgeEndComplexType);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              Element _element_2 = SchemaBuilder.element(XmlSchemaTemplate.this.to, edgeEndComplexType);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_4 = SchemaBuilder.withExtension(categoryComplexType, _function_6);
      ComplexType _complexType_6 = SchemaBuilder.complexType(this.edge, _withExtension_4);
      final ComplexType edgeComplexType = _complexType_6;
      String _parameter = this.terminology.parameter();
      final String parameter = _parameter;
      final Procedure1<Sequence> _function_7 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
          }
        };
      Extension _withExtension_5 = SchemaBuilder.withExtension(categoryComplexType, _function_7);
      ComplexType _complexType_7 = SchemaBuilder.complexType(parameter, _withExtension_5);
      final ComplexType parameterComplexType = _complexType_7;
      final Procedure1<Sequence> _function_8 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            List<Node> _children = it.getChildren();
            Element _element = SchemaBuilder.element(parameter, parameterComplexType, Cardinality.ZERO_TO_MANY);
            CollectionExtensions.<Element>operator_add(_children, _element);
          }
        };
      Extension _withExtension_6 = SchemaBuilder.withExtension(categoryComplexType, _function_8);
      ComplexType _complexType_8 = SchemaBuilder.complexType(this.function, _withExtension_6);
      final ComplexType functionComplexType = _complexType_8;
      final Procedure1<Sequence> _function_9 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
          }
        };
      Extension _withExtension_7 = SchemaBuilder.withExtension(functionComplexType, _function_9);
      ComplexType _complexType_9 = SchemaBuilder.complexType(this.command, _withExtension_7);
      final ComplexType commandComplexType = _complexType_9;
      final Procedure1<Sequence> _function_10 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
          }
        };
      Extension _withExtension_8 = SchemaBuilder.withExtension(functionComplexType, _function_10);
      ComplexType _complexType_10 = SchemaBuilder.complexType(this.query, _withExtension_8);
      final ComplexType queryComplexType = _complexType_10;
      final Procedure1<Sequence> _function_11 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _container = XmlSchemaTemplate.this.terminology.container();
              Element _element = SchemaBuilder.element(_container, identityReference);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              Element _element_2 = SchemaBuilder.element(XmlSchemaTemplate.this.vertex, vertexComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              Element _element_3 = SchemaBuilder.element(XmlSchemaTemplate.this.visibility, visibilityComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              Element _element_4 = SchemaBuilder.element(XmlSchemaTemplate.this.edge, edgeComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_4, _element_4);
              List<Node> _children_5 = it.getChildren();
              Element _element_5 = SchemaBuilder.element(XmlSchemaTemplate.this.superSetReference, superSetReferenceComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_5, _element_5);
              List<Node> _children_6 = it.getChildren();
              Element _element_6 = SchemaBuilder.element(XmlSchemaTemplate.this.command, commandComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_6, _element_6);
              List<Node> _children_7 = it.getChildren();
              Element _element_7 = SchemaBuilder.element(XmlSchemaTemplate.this.query, queryComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_7, _element_7);
            }
          }
        };
      Extension _withExtension_9 = SchemaBuilder.withExtension(categoryComplexType, _function_11);
      ComplexType _complexType_11 = SchemaBuilder.complexType(this.graph, _withExtension_9);
      final ComplexType graphComplexType = _complexType_11;
      final Procedure1<Sequence> _function_12 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
          }
        };
      Extension _withExtension_10 = SchemaBuilder.withExtension(graphComplexType, _function_12);
      ComplexType _complexType_12 = SchemaBuilder.complexType(this.model, _withExtension_10);
      final ComplexType modelComplexType = _complexType_12;
      Element _element_9 = SchemaBuilder.element(this.model, modelComplexType);
      final Element modelElement = _element_9;
      String _identity = this.terminology.identity();
      final Procedure1<Sequence> _function_13 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _identifier = XmlSchemaTemplate.this.terminology.identifier();
              Element _element = SchemaBuilder.element(_identifier, uuid);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _name = XmlSchemaTemplate.this.terminology.name();
              Element _element_1 = SchemaBuilder.element(_name, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              String _pluralName = XmlSchemaTemplate.this.terminology.pluralName();
              Element _element_2 = SchemaBuilder.element(_pluralName, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              String _payload = XmlSchemaTemplate.this.terminology.payload();
              Element _element_3 = SchemaBuilder.element(_payload, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              String _technicalName = XmlSchemaTemplate.this.terminology.technicalName();
              Element _element_4 = SchemaBuilder.element(_technicalName, DataType.STRING);
              CollectionExtensions.<Element>operator_add(_children_4, _element_4);
            }
          }
        };
      ComplexType _complexType_13 = SchemaBuilder.complexType(_identity, _function_13);
      final ComplexType identityComplexType = _complexType_13;
      String _semanticDomain = this.terminology.semanticDomain();
      final Procedure1<Sequence> _function_14 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(modelElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _identity = XmlSchemaTemplate.this.terminology.identity();
              Element _element_1 = SchemaBuilder.element(_identity, identityComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
            }
          }
        };
      ComplexType _complexType_14 = SchemaBuilder.complexType(_semanticDomain, _function_14);
      final ComplexType semanticDomainComplexType = _complexType_14;
      String _artifactSet = this.terminology.artifactSet();
      final Procedure1<Sequence> _function_15 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(modelElement, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _semanticDomain = XmlSchemaTemplate.this.terminology.semanticDomain();
              Element _element_1 = SchemaBuilder.element(_semanticDomain, semanticDomainComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
            }
          }
        };
      ComplexType _complexType_15 = SchemaBuilder.complexType(_artifactSet, _function_15);
      final ComplexType artifactSetComplexType = _complexType_15;
      String _artifactSet_1 = this.terminology.artifactSet();
      Element _element_10 = SchemaBuilder.element(_artifactSet_1, artifactSetComplexType);
      final Element artifactSetElement = _element_10;
      ArrayList<Element> _newArrayList = CollectionLiterals.<Element>newArrayList();
      _xblockexpression = (_newArrayList);
    }
    return _xblockexpression;
  }
  
  public CharSequence createSchema() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("\u00AC\u00B4\"schema\".node(SCHEMA_ATTRIBUTES, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("reusedElements,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("rootElement,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("modelArtefactEncoding,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("vertices,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("arrows,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("artifactFunctionality,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("semanticDomainArtefactEncoding");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence reusedElements() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4element(semanticIdentity, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(model, s23m(model))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(category, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(isAbstract, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(maxCardinality, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(minCardinality, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(isContainer, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(isNavigable, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(from, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(to, identityReferenceQualified)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(function, s23m(function))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexType(identityReference, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(terminology.uniqueRepresentationReference, uuidQualified),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(terminology.identifier, uuidQualified)");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexType(category, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(semanticIdentity),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(category)");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\u00AC\u00B4simpleType(uuid, XSD_STRING)\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence rootElement() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4val artifactSet = terminology.artifactSet\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4val semanticDomain = terminology.semanticDomain\u00AC\u00AA");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\u00AC\u00B4element(artifactSet, s23m(artifactSet))\u00AC\u00AA\t\t");
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexType(artifactSet, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(s23m(model)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(semanticDomain, s23m(semanticDomain))");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence modelArtefactEncoding() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4val container = terminology.container\u00AC\u00AA");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexTypeWithExtension(model, s23m(graph))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\u00AC\u00B4categoryComplexType(graph, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(container, identityReferenceQualified),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isAbstract),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(vertex, s23m(vertex)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(visibility, s23m(visibility)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(edge, s23m(edge)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(superSetReference, s23m(superSetReference)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(command, s23m(command)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(query, s23m(query))");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence vertices() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4categoryComplexType(vertex, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isAbstract),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(maxCardinality)");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence arrows() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\u00AC\u00B4categoryComplexType(superSetReference, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isAbstract),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(from),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(to)");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4categoryComplexType(visibility, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isAbstract),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(from),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(to)");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4categoryComplexType(edge, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isAbstract),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(from, s23m(edgeEnd)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(to, s23m(edgeEnd))");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4categoryComplexType(edgeEnd, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isAbstract),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(minCardinality),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(maxCardinality),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isContainer),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(isNavigable)");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence artifactFunctionality() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4val parameter = terminology.parameter\u00AC\u00AA");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\u00AC\u00B4categoryComplexType(function, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(parameter, s23m(parameter))");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4categoryComplexType(parameter, Collections::emptyList)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexTypeWithExtension(command, s23m(function))\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexTypeWithExtension(query, s23m(function))\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence semanticDomainArtefactEncoding() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4val identity = terminology.identity\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4val semanticDomain = terminology.semanticDomain\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4val identifier = terminology.identifier\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4val name = terminology.name\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4val pluralName = terminology.pluralName\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4val payload = terminology.payload\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4val technicalName = terminology.technicalName\u00AC\u00AA");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexType(semanticDomain, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(model),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("elementList(identity, s23m(identity))");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\u00AC\u00B4complexType(identity, asList(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(identifier, s23m(uuid)),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(name, XSD_STRING),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(pluralName, XSD_STRING),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(payload, XSD_STRING),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("element(technicalName, XSD_STRING)");
    _builder.newLine();
    _builder.append("))\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence complexType(final String name, final List<CharSequence> containedElements) {
    String _format = String.format("name=\"%s\"", name);
    String _join = IterableExtensions.join(containedElements, "\n");
    CharSequence _node = this.node("complexType", _format, _join);
    return _node;
  }
  
  private CharSequence complexTypeWithExtension(final String name, final String extensionBase) {
    List<CharSequence> _emptyList = Collections.<CharSequence>emptyList();
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension(name, extensionBase, _emptyList);
    return _complexTypeWithExtension;
  }
  
  private CharSequence categoryComplexType(final String name, final List<CharSequence> elementsInSequence) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4complexTypeWithExtension(name, category, elementsInSequence)\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence complexTypeWithExtension(final String name, final String extensionBase, final List<CharSequence> containedElements) {
    String _format = String.format("name=\"%s\"", name);
    String _s23m = XmlSchemaTemplate.s23m(extensionBase);
    String _format_1 = String.format("base=\"%s\"", _s23m);
    CharSequence _sequence = this.sequence(containedElements);
    CharSequence _node = this.node("extension", _format_1, _sequence);
    CharSequence _node_1 = this.node("complexContent", "", _node);
    CharSequence _node_2 = this.node("complexType", _format, _node_1);
    return _node_2;
  }
  
  private CharSequence sequence(final List<CharSequence> containedElements) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4IF !containedElements.empty\u00AC\u00AA\u00AC\u00B4node(\"sequence\", \"\", containedElements.join(\"\\n\"))\u00AC\u00AA\u00AC\u00B4ENDIF\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence simpleType(final String name, final String baseType) {
    String _format = String.format("name=\"%s\"", name);
    String _format_1 = String.format("base=\"%s\"", baseType);
    CharSequence _node = this.node("restriction", _format_1);
    CharSequence _node_1 = this.node("simpleType", _format, _node);
    return _node_1;
  }
  
  private CharSequence element(final String name, final String type) {
    String _format = String.format("name=\"%s\" type=\"%s\"", name, type);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence element(final String referencedName) {
    String _s23m = XmlSchemaTemplate.s23m(referencedName);
    String _format = String.format("ref=\"%s\"", _s23m);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence elementList(final String referencedName) {
    String _format = String.format("ref=\"%s\" minOccurs=\"0\" maxOccurs=\"unbounded\"", referencedName);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence elementList(final String name, final String type) {
    String _format = String.format("name=\"%s\" type=\"%s\" minOccurs=\"0\" maxOccurs=\"unbounded\"", name, type);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence node(final String tagName, final String attributeContents, final List<CharSequence> nestedElements) {
    String _join = IterableExtensions.join(nestedElements, "\n");
    CharSequence _node = this.node(tagName, attributeContents, _join);
    return _node;
  }
  
  private CharSequence node(final String tagName, final String attributeContents, final CharSequence nestedElements) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<\u00AC\u00B4xsd(tagName)\u00AC\u00AA\u00AC\u00B4IF attributeContents.empty\u00AC\u00AA>\u00AC\u00B4ELSE\u00AC\u00AA \u00AC\u00B4attributeContents\u00AC\u00AA>\u00AC\u00B4ENDIF\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\u00AC\u00B4nestedElements\u00AC\u00AA");
    _builder.newLine();
    _builder.append("</\u00AC\u00B4xsd(tagName)\u00AC\u00AA>");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence node(final String tagName, final String attributeContents) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<\u00AC\u00B4xsd(tagName)\u00AC\u00AA \u00AC\u00B4attributeContents\u00AC\u00AA/>");
    _builder.newLine();
    return _builder;
  }
  
  private static String xsd(final String name) {
    String _qualifiedName = XmlSchemaTemplate.qualifiedName(XmlSchemaTemplate.XSD, name);
    return _qualifiedName;
  }
  
  private static String s23m(final String name) {
    String _qualifiedName = XmlSchemaTemplate.qualifiedName(XmlSchemaTemplate.S23M, name);
    return _qualifiedName;
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
