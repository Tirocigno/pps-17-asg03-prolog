package it.unibo.pps17.controller

import it.unibo.pps17.core.prolog.GameEngine

trait Controller {

  /**
    * Reset the game engine.
    */
  def resetGameEngine(): Unit

  /**
    * Get the board dimension.
    */
  def boardDimension(): Unit

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

  /**
    * Reset the game engine.
    */
  override def resetGameEngine(): Unit = gameEngine = GameEngine()

  /**
    * Get the board dimension.
    */
  override def boardDimension(): Unit = println(gameEngine.getBoardDimension)

  /**
    * Start the game.
    */
  override def startGame(): Unit = println(gameEngine.startGame())

  /**
    * Compute next generation and print the system status.
    */
  override def computeNextGeneration(): Unit = println(gameEngine.computeNextGeneration())

}
