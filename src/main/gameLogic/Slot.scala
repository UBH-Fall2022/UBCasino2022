package main.gameLogic

import main.gameLogic.Player._
import scala.util.Random

class Slot (player: Player){
  val symbols: Array[String] = Array("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2")
  val spinCost: Int = 50

  def spinSlot(): Unit ={
    player.balance -= spinCost

    val reelOne: String = Random.shuffle(symbols.toList).head
    val reelTwo: String = Random.shuffle(symbols.toList).head
    val reelThr: String = Random.shuffle(symbols.toList).head

    if(reelOne==reelTwo && reelTwo==reelThr){
      reelOne match {
        case "A" => player.balance += 1000*spinCost
        case "K" => player.balance += 750*spinCost
        case "Q" => player.balance += 500*spinCost
        case "J" => player.balance += 300*spinCost
        case "10" => player.balance += 200*spinCost
        case "9" => player.balance += 100*spinCost
        case "8" => player.balance += 60*spinCost
        case "7" => player.balance += 50*spinCost
        case "6" => player.balance += 40*spinCost
        case "5" => player.balance += 20*spinCost
        case "4" => player.balance += 10*spinCost
        case "3" => player.balance += 5*spinCost
        case "2" => player.balance += spinCost
      }
    }
  }
}
