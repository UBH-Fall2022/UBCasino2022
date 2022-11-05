package main.gameLogic

import main.gameLogic.Player.Player

import scala.collection.mutable._

class blackJack(initPlayers: ArrayBuffer[Player]) extends Game(initPlayers) {
    var PlayerToValue: Map[Player, Int] = Map()

    def valueCount(): Unit ={
      for(player <- players)
        {
          var value = 0
          value = value + player.publHand.head.cardValue + player.privHand.head.cardValue
          if(value>21){
            value = 0
          }
          PlayerToValue(player) -> value
        }
    }
  def winners(): List[Player] = {
    var win: Int = 0
    var winners: List[Player] = List()
    for((player, hand) <- PlayerToValue){
      if(hand > win && player.in){
        win = hand
        winners= List(player)
      }
      else if(win==hand && hand>0 && player.in){
        winners = winners:+player
      }
    }
    winners
  }

  def giveWinnings(winners: List[Player]): Unit ={
    val per: Int = pot/(winners.length)
    for(player<-winners){
      player.balance+=per
    }
  }

  def deal(): Unit = {
    for(player <- players){
      if(player.in){
        player.publHand :+ deck.dealCard()
        player.privHand :+ deck.dealCard()
      }
    }
  }

}
