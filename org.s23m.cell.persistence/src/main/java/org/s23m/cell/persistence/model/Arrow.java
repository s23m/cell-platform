package org.s23m.cell.persistence.model;

import static org.s23m.cell.persistence.model.ProperClass.Edge;
import static org.s23m.cell.persistence.model.ProperClass.SuperSetReference;
import static org.s23m.cell.persistence.model.ProperClass.Visibility;

import java.util.EnumSet;
import java.util.Set;
import java.util.StringJoiner;

// TODO: make immutable
public final class Arrow {

	private static final Set<ProperClass> ALLOWABLE_PROPER_CLASSES = EnumSet.of(Edge, Visibility, SuperSetReference);

	/**
	 * The primary key
	 */
	private String urr;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String category;

	private ProperClass properClass;

	/**
	 * Reference to a {@link Graph}.
	 */
	private String fromGraph;

	/**
	 * Reference to a {@link Graph}.
	 */
	private String toGraph;

	/**
	 * Indicates whether a primary key has been assigned to the underlying row.
	 */
	public boolean isTransient() {
		return urr == null;
	}

	public String getUrr() {
		return urr;
	}

	public void setUrr(final String urr) {
		this.urr = urr;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public ProperClass getProperClass() {
		return properClass;
	}

	public void setProperClass(final ProperClass properClass) {
		if (!ALLOWABLE_PROPER_CLASSES.contains(properClass)) {
			throw new IllegalArgumentException("Proper class '" + properClass + "' is invalid for Arrow");
		}
		this.properClass = properClass;
	}

	public String getFromGraph() {
		return fromGraph;
	}

	public void setFromGraph(final String fromGraph) {
		this.fromGraph = fromGraph;
	}

	public String getToGraph() {
		return toGraph;
	}

	public void setToGraph(final String toGraph) {
		this.toGraph = toGraph;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
				.add("urr = " + urr)
				.add("category = " + category)
				.add("properClass = " + properClass)
				.add("fromGraph = " + fromGraph)
				.add("toGraph = " + toGraph)
				.toString();
	}
}
