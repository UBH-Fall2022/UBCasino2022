import main.State.{State, blackjackState, pokerState}
import main.gameLogic.{Game, blackJack, texasHoldem}
import main.gameLogic.Player.Player


import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.StdIn.readLine

object CreateGame {
  def main(args: Array[String]): Unit = {
    var Players :ArrayBuffer[Player] = new ArrayBuffer()

    var warped = ""
    println("---------------------------------")
    println("Hello Welcome to UBCasino \n \n \n")
    println("---------------------------------")
    println("Write A Name To Add A Player Max Players is 6 \n \n \n")
    println("---------------------------------")
    while(warped != "done"){
      println("Enter Player Name or Enter Done to Continue: ")
      warped = readLine()
      if(warped != "done"){
        Players = Players :+ new Player(warped)
      }

      println("Players In Game")
      for(items <- Players)
        println(items.name)
      if(Players.length == 6)
        {
          println("Max Amount Of Players Reached")
          warped = "done"
        }
    }
    println("Pick a Game")
    println("Enter 1 for Texas Hold Em")
    println("Enter 2 for Blackjack")
    var game = readLine()
    var gamemode :State = new blackjackState(Players)
    if(game.toLowerCase() == "blackjack")
      {
        gamemode = new blackjackState(Players)
      }
    else if(game.toLowerCase() == "texas hold em"){
      gamemode = new pokerState(Players)
    }

    gamemode.run()


  }
}