package org.s23m.cell.communication.xml;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.s23m.cell.communication.xml.StringUtils;
import org.s23m.cell.communication.xml.dom.CompositeNode;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;

@SuppressWarnings("all")
public class SchemaRendering {
  private static int INDENTATION = 2;
  
  public static CharSequence render(final Node node) {
    CharSequence _render = SchemaRendering.render(node, 0);
    return _render;
  }
  
  protected static CharSequence _render(final CompositeNode node, final int level) {
    StringConcatenation _builder = new StringConcatenation();
    List<Node> _children = node.getChildren();
    final List<Node> children = _children;
    _builder.newLineIfNotEmpty();
    String _renderPrefix = SchemaRendering.renderPrefix(node, level);
    _builder.append(_renderPrefix, "");
    {
      boolean _isEmpty = children.isEmpty();
      if (_isEmpty) {
        _builder.append("/>");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append(">");
        _builder.newLineIfNotEmpty();
        {
          for(final Node n : children) {
            int _operator_plus = IntegerExtensions.operator_plus(level, 1);
            CharSequence _render = SchemaRendering.render(n, _operator_plus);
            _builder.append(_render, "");
            _builder.newLineIfNotEmpty();
          }
        }
        String _renderSuffix = SchemaRendering.renderSuffix(node, level);
        _builder.append(_renderSuffix, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected static CharSequence _render(final Node node, final int level) {
    String _renderPrefix = SchemaRendering.renderPrefix(node, level);
    String _operator_plus = StringExtensions.operator_plus(_renderPrefix, "/>");
    return _operator_plus;
  }
  
  private static String renderPrefix(final Node node, final int level) {
    String _whitespace = SchemaRendering.whitespace(level);
    String _operator_plus = StringExtensions.operator_plus(_whitespace, "<");
    String _name = SchemaRendering.name(node);
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, _name);
    String _renderAttributes = SchemaRendering.renderAttributes(node);
    String _operator_plus_2 = StringExtensions.operator_plus(_operator_plus_1, _renderAttributes);
    return _operator_plus_2;
  }
  
  private static String renderSuffix(final Node node, final int level) {
    String _whitespace = SchemaRendering.whitespace(level);
    String _operator_plus = StringExtensions.operator_plus(_whitespace, "</");
    String _name = SchemaRendering.name(node);
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, _name);
    String _operator_plus_2 = StringExtensions.operator_plus(_operator_plus_1, ">");
    return _operator_plus_2;
  }
  
  private static String whitespace(final int level) {
    int _operator_multiply = IntegerExtensions.operator_multiply(level, SchemaRendering.INDENTATION);
    String _repeat = StringUtils.repeat(_operator_multiply, " ");
    return _repeat;
  }
  
  private static String name(final Node node) {
    Namespace _namespace = node.getNamespace();
    String _prefix = _namespace.getPrefix();
    String _operator_plus = StringExtensions.operator_plus(_prefix, ":");
    String _name = node.getName();
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, _name);
    return _operator_plus_1;
  }
  
  private static String renderAttributes(final Node node) {
    String _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder builder = _stringBuilder;
      Map<String,String> _attributes = node.getAttributes();
      Set<Entry<String,String>> _entrySet = _attributes.entrySet();
      final Set<Entry<String,String>> entrySet = _entrySet;
      for (final Entry<String,String> entry : entrySet) {
        {
          builder.append(" ");
          String _key = entry.getKey();
          builder.append(_key);
          builder.append("=\"");
          String _value = entry.getValue();
          builder.append(_value);
          builder.append("\"");
        }
      }
      String _string = builder.toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
  
  public static CharSequence render(final Node node, final int level) {
    if (node instanceof CompositeNode) {
      return _render((CompositeNode)node, level);
    } else if (node != null) {
      return _render(node, level);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(node, level).toString());
    }
  }
}
