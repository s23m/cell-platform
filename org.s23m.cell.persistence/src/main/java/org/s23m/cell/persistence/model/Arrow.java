package org.s23m.cell.persistence.model;

import static org.s23m.cell.persistence.model.ProperClass.Edge;
import static org.s23m.cell.persistence.model.ProperClass.SuperSetReference;
import static org.s23m.cell.persistence.model.ProperClass.Visibility;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public final class Arrow {

	/**
	 * The allowable proper classes
	 */
	private static final Set<ProperClass> ALLOWABLE_PROPER_CLASSES = EnumSet.of(Edge, Visibility, SuperSetReference);

	/**
	 * The primary key (a UUID), which is a reference to a {@link Graph}
	 */
	private final String urr;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String category;

	/**
	 * The proper class, which is one of {@link #ALLOWABLE_PROPER_CLASSES}
	 */
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
	public Arrow(final Graph urr, final Identity category, final ProperClass properClass, final Graph fromGraph, final Graph toGraph) {
		this(urr.getUrr(), category.getUuid(), properClass, fromGraph.getUrr(), toGraph.getUrr());
	}

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
		this.urr = Objects.requireNonNull(urr, "urr must not be null");
		this.category = Objects.requireNonNull(category, "category must not be null");
		if (!ALLOWABLE_PROPER_CLASSES.contains(properClass)) {
			throw new IllegalArgumentException("Proper class '" + properClass + "' is invalid for Arrow");
		}
		this.properClass = properClass;
		this.fromGraph = Objects.requireNonNull(fromGraph, "fromGraph must not be null");
		this.toGraph = Objects.requireNonNull(toGraph, "toGraph must not be null");
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
		result = prime * result + category.hashCode();
		result = prime * result + fromGraph.hashCode();
		result = prime * result + properClass.hashCode();
		result = prime * result + toGraph.hashCode();
		result = prime * result + urr.hashCode();
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
		if (!category.equals(other.category)) {
			return false;
		}
		if (!fromGraph.equals(other.fromGraph)) {
			return false;
		}
		if (properClass != other.properClass) {
			return false;
		}
		if (!toGraph.equals(other.toGraph)) {
			return false;
		}
		if (!urr.equals(other.urr)) {
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
