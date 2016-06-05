package org.s23m.cell.persistence.model;

import java.util.Objects;
import java.util.StringJoiner;

public final class Agent {

	private final String urr;

	private final String uuid;

	/**
	 * Email address (may be null). At least one of email and mobile is required.
	 */
	private final String email;

	/**
	 * Salted and hashed password
	 */
	private final String password;

	/**
	 * Mobile phone number. At least one of email and mobile is required.
	 */
	private final String mobile;

	private final String firstName;

	private final String lastName;

	private final String alias;

	/**
	 * Constructor
	 *
	 * @param urr
	 * @param uuid
	 * @param email
	 * @param password
	 * @param mobile
	 * @param firstName
	 * @param lastName
	 * @param alias
	 */
	public Agent(final Identity urr, final Identity uuid, final String email, final String password, final String mobile, final String firstName,
			final String lastName, final String alias) {
		this(urr.getUuid(), uuid.getUuid(), email, password, mobile, firstName, lastName, alias);
	}

	/**
	 * Constructor
	 *
	 * @param urr
	 * @param uuid
	 * @param email
	 * @param password
	 * @param mobile
	 * @param firstName
	 * @param lastName
	 * @param alias
	 */
	public Agent(final String urr, final String uuid, final String email, final String password, final String mobile, final String firstName,
			final String lastName, final String alias) {
		this.urr = Objects.requireNonNull(urr, "urr must not be null");
		this.uuid = Objects.requireNonNull(uuid, "uuid must not be null");
		this.password = Objects.requireNonNull(password, "password must not be null");
		this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
		this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
		this.alias = Objects.requireNonNull(alias, "alias must not be null");

		// require at least one of mobile and email
		if (email == null && mobile == null) {
			throw new IllegalArgumentException("At least one of email and mobile is required");
		}
		this.email = email;
		this.mobile = mobile;
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

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getMobile() {
		return mobile;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAlias() {
		return alias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		// mandatory fields

		result = prime * result + urr.hashCode();
		result = prime * result + uuid.hashCode();
		result = prime * result + password.hashCode();
		result = prime * result + firstName.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + alias.hashCode();

		// optional fields

		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());

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
		final Agent other = (Agent) obj;

		// mandatory fields
		if (!urr.equals(other.urr)) {
			return false;
		}
		if (!uuid.equals(other.uuid)) {
			return false;
		}
		if (!alias.equals(other.alias)) {
			return false;
		}
		if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (!password.equals(other.password)) {
			return false;
		}

		// email and/or mobile may be null

		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}

		if (mobile == null) {
			if (other.mobile != null) {
				return false;
			}
		} else if (!mobile.equals(other.mobile)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
				.add("urr = " + urr)
				.add("uuid = " + uuid)
				.add("email = " + email)
				.add("mobile = " + mobile)
				.add("firstName = " + firstName)
				.add("lastName = " + lastName)
				.add("alias = " + alias)
				.toString();
	}
}
