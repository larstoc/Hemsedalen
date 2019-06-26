name := "Hemsedalen"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies ++=  Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.1.8",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23", // or whatever the latest version is
  "com.typesafe.akka" %% "akka-actor" % "2.5.23"
)
