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
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.s23m.cell.objectpool.ObjectPool;
import org.s23m.cell.objectpool.PersonalObjectPool;

public class RepositoryClientObjectPool {

	private static final String SERIALIZED_FILE_NAME = "objpool.dat";

	private static final String SERIALIZATION_FILE = System.getProperty("user.dir")+"/"+SERIALIZED_FILE_NAME;

	private static class ObjectPoolHolder {
		private static boolean isInitialized = false;
		public static final ObjectPool POOL = setupObjectPool();

		private static ObjectPool setupObjectPool() {
			ObjectPool pool;
			if (!isInitialized) {
				try {
					pool = loadObjectPool();
				} catch (final FileNotFoundException e) {
					pool = new PersonalObjectPool(RepositoryClient.CLIENT_ID);
				} catch (final IOException e) {
					pool = new PersonalObjectPool(RepositoryClient.CLIENT_ID);
				} catch (final ClassNotFoundException e) {
					pool = new PersonalObjectPool(RepositoryClient.CLIENT_ID);
				}
			} else {
				pool = new PersonalObjectPool(RepositoryClient.CLIENT_ID);
			}
			isInitialized = true;
			return pool;
		}
	}

	protected static ObjectPool getObjectPool() {
		return ObjectPoolHolder.POOL;
	}

	private static ObjectPool loadObjectPool() throws FileNotFoundException, IOException, ClassNotFoundException{
		final File objectPoolFile = new File(SERIALIZATION_FILE);
		ObjectInputStream in = null;
		in = new ObjectInputStream(new FileInputStream(objectPoolFile));
		final ObjectPool pool = (ObjectPool) in.readObject();
		in.close();
		return pool;
	}

	protected static void saveObjectPool() throws IllegalStateException {
		try {
			final File f = new File(SERIALIZATION_FILE);
			if (f.exists()) {
				f.delete();
			}
			final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(SERIALIZATION_FILE));
			out.writeObject(ObjectPoolHolder.POOL);
			out.close();
		} catch (final FileNotFoundException ex) {
			throw new IllegalStateException("Object pool serialization failed", ex);
		} catch (final IOException ex) {
			throw new IllegalStateException("Object pool serialization failed", ex);
		}
	}

	protected static void removeSerializedObjectPool() throws IllegalStateException {
		final File objectPoolFile = new File(SERIALIZATION_FILE);
		if (objectPoolFile.exists()) {
			objectPoolFile.delete();
		}
	}

}
