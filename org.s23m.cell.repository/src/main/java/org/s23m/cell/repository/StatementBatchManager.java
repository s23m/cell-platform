/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.Serializer;
import org.s23m.cell.serialization.serializer.SerializerHolder;

public class StatementBatchManager {

	private final int updateBatchSize;
	private final PreparedStatement statement;
	private final List<String> statementStrings;
	private final List<String> urrsToFetch;
	private final Serializer serializer;
	private final int readBatchSize;
	private final String queryPrefix;
	private final String queryPostfix;
	private final String tableName;

	protected StatementBatchManager(final PreparedStatement statement, final String tableName, final String queryPrefix, final String queryPostfix, final int updateBatchSize, final int readBatchSize) {
		this.statement = statement;
		this.tableName = tableName;
		this.queryPrefix = queryPrefix;
		this.queryPostfix = queryPostfix;
		this.updateBatchSize = updateBatchSize;
		this.readBatchSize = readBatchSize;
		statementStrings = new ArrayList<String>();
		urrsToFetch = new ArrayList<String>();
		serializer = SerializerHolder.getGmodelInstanceSerializer(SerializationType.XML);
	}

	protected void addToBatch(final String sqlString) {
		statementStrings.add(sqlString);
	}

	protected void addToURRList(final String urr) {
		urrsToFetch.add(urr);
	}

	protected PreparedStatement getStatement() {
		return statement;
	}

	protected List<String> getStatementStrings() {
		return statementStrings;
	}

	protected void commitStatementBatches() throws SQLException {
		final List<String> insertQrys = getInsertQuery();
		int count = 1;
		for (final String qry : insertQrys) {
			statement.execute(qry);
			System.err.println("Updated "+count+"/"+insertQrys.size());
			count++;
		}
	}

	protected void executeSelectQueries(final ListOrderedMap xmlDocs) throws SQLException {
		final List<String> queries = getSelectQuery();
		int count = 1;
		for (final String qry : queries) {
			statement.execute(qry);
			final ResultSet result = statement.getResultSet();
			while (result.next()) {
				final String urr = result.getString(1);
				final String xml = result.getString(2);
				xmlDocs.put(urr, xml);
			}
			result.close();
			System.err.println("Fetched "+count+"/"+queries.size());
			count++;
		}
	}

	private List<String> getInsertQuery() {
		final List<String> qryStrings = new ArrayList<String>();
		int n = 1;
		int lastIndex = 0;

		String urrString = "";
		final Iterator<String> itr = statementStrings.iterator();
		while (itr.hasNext()) {
			urrString += itr.next()+",";
			if (n % updateBatchSize == 0) {
				if(urrString.endsWith(",")) {
					urrString = urrString.substring(0, urrString.length()-1);
					qryStrings.add(queryPrefix+urrString+queryPostfix);
				}
				urrString = "";
				lastIndex = n-1;
			}
			n++;
		}
		urrString = "";
		for (int i = lastIndex; i < statementStrings.size(); i++) {
			urrString += statementStrings.get(i)+",";
		}
		if(urrString.endsWith(",")) {
			urrString = urrString.substring(0, urrString.length()-1);
			qryStrings.add(queryPrefix+urrString+queryPostfix);
		}
		return qryStrings;
	}

	private List<String> getSelectQuery() {
		final List<String> qryStrings = new ArrayList<String>();
		final String qry = "select urr, contentAsXml from artifact where urr in ";
		int n = 1;
		int lastIndex = 0;
		final String openToken = "(";
		final String endToken = ")";

		String urrString = openToken;
		final Iterator<String> itr = urrsToFetch.iterator();
		while (itr.hasNext()) {
			urrString += "'"+itr.next()+"',";
			if (n % readBatchSize == 0) {
				urrString = urrString.substring(0, urrString.length()-1)+endToken;
				qryStrings.add(qry+urrString);
				urrString = openToken;
				lastIndex = n-1;
			}
			n++;
		}
		urrString = openToken;
		for (int i = lastIndex; i < urrsToFetch.size() ; i++) {
			urrString += "'"+urrsToFetch.get(i)+"',";
		}
		urrString = urrString.substring(0, urrString.length()-1)+endToken;
		qryStrings.add(qry+urrString);
		return qryStrings;
	}

}