package main.gameLogic

import main.gameLogic.Player.Player

class blackJack(initPlayers: Array[Player]) extends Game(initPlayers) {
    var PlayerToValue: Map[Player, Int] = Map()

    def valueCount(Entity :Player): Unit ={
      var value = 0
      for(cards <- Entity.cards)
        {
          value = value + cards.cardValue
        }
      value
    }

}
