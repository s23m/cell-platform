package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.CompositeNode
import org.s23m.cell.communication.xml.dom.Node

class SchemaRendering {
	
	static int INDENTATION = 2
	
	def static render(Node node) {
		render(node, 0)
	}
	
	def static dispatch render(CompositeNode node, int level) '''
		«renderPrefix(node, level)»>
			«FOR n : node.children»
				«render(n, level + 1)»
			«ENDFOR»
		«renderSuffix(node, level)»
	'''
	
	def static dispatch render(Node node, int level) {
		renderPrefix(node, level) + "/>"
	}
	
	def private static renderPrefix(Node node, int level) {
		whitespace(level) + "<" + node.name + renderAttributes(node)
	}
	
	def private static renderSuffix(Node node, int level) {
		whitespace(level) + "</" + node.name + ">"
	}
	
	def private static whitespace(int level) {
		org::s23m::cell::communication::xml::StringUtils::repeat(level * INDENTATION, " ")
	}
	
	def private static renderAttributes(Node node) {
		val builder = new StringBuilder()
		val entrySet = node.attributes.entrySet
		for (entry : entrySet) {
			builder.append(' ')
			builder.append(entry.key)
			builder.append('="')
			builder.append(entry.value)
			builder.append('"')
		}
		builder.toString
	}
	
	// TODO render LeafNode
	// TODO render CompositeNode
	// TODO render WrapperNode
	// TODO render DataType (or can that we use toString?)
}