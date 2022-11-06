package main.gameLogic
import main.cardLogic.Card
import main.gameLogic.Player.Player

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class texasHoldem(initPlayers: ArrayBuffer[Player]) extends Game(initPlayers) {

  val gameType: String = "Texas Holdem"
  var tableHand: Int = 0
  var flushBy: String = "null"


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

  def isTrio(): Unit = {
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
        if (firstCard.cardValue == secondCard.cardValue) {
          if (firstCardCount == 1 && secondCardCount == 1) {
            player.handRank = handRankings(7)
          }
        }
      }
    }
  }

  def isStraight(): Unit = {
    for (player <- players) {
      var combinedList: List[Int] = List()
      for (ele <- player.privHand) {
        if (!combinedList.contains(ele.cardValue)) {
          if (ele.cardValue == 14) {
            combinedList = combinedList :+ 1
            combinedList = combinedList :+ ele.cardValue
          } else {
            combinedList = combinedList :+ ele.cardValue
          }
        }
      }
      for (ele <- table) {
        if (!combinedList.contains(ele.cardValue)) {
          if (ele.cardValue == 14) {
            combinedList = combinedList :+ 1
            combinedList = combinedList :+ ele.cardValue
          } else {
            combinedList = combinedList :+ ele.cardValue
          }
        }
      }
      combinedList = combinedList.sorted
      //println(combinedList)
      var inputStraight: String = ""
      for (ele <- combinedList) {
        inputStraight = inputStraight + ele.toString
      }
      //println(inputStraight)
      val possibleStraights: List[String] = List(
        "12345",
        "23456",
        "34567",
        "45678",
        "56789",
        "678910",
        "7891011",
        "89101112",
        "910111213",
        "1011121314"
      )
      var straightCount: Int = 0
      for (ele <- possibleStraights) {
        if (inputStraight.contains(ele)) {
          player.straightBy = ele.toInt
          straightCount += 1
        }
      }
      if (straightCount > 0) {
        player.handRank = handRankings(6)
      }
    }
  }

  def isFlush(): Unit = {
    for (player <- players) {
      val currentRank: String = player.handRank
      val firstSuit: String = player.privHand.head.cardSuit
      val secondSuit: String = player.privHand(1).cardSuit
      var combinedSuits: List[String] = List()
      for (ele <- table) {
        combinedSuits = combinedSuits :+ ele.cardSuit
      }
      combinedSuits = combinedSuits :+ firstSuit
      combinedSuits = combinedSuits :+ secondSuit
      var countList: List[Int] = List()
      var suitFreq: Map[Int, String] = Map()
      for (ele <- combinedSuits) {
        suitFreq += (combinedSuits.count(x => {x == ele}) -> ele)
        countList = countList :+ combinedSuits.count(x => {x == ele})
      }
      var suitFreqList: List[Int] = List()
      for (ele <- suitFreq.keys) {
        suitFreqList = suitFreqList :+ ele
      }
      val suitMax: String = suitFreq(suitFreqList.max)
      flushBy = suitMax
      //println(countList)
      if (countList.max >= 5) {
        player.handRank = handRankings(5)
      }
      if (countList.max >= 5 && currentRank == handRankings(6)) {
        player.handRank = handRankings(2)
      }
    }
  }

  def isFullHouse(): Unit = {
    for (player <- players) {
      val firstCard: Int = player.privHand.head.cardValue
      val secondCard: Int = player.privHand(1).cardValue
      if (firstCard == secondCard) {
        var tableList: List[Int] = List()
        for (ele <- table) {
          tableList = tableList :+ ele.cardValue
        }
        val firstCount: Int = tableList.count(x => {x == firstCard})
        val secondCount: Int = tableList.count(x => {x == secondCard})
        val totalCount: Int = firstCount + secondCount
        if (tableHand == 3 && totalCount == 0) {
          player.handRank = handRankings(4)
        }
      }
      var tableList: List[Int] = List()
      for (ele <- table) {
        tableList = tableList :+ ele.cardValue
      }
      val firstCount: Int = tableList.count(x => {x == firstCard})
      val secondCount: Int = tableList.count(x => {x == secondCard})
      if (player.handRank == handRankings(9)) {
        if (firstCount == 1 || secondCount == 1) {
          if (tableHand == 3) {
            player.handRank = handRankings(4)
          }
        }
      }
      if (player.handRank == handRankings(7)) {
        if (tableHand == 8) {
          player.handRank = handRankings(4)
        }
      }
      if (player.handRank == handRankings(7) && player.privHand.head.cardValue == player.privHand(1).cardValue && tableHand == 2) {
        player.handRank = handRankings(4)
      }
    }
  }

  def isRoyalFlush(): Unit = {
    for (player <- players) {
      if (player.handRank == handRankings(5)) {
        var inputHand: List[Int] = List()
        for (ele <- player.privHand) {
          if (ele.cardSuit == flushBy) {
            inputHand = inputHand :+ ele.cardValue
          }
        }
        for (ele <- table) {
          if (ele.cardSuit == flushBy) {
            inputHand = inputHand :+ ele.cardValue
          }
        }
        inputHand = inputHand.sorted
        val expectedHand: List[Int] = List(
          10,
          11,
          12,
          13,
          14
        )
        var need: Int = 0
        for (ele <- inputHand) {
          if (expectedHand.contains(ele)) {
            need = need + 1
          }
        }
        if (need == 5) {
          player.handRank = handRankings(1)
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
    isTrio()
    isStraight()
    isFlush()
    isFullHouse()
    isRoyalFlush()
  }

  def computeWinner(): String = {
    var winner: Player = new Player("null")
    val winnerMap: Map[String, Int] = Map(
      "Royal Flush" -> 10,
      "Straight Flush" -> 9,
      "Four Of A Kind" -> 8,
      "Full House" -> 7,
      "Flush" -> 6,
      "Straight" -> 5,
      "Three Of A Kind" -> 4,
      "Two Pair" -> 3,
      "Pair" -> 2,
      "High Card" -> 1
    )

    for (player <- players) {
      player.winnerWeight = winnerMap(player.handRank)
    }
    for (player <- players) {
      if (player.winnerWeight > winner.winnerWeight) {
        winner = player
      } else if (player.winnerWeight == winner.winnerWeight) {
        val playerCardValues: List[Int] = List(player.privHand.head.cardValue, player.privHand(1).cardValue)
        val winnerCardValues: List[Int] = List(winner.privHand.head.cardValue, winner.privHand(1).cardValue)
        val winnerMax = winnerCardValues.max
        val playerMax = playerCardValues.max
        if (player.winnerWeight == 6) {
          var maxWinnerSuit: Int  = 0
          var maxPlayerSuit: Int = 0
          for (ele <- winner.privHand) {
            if (ele.cardSuit == flushBy) {
              if (ele.cardValue > maxWinnerSuit) {
                maxWinnerSuit = ele.cardValue
              }
            }
          }
          for (ele <- player.privHand) {
            if (ele.cardSuit == flushBy) {
              if (ele.cardValue > maxPlayerSuit) {
                maxPlayerSuit = ele.cardValue
              }
            }
          }
          if (maxPlayerSuit > maxWinnerSuit) {
            winner = player
          }
        } else if (player.winnerWeight == 5 || player.winnerWeight == 9) {
          if (player.straightBy > winner.straightBy) {
            winner = player
          } else if (player.straightBy == winner.straightBy) {
            val winnerConcat: String = winner.name + ", " + player.name
            return winnerConcat
          }
        }
        if (playerMax > winnerMax) {
          winner = player
        } else if (playerMax == winnerMax) {
          if (playerCardValues.sum > winnerCardValues.sum) {
            winner = player
          } else if (playerCardValues.sum == winnerCardValues.sum) {
            val winnerConcat: String = winner.name + ", " + player.name
            return winnerConcat
          }
        }
      }
    }
    winner.name
  }

}