organization := "oauth2"

name := "scala-oauth2"

version := "0.0.1"

scalaVersion := "2.9.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise")

unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)( _ :: Nil)

unmanagedSourceDirectories in Test <<= (scalaSource in Test)( _ :: Nil)

resolvers += "Element Nexus" at "http://maven.element.hr/nexus/content/groups/public/"

libraryDependencies ++= Seq(
  "net.databinder" %% "dispatch-http" % "0.8.8" % "test"
, "hr.element.etb" %% "etb-util" % "0.2.8"
, "ch.qos.logback" % "logback-classic" % "1.0.0" % "compile->default"
, "joda-time" % "joda-time" % "2.1"
, "org.joda" % "joda-convert" % "1.2"
, "org.scalatest" %% "scalatest" % "1.7.1" % "test"
)

