package main.gameLogic

import main.gameLogic.Player.Player
import main.cardLogic._
import scala.collection.mutable.ArrayBuffer

class Game(initPlayers: ArrayBuffer[Player]){
  var pot: Int = 0
  var players: ArrayBuffer[Player] = initPlayers
  val maxPlayers: Int = 6
  var deck: Deck = new Deck
  deck.createDeck()
  var table: Array[Card] = Array()

  def addPlayer(player: Player): Boolean = {
    if(players.length >= 6){
      false
    }
    else{
      players :+ player
      true
    }
  }

  def fold(player: Player): Unit = {
    player.privHand = List()
    //need an attribute to determine if they're still in or not
  }

  def removePlayer(player: Player): Boolean = {
    if(players.length <=2 ){
      //do something special to award pot to remaining player, end game
      false
    }
    else{
      for(index <- players){
        if(index == player){
          players -= index
        }
      }
      true
    }
  }

  def win(winner: Player): Unit ={
    winner.balance += pot
    pot = 0
  }

  def bet(player: Player, amount: Int): Unit = {
    if(player.balance < amount){
      pot+=player.balance
      player.balance = 0
    }
    else{
      pot+=amount
      player.balance -= amount
    }
  }
}