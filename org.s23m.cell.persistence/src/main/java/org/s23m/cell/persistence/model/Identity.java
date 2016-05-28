package org.s23m.cell.persistence.model;

import java.util.StringJoiner;

// TODO equals and hashCode (only uuid needed in hashCode)
// TODO make uuid final?
public final class Identity {

	/**
	 * The primary key
	 */
	private String uuid;

	private String name;

	private String pluralName;

	private String codeName;

	private String pluralCodeName;

	// TODO change to byte[] ?
	private String payload;

	/**
	 * Indicates whether a primary key has been assigned to the underlying row.
	 */
	public boolean isTransient() {
		return uuid == null;
	}

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
		return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
				.add("uuid = " + uuid)
				.add("name = " + name)
				.add("pluralName = " + pluralName)
				.add("codeName = " + codeName)
				.add("pluralCodeName = " + pluralCodeName)
				.add("payload = " + payload)
				.toString();
	}
}
