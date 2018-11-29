package it.unibo.pps17.core

import alice.tuprolog.Theory

package object prolog {

  /**
    * Path of Game of Life theory file.
    */
  val GOL_THEORY_PATH = "GameOfLife.pl"

  object Variables {
    /**
      * Val used for retrieving the X component of a board.
      */
    val X: String = "X"
    /**
      * Val used for retrieving the Y component of a board.
      */
    val Y: String = "Y"

    /**
      * Variable to bind to the list of alive cells.
      */
    val ALIVE_CELL_LIST: String = "AL"

    /**
      * Variable to bind to generation index.
      */
    val GENERATION: String = "G"
  }


  object Goals {

    /**
      * Goal to retrieve the dimension of the board.
      */
    val BOARD_GOAL: String = "board(" + Variables.X + ", " + Variables.Y + ")."

    /**
      * Start game goal.
      */
    val START_GAME_GOAL: String = "setup_board(" + Variables.ALIVE_CELL_LIST + ")."

    /**
      * Next generation goal.
      */
    val NEXT_GENERATION_GOAL: String = "compute_next_generation(" + Variables.ALIVE_CELL_LIST + "," +
      Variables.GENERATION + ")."
  }



  /**
    * Implict class to extend a file content containing a Logic Theory.
    * @param text the content of the file.
    */
  implicit class TextTheory(text:String) {
    def asTheory:Theory = new Theory(text)
  }

}
