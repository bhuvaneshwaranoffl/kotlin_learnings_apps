package com.bettervice.kotlinbasics

fun main(){


    val shoppingList = mutableListOf("RAM","PROCESSOR","ROM","COLOR")
    shoppingList.add(0,"RAM")
    shoppingList.removeAt(0)
    shoppingList.set(1,"Cool")
    val hasCool =   shoppingList.contains("Cool")
    println(hasCool)
    //println(shoppingList[2] +" " + shoppingList[3])
    //println(shoppingList)

    for(item in shoppingList){
        println(item)
    }

}