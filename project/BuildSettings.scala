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
	val buildVersion = "1.0.0"
	
	private val parallelTestExecution = true
	
	object StaticAnalysis {
		import de.johoop.cpd4sbt.Language
		import de.johoop.cpd4sbt.{ReportType => CpdReportType}
		import de.johoop.cpd4sbt.CopyPasteDetector._
		
		import de.johoop.jacoco4sbt._
		import JacocoPlugin._
	
		import de.johoop.findbugs4sbt.{ReportType => FindBugsReportType}
		import de.johoop.findbugs4sbt.FindBugs._
	
		private val cpd4sbtSettings = cpdSettings ++ Seq(
			cpdLanguage := Language.Java,
			cpdReportType := CpdReportType.Simple,
			cpdReportName := "cpd.txt"
		)
		
		private val findbugs4sbtSettings = findbugsSettings ++ Seq(
			findbugsReportType := Some(FindBugsReportType.Html)
		)
		
		private val jacoco4sbtSettings = jacoco.settings ++ Seq(
			jacoco.reportFormats in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8")),
			// for consistency with "test"
			parallelExecution in jacoco.Config := parallelTestExecution
		)
		
		val defaultSettings = jacoco4sbtSettings ++ cpd4sbtSettings ++ findbugs4sbtSettings 
	}
	
	val buildSettings = {
		Defaults.defaultSettings ++ 
		StaticAnalysis.defaultSettings ++
		Seq(
			organization := "org.s23m",
			// TODO replace with sbt-release version
			version      := buildVersion,
			scalaVersion := "2.11.7",
			shellPrompt  := ShellPrompt.buildShellPrompt,
			crossPaths := false,
			
			ivyXML := DependencyManagement.ivyXml,
		   
			// append several options to the list of options passed to the Java compiler
			javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
		)
	}
		
	val javaProjectSettings = buildSettings ++ Packaging.defaultSettings
  
    // see http://stackoverflow.com/a/34490115 for JUnit support
	val javaTestProjectSettings = javaProjectSettings ++ Seq(
		parallelExecution in Test := parallelTestExecution,
		libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test->default",
		libraryDependencies += DependencyManagement.JUnit,
		crossPaths := false,
		testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a"))
		
		/*
		testListeners <+= (target).map {
			t => new eu.henkelmann.sbt.JUnitXmlTestsListener(t.asFile.getAbsolutePath)
		}
		*/
	)
}