package org.s23m.cell.persistence.model;

import java.util.Objects;
import java.util.StringJoiner;

public final class Graph {

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String urr;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String uuid;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String category;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String container;

	/**
	 * Reference to an {@link Identity}.
	 */
	private final String isAbstractValue;

	private final ProperClass properClass;

	/**
	 * Nullable reference to an {@link Identity}.
	 */
	private final String maxCardinalityValueInContainer;

	/**
	 * The content of this graph as XML.
	 */
	private final String contentAsXml;

	/**
	 * Constructor
	 *
	 * @param urr
	 * @param uuid
	 * @param category
	 * @param container
	 * @param isAbstractValue
	 * @param properClass
	 * @param maxCardinalityValueInContainer
	 * @param contentAsXml
	 */
	public Graph(final Identity urr, final Identity uuid, final Identity category, final Identity container, final Identity isAbstractValue,
			final ProperClass properClass, final Identity maxCardinalityValueInContainer, final String contentAsXml) {
		this(urr.getUuid(), uuid.getUuid(), category.getUuid(), container.getUuid(), isAbstractValue.getUuid(), properClass,
				maxCardinalityValueInContainer.getUuid(), contentAsXml);
	}

	/**
	 * Constructor
	 *
	 * @param urr
	 * @param uuid
	 * @param category
	 * @param container
	 * @param isAbstractValue
	 * @param properClass
	 * @param maxCardinalityValueInContainer
	 * @param contentAsXml
	 */
	public Graph(final String urr, final String uuid, final String category, final String container, final String isAbstractValue,
			final ProperClass properClass, final String maxCardinalityValueInContainer, final String contentAsXml) {
		this.urr = Objects.requireNonNull(urr, "urr must not be null");
		this.uuid = Objects.requireNonNull(uuid, "uuid must not be null");
		this.category = Objects.requireNonNull(category, "category must not be null");
		this.container = Objects.requireNonNull(container, "container must not be null");
		this.isAbstractValue = Objects.requireNonNull(isAbstractValue, "isAbstractValue must not be null");
		this.properClass = Objects.requireNonNull(properClass, "properClass must not be null");
		this.maxCardinalityValueInContainer = maxCardinalityValueInContainer;
		this.contentAsXml = contentAsXml;
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

	public String getUuid() {
		return uuid;
	}

	public String getCategory() {
		return category;
	}

	public String getContainer() {
		return container;
	}

	public String getIsAbstractValue() {
		return isAbstractValue;
	}

	public ProperClass getProperClass() {
		return properClass;
	}

	public String getMaxCardinalityValueInContainer() {
		return maxCardinalityValueInContainer;
	}

	public String getContentAsXml() {
		return contentAsXml;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		// mandatory fields

		result = prime * result + urr.hashCode();
		result = prime * result + uuid.hashCode();
		result = prime * result + category.hashCode();
		result = prime * result + container.hashCode();
		result = prime * result + isAbstractValue.hashCode();
		result = prime * result + properClass.hashCode();

		// optional fields

		result = prime * result
				+ ((maxCardinalityValueInContainer == null) ? 0 : maxCardinalityValueInContainer.hashCode());
		result = prime * result + ((contentAsXml == null) ? 0 : contentAsXml.hashCode());

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
		final Graph other = (Graph) obj;

		// mandatory fields

		if (!urr.equals(other.urr)) {
			return false;
		}
		if (!uuid.equals(other.uuid)) {
			return false;
		}
		if (!category.equals(other.category)) {
			return false;
		}
		if (!container.equals(other.container)) {
			return false;
		}
		if (!isAbstractValue.equals(other.isAbstractValue)) {
			return false;
		}
		if (properClass != other.properClass) {
			return false;
		}

		// optional fields

		if (maxCardinalityValueInContainer == null) {
			if (other.maxCardinalityValueInContainer != null) {
				return false;
			}
		} else if (!maxCardinalityValueInContainer.equals(other.maxCardinalityValueInContainer)) {
			return false;
		}
		if (contentAsXml == null) {
			if (other.contentAsXml != null) {
				return false;
			}
		} else if (!contentAsXml.equals(other.contentAsXml)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
				.add("urr = " + urr)
				.add("uuid = " + uuid)
				.add("category = " + category)
				.add("container = " + container)
				.add("isAbstractValue = " + isAbstractValue)
				.add("properClass = " + properClass)
				.add("maxCardinalityValueInContainer = " + maxCardinalityValueInContainer)
				.add("contentAsXml = " + contentAsXml)
				.toString();
	}
}
