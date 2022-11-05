package main.gameLogic

import main.gameLogic.Player.Player

import scala.collection.mutable.ArrayBuffer

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

  def deal(): Unit = {
    for(player <- players){
      player.publHand :+ deck.dealCard()
      player.privHand :+ deck.dealCard()
    }
  }
}
