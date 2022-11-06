import main.State.{State, blackjackState, pokerState}
import main.gameLogic.{Game, blackJack, texasHoldem}
import main.gameLogic.Player.Player
import main.gameLogic.Player.Character

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.StdIn.readLine
import scala.util.Random

object CreateGame {
  def main(args: Array[String]): Unit = {
    var Players: ArrayBuffer[Player] = new ArrayBuffer()

    var warped = ""
    println("---------------------------------")
    println("Hello Welcome to UBCasino \n \n \n")
    println("---------------------------------")
    println("Write A Name To Add A Player Max Players is 6 \n \n \n")
    println("---------------------------------")
    while (warped != "done") {
      println("Enter Player Name or Enter Done to Continue: ")
      println("")
      warped = readLine()
      if (warped != "Done") {
        Players = Players :+ new Player(warped)
      }

      println("Players In Game")
      for (items <- Players)
        println(items.name)
      if (Players.length == 6) {
        println("Max Amount Of Players Reached")
        warped = "Done"
      }
    }
    println("Pick a Game")
    println("")
    println("Enter 1 for Texas Hold Em")
    println("")
    println("Enter 2 for Blackjack")
    println("")
    var game = readLine()
    var gamemode: State = new blackjackState(Players)
    if (game.toInt == 2) {
      gamemode = new blackjackState(Players)
    }
    else if (game.toInt == 1) {
      gamemode = new pokerState(Players)
    }
    else {
      println("Enter 1 or 2")
    }


    gamemode.run()


  }
}
