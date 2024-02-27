package com.bettervice.kotlinbasics

fun main(){

    var computerChoices = ""
    var playerChoice = ""

    println("Rock , Paper , Scissors ? enter the choice")

    playerChoice = readln()

    var randomNumber = (1..3).random()

     when(randomNumber){
         1->{
             computerChoices = "Rock"
         }
         2->{
             computerChoices= "paper"
         }
         3->{
             computerChoices="Scissors"
         }

     }
    println(computerChoices)

    val winner = when{
        playerChoice == computerChoices ->"tie"
        playerChoice=="Rock" && computerChoices=="Scissors" -> "player "
        playerChoice=="paper" && computerChoices=="Rock" -> "player "
        playerChoice=="Scissors" && computerChoices =="paper" ->"player"
        else->"computer "
    }
    println(winner + "won!")
}