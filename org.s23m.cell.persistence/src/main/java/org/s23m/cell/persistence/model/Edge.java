package org.s23m.cell.persistence.model;

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
		this.urr = urr;
		this.minCardinalityValueFromEdgeEnd = minCardinalityValueFromEdgeEnd;
		this.minCardinalityValueToEdgeEnd = minCardinalityValueToEdgeEnd;
		this.maxCardinalityValueFromEdgeEnd = maxCardinalityValueFromEdgeEnd;
		this.maxCardinalityValueToEdgeEnd = maxCardinalityValueToEdgeEnd;
		this.isNavigableValueFromEdgeEnd = isNavigableValueFromEdgeEnd;
		this.isNavigableValueToEdgeEnd = isNavigableValueToEdgeEnd;
		this.isContainerValueFromEdgeEnd = isContainerValueFromEdgeEnd;
		this.isContainerValueToEdgeEnd = isContainerValueToEdgeEnd;
		this.fromEdgeEnd = fromEdgeEnd;
		this.toEdgeEnd = toEdgeEnd;
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
