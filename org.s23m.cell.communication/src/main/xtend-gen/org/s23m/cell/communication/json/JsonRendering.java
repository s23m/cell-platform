package org.s23m.cell.communication.json;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.s23m.cell.communication.xml.StringUtils;
import org.s23m.cell.communication.xml.model.dom.CompositeNode;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.StringElement;

@SuppressWarnings("all")
public class JsonRendering {
  private final static int INDENTATION = 2;
  
  private final static String NEWLINE = "\n";
  
  private final static String COMMA = ",";
  
  private final static String COLON = ": ";
  
  private final static String START_BRACE = "{";
  
  private final static String END_BRACE = "}";
  
  public static String render(final ArtifactSet node) {
    CharSequence _doRender = JsonRendering.doRender(node);
    return _doRender.toString();
  }
  
  private static CharSequence doRender(final CompositeNode node) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    String _render = JsonRendering.render(node, 1);
    _builder.append(_render, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private static String _render(final CompositeNode node, final int level) {
    StringConcatenation _builder = new StringConcatenation();
    final Iterable<? extends Node> children = node.getChildren();
    _builder.newLineIfNotEmpty();
    String _renderPrefix = JsonRendering.renderPrefix(node, level);
    _builder.append(_renderPrefix, "");
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    {
      boolean _hasElements = false;
      for(final Node n : children) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(JsonRendering.COMMA, "");
        }
        String _render = JsonRendering.render(n, (level + 1));
        _builder.append(_render, "");
        _builder.newLineIfNotEmpty();
      }
    }
    String _renderSuffix = JsonRendering.renderSuffix(node, level);
    _builder.append(_renderSuffix, "");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private static String _render(final StringElement node, final int level) {
    String _renderPrefix = JsonRendering.renderPrefix(node, level);
    String _text = node.getText();
    String _quote = JsonRendering.quote(_text);
    return (_renderPrefix + _quote);
  }
  
  private static String _render(final Node node, final int level) {
    String _xblockexpression = null;
    {
      final StringBuilder builder = new StringBuilder();
      String _renderPrefix = JsonRendering.renderPrefix(node, level);
      builder.append(_renderPrefix);
      builder.append(JsonRendering.START_BRACE);
      String _renderAttributes = JsonRendering.renderAttributes(node, (level + 1));
      builder.append(_renderAttributes);
      builder.append(JsonRendering.NEWLINE);
      String _whitespace = JsonRendering.whitespace(level);
      builder.append(_whitespace);
      builder.append(JsonRendering.END_BRACE);
      _xblockexpression = builder.toString();
    }
    return _xblockexpression;
  }
  
  private static String renderPrefix(final Node node, final int level) {
    String _whitespace = JsonRendering.whitespace(level);
    String _name = node.getName();
    String _quote = JsonRendering.quote(_name);
    String _plus = (_whitespace + _quote);
    return (_plus + JsonRendering.COLON);
  }
  
  private static String renderSuffix(final Node node, final int level) {
    String _whitespace = JsonRendering.whitespace(level);
    return (_whitespace + JsonRendering.END_BRACE);
  }
  
  private static String whitespace(final int level) {
    return StringUtils.repeat((level * JsonRendering.INDENTATION), " ");
  }
  
  private static String quote(final String s) {
    return (("\"" + s) + "\"");
  }
  
  private static String renderAttributes(final Node node, final int level) {
    String _xblockexpression = null;
    {
      final String indentation = JsonRendering.whitespace(level);
      final StringBuilder builder = new StringBuilder();
      Map<String, String> _attributes = node.getAttributes();
      final Set<Map.Entry<String, String>> entrySet = _attributes.entrySet();
      final Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
      while (iterator.hasNext()) {
        {
          final Map.Entry<String, String> entry = iterator.next();
          builder.append(JsonRendering.NEWLINE);
          builder.append(indentation);
          String _key = entry.getKey();
          String _quote = JsonRendering.quote(_key);
          builder.append(_quote);
          builder.append(JsonRendering.COLON);
          String _value = entry.getValue();
          String _quote_1 = JsonRendering.quote(_value);
          builder.append(_quote_1);
          boolean _hasNext = iterator.hasNext();
          if (_hasNext) {
            builder.append(JsonRendering.COMMA);
          }
        }
      }
      _xblockexpression = builder.toString();
    }
    return _xblockexpression;
  }
  
  private static String render(final Node node, final int level) {
    if (node instanceof StringElement) {
      return _render((StringElement)node, level);
    } else if (node instanceof CompositeNode) {
      return _render((CompositeNode)node, level);
    } else if (node != null) {
      return _render(node, level);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(node, level).toString());
    }
  }
}
