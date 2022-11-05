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
          val currAmount: Int = c.cardValue
          if (firstCard.cardValue == c.cardValue || secondCard.cardValue == c.cardValue) {
            player.handRank = handRankings(9)
          }
        }
      }
    }
  }

  def twoPair(): Unit = {
    var pairCounter: Int = 0
    for (player <- players) {
      if (player.handRank == handRankings(9)) {
        val firstCard: Card = player.privHand.head
        val secondCard: Card = player.privHand(1)
        var combinedCards: List[Int] = List()
        for (c <- table) {
          combinedCards = combinedCards :+ c.cardValue
        }
        val firstCardCount: Int = combinedCards.count(x => {x == firstCard.cardValue})
        val secondCardCount: Int = combinedCards.count(x => {x == secondCard.cardValue})
        if (firstCard.cardValue != secondCard.cardValue) {
          if (firstCardCount == 1 && secondCardCount == 1) {
            pairCounter = 2
          }
        }
        if (pairCounter == 2) {
          player.handRank = handRankings(8)
        }
      }
    }
  }

  def isTrio(): Unit = {
    for (player <- players) {
      if (player.handRank == handRankings(9)) {
        val firstCard: Card = player.privHand.head
        val secondCard: Card = player.privHand(1)
        var combinedCards: List[Int] = List()
        for (c <- table) {
          combinedCards = combinedCards :+ c.cardValue
        }
        combinedCards = combinedCards :+ firstCard.cardValue
        combinedCards = combinedCards :+ secondCard.cardValue
        for (ele <- combinedCards) {
          var currAmount: Int = 0
          for (e <- combinedCards) {
            if (e == ele) {
              currAmount += 1
            }
          }
          if (currAmount == 3) {
            player.handRank = handRankings(7)
          }
          if (currAmount == 4) {
            player.handRank = handRankings(3)
          }
        }
      }
    }
  }

}