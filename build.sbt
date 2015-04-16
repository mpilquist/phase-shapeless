organization := "com.github.mpilquist"
name := "phase"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

scalaVersion := "2.11.5"
libraryDependencies ++= Seq(
  "org.scodec" %% "scodec-core" % "1.7.1",
  "com.chuusai" %% "shapeless" % "2.2.0-SNAPSHOT"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)
