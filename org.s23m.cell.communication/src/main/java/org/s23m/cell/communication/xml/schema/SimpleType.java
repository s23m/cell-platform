package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;
import org.s23m.cell.communication.xml.dom.WrapperNode;

public class SimpleType extends WrapperNode {

	public SimpleType(Namespace namespace, String name, Node child) {
		super(namespace, name, child);
	}

	public static class Restriction extends Node {
		public Restriction(Namespace namespace, Node base) {
			super(namespace, "restriction");

			attributes.put("base", base.qualifiedName());
		}
	}
}
