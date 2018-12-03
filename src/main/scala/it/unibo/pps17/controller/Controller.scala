package it.unibo.pps17.controller

import it.unibo.pps17.core.prolog.GameEngine

trait Controller {

  /**
    * Reset the game engine.
    */
  def resetGameEngine(): Unit


  /**
    * Start the game.
    */
  def startGame(): Unit

  /**
    * Compute next generation and print the system status.
    */
  def computeNextGeneration(): Unit

}

/**
  * Textual controller implementation.
  */
class TextController() extends Controller {

  var gameEngine = GameEngine()

  override def resetGameEngine(): Unit = gameEngine = GameEngine()

  override def startGame(): Unit = println(gameEngine.startGame())

  override def computeNextGeneration(): Unit = println(gameEngine.computeNextGeneration())

}
