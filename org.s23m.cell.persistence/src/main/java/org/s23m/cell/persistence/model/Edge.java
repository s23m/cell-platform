package org.s23m.cell.persistence.model;

import java.util.Objects;
import java.util.StringJoiner;

public final class Edge {

	/**
	 * The primary key
	 */
	private final String urr;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String minCardinalityValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String minCardinalityValueToEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String maxCardinalityValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String maxCardinalityValueToEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String isNavigableValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String isNavigableValueToEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String isContainerValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String isContainerValueToEdgeEnd;

	/**
	 * Reference to a {@link Graph}.
	 */
	private final String fromEdgeEnd;

	/**
	 * Reference to a {@link Graph}.
	 */
	private final String toEdgeEnd;

	/**
	 * Constructor
	 *
	 * @param urr
	 * @param minCardinalityValueFromEdgeEnd
	 * @param minCardinalityValueToEdgeEnd
	 * @param maxCardinalityValueFromEdgeEnd
	 * @param maxCardinalityValueToEdgeEnd
	 * @param isNavigableValueFromEdgeEnd
	 * @param isNavigableValueToEdgeEnd
	 * @param isContainerValueFromEdgeEnd
	 * @param isContainerValueToEdgeEnd
	 * @param fromEdgeEnd
	 * @param toEdgeEnd
	 */
	public Edge(final Arrow urr, final Identity minCardinalityValueFromEdgeEnd, final Identity minCardinalityValueToEdgeEnd,
			final Identity maxCardinalityValueFromEdgeEnd, final Identity maxCardinalityValueToEdgeEnd,
			final Identity isNavigableValueFromEdgeEnd, final Identity isNavigableValueToEdgeEnd, final Identity isContainerValueFromEdgeEnd,
			final Identity isContainerValueToEdgeEnd, final Graph fromEdgeEnd, final Graph toEdgeEnd) {

		this(urr.getUrr(), minCardinalityValueFromEdgeEnd.getUuid(), minCardinalityValueToEdgeEnd.getUuid(),
				maxCardinalityValueFromEdgeEnd.getUuid(), maxCardinalityValueToEdgeEnd.getUuid(),
				isNavigableValueFromEdgeEnd.getUuid(), isNavigableValueToEdgeEnd.getUuid(), isContainerValueFromEdgeEnd.getUuid(),
				isContainerValueToEdgeEnd.getUuid(), fromEdgeEnd.getUrr(), toEdgeEnd.getUrr());
	}

