package main.gameLogic
import main.cardLogic.Card
import main.gameLogic.Player.Player

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class texasHoldem(initPlayers: ArrayBuffer[Player]) extends Game(initPlayers) {
  val gameType: String = "Texas Holdem"
  def deal(): Unit ={
    for(player <- players) {
      player.privHand = player.privHand :+ deck.dealCard()
      player.privHand = player.privHand :+ deck.dealCard()
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
      val firstCard: Card = player.privHand.head
      val secondCard: Card = player.privHand(1)
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

  def isTrio(): Unit = {
    for (player <- players) {
      if (player.handRank == handRankings(9)) {
        val firstCard: Card = player.privHand.head
        val secondCard: Card = player.privHand(1)
        var combinedCards: mutable.Queue[Int] = new mutable.Queue
        for (c <- table) {
          combinedCards.enqueue(c.cardValue)
        }
        combinedCards.enqueue(firstCard.cardValue)
        combinedCards.enqueue(secondCard.cardValue)
        var currCard: Int = combinedCards.dequeue()
        var possible: Int = 0
        while (combinedCards.nonEmpty) {
          if (combinedCards.contains(currCard)) {
            possible += 1
            combinedCards.dequeue()
          } else {
            if (possible > 1) {
              player.handRank = handRankings(7)
            }
            currCard = combinedCards.dequeue()
            possible = 0
          }
        }
        if (possible > 1) {
          player.handRank = handRankings(7)
        }
      }
    }
  }

}