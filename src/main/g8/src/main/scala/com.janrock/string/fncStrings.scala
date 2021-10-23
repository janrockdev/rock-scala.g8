package com.janrock.string

/** Factory for [[com.janrock.string]] instances. */
object fncStrings {

  /** A simple string function.
   *
   *  @param str string to print.
   */
  def simpleString(str: String): String = {
    str
  }

  /** A simple format string function.
   *
   *  @param word string to add.
   *  @param id id tag for the print.
   */
  def simpleStringFormat(word: String, id: Int): String = {
    val world = "World"
    f"$word $world $id!"
  }

}