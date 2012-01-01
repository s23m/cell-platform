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

object BuildSettings {
  // TODO properly enforce naming conventions (http://maven.apache.org/guides/mini/guide-naming-conventions.html) with minimal duplication

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "org.s23m",
    // TODO replace with sbt-release version
    version      := "1.0.0",
    scalaVersion := "2.9.1",
    crossPaths := false,
    
    ivyXML := DependencyManagement.ivyXml,
   
    // append several options to the list of options passed to the Java compiler
    javacOptions ++= Seq("-source", "1.5", "-target", "1.5"),
    
    publishTo <<= (version) { version: String =>
      val nexus = "http://localhost:8081/nexus/content/repositories/"
      if (version.trim.endsWith("SNAPSHOT")) {
				Some("snapshots" at nexus + "snapshots/")
			} else {
				Some("releases"  at nexus + "releases/")
			}
		},
	
		credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )

	val buildAndPackageSettings = buildSettings ++ Packaging.defaultSettings 

  val javaProjectSettings = buildAndPackageSettings ++ Seq(
    // set the main Java source directory to be <base>/src
    javaSource in Compile <<= baseDirectory(_ / "src")
  )
  
  val javaTestProjectSettings = javaProjectSettings ++ Seq(
    javaSource in Test <<= baseDirectory(_ / "src"),
    parallelExecution in Test := false,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default",
    libraryDependencies += DependencyManagement.JUnit,
    testListeners <+= (target).map {
			t => new eu.henkelmann.sbt.JUnitXmlTestsListener(t.asFile.getAbsolutePath)
		}
  )
}
