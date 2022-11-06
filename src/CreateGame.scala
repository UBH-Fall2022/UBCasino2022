import main.State.{State, blackjackState, pokerState}
import main.gameLogic.{Game, blackJack, texasHoldem}
import main.gameLogic.Player.Player
import main.gameLogic.Player.Character

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.StdIn.readLine
import scala.util.Random

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
      println("")
      warped = readLine()
      if(warped != "Done"){
        Players = Players :+ new Player(warped)
      }

      println("Players In Game")
      for(items <- Players)
        println(items.name)
      if(Players.length == 6)
        {
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
    var gamemode :State = new blackjackState(Players)
    if(game.toInt == 2)
      {
        gamemode = new blackjackState(Players)
      }
    else if(game.toInt == 1){
      if (Players.length == 1) {
        Players = Players :+ new Player("Dealer")
      }
      val newGame: texasHoldem = new texasHoldem(Players)

      val myPlayer: Player = Players.head

      newGame.deal()

      println("Your cards: ")
      myPlayer.displayCards()
      println("")

      println("Check, Bet, or Fold?")

      var read = readLine()

      if (read == "Fold") {
        newGame.fold(myPlayer)
        return
      } else if (read == "Check") {
        newGame.buyIn()
      } else if(read == "Bet") {
        println("Enter bet amount.")
        read = readLine()
        newGame.bet(myPlayer, read.toInt)
        for (player <- Players) {
          if (player != myPlayer) {
              newGame.bet(player, newGame.currBet)
          }
        }
      } else {
        println("Invalid input error")
      }

      println("Current Pot: " + newGame.pot)
      println("")

      var inCounter: Int = 0

      for (player <- Players) {
        if (player.in) {
          inCounter += 1
        }
      }

      if (inCounter == 1) {
        for (player <- Players) {
          if (player.in) {
            newGame.gameWinner = player.name
            println(newGame.gameWinner + " Won!")
            return
          }
        }
      }

      newGame.addCards(3)

      println("Table Cards: ")
      for (ele <- newGame.table) {
        if (ele.cardValue == 11) {
          println("Jack of " + ele.cardSuit)
        } else if(ele.cardValue == 12) {
          println("Queen of " + ele.cardSuit)
        } else if (ele.cardValue == 13) {
          println("King of " + ele.cardSuit)
        } else if (ele.cardValue == 14) {
          println("Ace of " + ele.cardSuit)
        } else {
          println(ele.cardValue + " of " + ele.cardSuit)
        }
      }
      println("")

      println("Your cards: ")
      myPlayer.displayCards()
      println("")

      println("Check, Bet, or Fold?")

      read = readLine()

      if (read == "Fold") {
        newGame.fold(myPlayer)
        return
      } else if (read == "Check") {
      } else if(read == "Bet") {
        println("Enter bet amount.")
        read = readLine()
        newGame.bet(myPlayer, read.toInt)
        for (player <- Players) {
          if (player != myPlayer) {
            newGame.bet(player, newGame.currBet)
          }
        }
      } else {
        println("Invalid input error")
      }

      println("Current Pot: " + newGame.pot)

      inCounter = 0

      for (player <- Players) {
        if (player.in) {
          inCounter += 1
        }
      }

      if (inCounter == 1) {
        for (player <- Players) {
          if (player.in) {
            newGame.gameWinner = player.name
            println(newGame.gameWinner + " Won!")
            return
          }
        }
      }

      newGame.addCards(1)

      println("Table Cards: ")
      for (ele <- newGame.table) {
        if (ele.cardValue == 11) {
          println("Jack of " + ele.cardSuit)
        } else if(ele.cardValue == 12) {
          println("Queen of " + ele.cardSuit)
        } else if (ele.cardValue == 13) {
          println("King of " + ele.cardSuit)
        } else if (ele.cardValue == 14) {
          println("Ace of " + ele.cardSuit)
        } else {
          println(ele.cardValue + " of " + ele.cardSuit)
        }
      }
      println("")

      println("Your cards: ")
      myPlayer.displayCards()
      println("")

      println("Check, Bet, or Fold?")

      read = readLine()

      if (read == "Fold") {
        newGame.fold(myPlayer)
        return
      } else if (read == "Check") {
      } else if(read == "Bet") {
        println("Enter bet amount.")
        read = readLine()
        newGame.bet(myPlayer, read.toInt)
        for (player <- Players) {
          if (player != myPlayer) {
            newGame.bet(player, newGame.currBet)
          }
        }
      } else {
        println("Invalid input error")
      }

      println("Current Pot: " + newGame.pot)

      inCounter = 0

      for (player <- Players) {
        if (player.in) {
          inCounter += 1
        }
      }

      if (inCounter == 1) {
        for (player <- Players) {
          if (player.in) {
            newGame.gameWinner = player.name
            println(newGame.gameWinner + " Won!")
            return
          }
        }
      }

      newGame.addCards(1)

      println("Table Cards: ")
      for (ele <- newGame.table) {
        if (ele.cardValue == 11) {
          println("Jack of " + ele.cardSuit)
        } else if(ele.cardValue == 12) {
          println("Queen of " + ele.cardSuit)
        } else if (ele.cardValue == 13) {
          println("King of " + ele.cardSuit)
        } else if (ele.cardValue == 14) {
          println("Ace of " + ele.cardSuit)
        } else {
          println(ele.cardValue + " of " + ele.cardSuit)
        }
      }
      println("")

      println("Your cards: ")
      myPlayer.displayCards()
      println("")

      println("Check, Bet, or Fold?")

      read = readLine()

      if (read == "Fold") {
        newGame.fold(myPlayer)
        return
      } else if (read == "Check") {
      } else if(read == "Bet") {
        println("Enter bet amount.")
        read = readLine()
        newGame.bet(myPlayer, read.toInt)
        for (player <- Players) {
          if (player != myPlayer) {
            newGame.bet(player, newGame.currBet)
          }
        }
      } else {
        println("Invalid input error")
      }

      println("Current Pot: " + newGame.pot)

      inCounter = 0

      for (player <- Players) {
        if (player.in) {
          inCounter += 1
        }
      }

      if (inCounter == 1) {
        for (player <- Players) {
          if (player.in) {
            newGame.gameWinner = player.name
            println(newGame.gameWinner + " Won!")
            return
          }
        }
      }

      println(newGame.computeWinner() + " wins!")






    }



  }
  
}
