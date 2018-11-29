package it.unibo.pps17.core.prolog

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, FunSuite}

@RunWith(classOf[JUnitRunner])
class GameEngineTest extends FunSuite with BeforeAndAfterEach {


  val DEFAULT_BOARD_DIMENDION: Int = 20
  var gameEngine: GameEngine = _

  override def beforeEach() {
    gameEngine = GameEngine()
  }

  test("Board dimension retrieval") {
    assert(gameEngine.getBoardDimension == (DEFAULT_BOARD_DIMENDION, DEFAULT_BOARD_DIMENDION))
  }


}
