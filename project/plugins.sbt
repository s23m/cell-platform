addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")

addSbtPlugin("de.johoop" % "cpd4sbt" % "1.1.5")

addSbtPlugin("de.johoop" % "findbugs4sbt" % "1.4.0")

// --------------------------------------------------------------
// jacoco4sbt: https://bitbucket.org/jmhofer/jacoco4sbt/wiki/Home
// --------------------------------------------------------------

//libraryDependencies ++= Seq(
//  "org.jacoco" % "org.jacoco.core" % "0.5.9.201207300726" artifacts(Artifact("org.jacoco.core", "jar", "jar")),
//  "org.jacoco" % "org.jacoco.report" % "0.5.9.201207300726" artifacts(Artifact("org.jacoco.report", "jar", "jar")))

addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")	