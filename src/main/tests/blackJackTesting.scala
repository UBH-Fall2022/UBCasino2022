package main.tests

import main.gameLogic.Player._
import main.gameLogic.blackJack

import scala.collection.mutable.ArrayBuffer

object blackJackTesting {
  def main(args: Array[String]): Unit = {
    val Jeremy: Player = new Player("Jeremy")
    val Zac: Player = new Player("Zac")
    val Journey: Player = new Player("Journey")

    val players: ArrayBuffer[Player] = ArrayBuffer(Jeremy, Zac, Journey)

    val testGame: blackJack = new blackJack(players)

    testGame.deal()

    print("Private hand: "+testGame.dealer.privHand.head.cardValue+"\n Public Hand: "+testGame.dealer.publHand.head.cardValue)
  }
}
