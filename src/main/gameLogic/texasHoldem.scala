package main.gameLogic
import main.cardLogic.Card
import main.gameLogic.Player.Player

import scala.collection.mutable.ArrayBuffer

class texasHoldem(initPlayers: ArrayBuffer[Player]) extends Game(initPlayers) {
  val gameType: String = "Texas Holdem"
  def deal(): Unit ={
    for(player <- players) {
      player.cards = player.cards :+ deck.dealCard()
      player.cards = player.cards :+ deck.dealCard()
    }
  }

  def addCards(num: Int): Unit = {
    for(num <- 1 to num){
      table = table :+ deck.dealCard()
    }
  }

  val handRankings: Map[Int, String] = Map(
    1 -> "Royal Flush",
    2 -> "Straight Flush",
    3 -> "Four Of A Kind",
    4 -> "Full House",
    5 -> "Flush",
    6 -> "Straight",
    7 -> "Three Of A Kind",
    8 -> "Two Pair",
    9 -> "Pair",
    10 -> "High Card"
  )

  def isPair(): Unit = {
    for (player <- players) {
      val firstCard: Card = player.cards.head
      val secondCard: Card = player.cards(1)
      if (firstCard.cardValue == secondCard.cardValue) {
        player.handRank = handRankings(9)
      } else {
        for (c <- table) {
          if (firstCard.cardValue == c.cardValue || secondCard.cardValue == c.cardValue) {
            player.handRank = handRankings(9)
          }
        }
      }
    }
  }

}