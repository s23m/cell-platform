package org.s23m.cell.persistence.model;

import java.util.StringJoiner;

public final class Edge {

	/**
	 * The primary key
	 */
	private String urr;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String minCardinalityValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String minCardinalityValueToEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String maxCardinalityValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String maxCardinalityValueToEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String isNavigableValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String isNavigableValueToEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String isContainerValueFromEdgeEnd;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String isContainerValueToEdgeEnd;

	/**
	 * Reference to a {@link Graph}.
	 */
	private String fromEdgeEnd;

	/**
	 * Reference to a {@link Graph}.
	 */
	private String toEdgeEnd;

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

	public String getMinCardinalityValueFromEdgeEnd() {
		return minCardinalityValueFromEdgeEnd;
	}

	public void setMinCardinalityValueFromEdgeEnd(final String minCardinalityValueFromEdgeEnd) {
		this.minCardinalityValueFromEdgeEnd = minCardinalityValueFromEdgeEnd;
	}

	public String getMinCardinalityValueToEdgeEnd() {
		return minCardinalityValueToEdgeEnd;
	}

	public void setMinCardinalityValueToEdgeEnd(final String minCardinalityValueToEdgeEnd) {
		this.minCardinalityValueToEdgeEnd = minCardinalityValueToEdgeEnd;
	}

	public String getMaxCardinalityValueFromEdgeEnd() {
		return maxCardinalityValueFromEdgeEnd;
	}

	public void setMaxCardinalityValueFromEdgeEnd(final String maxCardinalityValueFromEdgeEnd) {
		this.maxCardinalityValueFromEdgeEnd = maxCardinalityValueFromEdgeEnd;
	}

	public String getMaxCardinalityValueToEdgeEnd() {
		return maxCardinalityValueToEdgeEnd;
	}

	public void setMaxCardinalityValueToEdgeEnd(final String maxCardinalityValueToEdgeEnd) {
		this.maxCardinalityValueToEdgeEnd = maxCardinalityValueToEdgeEnd;
	}

	public String getIsNavigableValueFromEdgeEnd() {
		return isNavigableValueFromEdgeEnd;
	}

	public void setIsNavigableValueFromEdgeEnd(final String isNavigableValueFromEdgeEnd) {
		this.isNavigableValueFromEdgeEnd = isNavigableValueFromEdgeEnd;
	}

	public String getIsNavigableValueToEdgeEnd() {
		return isNavigableValueToEdgeEnd;
	}

	public void setIsNavigableValueToEdgeEnd(final String isNavigableValueToEdgeEnd) {
		this.isNavigableValueToEdgeEnd = isNavigableValueToEdgeEnd;
	}

	public String getIsContainerValueFromEdgeEnd() {
		return isContainerValueFromEdgeEnd;
	}

	public void setIsContainerValueFromEdgeEnd(final String isContainerValueFromEdgeEnd) {
		this.isContainerValueFromEdgeEnd = isContainerValueFromEdgeEnd;
	}

	public String getIsContainerValueToEdgeEnd() {
		return isContainerValueToEdgeEnd;
	}

	public void setIsContainerValueToEdgeEnd(final String isContainerValueToEdgeEnd) {
		this.isContainerValueToEdgeEnd = isContainerValueToEdgeEnd;
	}

	public String getFromEdgeEnd() {
		return fromEdgeEnd;
	}

	public void setFromEdgeEnd(final String fromEdgeEnd) {
		this.fromEdgeEnd = fromEdgeEnd;
	}

	public String getToEdgeEnd() {
		return toEdgeEnd;
	}

	public void setToEdgeEnd(final String toEdgeEnd) {
		this.toEdgeEnd = toEdgeEnd;
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
