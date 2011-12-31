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
import xml.Node

object DependencyManagement {

	lazy val JUnit = "junit" % "junit" % "4.10"
	lazy val Vaadin = "com.vaadin" % "vaadin" % "6.7.1"
  lazy val Jetty = "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container"

	def ivyXml = {
		<dependencies>
	  { overridesFor( Seq(JUnit) ) }
	</dependencies>
	}
	
	private def overridesFor(modules: Seq[ModuleID]): Seq[Node] = {
		modules.map{m =>
		  val org = m.organization
		  val module = m.name
		  val rev = m.revision
		  <override org={org} module={module} rev={rev}/>
		}
	}
}
