package com.jongwonlee.api.bank.datasource

import com.jongwonlee.api.bank.model.Bank

interface BankDataSource {

    fun getBanks(): Collection<Bank>
}