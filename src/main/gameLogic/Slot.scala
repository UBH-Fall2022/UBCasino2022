package main.gameLogic

import main.gameLogic.Player._
import scala.util.Random

class Slot (player: Player){
  val symbols: Array[String] = Array("5", "4", "3", "2")
  val spinCost: Int = 5

  def spinSlot(): Int ={
    player.balance -= spinCost
    var win: Int = 0
    win = -spinCost
    val reelOne: String = Random.shuffle(symbols.toList).head
    val reelTwo: String = Random.shuffle(symbols.toList).head
    val reelThr: String = Random.shuffle(symbols.toList).head

    if(reelOne==reelTwo && reelTwo==reelThr){
      reelOne match {
        case "5" => player.balance += 20*spinCost; win = 20*spinCost
        case "4" => player.balance += 10*spinCost; win = 10*spinCost
        case "3" => player.balance += 5*spinCost; win = 5*spinCost
        case "2" => player.balance += spinCost; win = spinCost
      }
    }
    win
  }
}
