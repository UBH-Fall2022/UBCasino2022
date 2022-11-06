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
    var twoPairCount: Int = 0
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
    for (ele <- countList) {
      if (ele == 2 && countList.count(x => {x == ele}) == 4) {
        twoPairCount = 1
      }
    }
    if (twoPairCount == 1) {
      tableHand = 8
    } else {
      tableHand = countList.max
    }
  }

  def isPair(): Unit = {
    for (player <- players) {
      val firstCard: Card = player.privHand.head
      val secondCard: Card = player.privHand(1)
      if (firstCard.cardValue == secondCard.cardValue) {
        player.handRank = handRankings(9)
        var tableList: List[Int] = List()
        for (ele <- table) {
          tableList = tableList :+ ele.cardValue
        }
        val firstCount: Int = tableList.count(x => {x == firstCard.cardValue})
        val secondCount: Int = tableList.count(x => {x == secondCard.cardValue})
        if (firstCount == 2 || secondCount == 2) {
          player.handRank = handRankings(3)
        }
      } else {
        for (c <- table) {
          if (firstCard.cardValue == c.cardValue || secondCard.cardValue == c.cardValue) {
            player.handRank = handRankings(9)
          }
        }
        var tableList: List[Int] = List()
        for (ele <- table) {
          tableList = tableList :+ ele.cardValue
        }
        val firstCount: Int = tableList.count(x => {x == firstCard.cardValue})
        val secondCount: Int = tableList.count(x => {x == secondCard.cardValue})
        if (firstCount == 3 || secondCount == 3) {
          player.handRank = handRankings(3)
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
          } else if (firstCardCount == 1 && tableHand == 2) {
            player.handRank = handRankings(8)
          } else if (secondCardCount == 1 && tableHand == 2) {
            player.handRank = handRankings(8)
          }
        }
        if (firstCardCount != 1 && secondCardCount != 1) {
          if (firstCard.cardValue == secondCard.cardValue && tableHand == 2) {
            player.handRank = handRankings(8)
          }
        }
      }
    }
  }

  def isFlush(): Unit = {
    for (player <- players) {
      val firstSuit: String = player.privHand.head.cardSuit
      val secondSuit: String = player.privHand(1).cardSuit
      var combinedSuits: List[String] = List()
      for (ele <- table) {
        combinedSuits = combinedSuits :+ ele.cardSuit
      }
      combinedSuits = combinedSuits :+ firstSuit
      combinedSuits = combinedSuits :+ secondSuit
      var countList: List[Int] = List()
      for (ele <- combinedSuits) {
        countList = countList :+ combinedSuits.count(x => {x == ele})
      }
      //println(countList)
      if (countList.max >= 5) {
        player.handRank = handRankings(5)
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
        } else if (tableHand == 8) {
          player.handRank = handRankings(8)
        }
      } else if (player.handRank == handRankings(9)) {
        if (tableHand == 8) {
          player.handRank = handRankings(8)
        } else if (tableHand == 2) {
          player.handRank = handRankings(8)
        }
      }
    }
  }

  def calculateHands(): Unit = {
    checkTable()
    isPair()
    twoPair()
    isFlush()
  }

}