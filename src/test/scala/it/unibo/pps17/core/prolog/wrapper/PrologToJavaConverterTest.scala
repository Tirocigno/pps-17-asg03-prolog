package it.unibo.pps17.core.prolog.wrapper

import alice.tuprolog.{Prolog, SolveInfo}
import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter.PositionList
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import it.unibo.pps17.core.prolog.{GOL_THEORY_PATH, GameEngine, Goals}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PrologToJavaConverterTest extends FunSuite with BeforeAndAfterEach {

  var prologEngine:Prolog = _

  override def beforeEach() {
    prologEngine = GameEngine.buildPrologEngineFromPaths(Set(GOL_THEORY_PATH))
  }

  test("Conversion of a list") {
    val prologList = executeGoal(Goals.START_GAME_GOAL).getTerm(Goals.ALIVE_CELL_LIST)
    val javaList = PrologToJavaConverter.parsePrologListToJava(prologList)
    assert(javaList.isInstanceOf[PositionList] && !javaList.isEmpty)
  }

  override def afterEach(){
    prologEngine.clearTheory()
  }

  private def executeGoal(goal:String):SolveInfo = prologEngine.solve(goal)

}
