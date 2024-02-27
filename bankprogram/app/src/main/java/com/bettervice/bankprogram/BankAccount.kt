package com.bettervice.bankprogram

class BankAccount(var accountHolder:String , var balance:Double) {
  private val transactionHistory = mutableListOf<String>()
    // the list is empty so we defined the data type
   fun deposit(amount:Double){
       //after deposit the amount have to increase

       balance +=amount

        transactionHistory.add("$accountHolder deposited the amount $$amount")
   }

    fun withdrawal(amount: Double){
      if (amount < balance){
           balance -=amount
      }else{
              //we cannot withdraw
          println("you don't have enough amount to withdraw $$amount")
      }

    }

    fun accBalance(){
        println("the account balance of $accountHolder is $$balance")
    }

    fun displayTransactionHistory(){
   println("The transaction history for the $accountHolder")

        for(transaction in transactionHistory){
            println(transaction)
        }
    }
}