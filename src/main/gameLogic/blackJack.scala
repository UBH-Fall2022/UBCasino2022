package main.gameLogic

import main.gameLogic.Player._

import scala.collection.mutable._

class blackJack(initPlayers: ArrayBuffer[Player]) extends Game(initPlayers) {
  var PlayerToValue: Map[Character, Int] = Map()
  val dealer: Dealer = new Dealer

  def valueCount(player: Character): Int ={
    var ret: Int = 0
    var aces: Int = 0
    for(card <- player.publHand){
      if(card.cardValue >= 10 && card.cardValue < 14){
        ret = ret+10
      }
      else if(card.cardValue==14){
        aces += 1
      }
      else{
        ret = ret + card.cardValue
      }

      if(ret <= 10 && aces >= 1){
        ret = ret + 11
        aces-=1
        ret = ret + aces
      }
      else{
        ret+=aces
      }
    }
    if(!player.privHand.isEmpty){
      val priv: Int = player.privHand.head.cardValue
      if(priv < 14){
        ret += Math.min(priv, 10)
      }
      else{
        if(ret > 10){
          ret+=1
        }
        else{
          ret+=11
        }
      }
    }
    ret
  }

  def playerMap(): Unit = {
    for(player <- players){
      PlayerToValue = PlayerToValue + (player -> valueCount(player))
    }
     PlayerToValue = PlayerToValue + ((dealer) -> valueCount(dealer))
  }

  def giveWinnings(winners: List[Player]): Unit ={
    val per: Int = pot/(winners.length)
    for(player<-winners){
      player.balance+=per
    }
  }

  def winners(): List[Character] = {
    var top = 0
    var winners: List[Character] = List[Character]()
    if(valueCount(dealer) > 21){
      for((player, hand) <- PlayerToValue){
        if(hand<=21){
          winners = winners :+ player
        }
      }
    }
    else{
      for((player, hand) <- PlayerToValue){
        if(hand<=21 && hand>top){
          winners = List[Character](player)
          top = hand
        }
        else if(hand == top){
          winners = winners :+ player
        }
      }
    }
    winners
  }

  def deal(): Unit = {
    dealer.privHand = dealer.privHand :+ deck.dealCard()
    for(player <- players){
      player.publHand = player.publHand :+ deck.dealCard()
      player.publHand = player.publHand :+ deck.dealCard()
    }
    dealer.publHand = dealer.publHand :+deck.dealCard()
  }

  def hit(player: Character): Unit = {
    if(player.in){
      player.privHand :+ deck.dealCard()
    }
    if(valueCount(player) > 21){
      player.in = false
    }
  }

}
