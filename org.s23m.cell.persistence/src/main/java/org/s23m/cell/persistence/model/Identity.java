package org.s23m.cell.persistence.model;

import com.google.common.base.Objects;

// TODO equals and hashCode (only uuid needed in hashCode)
// TODO make uuid final?
public final class Identity {

	private String uuid;

	private String name;

	private String pluralName;

	private String codeName;

	private String pluralCodeName;

	// TODO change to byte[] ?
	private String payload;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(final String pluralName) {
		this.pluralName = pluralName;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(final String codeName) {
		this.codeName = codeName;
	}

	public String getPluralCodeName() {
		return pluralCodeName;
	}

	public void setPluralCodeName(final String pluralCodeName) {
		this.pluralCodeName = pluralCodeName;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(final String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(getClass())
				.add("uuid", uuid)
				.add("name", name)
				.add("pluralName", pluralName)
				.add("codeName", codeName)
				.add("pluralCodeName", pluralCodeName)
				.add("payload", payload)
				.toString();
	}
}
