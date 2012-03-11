package org.s23m.cell.communication.xml

import org.eclipse.xtext.xbase.lib.Pair
import java.util.LinkedHashMap

/**
 * Contains syntactic sugar for operations so that we do not
 * need to pollute the domain model with foreign types
 */
class Extensions {
	
	def static <A, B> operator_add(LinkedHashMap<A, B> map, Pair<A, B> pair) {
		map.put(pair.key, pair.value)
	}
	
	def static <A, B> operator_add(LinkedHashMap<A, B> map, LinkedHashMap<A, B> additions) {
		map.putAll(additions)
	}
}