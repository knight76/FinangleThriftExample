// https://twitter.github.io/scrooge/SBTPlugin.html
name := "finagleThriftExample"

version := "0.1"

lazy val versions = new {
  val finagle = "19.4.0"
  val thrift = "0.12.0"
}

lazy val baseSettings = Seq(
  version := "1.0.0-SNAPSHOT",
  scalaVersion := "2.12.8",
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    "Twitter Maven" at "https://maven.twttr.com"
  ),
  libraryDependencies ++= Seq(
    "com.twitter" %% "scrooge-core" % versions.finagle,
    "org.apache.thrift" % "libthrift" % versions.thrift,
    "com.twitter" %% "finagle-thrift" % versions.finagle
  ),
  publishMavenStyle := true
)


lazy val root = (project in file("."))
  .settings(baseSettings: _*)
  .dependsOn(idl, client, server)
  .aggregate(idl, client, server)


lazy val idl = (project in file("idl"))
  .settings(baseSettings: _*)
  .settings(
    name := "idl",
    moduleName := "idl",
    scroogeLanguages in Compile := Seq("scala")

  )


lazy val client = (project in file("client"))
  .settings(baseSettings: _*)
  .settings(
    name := "client",
    moduleName := "client"
  )
  .dependsOn(idl)



lazy val server = (project in file("server"))
  .settings(baseSettings: _*)
  .settings(
    name := "server",
    moduleName := "server"
  )
  .dependsOn(idl)