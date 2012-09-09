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
 * Copyright (C) 2011-2012 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */

import sbt._
import Keys._
import java.net.URL
import java.util.Map.Entry
import java.util.jar.Attributes
import java.util.jar.{Manifest => JarManifest}
import scala.collection.JavaConverters._

object Manifest {

	private val bundleVersionKey = "Bundle-Version"
		
	/**
   * Incorporates existing MANIFEST.MF entries in resulting jar (plus extra ones defined by SBT)
	 */
  def enrichManifest() = {

		(packageOptions, mainClass in Compile, name, version, homepage, organization, organizationName, baseDirectory) map {
			(p, main, name, ver, h, org, orgName, bd) => {
				// defaults (can we remove this duplication?)
				Package.addSpecManifestAttributes(name, ver, orgName) +:
				Package.addImplManifestAttributes(name, ver, h, org, orgName) +:
				// added - use the entries already defined in MANIFEST.MF
				useExistingManifestEntries(bd, ver) +:
				// more defaults
				main.map(Package.MainClass.apply) ++: 
				p
			}
		}
	}

	private def useExistingManifestEntries(baseDir: File, version: String): PackageOption = {
		val allEntries = retrieveManifestEntries(baseDir)
		// replace the bundle version in the manifest with the project's version
		val withUpdatedBundleVersion = allEntries.filterNot(e => e._1 == bundleVersionKey) ++ List( (bundleVersionKey, version) )
		Package.ManifestAttributes(withUpdatedBundleVersion: _*)
	}

	private def retrieveManifestEntries(baseDir: File): List[(String,String)] = {
		val manifestLocation = "file:///" + baseDir.getAbsolutePath + "/META-INF/MANIFEST.MF"
		println("Manifest location: " + manifestLocation)
		val manifest = new JarManifest(new URL(manifestLocation).openStream)
		val attributes = manifest.getMainAttributes
		val entrySet = attributes.entrySet.asScala
		entrySet.map{entry => (entry.getKey.toString, entry.getValue.toString)}.toList
	}

}
