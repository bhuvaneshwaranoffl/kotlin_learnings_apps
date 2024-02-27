package com.bettervice.bankprogram

fun main(){

    var luciferAccounts = BankAccount("Lucifer", 20000.90)
    luciferAccounts.deposit(909090.09)
    luciferAccounts.displayTransactionHistory()
    luciferAccounts.accBalance()
    luciferAccounts.withdrawal(20000.90)
    luciferAccounts.accBalance()

}