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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import org.s23m.cell.G;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.serializerinterface.Reconstitution;

public class Verifier {

	private static final String SEPERATOR = "#######################################################################################";

	public static void checkInstanceDumpFile(final String dumpFilePath, final String outputPath) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(dumpFilePath));
			final StringBuffer sBuff = new StringBuffer();
			String strLine;
			while ((strLine = in.readLine()) != null)   {
				if (!strLine.startsWith("#")) {
					final StringTokenizer tok = new StringTokenizer(strLine, ",");
					if (tok.countTokens() == 3) {
						final String urr = tok.nextToken().trim();
						final String name = tok.nextToken().trim();
						final String flavor = tok.nextToken().trim();
						final Identity id = Reconstitution.reconstituteIdentity(name, name, UUID.fromString(urr), UUID.fromString(urr));
						final Set set = Reconstitution.getSetFromLocalMemory(id);
						if (set.identity().uniqueRepresentationReference().equals(UUID.fromString(urr))) {
							sBuff.append("FOUND,"+urr+","+name+","+flavor+"\r\n");
						}
						else {
							sBuff.append("NOT FOUND,"+urr+","+name+","+flavor+"\r\n");
						}
					}
				} else {
					sBuff.append(SEPERATOR+"\r\n");
				}
			}
			dumpContent(outputPath,sBuff.toString());
		} catch (final FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final IOException ex) {}
			}
		}
	}

	private static void dumpContent(final String path, final String content) {
		FileWriter out = null;
		try {
			out = new FileWriter(new File(path));
			out.write(content);
			out.close();
		} catch (final IllegalStateException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private static void getInstancesInMemory(final Set set, final List<Set> allInstancesInMemory) {
		allInstancesInMemory.add(null);
		allInstancesInMemory.add(set);
		//process links
		for (final Set i : set.filterInstances()) {
			if (i.flavor().isEqualTo(G.coreGraphs.edge)) {
				allInstancesInMemory.add(i);
			} else if (i.flavor().isEqualTo(G.coreGraphs.visibility)) {
				allInstancesInMemory.add(i);
			}  else if (i.flavor().isEqualTo(G.coreGraphs.superSetReference)) {
				allInstancesInMemory.add(i);
			}
		}
		allInstancesInMemory.add(null);
		for (final Set i : set.filterInstances()) {
			//if (i.flavor().isEqualTo(F_SemanticStateOfInMemoryModel.coreGraphs.vertex) && !i.identity().isEqualTo(Root.transportcontainer.identity())) {
			if (i.flavor().isEqualTo(G.coreGraphs.vertex) ) {

				getInstancesInMemory(i, allInstancesInMemory);
			}
		}
	}

	public static void printOutALlInstancesInMemory(final String path) {
		final List<Set> allInstancesInMemory = new ArrayList<Set>();
		getInstancesInMemory(Root.models, allInstancesInMemory);
		getInstancesInMemory(Root.semanticdomains, allInstancesInMemory);

		final StringBuffer sBuff = new StringBuffer();
		for (final Set i : allInstancesInMemory) {
			if (i == null) {
				sBuff.append(SEPERATOR+"\r\n");
			} else if (!i.identity().name().contains("this set is not available in memory")) {
				sBuff.append(i.identity().uniqueRepresentationReference()+","+i+","+i.flavor()+"\r\n");
			}
		}
		dumpContent(path,sBuff.toString());
	}

}
