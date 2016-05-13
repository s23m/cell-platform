package org.s23m.cell.persistence.model;

import static org.s23m.cell.persistence.model.Graph.ProperClasses.EDGE;
import static org.s23m.cell.persistence.model.Graph.ProperClasses.SUPERSET_REFERENCE;
import static org.s23m.cell.persistence.model.Graph.ProperClasses.VISIBILITY;

import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

public final class Arrow {

	private static final Set<String> ALLOWABLE_PROPER_CLASSES = ImmutableSet.of(EDGE, VISIBILITY, SUPERSET_REFERENCE);

	private String urr;

	private String category;

	private String properClass;

	private String fromGraph;

	private String toGraph;

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

	public String getProperClass() {
		return properClass;
	}

	public void setProperClass(final String properClass) {
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
		return Objects.toStringHelper(getClass())
				.add("urr", urr)
				.add("category", category)
				.add("properClass", properClass)
				.add("fromGraph", fromGraph)
				.add("toGraph", toGraph)
				.toString();
	}
}
