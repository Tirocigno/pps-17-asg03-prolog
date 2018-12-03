package it.unibo.pps17.controller

import it.unibo.pps17.core.prolog.GameEngine
import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter.PositionList
import it.unibo.pps17.view.Gui

trait GuiController extends Controller {

  /**
    * Set a view controller inside the GuiController.
    *
    * @param viewController controller of the graphic stage.
    */
  def setViewController(viewController: Gui)
}

object GuiController {

  def apply(): GuiController = new GuiControllerImpl()

  private class GuiControllerImpl() extends GuiController {
    var viewController: Gui = _
    var engine: GameEngine = GameEngine()


    override def setViewController(viewController: Gui): Unit = {
      this.viewController = viewController
      initializeViewController()
    }

    private def initializeViewController(): Unit = {
      viewController.setGuiController(this)
      val boardDimension = engine.getBoardDimension
      viewController.initializeBoard(boardDimension._1, boardDimension._2)
    }

    override def resetGameEngine(): Unit = {
      engine = GameEngine()
    }

    override def startGame(): Unit = {
      displayAndUpdateAliveCellsList(engine.startGame())
    }

    override def computeNextGeneration(): Unit = {
      val generation = engine.computeNextGeneration()
      displayAndUpdateAliveCellsList(generation.aliveCellsList)
      viewController.updateGeneration(generation.generationIndex.toString)
    }

    private def displayAndUpdateAliveCellsList(aliveCellsList: PositionList): Unit = {
      viewController.displayAliveCells(aliveCellsList)
      viewController.displayAliveCellsCount(aliveCellsList.size().toString)
    }
  }

}
