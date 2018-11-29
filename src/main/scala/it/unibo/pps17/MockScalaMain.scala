package it.unibo.pps17

import it.unibo.pps17.controller.Controller

object MockScalaMain extends App {

  val controller = new Controller()

  println("Dimensioni della schacchiera: ")
  controller.boardDimension()

  println("Stato iniziale del sistema: ")
  controller.startGame()

  while (true) {
    Thread.sleep(2000)
    println("------------------------Next Generation----------------------")
    controller.computeNextGeneration()
  }

}
