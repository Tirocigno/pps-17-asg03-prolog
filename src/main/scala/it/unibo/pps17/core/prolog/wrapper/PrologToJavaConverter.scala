package it.unibo.pps17.core.prolog.wrapper

import java.util

import alice.tuprolog.{Struct, Term}

import scala.language.implicitConversions


object PrologToJavaConverter {

  type JavaList[A] = java.util.List[A]
  type PositionList = JavaList[Position]
  val POSITION_PREFIX = "pos("
  val POSITION_SUFFIX = ")"
  val POSITION_SPLIT = ","
  val EMPTY_STRING = ""
  val Y_COORDINATE_INDEX = 1

  /**
    * Convert a Prolog position to a scala one.
    * @param prologPosition a term containing a prolog position.
    * @return the translated scala position.
    */
  private def convertPositionToScala(prologPosition:Term):Position = {
    val coordinates = prologPosition.toString
      .replace(POSITION_PREFIX,EMPTY_STRING)
      .replace(POSITION_SUFFIX,EMPTY_STRING)
      .split(POSITION_SPLIT)
      .toList
    Position(coordinates.head.toInt, coordinates(Y_COORDINATE_INDEX).toInt)
  }

  /**
    * Iterate a prolog list and converts its elements.
    * @param javaList a Java list on which the converted elements will be added.
    * @param prologList the source prolog list.
    */
  private def iteratePrologList(javaList: PositionList, prologList:Struct):Unit = {
    val iterator = prologList.listIterator()
    while(iterator.hasNext) {
      javaList.add(convertPositionToScala(iterator.next()))
    }
  }

  /**
    * Parse a prolog list of position to a java one.
    * @param prologList a prolog term containing a prolog list.
    * @return a java list composed of Positions.
    */
  def parsePrologListToJava(prologList:Term): PositionList = {
    val positionList: PositionList = new util.ArrayList()
    if(prologList.isList) {
      val plStruct = prologList.asInstanceOf[Struct]
      iteratePrologList(positionList,plStruct)
    }
    positionList
    }

  /**
    * Convert a generation calculated via Prolog engine to a scala object.
    *
    * @param prologList      the list of alive cells as prolog term.
    * @param generationIndex the index of the generation as a prolog fact.
    * @return a generation object built upon the specified parameters.
    */
  def parseGeneration(prologList: Term, generationIndex: Term): Generation =
    Generation(parsePrologListToJava(prologList), generationIndex.toString.toInt)


}
