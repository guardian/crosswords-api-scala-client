import sbtrelease._
import ReleaseStateTransformations._

releaseSettings

sonatypeSettings

organization := "com.gu"

name := "crosswords-api-client"

scalaVersion := "2.10.4"

resolvers ++= Seq(
  "Guardian GitHub Releases" at "http://guardian.github.io/maven/repo-releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.2.0",
  "joda-time" % "joda-time" % "2.3",
  "org.specs2" %% "specs2" % "2.3.4" % "test"
)

description := "Scala client for the Guardian's Crosswords API"

scmInfo := Some(ScmInfo(
  url("https://github.com/guardian/crosswords-api-scala-client"),
  "scm:git:git@github.com:guardian/crosswords-api-scala-client.git"
))

pomExtra := (
  <url>https://github.com/guardian/crosswords-api-scala-client</url>
    <developers>
      <developer>
        <id>robertberry</id>
        <name>Robert Berry</name>
        <url>https://github.com/robertberry</url>
      </developer>
    </developers>
  )

licenses := Seq("Apache V2" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

ReleaseKeys.crossBuild := true

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(
    action = state => Project.extract(state).runTask(PgpKeys.publishSigned, state)._1,
    enableCrossBuild = true
  ),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(state => Project.extract(state).runTask(SonatypeKeys.sonatypeReleaseAll, state)._1),
  pushChanges
)
