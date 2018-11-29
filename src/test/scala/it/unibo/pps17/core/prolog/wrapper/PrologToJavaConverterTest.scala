package it.unibo.pps17.core.prolog.wrapper

import alice.tuprolog.{Prolog, SolveInfo}
import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter.PositionList
import it.unibo.pps17.core.prolog.{GOL_THEORY_PATH, GameEngine, Goals}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, FunSuite}

@RunWith(classOf[JUnitRunner])
class PrologToJavaConverterTest extends FunSuite with BeforeAndAfterEach {

  var prologEngine:Prolog = _
  val TEST_GENERATION_INDEX = 1

  override def beforeEach() {
    prologEngine = GameEngine.buildPrologEngineFromPaths(Set(GOL_THEORY_PATH))
  }

  test("Conversion of a list") {
    val prologList = executeGoal(Goals.START_GAME_GOAL).getTerm(Goals.ALIVE_CELL_LIST)
    val javaList = PrologToJavaConverter.parsePrologListToJava(prologList)
    assert(javaList.isInstanceOf[PositionList] && !javaList.isEmpty)
  }

  test("Conversion of a generation") {
    executeGoal(Goals.START_GAME_GOAL)
    val solution = executeGoal(Goals.NEXT_GENERATION_GOAL)
    val aliveCells = solution.getTerm(Goals.ALIVE_CELL_LIST)
    val generationIndex = solution.getTerm(Goals.GENERATION)
    val generation = PrologToJavaConverter.parseGeneration(aliveCells, generationIndex)
    assert(generation.aliveCellsList.isInstanceOf[PositionList] &&
      generation.generationIndex == TEST_GENERATION_INDEX)
  }

  override def afterEach(){
    prologEngine.clearTheory()
  }

  private def executeGoal(goal:String):SolveInfo = prologEngine.solve(goal)

}
