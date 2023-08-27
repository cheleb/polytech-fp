name := "Polytech FP"

scalaVersion := "3.3.0"

libraryDependencies += "dev.zio" %% "zio" % "2.0.16"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
//  "-Xfatal-warnings",
  "-Xlint"
)
