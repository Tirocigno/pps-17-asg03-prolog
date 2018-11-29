package it.unibo.pps17.core.prolog

import alice.tuprolog.Prolog
import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter.PositionList
import it.unibo.pps17.core.prolog.wrapper.{Generation, PrologToJavaConverter}

/**
  * Define the basic operation called inside a game engine.
  */
trait GameEngine {

  /**
    * Retrieve the board dimension from the prolog engine.
    *
    * @return
    */
  def getBoardDimension: (Int, Int)

  /**
    * Start the game inside the prolog engine.
    * @return the initial configuration of the game board.
    */
  def startGame(): PositionList


  /**
    * Compute next generation of cells.
    *
    * @return the initial configuration of the
    */
  def computeNextGeneration(): Generation

}


object GameEngine {

  /**
    * Build a prolog engine loading the specified theories.
    * @param theoriesPaths a set containing all the theory file paths to load.
    * @return a Prolog engine.
    */
  def buildPrologEngineFromPaths(theoriesPaths: Set[String]):Prolog = {
    val engine:Prolog = new Prolog()
    theoriesPaths.foreach(path => {
     val file = io.Source.fromInputStream(getClass.getResourceAsStream(path)).mkString
     engine.addTheory(file.asTheory)
    }
   )
    engine
  }



  private class GameEngineImpl extends GameEngine {

    val engine:Prolog = buildPrologEngineFromPaths(Set(GOL_THEORY_PATH))

    override def getBoardDimension: (Int, Int) = {
      val solution = engine.solve(Goals.POS_GOAL)
      val xPos = solution.getTerm(Variables.X).toString
      val yPos = solution.getTerm(Variables.Y).toString
      (xPos.toInt, yPos.toInt)
    }

    override def startGame(): PositionList =
      PrologToJavaConverter.parsePrologListToJava(
        engine.solve(Goals.START_GAME_GOAL).getTerm(Variables.ALIVE_CELL_LIST))

    override def computeNextGeneration(): Generation = {
      val solution = engine.solve(Goals.NEXT_GENERATION_GOAL)
      val aliveCells = solution.getTerm(Variables.ALIVE_CELL_LIST)
      val generation = solution.getTerm(Variables.GENERATION)
      PrologToJavaConverter.parseGeneration(aliveCells, generation)
    }

  }
}

