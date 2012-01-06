//resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

//resolvers += "Maven central" at "http://repo1.maven.org/maven2"

//libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.10"))


// remove this once plugins are working
//retrieveManaged := true // and the dependencies will be copied to lib_managed as a build-local cache

sbtVersion := "0.11.1"

resolvers ++= Seq(
  "Web plugin repo" at "http://siasia.github.com/maven2",
  //"Arnauld" at "https://github.com/Arnauld/arnauld.github.com/blob/master/maven2" //"https://github.com/Arnauld/arnauld.github.com/raw/master/maven2"
  "Arnauld" at "https://github.com/Arnauld/arnauld.github.com/raw/master/maven2"
)

addSbtPlugin("org.technbolts" % "sbt-vaadin-plugin" %  "0.0.2-SNAPSHOT")


//addSbtPlugin("org.technbolts" %% "sbt-vaadin-plugin" %  "0.0.2-SNAPSHOT") // _0.11.1

// See https://github.com/siasia/xsbt-web-plugin/issues/49
libraryDependencies ++= Seq(
  "com.github.siasia" %% "xsbt-web-plugin" % "0.11.1-0.2.10"
  //"org.technbolts" % "sbt-vaadin-plugin" % "0.0.2-SNAPSHOT_0.11.1"
)

//libraryDependencies <++= (sbtVersion) { (sbt_ver) =>
//    Seq("com.github.siasia" %% "xsbt-web-plugin" % (sbt_ver+"-0.2.10"))
//}

resolvers += Classpaths.typesafeResolver

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.5.0")

