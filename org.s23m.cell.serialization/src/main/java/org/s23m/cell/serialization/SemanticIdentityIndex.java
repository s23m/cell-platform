package org.s23m.cell.serialization;

public class SemanticIdentityIndex extends SemanticIdType {

	private String metaElementId;
	private String metaElementTypeName;

	public String getMetaElementId() {
		return metaElementId;
	}
	public void setMetaElementId(final String metaElementId) {
		this.metaElementId = metaElementId;
	}
	public String getMetaElementTypeName() {
		return metaElementTypeName;
	}
	public void setMetaElementTypeName(final String metaElementTypeName) {
		this.metaElementTypeName = metaElementTypeName;
	}

}
