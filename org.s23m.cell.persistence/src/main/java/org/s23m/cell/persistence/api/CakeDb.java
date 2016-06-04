package org.s23m.cell.persistence.api;

import org.s23m.cell.Set;

/**
 * An interface to the persistence component
 */
public class CakeDb {

	public void persist(final Set setOfGraphs) {

		// for each set in sequence (iterate through in order):
		//    persist all identities first
		//    persist graph itself:
		//       either persist either edge or arrow (and if edge, persist corresponding arrow)

		// Note: http://blog.ploeh.dk/2014/08/11/cqs-versus-server-generated-ids/

	}

}
