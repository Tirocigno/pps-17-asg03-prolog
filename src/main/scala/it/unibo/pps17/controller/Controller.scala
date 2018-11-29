package it.unibo.pps17.controller

import it.unibo.pps17.core.prolog.GameEngine

/**
  * Controller class of all system.
  */
class Controller {

  var gameEngine = GameEngine()

  /**
    * Reset the game engine.
    */
  def resetGameEngine(): Unit = gameEngine = GameEngine()

  /**
    * Get the board dimension.
    */
  def boardDimension(): Unit = println(gameEngine.getBoardDimension)

  /**
    * Start the game.
    */
  def startGame(): Unit = println(gameEngine.startGame())

  /**
    * Compute next generation and print the system status.
    */
  def computeNextGeneration(): Unit = println(gameEngine.computeNextGeneration())

}
