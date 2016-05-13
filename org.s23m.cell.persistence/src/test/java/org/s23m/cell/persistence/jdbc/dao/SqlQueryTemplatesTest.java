package org.s23m.cell.persistence.jdbc.dao;

import junit.framework.TestCase;

import org.s23m.cell.persistence.model.Identity;

public class SqlQueryTemplatesTest extends TestCase {

	public void testCreateSelectByUuidQueryTemplate() {
		final String identitySelect = SqlQueryTemplates.createSelectByIdQueryTemplate(Identity.class, "pk");
		assertEquals("SELECT * FROM Identity WHERE pk=?", identitySelect);
	}

	public void testCreateUpdateStatementTemplate() {
		final String[] columnNames = {"a", "b", "c"};
		final String identityUpdate = SqlQueryTemplates.createUpdateStatementTemplate(Identity.class, columnNames);
		assertEquals("UPDATE Identity SET a=?,b=? WHERE c=?", identityUpdate);
	}

	public void testCreateInsertStatementTemplate() {
		final String[] columnNames = {"a", "b", "c"};
		final String identityInsert = SqlQueryTemplates.createInsertStatementTemplate(Identity.class, columnNames);
		assertEquals("INSERT INTO Identity (a,b,c) VALUES (?,?,?)", identityInsert);
	}
}
