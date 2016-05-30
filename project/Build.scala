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

import BuildSettings._
import DependencyManagement._
import Keys._

object CellBuild extends Build {

  lazy val root = Project(
    "root",
    file ("."),
    settings = buildSettings
  ) aggregate (
    communication,
    kernel,
    kernelTests,
    persistence,
    platform
  )

  lazy val communication = Project(
    "communication",
    file ("org.s23m.cell.communication"),
    settings = javaTestProjectSettings ++ Seq(
	    unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "src" / "main" / "xtend") },
		unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "src" / "main" / "xtend-gen") }
	)
  ) dependsOn (kernel, kernelTests, platform % "test->test;compile->compile")

  lazy val kernel = Project(
    "kernel",
    file ("org.s23m.cell.kernel"),
    settings = javaTestProjectSettings
  )

  lazy val kernelTests = Project(
    "kernel-tests",
    file ("org.s23m.cell.kernel.tests"),
    settings = javaTestProjectSettings
  ) dependsOn (kernel)

  lazy val persistence = Project(
    "persistence",
    file ("org.s23m.cell.persistence"),
    settings = javaTestProjectSettings ++ Seq(
      unmanagedJars in Test <+= (baseDirectory) map { bd => Attributed.blank(bd / "test-lib" / "h2-1.4.192.jar") }
    )
  )

  lazy val platform = Project(
    "platform",
    file ("org.s23m.cell.platform"),
    settings = javaTestProjectSettings
  ) dependsOn (kernel)
  
}
