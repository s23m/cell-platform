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

import de.johoop.cpd4sbt.Language
import de.johoop.cpd4sbt.ReportType
import de.johoop.cpd4sbt.CopyPasteDetector._

import de.johoop.jacoco4sbt._
import JacocoPlugin._

object BuildSettings {
	// TODO properly enforce naming conventions (http://maven.apache.org/guides/mini/guide-naming-conventions.html) with minimal duplication
	val buildVersion = "1.0.0"
	
	private val parallelTestExecution = false
  
	// jacoco4sbt
	private val jacoco4sbtSettings = jacoco.settings ++ Seq(
		jacoco.reportFormats in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8")),
		// for consistency with "test"
		parallelExecution in jacoco.Config := parallelTestExecution
	)
	
	private val cpd4sbtSettings = cpdSettings ++ Seq(
		cpdLanguage := Language.Java,
		cpdReportType := ReportType.Simple,
		cpdReportName := "cpd.txt"
	)
	
	val buildSettings = {
		Defaults.defaultSettings ++ 
		jacoco4sbtSettings ++
		cpd4sbtSettings ++
		Seq(
			organization := "org.s23m",
			// TODO replace with sbt-release version
			version      := buildVersion,
			scalaVersion := "2.9.1",
			shellPrompt  := ShellPrompt.buildShellPrompt,
			crossPaths := false,
			
			ivyXML := DependencyManagement.ivyXml,
		   
			// append several options to the list of options passed to the Java compiler
			javacOptions ++= Seq("-source", "1.5", "-target", "1.5")
		)
	}
		
	val javaProjectSettings = buildSettings ++ Packaging.defaultSettings
  
	val javaTestProjectSettings = javaProjectSettings ++ Seq(
		parallelExecution in Test := parallelTestExecution,
		libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default",
		libraryDependencies += DependencyManagement.JUnit,
		testListeners <+= (target).map {
			t => new eu.henkelmann.sbt.JUnitXmlTestsListener(t.asFile.getAbsolutePath)
		}
	)
}
