package main.State

import main.gameLogic.Player.Player
import main.gameLogic.texasHoldem

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine

class pokerState(var Players :ArrayBuffer[Player]) extends State {
  override def run(): Unit ={
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
    } else if (read == "Bet") {
      println("Enter bet amount.")
      read = readLine()
      newGame.placeBet(myPlayer, read.toInt)
      for (player <- Players) {
        if (player != myPlayer) {
          newGame.placeBet(player, newGame.currBet)
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
      } else if (ele.cardValue == 12) {
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
    } else if (read == "Bet") {
      println("Enter bet amount.")
      read = readLine()
      newGame.placeBet(myPlayer, read.toInt)
      for (player <- Players) {
        if (player != myPlayer) {
          newGame.placeBet(player, newGame.currBet)
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
      } else if (ele.cardValue == 12) {
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
    } else if (read == "Bet") {
      println("Enter bet amount.")
      read = readLine()
      newGame.placeBet(myPlayer, read.toInt)
      for (player <- Players) {
        if (player != myPlayer) {
          newGame.placeBet(player, newGame.currBet)
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
      } else if (ele.cardValue == 12) {
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
    } else if (read == "Bet") {
      println("Enter bet amount.")
      read = readLine()
      newGame.placeBet(myPlayer, read.toInt)
      for (player <- Players) {
        if (player != myPlayer) {
          newGame.placeBet(player, newGame.currBet)
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

    val winnerString: String = newGame.gameWinner
    var winnerPlayer: Player = new Player("null")
    for (ele <- Players) {
      if (ele.name == winnerString) {
        winnerPlayer = ele
      }
    }
    println("Winning Hand: " + winnerPlayer.handRank)
    winnerPlayer.displayCards()
    println("")
    println("Your balance: " + myPlayer.balance)
      }
  
  

}
