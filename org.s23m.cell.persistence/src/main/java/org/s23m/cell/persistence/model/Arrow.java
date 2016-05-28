package org.s23m.cell.persistence.model;

import static org.s23m.cell.persistence.model.ProperClass.Edge;
import static org.s23m.cell.persistence.model.ProperClass.SuperSetReference;
import static org.s23m.cell.persistence.model.ProperClass.Visibility;

import java.util.EnumSet;
import java.util.Set;
import java.util.StringJoiner;

public final class Arrow {

	private static final Set<ProperClass> ALLOWABLE_PROPER_CLASSES = EnumSet.of(Edge, Visibility, SuperSetReference);

	/**
	 * The primary key
	 */
	private final String urr;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String category;

	private final ProperClass properClass;

	/**
	 * Reference to a {@link Graph}.
	 */
	private final String fromGraph;

	/**
	 * Reference to a {@link Graph}.
	 */
	private final String toGraph;

	/**
	 * Constructor
	 *
	 * @param urr
	 * @param category
	 * @param properClass
	 * @param fromGraph
	 * @param toGraph
	 */
	public Arrow(final String urr, final String category, final ProperClass properClass, final String fromGraph, final String toGraph) {
		this.urr = urr;
		this.category = category;
		if (!ALLOWABLE_PROPER_CLASSES.contains(properClass)) {
			throw new IllegalArgumentException("Proper class '" + properClass + "' is invalid for Arrow");
		}
		this.properClass = properClass;
		this.fromGraph = fromGraph;
		this.toGraph = toGraph;
	}

	/**
	 * Indicates whether a primary key has been assigned to the underlying row.
	 */
	public boolean isTransient() {
		return urr == null;
	}

	public String getUrr() {
		return urr;
	}

	public String getCategory() {
		return category;
	}

	public ProperClass getProperClass() {
		return properClass;
	}

	public String getFromGraph() {
		return fromGraph;
	}

	public String getToGraph() {
		return toGraph;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((fromGraph == null) ? 0 : fromGraph.hashCode());
		result = prime * result + ((properClass == null) ? 0 : properClass.hashCode());
		result = prime * result + ((toGraph == null) ? 0 : toGraph.hashCode());
		result = prime * result + ((urr == null) ? 0 : urr.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Arrow other = (Arrow) obj;
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (fromGraph == null) {
			if (other.fromGraph != null) {
				return false;
			}
		} else if (!fromGraph.equals(other.fromGraph)) {
			return false;
		}
		if (properClass != other.properClass) {
			return false;
		}
		if (toGraph == null) {
			if (other.toGraph != null) {
				return false;
			}
		} else if (!toGraph.equals(other.toGraph)) {
			return false;
		}
		if (urr == null) {
			if (other.urr != null) {
				return false;
			}
		} else if (!urr.equals(other.urr)) {
			return false;
		}
		return true;
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
