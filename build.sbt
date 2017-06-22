import sbtrelease._
import ReleaseStateTransformations._

organization := "com.gu"

name := "crosswords-api-client"

scalaVersion := "2.11.11"

resolvers ++= Seq(
  "Guardian GitHub Releases" at "http://guardian.github.io/maven/repo-releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.5.15",
  "joda-time" % "joda-time" % "2.9.9",
  "org.specs2" %% "specs2-core" % "3.7" % "test"
)

description := "Scala client for the Guardian's Crosswords API"

scmInfo := Some(ScmInfo(
  url("https://github.com/guardian/crosswords-api-scala-client"),
  "scm:git:git@github.com:guardian/crosswords-api-scala-client.git"
))

pomExtra :=
  <url>https://github.com/guardian/crosswords-api-scala-client</url>
    <developers>
      <developer>
        <id>theguardian</id>
        <name>The Guardian</name>
        <url>https://github.com/guardian/</url>
      </developer>
    </developers>


licenses := Seq("Apache V2" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

releaseCrossBuild := true

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
  pushChanges
)
