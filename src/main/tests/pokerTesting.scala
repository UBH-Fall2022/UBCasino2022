package main.tests

import main.gameLogic.Player.Player
import main.gameLogic.texasHoldem
import org.scalatest.FunSuite
import main.cardLogic.Card

import scala.collection.mutable.ArrayBuffer

class pokerTesting extends FunSuite{

  def createCard(suit: String, sign: String): Card = {
    var one = new Card()
    one.setSuit(suit)
    one.setSign(sign)
    one
  }
  test("Testing Straight"){
    val zac: Player = new Player("Zac")
    val ej: Player = new Player("EJ")
    val jeremy: Player = new Player("Jeremy")
    val journey: Player = new Player("Journey")

    val gamePlayers: ArrayBuffer[Player] = ArrayBuffer(zac, ej, jeremy, journey)


    val testGame: texasHoldem = new texasHoldem(gamePlayers)

    testGame.deal()
    zac.clearCards()
    var one = createCard("D", "2")
    var two = createCard("H", "3")
    var three = createCard("D", "4")
    var four = createCard("H", "5")
    var five = createCard("S", "6")
    var six = createCard("D", "10")
    var seven = createCard("H", "J")
    zac.updateCards(one)
    zac.updateCards(two)
    var lst: Array[Card] = Array(three, four, five, six, seven)
    testGame.table = lst


    testGame.calculateHands()

    var ans = testGame.isFlush()
    assert(1 == 0, ans)
  }

}
