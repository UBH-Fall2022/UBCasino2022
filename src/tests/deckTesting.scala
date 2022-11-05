package tests

import main.cardLogic.Deck

object deckTesting {

  def deckTest(): Unit = {
    val testDeck: Deck = new Deck
    testDeck.createDeck()
    println("Deck Length: " + testDeck.deckLen)
    println("Deck: " + testDeck.deckQueue)
    println("Head Card: " + testDeck.headOfDeck)
  }


  def main(args: Array[String]): Unit = {
    deckTest()
  }

}
