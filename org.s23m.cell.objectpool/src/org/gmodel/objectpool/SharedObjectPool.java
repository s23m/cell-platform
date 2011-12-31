/* ***** BEGIN LICENSE BLOCK *****
 * Version: SMTL 1.0
 *
 * The contents of this file are subject to the Sofismo Model Technology License Version
 * 1.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.sofismo.ch/SMTL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis.
 * See the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is Gmodel Semantic Extensions Edition.
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

package org.s23m.cell.objectpool;

import java.util.UUID;

@SuppressWarnings("serial")
public class SharedObjectPool extends GenericObjectPool {

	protected SharedObjectPool() {
	}
	
	public void addArtifact(final String key, final ObjectPoolArtifact artifact) {
		final ObjectPoolArtifact val = poolMap.get(key);
		if (val != null) {
			poolMap.replace(key, val, artifact);
		} else {
			poolMap.putIfAbsent(key, artifact);
		}
	}

	public void initializePool(final UUID key) {
		
	}
	
}
