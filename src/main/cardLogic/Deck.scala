package main.cardLogic

import scala.collection.mutable
import scala.util.Random

class Deck() {


  var deckQueue: mutable.Queue[Card] = new mutable.Queue()
  var headOfDeck: Card = deckQueue.head
  val intDeck: List[Int] = List(1,2,3,4,5,6,7,8,9,10,11,12,13,14)
  var deckLen: Int = 0

  def createDeck(): Unit = {
    var i: Double = 1
    for (ele <- intDeck) {
      val createSpade: Card = new Card
      val createHeart: Card = new Card
      val createDiamond: Card = new Card
      val createClub: Card = new Card
      createClub.setSuit("C")
      createDiamond.setSuit("D")
      createHeart.setSuit("H")
      createSpade.setSuit("S")
      createSpade.setSuit(i.toString)
      createClub.setSuit(i.toString)
      createHeart.setSuit(i.toString)
      createDiamond.setSuit(i.toString)
      i += 1
      deckQueue.enqueue(createSpade)
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
    val random = new Random()
    while (deckQueue.nonEmpty) {
      randomList = randomList :+ deckQueue(random.nextInt(deckQueue.length))
      deckQueue.dequeue()
    }
    for (ele <- randomList) {
      deckQueue.enqueue(ele)
    }
  }

}