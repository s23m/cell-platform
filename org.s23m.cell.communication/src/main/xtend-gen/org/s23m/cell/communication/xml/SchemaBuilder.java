package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.schema.Cardinality;
import org.s23m.cell.communication.xml.schema.Element;
import org.s23m.cell.communication.xml.schema.Schema;
import org.s23m.cell.communication.xml.schema.Sequence;
import org.s23m.cell.communication.xml.schema.Type;

@SuppressWarnings("all")
public class SchemaBuilder {
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
  
  public static Element element(final Namespace namespace, final String name, final Type type) {
    final Procedure1<Element> _function = new Procedure1<Element>() {
        public void apply(final Element it) {
        }
      };
    Element _element = SchemaBuilder.element(namespace, name, type, _function);
    return _element;
  }
  
  public static Element element(final Namespace namespace, final String name, final Type type, final Procedure1<? super Element> initialiser) {
    Element _element = SchemaBuilder.element(namespace, name, type, Cardinality.EXACTLY_ONE, initialiser);
    return _element;
  }
  
  public static Element element(final Namespace namespace, final String name, final Type type, final Cardinality cardinality, final Procedure1<? super Element> initialiser) {
    Element _xblockexpression = null;
    {
      Element _element = new Element(namespace, name, type, cardinality);
      final Element result = _element;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
}
