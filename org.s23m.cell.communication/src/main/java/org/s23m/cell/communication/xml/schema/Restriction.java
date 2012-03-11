package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;

public class Restriction extends Node {
	public Restriction(Namespace namespace, Node base) {
		super(namespace, "restriction");

		attributes.put("base", base.qualifiedName());
	}
}