package it.unibo.pps17.core.prolog.wrapper


/**
  * This trait represent wrap a pos(X,Y) fact expressed inside prolog.
  */
trait Position {

  /**
    * X coordinate of the position.
    * @return an integer containing the x value of the cell position.
    */
  def x:Int

  /**
    * Y coordinate of the position.
    * @return an integer containing the y value of the cell position.
    */
  def y:Int

}

object Position {

  def apply(posX:Int, posY:Int):Position = PositionImpl(posX, posY)

  /**
    * Case class that extends Position trait.
    * @param x the x coordinate of the cell.
    * @param y the y coordinate of the cell.
    */
  private case class PositionImpl(override val x:Int, override val y:Int) extends Position

}


