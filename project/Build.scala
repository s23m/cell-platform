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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011-2012 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */

import sbt._
import sbt.Path._

import BuildSettings._
import DependencyManagement._
import Keys._
  
/*
TODO fix up project structure to eliminate warnings

[warn] Using project/plugins/ is deprecated for plugin configuration (/home/andrew/code/GoogleCode/gmodel-sbt-build/project/plugins).
[warn] Put .sbt plugin definitions directly in project/,
[warn]   .scala plugin definitions in project/project/,
[warn]   and remove the project/plugins/ directory.
*/

object CellBuild extends Build {

  lazy val root = Project(
    "root",
    file ("."),
    settings = buildSettings
  ) aggregate (
    communication,
    kernel,
    kernelTestbench,
    platform,

	platformTestscripts,
    kernelTests
  )

  lazy val communication = Project(
    "communication",
    file ("org.s23m.cell.communication"),
    settings = javaTestProjectSettings ++ Seq(
	    unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "src" / "main" / "xtend") },
		unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "src" / "main" / "xtend-gen") }
	)
  ) dependsOn (kernel, kernelTests, platform)

  lazy val kernel = Project(
    "kernel",
    file ("org.s23m.cell.kernel"),
    settings = javaProjectSettings
  )

  lazy val kernelTests = Project(
    "kernel-tests",
    file ("org.s23m.cell.kernel.tests"),
    settings = javaTestProjectSettings ++ Seq(
	    /*
	     * Set the Java test source directory to be <base>/src/main/java
	     * because of the cell-eclipse projects which depend on these classes 
	     */
	    javaSource in Test <<= baseDirectory(_ / "src" / "main" / "java")
  	)
  ) dependsOn (kernel)

  lazy val kernelTestbench = Project(
    "kernel-testbench",
    file ("org.s23m.cell.kernel.testbench"),
    settings = javaProjectSettings
  ) dependsOn (kernel)

  lazy val platform = Project(
    "platform",
    file ("org.s23m.cell.platform"),
    settings = javaTestProjectSettings
  ) dependsOn (kernel)
  
  lazy val platformTestscripts = Project(
    "platform-testscripts",
    file ("org.s23m.cell.platform.testscripts"),
    settings = javaProjectSettings
  ) dependsOn (kernel, kernelTests, kernelTestbench, platform)

}
