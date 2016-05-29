package org.s23m.cell.persistence.jdbc.dao;

import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Edge;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Identity;
import org.s23m.cell.persistence.model.ProperClass;

public class TestData {

	/*
	 * Creates an Arrow instance by reusing the same provided UUID for the primary key and all foreign keys
	 */
	public static Arrow createArrow(final String uuid, final ProperClass properClass) {
		return new Arrow(uuid, uuid, properClass, uuid, uuid);
	}

	/*
	 * Creates an Edge instance by reusing the same provided UUID for the primary key and all foreign keys
	 */
	public static Edge createEdge(final String uuid) {
		final Edge edge = new Edge();
		edge.setUrr(uuid);
		edge.setMinCardinalityValueFromEdgeEnd(uuid);
		edge.setMinCardinalityValueToEdgeEnd(uuid);
		edge.setMaxCardinalityValueFromEdgeEnd(uuid);
		edge.setMaxCardinalityValueToEdgeEnd(uuid);
		edge.setIsNavigableValueFromEdgeEnd(uuid);
		edge.setIsNavigableValueToEdgeEnd(uuid);
		edge.setIsContainerValueFromEdgeEnd(uuid);
		edge.setIsContainerValueToEdgeEnd(uuid);
		edge.setFromEdgeEnd(uuid);
		edge.setToEdgeEnd(uuid);
		return edge;
	}

	/*
	 * Creates a Graph instance by reusing the same provided UUID for the primary key and all foreign keys
	 */
	public static Graph createGraph(final String uuid, final ProperClass properClass) {
		return new Graph(uuid, uuid, uuid, uuid, uuid, properClass, uuid, uuid);
	}

	/*
	 * Creates an Identity instance with the given UUID as its primary key
	 */
	public static Identity createIdentity(final String uuid) {
		return new Identity(uuid, "name", "pluralName", "codeName", "pluralCodeName", "payload");
	}
}
