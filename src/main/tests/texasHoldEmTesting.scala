package main.tests

import main.cardLogic.Card
import main.gameLogic.Player.Player
import main.gameLogic.texasHoldem
import scala.collection.mutable.ArrayBuffer

object texasHoldEmTesting {

  def royalflush(): Unit = {
    val ej: Player = new Player("ej")

    val TenofHearts: Card = new Card()
    TenofHearts.setSuit("H")
    TenofHearts.setSign("10")

    val JackofHearts:Card = new Card()
    JackofHearts.setSuit("H")
    JackofHearts.setSign("J")

    val QueenofHearts:Card = new Card()
    QueenofHearts.setSuit("H")
    QueenofHearts.setSign("Q")

    val KingofHearts:Card = new Card()
    KingofHearts.setSuit("H")
    KingofHearts.setSign("K")

    val AceofHearts:Card = new Card()
    AceofHearts.setSuit("H")
    AceofHearts.setSign("A")

    ej.privHand = ej.privHand :+ AceofHearts
    ej.privHand = ej.privHand :+ KingofHearts

    val gamePlayers: ArrayBuffer[Player] = ArrayBuffer(ej)

    val fourTestGame: texasHoldem = new texasHoldem(gamePlayers)

    fourTestGame.addCards(2)

    fourTestGame.table = fourTestGame.table :+ QueenofHearts
    fourTestGame.table = fourTestGame.table :+ JackofHearts
    fourTestGame.table = fourTestGame.table :+ TenofHearts

    for(card <- fourTestGame.table){
      println(card.cardSuit +" of "+card.cardValue)
    }
    println(ej.privHand(0).cardSuit +" of "+ ej.privHand(0).cardValue +" and "+ej.privHand(1).cardSuit + " of "+ej.privHand(1).cardValue)
    fourTestGame.calculateHands()

    println(ej.handRank)
    println("")

    println("Winner: " + fourTestGame.computeWinner())

  }

//  def fourOfKind(): Unit = {
//
//    val journey: Player = new Player("Journey")
//
//    val fourOfHearts: Card = new Card()
//    fourOfHearts.setSuit("H")
//    fourOfHearts.setSign("4")
//
//    val fourOfSpades: Card = new Card()
//    fourOfSpades.setSuit("S")
//    fourOfSpades.setSign("4")
//
//    val fourOfDiamonds: Card = new Card()
//    fourOfDiamonds.setSuit("D")
//    fourOfDiamonds.setSign("4")
//
//    val fourOfClubs: Card = new Card()
//    fourOfClubs.setSuit("C")
//    fourOfClubs.setSign("4")
//
//    val aceOfSpades: Card = new Card()
//    aceOfSpades.setSuit("S")
//    aceOfSpades.setSign("A")
//
//    journey.privHand = journey.privHand :+ aceOfSpades
//    journey.privHand = journey.privHand :+ fourOfClubs
//
//    val gamePlayers: ArrayBuffer[Player] = ArrayBuffer(journey)
//
//    val fourTestGame: texasHoldem = new texasHoldem(gamePlayers)
//
//    fourTestGame.addCards(2)
//
//    fourTestGame.table = fourTestGame.table :+ fourOfHearts
//    fourTestGame.table = fourTestGame.table :+ fourOfDiamonds
//    fourTestGame.table = fourTestGame.table :+ fourOfSpades
//
//    fourTestGame.calculateHands()
//
//    var i: Int = 1
//    println("Table: ")
//    for (c <- fourTestGame.table) {
//      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
//      i += 1
//    }
//    println("")
//    println(journey.name)
//    for (c <- journey.privHand) {
//      println("Card " + i + ": " + c.cardValue + " of " + c.cardSuit)
//      i += 1
//    }
//    println(journey.handRank)
//    println("")
//
//  }

  def gameTest(): Unit = {

    val zac: Player = new Player("Zac")
    val ej: Player = new Player("EJ")
    val jeremy: Player = new Player("Jeremy")
    val journey: Player = new Player("Journey")

    val gamePlayers: ArrayBuffer[Player] = ArrayBuffer(zac, ej, jeremy, journey)

    val testGame: texasHoldem = new texasHoldem(gamePlayers)

    testGame.deal()
    testGame.addCards(5)

    testGame.calculateHands()

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

    println("Winner: " + testGame.computeWinner())

  }

  def main(args: Array[String]): Unit = {
    //gameTest()
    royalflush()
  }


}


