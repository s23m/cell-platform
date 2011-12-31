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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */

import sbt._
import Keys._
import java.io.File
import java.io.FilenameFilter

object Packaging {

	val defaultSettings = Seq(
		packageConfiguration in Compile in packageBin <<= addEmbeddedJarsToBinary(),		

		packageOptions <<= Manifest.enrichManifest
	)
		
	/**
   * Adds all jars in the lib directory to the resulting jar
	 */
  def addEmbeddedJarsToBinary() = {
		(packageConfiguration in Compile in packageBin, baseDirectory) map {
      (config, base) => {
				var embeddedJarDir = "lib"
				var libDirectory = new File(base, embeddedJarDir)

				val additionalSources = if (libDirectory.exists) {

					var jarFilter = new FilenameFilter() {
						def accept(dir: File, name: String): Boolean = name.endsWith(".jar")
					}
					val jarFiles = List(libDirectory.listFiles(jarFilter): _*)

					jarFiles.map{j =>
						val jarPath = embeddedJarDir + "/" + j.getName
						(j, jarPath) 
					}
				} else {
					Seq.empty
				}

        new Package.Configuration(config.sources ++ additionalSources, config.jar, config.options)
      }
    }
	}

}
