package it.unibo.pps17.view

import it.unibo.pps17.controller.GuiController
import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter.PositionList

/**
  * Trait to define the basic operation supported by a Gui object used inside GuiController.
  */
trait Gui {

  /**
    * Set a GuiController inside the GUI.
    *
    * @param controller the controller object to bind to that gui.
    */
  def setGuiController(controller: GuiController)

  /**
    * Display a list of cells.
    *
    * @param aliveCellList the list of cells passed as a list of coordinates.
    */
  def displayAliveCells(aliveCellList: PositionList): Unit

  /**
    * Set the board dimensions inside the GUI.
    *
    * @param boardWidth  board width.
    * @param boardHeight board height.
    */
  def initializeBoard(boardWidth: Int, boardHeight: Int): Unit

  /**
    * Display the count of alive cells.
    *
    * @param aliveCellsCount a string containing the number of alive cells.
    */
  def displayAliveCellsCount(aliveCellsCount: String): Unit

  /**
    * Update the generation count inside the GUI.
    *
    * @param generation a string containing the generation index.
    */
  def updateGeneration(generation: String): Unit

}
