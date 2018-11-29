package it.unibo.pps17.core.prolog.wrapper

import it.unibo.pps17.core.prolog.wrapper.PrologToJavaConverter.PositionList

/**
  * Class to convert a generation from prolog to scala.
  */
trait Generation {

  /**
    * The list of alive cells in a specific generation.
    *
    * @return a List containing all alive cells.
    */
  def aliveCellsList: PositionList

  /**
    * The generation index.
    *
    * @return an integer containing the generation index.
    */
  def generationIndex: Int

}

object Generation {

  def apply(aliveCellsList: PositionList, generationIndex: Int): Generation =
    GenerationImpl(aliveCellsList, generationIndex)

  private case class GenerationImpl(override val aliveCellsList: PositionList,
                                    override val generationIndex: Int) extends Generation

}
