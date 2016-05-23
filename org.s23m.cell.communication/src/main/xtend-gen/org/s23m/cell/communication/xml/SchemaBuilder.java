package org.s23m.cell.communication.xml;

import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.NamespaceExtensions;
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
import org.s23m.cell.communication.xml.model.schema.SimpleType;
import org.s23m.cell.communication.xml.model.schema.Type;
import org.s23m.cell.communication.xml.model.schema.XmlSchemaConstants;

@SuppressWarnings("all")
public class SchemaBuilder {
  private Schema schema;
  
  private String rootElementName;
  
  public SchemaBuilder(final String rootElementName) {
    this.rootElementName = rootElementName;
    Schema _schema = new Schema();
    this.schema = _schema;
    String _xmlns = NamespaceExtensions.xmlns(XmlSchemaConstants.XML_SCHEMA_PREFIX);
    this.schema.setAttribute(_xmlns, XmlSchemaConstants.XML_SCHEMA_URI);
    String _xmlns_1 = NamespaceExtensions.xmlns(NamespaceConstants.S23M);
    this.schema.setAttribute(_xmlns_1, NamespaceConstants.S23M_SCHEMA_URI);
    this.schema.setAttribute("targetNamespace", NamespaceConstants.S23M_SCHEMA_URI);
    this.schema.setAttribute("elementFormDefault", "qualified");
    this.schema.setAttribute("attributeFormDefault", "unqualified");
  }
  
  public Schema build() {
    Schema _xblockexpression = null;
    {
      this.removeElementsWithoutReferences();
      _xblockexpression = this.schema;
    }
    return _xblockexpression;
  }
  
  private boolean removeElementsWithoutReferences() {
    boolean _xblockexpression = false;
    {
      final Function1<Node, Boolean> _function = (Node it) -> {
        boolean _and = false;
        boolean _and_1 = false;
        if (!(it instanceof Element)) {
          _and_1 = false;
        } else {
          List<ElementReference> _references = ((Element) it).getReferences();
          boolean _isEmpty = _references.isEmpty();
          _and_1 = _isEmpty;
        }
        if (!_and_1) {
          _and = false;
        } else {
          String _nameAttribute = ((Element) it).getNameAttribute();
          boolean _notEquals = (!Objects.equal(_nameAttribute, this.rootElementName));
          _and = _notEquals;
        }
        return Boolean.valueOf(_and);
      };
      final Function1<Node, Boolean> predicate = _function;
      List<Node> _children = this.schema.getChildren();
      Iterable<Node> _filter = IterableExtensions.<Node>filter(_children, predicate);
      final List<Node> elements = IterableExtensions.<Node>toList(_filter);
      List<Node> _children_1 = this.schema.getChildren();
      _xblockexpression = _children_1.removeAll(elements);
    }
    return _xblockexpression;
  }
  
  private <T extends Node> T store(final T node) {
    T _xblockexpression = null;
    {
      List<Node> _children = this.schema.getChildren();
      _children.add(node);
      _xblockexpression = node;
    }
    return _xblockexpression;
  }
  
  public ConstrainedSimpleType simpleType(final String nameAttribute, final DataType restrictionDataType) {
    ConstrainedSimpleType _constrainedSimpleType = new ConstrainedSimpleType(NamespaceConstants.NS_S23M, nameAttribute, restrictionDataType);
    return this.<ConstrainedSimpleType>store(_constrainedSimpleType);
  }
  
  public ComplexType complexType(final String name, final Procedure1<? super Sequence> initialiser) {
    Sequence _sequence = SchemaBuilder.sequence(initialiser);
    ComplexType _complexType = new ComplexType(NamespaceConstants.NS_S23M, name, _sequence);
    return this.<ComplexType>store(_complexType);
  }
  
  public ComplexType complexType(final String name, final Extension ext) {
    ComplexType _complexType = new ComplexType(NamespaceConstants.NS_S23M, name, ext);
    return this.<ComplexType>store(_complexType);
  }
  
  public ComplexType complexType(final String name, final List<Attribute> attributes) {
    ComplexType _complexType = new ComplexType(NamespaceConstants.NS_S23M, name, attributes);
    return this.<ComplexType>store(_complexType);
  }
  
  public ComplexType complexType(final String name, final List<Attribute> attributes, final Procedure1<? super Sequence> initialiser) {
    Sequence _sequence = SchemaBuilder.sequence(initialiser);
    ComplexType _complexType = new ComplexType(NamespaceConstants.NS_S23M, name, attributes, _sequence);
    return this.<ComplexType>store(_complexType);
  }
  
  public Attribute mandatoryAttribute(final String name, final SimpleType type) {
    return new Attribute(name, type, true);
  }
  
  public Attribute optionalAttribute(final String name, final SimpleType type) {
    return new Attribute(name, type, false);
  }
  
  public Element element(final String name, final Type type) {
    final Procedure1<Element> _function = (Element it) -> {
    };
    return this.element(name, type, _function);
  }
  
  public Element element(final String name, final Type type, final Procedure1<? super Element> initialiser) {
    return this.element(name, type, Cardinality.EXACTLY_ONE, initialiser);
  }
  
  public Element element(final String name, final Type type, final Cardinality cardinality) {
    Element _element = new Element(NamespaceConstants.NS_S23M, name, type, cardinality);
    return this.<Element>store(_element);
  }
  
  public Element element(final String name, final Type type, final Cardinality cardinality, final Procedure1<? super Element> initialiser) {
    Element _xblockexpression = null;
    {
      final Element result = new Element(NamespaceConstants.NS_S23M, name, type, cardinality);
      initialiser.apply(result);
      _xblockexpression = this.<Element>store(result);
    }
    return _xblockexpression;
  }
  
  public ElementReference element(final Element referencedElement) {
    return new ElementReference(referencedElement);
  }
  
  public ElementReference element(final Element referencedElement, final Cardinality cardinality) {
    return new ElementReference(referencedElement, cardinality);
  }
  
  /**
   * Helpers used in creation of top-level nodes
   */
  public static Extension withExtension(final ComplexType base) {
    Sequence _sequence = new Sequence();
    return new Extension(base, _sequence);
  }
  
  public static Extension withExtension(final ComplexType base, final Procedure1<? super Sequence> initialiser) {
    Sequence _sequence = SchemaBuilder.sequence(initialiser);
    return new Extension(base, _sequence);
  }
  
  private static Sequence sequence(final Procedure1<? super Sequence> initialiser) {
    Sequence _xblockexpression = null;
    {
      final Sequence result = new Sequence();
      initialiser.apply(result);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
}
