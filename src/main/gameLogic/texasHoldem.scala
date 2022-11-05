package main.gameLogic
import main.gameLogic.Player.Player

class texasHoldem(initPlayers: Array[Player]) extends Game(initPlayers) {
  val gameType: String = "Texas Holdem"
  def deal(): Unit ={
    for(player <- players) {
      player.cards :+ deck.dealCard()
      player.cards :+ deck.dealCard()
    }
  }

  def addCards(num: Int): Unit = {
    for(num <- 1 to num){
      table :+ deck.dealCard()
    }
  }
}