	/**
	 * Constructor
	 *
	 * @param urr
	 * @param minCardinalityValueFromEdgeEnd
	 * @param minCardinalityValueToEdgeEnd
	 * @param maxCardinalityValueFromEdgeEnd
	 * @param maxCardinalityValueToEdgeEnd
	 * @param isNavigableValueFromEdgeEnd
	 * @param isNavigableValueToEdgeEnd
	 * @param isContainerValueFromEdgeEnd
	 * @param isContainerValueToEdgeEnd
	 * @param fromEdgeEnd
	 * @param toEdgeEnd
	 */
	public Edge(final String urr, final String minCardinalityValueFromEdgeEnd, final String minCardinalityValueToEdgeEnd,
			final String maxCardinalityValueFromEdgeEnd, final String maxCardinalityValueToEdgeEnd,
			final String isNavigableValueFromEdgeEnd, final String isNavigableValueToEdgeEnd, final String isContainerValueFromEdgeEnd,
			final String isContainerValueToEdgeEnd, final String fromEdgeEnd, final String toEdgeEnd) {

		this.urr = Objects.requireNonNull(urr, "urr must not be null");
		this.minCardinalityValueFromEdgeEnd = Objects.requireNonNull(minCardinalityValueFromEdgeEnd, "minCardinalityValueFromEdgeEnd must not be null");
		this.minCardinalityValueToEdgeEnd = Objects.requireNonNull(minCardinalityValueToEdgeEnd, "minCardinalityValueToEdgeEnd must not be null");
		this.maxCardinalityValueFromEdgeEnd = Objects.requireNonNull(maxCardinalityValueFromEdgeEnd, "maxCardinalityValueFromEdgeEnd must not be null");
		this.maxCardinalityValueToEdgeEnd = Objects.requireNonNull(maxCardinalityValueToEdgeEnd, "maxCardinalityValueToEdgeEnd must not be null");
		this.isNavigableValueFromEdgeEnd = Objects.requireNonNull(isNavigableValueFromEdgeEnd, "isNavigableValueFromEdgeEnd must not be null");
		this.isNavigableValueToEdgeEnd = Objects.requireNonNull(isNavigableValueToEdgeEnd, "isNavigableValueToEdgeEnd must not be null");
		this.isContainerValueFromEdgeEnd = Objects.requireNonNull(isContainerValueFromEdgeEnd, "isContainerValueFromEdgeEnd must not be null");
		this.isContainerValueToEdgeEnd = Objects.requireNonNull(isContainerValueToEdgeEnd, "isContainerValueToEdgeEnd must not be null");
		this.fromEdgeEnd = Objects.requireNonNull(fromEdgeEnd, "fromEdgeEnd must not be null");
		this.toEdgeEnd = Objects.requireNonNull(toEdgeEnd, "toEdgeEnd must not be null");
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

	public String getMinCardinalityValueFromEdgeEnd() {
		return minCardinalityValueFromEdgeEnd;
	}

	public String getMinCardinalityValueToEdgeEnd() {
		return minCardinalityValueToEdgeEnd;
	}

	public String getMaxCardinalityValueFromEdgeEnd() {
		return maxCardinalityValueFromEdgeEnd;
	}

	public String getMaxCardinalityValueToEdgeEnd() {
		return maxCardinalityValueToEdgeEnd;
	}

	public String getIsNavigableValueFromEdgeEnd() {
		return isNavigableValueFromEdgeEnd;
	}

	public String getIsNavigableValueToEdgeEnd() {
		return isNavigableValueToEdgeEnd;
	}

	public String getIsContainerValueFromEdgeEnd() {
		return isContainerValueFromEdgeEnd;
	}

	public String getIsContainerValueToEdgeEnd() {
		return isContainerValueToEdgeEnd;
	}

	public String getFromEdgeEnd() {
		return fromEdgeEnd;
	}

	public String getToEdgeEnd() {
		return toEdgeEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fromEdgeEnd.hashCode();
		result = prime * result + isContainerValueFromEdgeEnd.hashCode();
		result = prime * result + isContainerValueToEdgeEnd.hashCode();
		result = prime * result + isNavigableValueFromEdgeEnd.hashCode();
		result = prime * result + isNavigableValueToEdgeEnd.hashCode();
		result = prime * result + maxCardinalityValueFromEdgeEnd.hashCode();
		result = prime * result + maxCardinalityValueToEdgeEnd.hashCode();
		result = prime * result + minCardinalityValueFromEdgeEnd.hashCode();
		result = prime * result + minCardinalityValueToEdgeEnd.hashCode();
		result = prime * result + toEdgeEnd.hashCode();
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
		final Edge other = (Edge) obj;
		if (!fromEdgeEnd.equals(other.fromEdgeEnd)) {
			return false;
		}
		if (!isContainerValueFromEdgeEnd.equals(other.isContainerValueFromEdgeEnd)) {
			return false;
		}
		if (!isContainerValueToEdgeEnd.equals(other.isContainerValueToEdgeEnd)) {
			return false;
		}
		if (!isNavigableValueFromEdgeEnd.equals(other.isNavigableValueFromEdgeEnd)) {
			return false;
		}
		if (!isNavigableValueToEdgeEnd.equals(other.isNavigableValueToEdgeEnd)) {
			return false;
		}
		if (!maxCardinalityValueFromEdgeEnd.equals(other.maxCardinalityValueFromEdgeEnd)) {
			return false;
		}
		if (!maxCardinalityValueToEdgeEnd.equals(other.maxCardinalityValueToEdgeEnd)) {
			return false;
		}
		if (!minCardinalityValueFromEdgeEnd.equals(other.minCardinalityValueFromEdgeEnd)) {
			return false;
		}
		if (!minCardinalityValueToEdgeEnd.equals(other.minCardinalityValueToEdgeEnd)) {
			return false;
		}
		if (!toEdgeEnd.equals(other.toEdgeEnd)) {
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
				.add("minCardinalityValueFromEdgeEnd = " + minCardinalityValueFromEdgeEnd)
				.add("minCardinalityValueToEdgeEnd = " + minCardinalityValueToEdgeEnd)
				.add("maxCardinalityValueFromEdgeEnd = " + maxCardinalityValueFromEdgeEnd)
				.add("maxCardinalityValueToEdgeEnd = " + maxCardinalityValueToEdgeEnd)
				.add("isNavigableValueFromEdgeEnd = " + isNavigableValueFromEdgeEnd)
				.add("isNavigableValueToEdgeEnd = " + isNavigableValueToEdgeEnd)
				.add("isContainerValueFromEdgeEnd = " + isContainerValueFromEdgeEnd)
				.add("isContainerValueToEdgeEnd = " + isContainerValueToEdgeEnd)
				.add("fromEdgeEnd = " + fromEdgeEnd)
				.add("toEdgeEnd = " + toEdgeEnd)
				.toString();
	}
}
