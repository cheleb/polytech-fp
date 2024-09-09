name := "Polytech FP"

scalaVersion := "3.5.0"

val PekkoVersion = "1.1.0"

libraryDependencies ++= Seq(
  "dev.optics" %% "monocle-core" % "3.2.0",
  "dev.optics" %% "monocle-macro" % "3.3.0",
  "dev.zio" %% "zio" % "2.1.1",
  "dev.zio" %% "zio-streams" % "2.1.9",
  "dev.zio" %% "zio-json" % "0.7.3",
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "org.apache.pekko" %% "pekko-connectors-json-streaming" % "1.1.0-M1",
  "org.apache.pekko" %% "pekko-connectors-file" % "1.1.0-M1",
  "co.fs2" %% "fs2-io" % "3.11.0",
  "org.gnieh" %% "fs2-data-json" % "1.11.1",
  "org.gnieh" %% "fs2-data-json-circe" % "1.11.1",
  "io.circe" %% "circe-generic" % "0.14.10",
  "io.circe" %% "circe-parser" % "0.14.10"
)
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings"
)
