package com.janrock.string

import org.scalatest.funsuite.AnyFunSuite

class fncStringsTest extends AnyFunSuite {

  test("fncStrings.simpleString") {
    assert(fncStrings.simpleString("Hello World!") === "Hello World!")
  }
  test("fncStrings.simpleStringFormat") {
    assert(fncStrings.simpleStringFormat("Hello", 2) === "Hello World 2!")
  }
}