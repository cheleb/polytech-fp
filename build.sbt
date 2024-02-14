name := "Polytech FP"

scalaVersion := "3.3.2"

libraryDependencies ++= Seq(
  "dev.optics" %% "monocle-core" % "3.2.0",
  "dev.optics" %% "monocle-macro" % "3.2.0",
  "dev.zio" %% "zio" % "2.0.21"
)
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings"
)
