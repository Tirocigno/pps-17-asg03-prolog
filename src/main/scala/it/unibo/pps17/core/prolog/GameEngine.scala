package it.unibo.pps17.core.prolog

import alice.tuprolog.Prolog
import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter
import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter.PositionList

/**
  * Define the basic operation called inside a game engine.
  */
trait GameEngine {

  /**
    * Start the game inside the prolog engine.
    * @return the initial configuration of the game board.
    */
  def startGame(): PositionList


  /**
    * Compute next generation of cells.
    * @return the initial configuration of the
    */
  def computeNextGeneration(): PositionList

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

    override def startGame(): PositionList =
      PrologToJavaConverter.parsePrologListToJava(engine.termSolve(Goals.START_GAME_GOAL))

    override def computeNextGeneration(): PositionList = ???

  }
}

