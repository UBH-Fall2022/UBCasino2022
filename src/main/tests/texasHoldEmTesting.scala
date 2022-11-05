package main.tests

import main.gameLogic.Player.Player
import main.gameLogic.texasHoldem

import scala.collection.mutable.ArrayBuffer

object texasHoldEmTesting {

  def main(args: Array[String]): Unit = {

    val zac: Player = new Player("Zac")
    val ej: Player = new Player("EJ")
    val jeremy: Player = new Player("Jeremy")
    val journey: Player = new Player("Journey")

    val gamePlayers: ArrayBuffer[Player] = ArrayBuffer(zac, ej, jeremy, journey)

    val testGame: texasHoldem = new texasHoldem(gamePlayers)

    testGame.deal()
    testGame.addCards(5)

    testGame.isPair()

    var i: Int = 1
    println("Table: ")
    for (c <- testGame.table) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    i = 1
    println(zac.name)
    for (c <- zac.cards) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(zac.handRank)
    i = 1
    println(ej.name)
    for (c <- ej.cards) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(ej.handRank)
    i = 1
    println(jeremy.name)
    for (c <- jeremy.cards) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(jeremy.handRank)
    i = 1
    println(journey.name)
    for (c <- journey.cards) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(journey.handRank)
  }


}
