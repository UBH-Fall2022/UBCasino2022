package main.tests

import main.gameLogic.Player._
import main.gameLogic.blackJack
import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

class blackJackTesting extends FunSuite {
  test("Testing everything :)"){
    val Jeremy: Player = new Player("Jeremy")
    val Zac: Player = new Player("Zac")
    val Journey: Player = new Player("Journey")

    val players: ArrayBuffer[Player] = ArrayBuffer(Jeremy, Zac, Journey)

    val testGame: blackJack = new blackJack(players)

    testGame.deal()

    assert(testGame.dealer.privHand.nonEmpty)
    assert(testGame.dealer.publHand.nonEmpty)

    assert(testGame.dealer.privHand.length == 1)
    assert(testGame.dealer.publHand.length == 1)

    for(player <- testGame.players){
      assert(player.publHand.length == 2)
      assert(player.publHand.nonEmpty)
    }

    testGame.playerMap()
    val winners = testGame.winners()

    assert(winners.nonEmpty)
  }
}

