package org.s23m.cell.persistence.jdbc.dao;

import org.s23m.cell.persistence.model.Agent;
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
		return new Edge(uuid, uuid, uuid, uuid, uuid, uuid, uuid, uuid, uuid, uuid, uuid);
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

	/*
	 * Creates an Agent instance with the given UUID as its primary key
	 */
	public static Agent createAgent(final String uuid) {
		return new Agent(uuid, uuid, "bob@example.com", "secret", "0211231234", "Robert", "Smith", "Bob");
	}
}
