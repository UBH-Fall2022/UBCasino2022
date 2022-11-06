package main.tests

import main.gameLogic.Player._
import main.gameLogic.blackJack

import scala.collection.mutable.ArrayBuffer

object blackJackTesting {
  def main(args: Array[String]): Unit = {
    val Jeremy: Player = new Player("Jeremy")
    val Zac: Player = new Player("Zac")
    val Journey: Player = new Player("Journey")

    val players: ArrayBuffer[Player] = ArrayBuffer(Jeremy, Zac, Journey)

    val testGame: blackJack = new blackJack(players)

    testGame.deal()

    println("Dealer Private hand: "+testGame.dealer.privHand.head.cardValue+" of "+testGame.dealer.privHand.head.cardSuit+
      "\nDealer Public Hand: "+testGame.dealer.publHand.head.cardValue+" of "+testGame.dealer.publHand.head.cardSuit)

    println(testGame.players(0).name+" has "+testGame.players(0).publHand(0).cardValue+" of "+testGame.players(0).publHand(0).cardSuit+
      " and "+testGame.players(0).publHand(1).cardValue+" of "+testGame.players(0).publHand(1).cardSuit)
    println("Totaling to "+testGame.valueCount(testGame.players(0))+"/21")

    //println("Dealer has: "+testGame.valueCount(testGame.dealer))
    //println(testGame.players(0).name+" has "+testGame.valueCount(testGame.players(0)))
    //println(testGame.players(1).name+" has "+testGame.valueCount(testGame.players(1)))
    //println(testGame.players(2).name+" has "+testGame.valueCount(testGame.players(2)))

    testGame.playerMap()
    for((player, hand) <- testGame.PlayerToValue){
      println(player.name +" has "+hand)
    }
    val wins: List[Character] = testGame.winners()
    print("Winner(s): ")
    for(winner <- wins){
      print(winner.name+" ")
    }
  }
}
