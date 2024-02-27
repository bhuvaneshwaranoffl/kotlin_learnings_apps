package com.bettervice.kotlinbasics

import com.bettervice.kotlinbasics.CoffeeDetails as CoffeeDetails

data class CoffeeDetails(
    val sugarCount: Int,
    val name:String,
    val size:String,
    val creamAmount:Int,
)
fun main() {
    //call fun

    val coffeeDetails = CoffeeDetails(
       0,"lucifer", "large", 10
    )

    makeCoffee(coffeeDetails)
}



fun askCoffeeDetails(){
    val coffeeDetails = CoffeeDetails(
        0,"lucifer", "large", 10
    )

    val name = readln()

    val sugarCount = readln()

    val sugarCountInt = sugarCount.toInt()

    makeCoffee(coffeeDetails)
}

//define fun
fun makeCoffee(coffeeDetails: CoffeeDetails){
   if (coffeeDetails.sugarCount==1){
       println("coffer with  ${coffeeDetails.sugarCount} spoon of sugar for ${coffeeDetails.name}")
   }else{
       println("coffer with ${coffeeDetails.sugarCount} spoons of sugar for ${coffeeDetails.name}")
   }
}