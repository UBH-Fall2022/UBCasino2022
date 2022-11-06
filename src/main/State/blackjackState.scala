package main.State

import main.gameLogic.Player.Player
import main.gameLogic.blackJack
import scala.collection.mutable.ArrayBuffer

class blackjackState(var Players :ArrayBuffer[Player]) extends State {
  override def run(): Unit = {
    if (Players.length == 1) {
      Players = Players :+ new Player("Dealer")
    }
    val newGame:blackJack = new blackJack(Players)

    val myPlayer: Player = Players.head

    newGame.deal()

    println("your cards are")
    myPlayer.displayBJ()
    println("")

    println("Hit or Stay or Double Down")
      val read = readLine()
        if (read == "Hit"){
          
        }

  }

}
