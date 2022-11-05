package main.cardLogic

class Card() {

  var cardSuit: String = ""
  var cardValue: Int = 0

  def setSuit(givenSuit: String): Unit = {
    if (givenSuit == "C") {
      cardSuit = "Clubs"
    } else if (givenSuit == "H") {
      cardSuit = "Hearts"
    } else if (givenSuit == "S") {
      cardSuit = "Spades"
    } else if (givenSuit == "D") {
      cardSuit = "Diamonds"
    } else {
      throw new IndexOutOfBoundsException("Card Suit out of bounds!")
    }
  }

  def setSign(givenValue: String): Unit = {
    if (givenValue == "1") {
      cardValue = 1
    } else if (givenValue == "2") {
      cardValue = 2
    } else if (givenValue == "3") {
      cardValue = 3
    } else if (givenValue == "4") {
      cardValue = 4
    } else if (givenValue == "5") {
      cardValue = 5
    } else if (givenValue == "6") {
      cardValue = 6
    } else if (givenValue == "7") {
      cardValue = 7
    } else if (givenValue == "8") {
      cardValue = 8
    } else if (givenValue == "9") {
      cardValue = 9
    } else if (givenValue == "10") {
      cardValue = 10
    } else if (givenValue == "J") {
      cardValue = 11
    } else if (givenValue == "Q") {
      cardValue = 12
    } else if (givenValue == "K") {
      cardValue = 13
    } else if (givenValue == "A") {
      cardValue = 14
    } else {
      throw new IndexOutOfBoundsException("Card Sign out of bounds!")
    }
  }

}