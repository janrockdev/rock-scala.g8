import Dependencies._

name := "rock-scala-project"

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.janrock"
ThisBuild / organizationName := "janrock"

// deprecation param
lazy val root = (project in file("."))
  .settings(
    scalacOptions := List(
      "-encoding",
      "utf8",
      "-Xfatal-warnings",
      "-deprecation",
      "-unchecked"
    ),
    scalacOptions := {
      val old = scalacOptions.value
      scalaBinaryVersion.value match {
        case "2.12" => old
        case _      => old filterNot Set("-Xfatal-warnings", "-deprecation").apply
      }
    }
  )

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.9",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.6",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "com.typesafe" % "config" % "1.4.1"
)

//coverageEnabled := true
coverageExcludedPackages := "com.janrock"
coverageFailOnMinimum := true
coverageMinimumStmtTotal := 100
coverageMinimumBranchTotal := 100
//coverageMinimumStmtPerPackage := 100
//coverageMinimumBranchPerPackage := 100
//coverageMinimumStmtPerFile := 100
//coverageMinimumBranchPerFile := 100

Compile / packageBin / mainClass := Some("com.janrock.main")

enablePlugins(DockerPlugin)

docker / dockerfile := {
  // The assembly task generates a fat JAR file
  val artifact: File = assembly.value
  val artifactTargetPath = "/app/" + artifact.name

  new Dockerfile {
    from("openjdk:11-jre")
    add(artifact, artifactTargetPath)
    entryPoint("java", "-jar", artifactTargetPath)
  }
}

docker / imageNames := Seq(
  // Sets the latest tag
  ImageName("janrock" + "/" + name.value + ":latest"),
  // Sets a name with a tag that contains the project version
  ImageName(
    namespace = Some("janrock"),
    repository = name.value,
    tag = Some("v" + version.value)
  )
)

ThisBuild / assemblyMergeStrategy := {
  case PathList("module-info.class")         => MergeStrategy.discard
  case x if x.endsWith("/module-info.class") => MergeStrategy.discard
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}
