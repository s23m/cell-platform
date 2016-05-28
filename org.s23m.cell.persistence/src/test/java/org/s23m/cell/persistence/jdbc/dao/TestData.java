package org.s23m.cell.persistence.jdbc.dao;

import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Edge;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Identity;

public class TestData {

	/*
	 * Creates an Arrow instance by reusing the same provided UUID for the primary key and all foreign keys
	 */
	public static Arrow createArrow(final String uuid, final String properClass) {
		final Arrow result = new Arrow();
		result.setUrr(uuid);
		result.setFromGraph(uuid);
		result.setToGraph(uuid);
		result.setCategory(uuid);
		result.setProperClass(properClass);
		return result;
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
	public static Graph createGraph(final String uuid, final String properClass) {
		final Graph graph = new Graph();
		graph.setUrr(uuid);
		graph.setUuid(uuid);
		graph.setCategory(uuid);
		graph.setContainer(uuid);
		graph.setIsAbstractValue(uuid);
		graph.setMaxCardinalityValueInContainer(uuid);
		graph.setProperClass(properClass);
		graph.setContentAsXml("<xml>content</xml>");
		return graph;
	}

	/*
	 * Creates an Identity instance with the given UUID as its primary key
	 */
	public static Identity createIdentity(final String uuid) {
		final Identity identity = new Identity();
		identity.setUuid(uuid);
		identity.setName("name");
		identity.setPluralName("pluralName");
		identity.setCodeName("codeName");
		identity.setPluralCodeName("pluralClassName");
		identity.setPayload("payload");
		return identity;
	}
}
