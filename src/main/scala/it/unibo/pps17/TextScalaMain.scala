package it.unibo.pps17

import it.unibo.pps17.controller.TextController

object TextScalaMain extends App {

  val controller = new TextController()


  println("Stato iniziale del sistema: ")
  controller.startGame()

  while (true) {
    Thread.sleep(2000)
    println("------------------------Next Generation----------------------")
    controller.computeNextGeneration()
  }

}
