//resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

resolvers += "Maven central" at "http://repo1.maven.org/maven2"

//libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.10"))

libraryDependencies += "com.github.siasia" %% "xsbt-web-plugin" % "0.11.0-0.2.8"

resolvers += Classpaths.typesafeResolver

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.5.0")

