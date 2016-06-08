package org.s23m.cell.kernel.artifactinstantiation;

import org.s23m.cell.Set;

public interface InstantiationSequence {

	/**
	 * Executes the instantiation sequence and returns any runtime errors as a Set
	 */
	Set execute();
}
