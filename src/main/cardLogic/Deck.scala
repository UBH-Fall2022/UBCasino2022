package main.cardLogic

import scala.collection.mutable
import scala.util.Random

class Deck() {

  var deckQueue: mutable.Queue[Card] = new mutable.Queue()
  var headOfDeck: Card = new Card
  val intDeck: List[String] = List("2","3","4","5","6","7","8","9","10","J","Q","K","A")
  var deckLen: Int = 0

  def createDeck(): Unit = {
    for (ele <- intDeck) {
      val createSpade: Card = new Card
      val createHeart: Card = new Card
      val createDiamond: Card = new Card
      val createClub: Card = new Card
      createClub.setSuit("C")
      createDiamond.setSuit("D")
      createHeart.setSuit("H")
      createSpade.setSuit("S")
      createSpade.setSign(ele)
      createClub.setSign(ele)
      createHeart.setSign(ele)
      createDiamond.setSign(ele)
      deckQueue.enqueue(createSpade)
      deckQueue.enqueue(createClub)
      deckQueue.enqueue(createDiamond)
      deckQueue.enqueue(createHeart)
    }
    headOfDeck = deckQueue.head
    deckLen = deckQueue.length
    deckShuffle()
  }

  def dealCard(): Card = {
    val retCard: Card = headOfDeck
    deckQueue.enqueue(deckQueue.dequeue())
    headOfDeck = deckQueue.head
    retCard
  }

  def deckShuffle(): Unit = {
    var randomList: List[Card] = List()
    while (deckQueue.nonEmpty) {
      randomList = randomList :+ deckQueue.head
      deckQueue.dequeue()
    }
    val newList = Random.shuffle(randomList)
    for (ele <- newList) {
      deckQueue.enqueue(ele)
    }
  }

  def printCard(): Unit = {
    for (ele <- deckQueue) {
      if (ele.cardValue < 11) {
        println("Card: " + ele.cardValue + " of " + ele.cardSuit)
      } else if (ele.cardValue == 11) {
        println("Card: Jack of " + ele.cardSuit)
      } else if (ele.cardValue == 12) {
        println("Card: Queen of " + ele.cardSuit)
      } else if (ele.cardValue == 13) {
        println("Card: King of " + ele.cardSuit)
      } else if (ele.cardValue == 14) {
        println("Card: Ace of " + ele.cardSuit)
      } else {
        throw new IndexOutOfBoundsException("Card Value is invalid!")
      }
    }
  }

}