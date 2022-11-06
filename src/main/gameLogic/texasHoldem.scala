package main.gameLogic
import main.cardLogic.Card
import main.gameLogic.Player.Player

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class texasHoldem(initPlayers: ArrayBuffer[Player]) extends Game(initPlayers) {

  val gameType: String = "Texas Holdem"
  var tableHand: Int = 0


  def deal(): Unit = {
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

  def tableCounts(): Unit = {
    val tableValues: List[Int] = List(
      table(0).cardValue,
      table(1).cardValue,
      table(2).cardValue,
      table(3).cardValue,
      table(4).cardValue
    )
    val firstCardCount: Int = tableValues.count(x => {x == tableValues.head})
    val secondCardCount: Int = tableValues.count(x => {x == tableValues(1)})
    val thirdCardCount: Int = tableValues.count(x => {x == tableValues(2)})
    val fourthCardCount: Int = tableValues.count(x => {x == tableValues(3)})
    val fifthCardCount: Int = tableValues.count(x => {x == tableValues(4)})
    val countList: List[Int] = List(
      firstCardCount,
      secondCardCount,
      thirdCardCount,
      fourthCardCount,
      fifthCardCount
    )
    val hand: Int = countList.max
    tableHand = hand
  }

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

  def twoPair(): Unit = {
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
            player.handRank = handRankings(8)
          } else if (firstCardCount == 2 || secondCardCount == 2) {
            player.handRank = handRankings(7)
          } else if (firstCardCount == 3 || secondCardCount == 3) {
            player.handRank = handRankings(3)
          }
        }
      }
    }
  }

  def checkTable(): Unit = {
    tableCounts()
    for (player <- players) {
      if (player.handRank == handRankings(10)) {
        if (tableHand == 2) {
          player.handRank = handRankings(9)
        } else if (tableHand == 3) {
          player.handRank = handRankings(7)
        } else if (tableHand == 4) {
          player.handRank = handRankings(3)
        }
      } else if (player.handRank == handRankings(9)) {
        if (tableHand == 2) {
          player.handRank = handRankings(8)
        } else if (tableHand == 3) {
          player.handRank = handRankings(4)
        } else if (tableHand == 4) {
          player.handRank = handRankings(3)
        }
      } else if (player.handRank == handRankings(8)) {
        if (tableHand == 3) {
          player.handRank = handRankings(4)
        } else if (tableHand == 4) {
          player.handRank = handRankings(3)
        }
      }
    }
  }

}