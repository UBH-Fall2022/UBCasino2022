package main.gameLogic.Player
import main.cardLogic.Card

abstract class Character {
  var name = ""
  var balance: Int = 10000
  var publHand: List[Card] = List()
  var privHand: List[Card] = List()
  var handRank: String = "High Card"
  var winnerWeight: Int = 0
  var straightBy: Int = 0
  var pairBy: Int = 0
  var twoPairBy1: Int = 0
  var twoPairBy2: Int = 0
  var in: Boolean = false
  
  def displayBJ(): Unit = {
    for (ele <- privHand) {
      if (ele.cardValue == 11) {
        println("Jack of " + ele.cardSuit)
      } else if (ele.cardValue == 12) {
        println("Queen of " + ele.cardSuit)
      } else if (ele.cardValue == 13) {
        println("King of " + ele.cardSuit)
      } else if (ele.cardValue == 14) {
        println("Ace of " + ele.cardSuit)
      } else {
        println(ele.cardValue + " of " + ele.cardSuit)
      }
    }
    for (ele <- publHand) {
      if (ele.cardValue == 11) {
        println("Jack of " + ele.cardSuit)
      } else if (ele.cardValue == 12) {
        println("Queen of " + ele.cardSuit)
      } else if (ele.cardValue == 13) {
        println("King of " + ele.cardSuit)
      } else if (ele.cardValue == 14) {
        println("Ace of " + ele.cardSuit)
      } else {
        println(ele.cardValue + " of " + ele.cardSuit)
      }
    }
  }

  def displayCards(): Unit = {
    for (ele <- privHand) {
      if (ele.cardValue == 11) {
        println("Jack of " + ele.cardSuit)
      } else if(ele.cardValue == 12) {
        println("Queen of " + ele.cardSuit)
      } else if (ele.cardValue == 13) {
        println("King of " + ele.cardSuit)
      } else if (ele.cardValue == 14) {
        println("Ace of " + ele.cardSuit)
      } else {
        println(ele.cardValue + " of " + ele.cardSuit)
      }
    }
  }

  def changePrice(balance: Int): Unit = {
    this.balance = balance
  }

  def updateCards(card: Card): Unit = {
    privHand = privHand :+ card
  }

  def clearCards(): Unit = {
    publHand = List()
    privHand = List()
  }


}
