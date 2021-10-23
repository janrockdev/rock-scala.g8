import Dependencies._

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.janrock"
ThisBuild / organizationName := "janrock"

lazy val root = (project in file("."))
  .settings(name := "$name$", libraryDependencies += scalaTest % Test)
