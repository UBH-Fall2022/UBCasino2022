package main.State

import main.gameLogic.Player.Player
import main.gameLogic.Slot
import scala.io.StdIn.readLine

import scala.collection.mutable.ArrayBuffer

class slotState(Players :ArrayBuffer[Player]) extends State {
  override def run(): Unit = {
     var player = Players(0)
    var slot = new Slot(player)
    while(player.balance >0){
      println("type spin then enter to spin \n type spin2 then enter to spin 10 times \n type spin3 then enter to spin 100 times ")
      var action = readLine()
      if(action.toLowerCase() == "spin")
        {
          var before = player.balance
          slot.spinSlot()
          var after = player.balance
          var dif = before-after
          if(dif<0){
            println("You Won " +Math.abs(dif).toString)
          }
          else if(dif>0){
            println("You Lost " +Math.abs(dif).toString)
          }
          else if(dif == 0)
            {
              println("You Won 0")
            }
          else
            {
              println("OOps Something WeNt WrOng")
            }
          println("Your Balance is " + player.balance)
        }
      if (action.toLowerCase() == "spin2") {
        var before = player.balance
        for(i <- 1 to 10)
        slot.spinSlot()
        var after = player.balance
        var dif = before - after
        if (dif < 0) {
          println("You Won " + Math.abs(dif).toString)
        }
        else if (dif > 0) {
          println("You Lost " + Math.abs(dif).toString)
        }
        else if (dif == 0) {
          println("You Won 0")
        }
        else {
          println("OOps Something WeNt WrOng")
        }
        println("Your Balance is " + player.balance)
      }
      if (action.toLowerCase() == "spin3") {
        var before = player.balance
        for (i <- 1 to 100)
          slot.spinSlot()
        var after = player.balance
        var dif = before - after
        if (dif < 0) {
          println("You Won " + Math.abs(dif).toString)
        }
        else if (dif > 0) {
          println("You Lost " + Math.abs(dif).toString)
        }
        else if (dif == 0) {
          println("You Won 0")
        }
        else {
          println("OOps Something WeNt WrOng")
        }
        println("Your Balance is " + player.balance)
      }

      println("Would you like to spin again Y/N")
      var ans = readLine()
      if(ans.toLowerCase == "n")
        return


    }
    
    
  }
}
