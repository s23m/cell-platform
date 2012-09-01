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

import Predef.{conforms => _, _}
import sbt.Types.:+:
import sbt.Path._

/*
TODO fix up project structure to eliminate warnings

[warn] Using project/plugins/ is deprecated for plugin configuration (/home/andrew/code/GoogleCode/gmodel-sbt-build/project/plugins).
[warn] Put .sbt plugin definitions directly in project/,
[warn]   .scala plugin definitions in project/project/,
[warn]   and remove the project/plugins/ directory.
*/

object GmodelBuild extends Build {
  import BuildSettings._
  import DependencyManagement._
  import com.github.siasia.WebPlugin

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
	
	//artifactpool,
    //common,
	//connector,
    //editorSemanticdomain,
    //hibernateosgi,
    //objectpool,
    //repository,
    //repositoryClient,
    //serialization,
    //statistics,
    //generator,
	//artifactpoolTests
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

  /*

  lazy val artifactpool = Project(
    "artifactpool",
    file ("org.s23m.cell.artifactpool"),
    settings = javaProjectSettings
  )
  
  lazy val artifactpoolTests = Project(
    "artifactpool-tests",
    file ("org.s23m.cell.artifactpool.tests"),
    settings = javaTestProjectSettings
  ) dependsOn(artifactpool)
  
  lazy val common = Project(
    "common",
    file ("org.s23m.cell.common"),
    settings = javaProjectSettings
  )

  lazy val connector = Project(
    "connector",
    file ("org.s23m.cell.connector"),
    settings = javaProjectSettings
  ) dependsOn (serialization)
    
  lazy val editorSemanticdomain = Project(
    "editor-semanticdomain",
    file ("org.s23m.cell.editor.semanticdomain"),
    settings = {
      buildSettings ++
	  WebPlugin.webSettings ++
	  VaadinPlugin.vaadinSettings ++
	  Seq(
	    libraryDependencies ++= Seq(Vaadin, Jetty),
        // see https://github.com/harrah/xsbt/wiki/Library-Management
        unmanagedJars in Compile <++= baseDirectory map { base =>
		  val dir = base / "src" / "main" / "webapp" / "WEB-INF" / "lib"
		  val customJars = (dir ** "*.jar")
		  customJars.classpath
        },
        
        //port := 8080,
        VaadinPlugin.vaadinWidgetSet := "org.s23m.cell.editor.semanticdomain.widgetset.editorWidgetset"
      )
    }
  ) dependsOn (kernel, repositoryClient, serialization, common, platform)

  lazy val generator = Project(
    "generator",
    file ("org.s23m.cell.generator"),
    settings = javaProjectSettings
  ) dependsOn (kernel)

  lazy val hibernateosgi = Project(
    "hibernateosgi",
    file("org.s23m.cell.hibernateosgi"),
    settings = javaProjectSettings ++ Packaging.defaultSettings
  )

  lazy val objectpool = Project(
    "objectpool",
    file ("org.s23m.cell.objectpool"),
    settings = javaProjectSettings
  ) dependsOn (kernel)

  lazy val repository = Project(
    "repository",
    file ("org.s23m.cell.repository"),
    settings = javaProjectSettings
  ) dependsOn (artifactpool, common, connector, hibernateosgi, kernel, serialization, statistics)

  lazy val repositoryClient = Project(
    "repository-client",
    file ("org.s23m.cell.repository.client"),
    settings = javaProjectSettings
  ) dependsOn (connector, kernel, objectpool, repository, serialization, statistics)

  lazy val serialization = Project(
    "serialization",
    file ("org.s23m.cell.serialization"),
    settings = javaProjectSettings
  ) dependsOn (kernel, statistics)

  lazy val statistics = Project(
    "statistics",
    file ("org.s23m.cell.statistics"),
    settings = javaProjectSettings
  )

  */  
}
