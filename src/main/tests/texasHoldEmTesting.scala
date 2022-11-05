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
    testGame.isTrio()

    var i: Int = 1
    println("Table: ")
    for (c <- testGame.table) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println("")
    i = 1
    println(zac.name)
    for (c <- zac.privHand) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(zac.handRank)
    println("")
    i = 1
    println(ej.name)
    for (c <- ej.privHand) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(ej.handRank)
    println("")
    i = 1
    println(jeremy.name)
    for (c <- jeremy.privHand) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(jeremy.handRank)
    println("")
    i = 1
    println(journey.name)
    for (c <- journey.privHand) {
      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
      i += 1
    }
    println(journey.handRank)
    println("")
  }


}
