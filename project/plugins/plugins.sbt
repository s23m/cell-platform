// -----------------------------------------------------------------------------
// Vaadin plugin
// -----------------------------------------------------------------------------
resolvers ++= Seq(
  "Arnauld" at "https://github.com/Arnauld/arnauld.github.com/raw/master/maven2"
)

addSbtPlugin("org.technbolts" % "sbt-vaadin-plugin" %  "0.0.2-SNAPSHOT")

// -----------------------------------------------------------------------------
// xsbt-web-plugin
// -----------------------------------------------------------------------------
// See https://github.com/siasia/xsbt-web-plugin/issues/49
libraryDependencies <++= (sbtVersion) { (sbt_ver) =>
    Seq("com.github.siasia" %% "xsbt-web-plugin" % (sbt_ver+"-0.2.10"))
}

// -----------------------------------------------------------------------------
// Tools
// -----------------------------------------------------------------------------
resolvers += Classpaths.typesafeResolver

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.5.0")

