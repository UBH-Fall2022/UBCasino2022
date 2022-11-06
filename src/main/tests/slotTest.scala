package main.tests

import main.gameLogic.Player._
import main.gameLogic.Slot
import org.scalatest.FunSuite

class slotTest extends FunSuite {
  test("Testing Everything in one spot again ;)"){
    val player: Player = new Player("Jeremy")

    val testGame: Slot = new Slot(player)

    val startBal: Int = player.balance
    val win: Int = testGame.spinSlot()
    assert(player.balance == startBal+win)
  }
}