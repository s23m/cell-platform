package org.s23m.cell.persistence.jdbc.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createAgent;

import org.junit.Test;
import org.s23m.cell.persistence.model.Agent;

public class AgentTest {

	@Test(expected = NullPointerException.class)
	public void testNullUUIDSpecified() {
		createAgent(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNeitherEmailNorMobileSpecified() {
		new Agent("urr", "uuid", null, "secret", null, "Robert", "Smith", "Bob");
	}

	@Test
	public void testEqualsAndHashCode() {
		final Agent a = new Agent("urr", "uuid", "email", "secret", null, "Robert", "Smith", "Bob");
		final Agent b = new Agent("urr", "uuid", "email", "secret", null, "Robert", "Smith", "Bob");
		assertThat(a, is(equalTo(b)));
		assertThat(a.hashCode(), is(equalTo(b.hashCode())));

		final Agent c = new Agent("urr", "uuid", null, "secret", "mobile", "Robert", "Smith", "Bob");
		final Agent d = new Agent("urr", "uuid", null, "secret", "mobile", "Robert", "Smith", "Bob");
		assertThat(c, is(equalTo(d)));
		assertThat(c.hashCode(), is(equalTo(d.hashCode())));

		assertThat(a, is(not(c)));
		assertThat(a.hashCode(), is(not(c.hashCode())));
	}
}
