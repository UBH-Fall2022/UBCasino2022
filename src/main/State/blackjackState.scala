package main.State

import main.gameLogic.Player.Player
import main.gameLogic.blackJack
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine
import main.gameLogic.Player.Character

class blackjackState(var Players :ArrayBuffer[Player]) extends State {
  override def run(): Unit = {
    if (Players.length == 1) {
      Players = Players :+ new Player("Dealer")
    }
    val newGame: blackJack = new blackJack(Players)

    val myPlayer: Player = Players.head
    
    println("Type Start to Deal")
    println("")
    
    val d = readLine()
    if (d == "Start") {
      newGame.deal()
      newGame.bet(myPlayer, 25)
    }
    
    println("Current Pot: ")
    println(newGame.pot)
    println("")

    println("Your Cards: ")
    myPlayer.displayBJ()
    println("")

    println("Hit, Stay, or Double Down")
    var read = readLine()
    if (read == "Hit"){
      newGame.hit(myPlayer)
      println("Your Cards: ")
      myPlayer.displayBJ()
      println("")
      while (read != "Stay") {
        println("Hit or Stay?")
        read = readLine()
        if (read == "Hit") {
          newGame.hit(myPlayer)
          println("Your Cards: ")
          myPlayer.displayBJ()
          println("")
        }
      }
    } else if (read == "Double Down") {
      newGame.doubleDown(myPlayer)
      println("Your Cards: ")
      myPlayer.displayBJ()
      println("")
    } else if (read == "Stay") {
    } else {
      println("Invalid Input!")
    }
    
    newGame.playerMap()
    
    val win: List[Character] = newGame.winners()
    
    println("The Winners are: ")
    for (ele <- win) {
      println(ele.name)
    }
    println("")
    
    newGame.giveWinnings(win)
    
    println("Your New Balance: ")
    println(myPlayer.balance)

  }

}
