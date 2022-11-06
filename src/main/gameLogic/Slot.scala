package main.gameLogic

import main.gameLogic.Player._
import scala.util.Random

class Slot (player: Player){
  val symbols: Array[String] = Array("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2")
  val spinCost: Int = 50

  def spinSlot(): Int ={
    player.balance -= spinCost
    var win: Int = 0
    win = -spinCost
    val reelOne: String = Random.shuffle(symbols.toList).head
    val reelTwo: String = Random.shuffle(symbols.toList).head
    val reelThr: String = Random.shuffle(symbols.toList).head

    if(reelOne==reelTwo && reelTwo==reelThr){
      reelOne match {
        case "A" => player.balance += 1000*spinCost; win = 1000*spinCost
        case "K" => player.balance += 750*spinCost; win = 750*spinCost
        case "Q" => player.balance += 500*spinCost; win = 500*spinCost
        case "J" => player.balance += 300*spinCost; win = 300*spinCost
        case "10" => player.balance += 200*spinCost; win = 200*spinCost
        case "9" => player.balance += 100*spinCost; win = 100*spinCost
        case "8" => player.balance += 60*spinCost; win = 60*spinCost
        case "7" => player.balance += 50*spinCost; win = 50*spinCost
        case "6" => player.balance += 40*spinCost; win = 40*spinCost
        case "5" => player.balance += 20*spinCost; win = 20*spinCost
        case "4" => player.balance += 10*spinCost; win = 10*spinCost
        case "3" => player.balance += 5*spinCost; win = 5*spinCost
        case "2" => player.balance += spinCost; win = spinCost
      }
    }
    win
  }
}
