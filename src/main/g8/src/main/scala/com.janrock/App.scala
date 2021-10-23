package com.janrock

import com.typesafe.scalalogging.Logger
import com.janrock.string.fncStrings.{simpleString, simpleStringFormat}
import com.typesafe.config.ConfigFactory

/** Main object to run all challenges for [[com.janrock]] package.
 *
 *  Features:
 *  - assembly
 *  - dockerBuildAndPush
 *  - test
 *  - coverage
 *  - coverageReport
 *
 * Execute:
 *  {{{
 *  sbt assembly
 *  sh run.sh
 *  #or
 *  sbt dockerBuildAndPush
 *  docker run -it janrock/scalachallenge:latest
 *  }}}
 *
 */
object Main {

  val logger: Logger = Logger("scala-template")

  // params
  val word: String = ConfigFactory.load().getString("config.word")

  /** Main function to run all challenges for [[com.janrock]] package.
   */
  def main(args: Array[String]): Unit = {
    logger.debug("Starting application...")
    println(word)
    println(simpleString("Hello World!"))
    println(simpleStringFormat("Hello", 2))
    logger.debug("Done!")
  }

}
