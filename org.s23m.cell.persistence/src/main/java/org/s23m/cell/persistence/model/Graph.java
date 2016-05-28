package org.s23m.cell.persistence.model;

import java.util.StringJoiner;

public class Graph {

	/**
	 * Reference to an {@link Identity}.
	 */
	private String urr;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String uuid;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String category;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String container;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String isAbstractValue;

	/**
	 * Nullable reference to an {@link Identity}.
	 */
	private String maxCardinalityValueInContainer;

	private ProperClass properClass;

	/**
	 * Reference to an {@link Identity}.
	 */
	private String contentAsXml;

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(final String container) {
		this.container = container;
	}

	public String getIsAbstractValue() {
		return isAbstractValue;
	}

	public void setIsAbstractValue(final String isAbstractValue) {
		this.isAbstractValue = isAbstractValue;
	}

	public String getMaxCardinalityValueInContainer() {
		return maxCardinalityValueInContainer;
	}

	public void setMaxCardinalityValueInContainer(final String maxCardinalityValueInContainer) {
		this.maxCardinalityValueInContainer = maxCardinalityValueInContainer;
	}

	public ProperClass getProperClass() {
		return properClass;
	}

	public void setProperClass(final ProperClass properClass) {
		this.properClass = properClass;
	}

	public String getContentAsXml() {
		return contentAsXml;
	}

	public void setContentAsXml(final String contentAsXml) {
		this.contentAsXml = contentAsXml;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
				.add("urr = " + urr)
				.add("uuid = " + uuid)
				.add("category = " + category)
				.add("container = " + container)
				.add("isAbstractValue = " + isAbstractValue)
				.add("maxCardinalityValueInContainer = " + maxCardinalityValueInContainer)
				.add("properClass = " + properClass)
				.add("contentAsXml = " + contentAsXml)
				.toString();
	}
}
