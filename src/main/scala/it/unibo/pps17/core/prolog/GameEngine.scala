package it.unibo.pps17.core.prolog

/**
  * Define the basic operation called inside a game engine.
  */
trait GameEngine {

  def startGame()

  def computeNextGeneration():

  def isGameOver():Boolean

}
