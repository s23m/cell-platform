package org.s23m.cell.persistence.model;

import java.util.Objects;
import java.util.StringJoiner;

public final class Identity {

	/**
	 * The primary key
	 */
	private final String uuid;

	private final String name;

	private final String pluralName;

	private final String codeName;

	private final String pluralCodeName;

	// TODO change to byte[] ?
	private final String payload;

	/**
	 * Constructor
	 *
	 * @param uuid
	 * @param name
	 * @param pluralName
	 * @param codeName
	 * @param pluralCodeName
	 * @param payload
	 */
	public Identity(final String uuid, final String name, final String pluralName, final String codeName, final String pluralCodeName,
			final String payload) {
		this.uuid = Objects.requireNonNull(uuid, "uuid must not be null");
		this.name = Objects.requireNonNull(name, "name must not be null");
		this.pluralName = Objects.requireNonNull(pluralName, "pluralName must not be null");
		this.codeName = codeName;
		this.pluralCodeName = pluralCodeName;
		this.payload = payload;
	}

	/**
	 * Indicates whether a primary key has been assigned to the underlying row.
	 */
	public boolean isTransient() {
		return uuid == null;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public String getCodeName() {
		return codeName;
	}

	public String getPluralCodeName() {
		return pluralCodeName;
	}

	public String getPayload() {
		return payload;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		// mandatory fields

		result = prime * result + uuid.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + pluralName.hashCode();

		// optional fields

		result = prime * result + ((codeName == null) ? 0 : codeName.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result + ((pluralCodeName == null) ? 0 : pluralCodeName.hashCode());
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
		final Identity other = (Identity) obj;

		// mandatory fields

		if (!uuid.equals(other.uuid)) {
			return false;
		}
		if (!name.equals(other.name)) {
			return false;
		}
		if (!pluralName.equals(other.pluralName)) {
			return false;
		}

		// optional fields

		if (codeName == null) {
			if (other.codeName != null) {
				return false;
			}
		} else if (!codeName.equals(other.codeName)) {
			return false;
		}
		if (pluralCodeName == null) {
			if (other.pluralCodeName != null) {
				return false;
			}
		} else if (!pluralCodeName.equals(other.pluralCodeName)) {
			return false;
		}
		if (payload == null) {
			if (other.payload != null) {
				return false;
			}
		} else if (!payload.equals(other.payload)) {
			return false;
		}

		return true;
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
