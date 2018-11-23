package it.unibo.pps17.core.prolog

import it.unibo.pps17.core.prolog.wrapper.Position

/**
  * Define the basic operation called inside a game engine.
  */
trait GameEngine {

  /**
    * Start the game inside the prolog engine.
    * @return the initial configuration of the game board.
    */
  def startGame():List[Position]


  /**
    * Compute next generation of cells.
    * @return the initial configuration of the
    */
  def computeNextGeneration():List[Position]

  /**
    * Check if the game is over.
    * @return true if the game is over, false otherwise.
    */
  def isGameOver():Boolean

}
