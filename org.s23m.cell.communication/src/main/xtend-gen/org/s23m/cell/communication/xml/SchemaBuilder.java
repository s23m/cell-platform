package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.schema.Cardinality;
import org.s23m.cell.communication.xml.schema.ComplexType;
import org.s23m.cell.communication.xml.schema.DataType;
import org.s23m.cell.communication.xml.schema.Element;
import org.s23m.cell.communication.xml.schema.ElementReference;
import org.s23m.cell.communication.xml.schema.Extension;
import org.s23m.cell.communication.xml.schema.Schema;
import org.s23m.cell.communication.xml.schema.Sequence;
import org.s23m.cell.communication.xml.schema.SimpleType;
import org.s23m.cell.communication.xml.schema.Type;

@SuppressWarnings("all")
public class SchemaBuilder {
  private static String S23M = "agentSemanticDomains";
  
  private static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012";
  
  private static Namespace NS_S23M = new Function0<Namespace>() {
    public Namespace apply() {
      Namespace _namespace = new Namespace(SchemaBuilder.S23M, SchemaBuilder.S23M_SCHEMA);
      return _namespace;
    }
  }.apply();
  
  public static Schema schema(final Procedure1<? super Schema> initialiser) {
    Schema _xblockexpression = null;
    {
      Schema _schema = new Schema();
      final Schema result = _schema;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public static SimpleType simpleType(final String nameAttribute, final DataType restrictionDataType) {
    SimpleType _simpleType = new SimpleType(SchemaBuilder.NS_S23M, nameAttribute, restrictionDataType);
    return _simpleType;
  }
  
  public static ComplexType complexType(final String name, final Procedure1<? super Sequence> initialiser) {
    Sequence _sequence = SchemaBuilder.sequence(initialiser);
    ComplexType _complexType = new ComplexType(SchemaBuilder.NS_S23M, name, _sequence);
    return _complexType;
  }
  
  public static ComplexType complexType(final String name, final Extension ext) {
    ComplexType _complexType = new ComplexType(SchemaBuilder.NS_S23M, name, ext);
    return _complexType;
  }
  
  public static Extension withExtension(final ComplexType base, final Procedure1<? super Sequence> initialiser) {
    Sequence _sequence = SchemaBuilder.sequence(initialiser);
    Extension _extension = new Extension(base, _sequence);
    return _extension;
  }
  
  public static Sequence sequence(final Procedure1<? super Sequence> initialiser) {
    Sequence _xblockexpression = null;
    {
      Sequence _sequence = new Sequence();
      final Sequence result = _sequence;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public static ElementReference element(final Element referencedElement) {
    ElementReference _elementReference = new ElementReference(referencedElement);
    return _elementReference;
  }
  
  public static ElementReference element(final Element referencedElement, final Cardinality cardinality) {
    ElementReference _elementReference = new ElementReference(referencedElement, cardinality);
    return _elementReference;
  }
  
  public static Element element(final String name, final Type type) {
    final Procedure1<Element> _function = new Procedure1<Element>() {
        public void apply(final Element it) {
        }
      };
    Element _element = SchemaBuilder.element(name, type, _function);
    return _element;
  }
  
  public static Element element(final String name, final Type type, final Procedure1<? super Element> initialiser) {
    Element _element = SchemaBuilder.element(name, type, Cardinality.EXACTLY_ONE, initialiser);
    return _element;
  }
  
  public static Element element(final String name, final Type type, final Cardinality cardinality) {
    Element _element = new Element(SchemaBuilder.NS_S23M, name, type, cardinality);
    return _element;
  }
  
  public static Element element(final String name, final Type type, final Cardinality cardinality, final Procedure1<? super Element> initialiser) {
    Element _xblockexpression = null;
    {
      Element _element = new Element(SchemaBuilder.NS_S23M, name, type, cardinality);
      final Element result = _element;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
}
