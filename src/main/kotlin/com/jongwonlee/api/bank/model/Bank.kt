package com.jongwonlee.api.bank.model

data class Bank(
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int)