package org.s23m.cell.communication.xml;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.s23m.cell.communication.xml.dom.CompositeNode;
import org.s23m.cell.communication.xml.dom.Node;

@SuppressWarnings("all")
public class SchemaRendering {
  protected static CharSequence _render(final CompositeNode node) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _name = node.getName();
    _builder.append(_name, "");
    _builder.append(" ");
    String _renderAttributes = SchemaRendering.renderAttributes(node);
    _builder.append(_renderAttributes, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    {
      List<Node> _children = node.getChildren();
      for(final Node n : _children) {
        CharSequence _render = SchemaRendering.render(n);
        _builder.append(_render, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</");
    String _name_1 = node.getName();
    _builder.append(_name_1, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected static CharSequence _render(final Node node) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _name = node.getName();
    _builder.append(_name, "");
    _builder.append(" ");
    String _renderAttributes = SchemaRendering.renderAttributes(node);
    _builder.append(_renderAttributes, "");
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private static String renderAttributes(final Node node) {
    String _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder builder = _stringBuilder;
      Map<String,String> _attributes = node.getAttributes();
      Set<Entry<String,String>> _entrySet = _attributes.entrySet();
      for (final Entry<String,String> entry : _entrySet) {
        {
          String _key = entry.getKey();
          builder.append(_key);
          builder.append("=\"");
          String _value = entry.getValue();
          builder.append(_value);
          builder.append("\" ");
        }
      }
      String _string = builder.toString();
      String _trim = _string.trim();
      _xblockexpression = (_trim);
    }
    return _xblockexpression;
  }
  
  public static CharSequence render(final Node node) {
    if (node instanceof CompositeNode) {
      return _render((CompositeNode)node);
    } else if (node != null) {
      return _render(node);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(node).toString());
    }
  }
}
