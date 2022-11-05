package main.gameLogic.Player
import main.cardLogic.Card

abstract class Character {
  var name = ""
  var balance: Int = 10000
  var publHand: List[Card] = List()
  var privHand: List[Card] = List()
  var handRank: String = "High Card"

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
