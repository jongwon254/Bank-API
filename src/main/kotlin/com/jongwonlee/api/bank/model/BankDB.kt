package com.jongwonlee.api.bank.model

class BankDB(
    val id: Int,
    val ip_address: String,
    val account_number: String,
    val port: Int,
    val node_identifier: String,
    val version: String,
    val protocol: String,
    val transaction_fee: Int,
    val trust: Int
)