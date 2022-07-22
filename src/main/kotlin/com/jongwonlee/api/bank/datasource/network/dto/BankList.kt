package com.jongwonlee.api.bank.datasource.network.dto

import com.jongwonlee.api.bank.model.Bank

data class BankList(
    val results: Collection<Bank>
)