package it.unibo.pps17.core

import alice.tuprolog.Theory

package object prolog {

  /**
    * Path of Game of Life theory file.
    */
  val GOL_THEORY_PATH = "GameOfLife.pl"


  object Goals {

    /**
      * Variable to bind to the list of alive cells.
      */
    val ALIVE_CELL_LIST = "AL"

    /**
      * Variable to bind to generation index.
      */
    val GENERATION = "G"

    /**
      * Start game goal.
      */
    val START_GAME_GOAL = "setup_board(" + ALIVE_CELL_LIST + ")."

    /**
      * Next generation goal.
      */
    val NEXT_GENERATION_GOAL = "compute_next_generation(" + ALIVE_CELL_LIST + "," + GENERATION + ")."
  }



  /**
    * Implict class to extend a file content containing a Logic Theory.
    * @param text the content of the file.
    */
  implicit class TextTheory(text:String) {
    def asTheory:Theory = new Theory(text)
  }

}
