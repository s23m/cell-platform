package org.s23m.cell.communication.xml;

import java.util.LinkedHashMap;
import org.eclipse.xtext.xbase.lib.Pair;

/**
 * Contains syntactic sugar for operations so that we do not
 * need to pollute the domain model with foreign types
 */
@SuppressWarnings("all")
public class Extensions {
  public static <A extends Object, B extends Object> B operator_add(final LinkedHashMap<A,B> map, final Pair<A,B> pair) {
    A _key = pair.getKey();
    B _value = pair.getValue();
    B _put = map.put(_key, _value);
    return _put;
  }
  
  public static <A extends Object, B extends Object> void operator_add(final LinkedHashMap<A,B> map, final LinkedHashMap<A,B> additions) {
    map.putAll(additions);
  }
}
