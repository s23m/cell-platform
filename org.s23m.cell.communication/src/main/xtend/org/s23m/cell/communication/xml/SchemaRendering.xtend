package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.CompositeNode
import org.s23m.cell.communication.xml.dom.Node

class SchemaRendering {
	
	def static dispatch render(CompositeNode node) '''
		<«node.name» «renderAttributes(node)»>
		«FOR n : node.children»
			«render(n)»
		«ENDFOR»
		</«node.name»>
	'''
	
	def static dispatch render(Node node) '''
		<«node.name» «renderAttributes(node)»/>
	'''
	
	def private static renderAttributes(Node node) {
		val builder = new StringBuilder()
		for (entry : node.attributes.entrySet) {
			builder.append(entry.key)
			builder.append('="')
			builder.append(entry.value)
			builder.append('" ')
		}
		builder.toString.trim
	}
	
	// TODO render LeafNode
	// TODO render CompositeNode
	// TODO render WrapperNode
	// TODO render DataType (or can that we use toString?)
}