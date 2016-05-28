package org.s23m.cell.persistence.model;

import static org.s23m.cell.persistence.model.Graph.ProperClasses.EDGE;
import static org.s23m.cell.persistence.model.Graph.ProperClasses.EDGE_END;
import static org.s23m.cell.persistence.model.Graph.ProperClasses.SUPERSET_REFERENCE;
import static org.s23m.cell.persistence.model.Graph.ProperClasses.VERTEX;
import static org.s23m.cell.persistence.model.Graph.ProperClasses.VISIBILITY;

import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

public class Graph {

	/**
	 * Identifiers for proper classes
	 */
	public static class ProperClasses {
		public static final String VERTEX = "Vertex";
		public static final String EDGE = "Edge";
		public static final String EDGE_END = "EdgeEnd";
		public static final String VISIBILITY = "Visibility";
		public static final String SUPERSET_REFERENCE = "SuperSetReference";
	}

	private static final Set<String> PROPER_CLASSES = ImmutableSet.of(VERTEX, EDGE, EDGE_END, VISIBILITY, SUPERSET_REFERENCE);

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

	private String properClass;

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

	public String getProperClass() {
		return properClass;
	}

	public void setProperClass(final String properClass) {
		if (!PROPER_CLASSES.contains(properClass)) {
			throw new IllegalArgumentException("Proper class '" + properClass + "' is invalid for Graph");
		}
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
		return Objects.toStringHelper(getClass())
				.add("urr", urr)
				.add("uuid", uuid)
				.add("category", category)
				.add("container", container)
				.add("isAbstractValue", isAbstractValue)
				.add("maxCardinalityValueInContainer", maxCardinalityValueInContainer)
				.add("properClass", properClass)
				.add("contentAsXml", contentAsXml)
				.toString();
	}
}
