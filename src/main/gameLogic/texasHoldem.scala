package main.gameLogic
import main.gameLogic.Player.Player

class texasHoldem(initPlayers: Array[Player]) extends Game(initPlayers) {
  val gameType: String = "Texas Holdem"

  def deal(): Unit ={
    for(player <- players) {
      //give 2 random cards to all, hidden from others
    }
    //NO DEALER
  }

  def betOption(): Int = {
    //go around the table asking for players to bet. If yes, go around asking to raise/call/fold
    0
  }

  def flop(): Unit = {
    //put three cards on the table
  }

  def turn(): Unit = {

  }

  def river(): Unit = {

  }

  def gameSeq(): Unit ={
    deal()
    betOption()
    flop()
    betOption()
    turn()
    betOption()
    river()
  }
}