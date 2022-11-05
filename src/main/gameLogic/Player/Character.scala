package main.gameLogic.Player
import main.cardLogic.Card

abstract class Character {
  var name = ""
  var balance: Int = 10000
  var cards: List[Card] = List()

  def changePrice(balance: Int): Unit = {
    this.balance = balance
  }

  def getPrice(): Unit = {
    balance
  }

  def updateCards(card: Card): Unit = {
    cards = cards :+ card
  }

  def clearCards(): Unit = {
    cards = List()
  }

  def displayCards(): Unit = {
    cards.toString()
  }
}
