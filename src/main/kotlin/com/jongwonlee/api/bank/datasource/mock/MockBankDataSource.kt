package com.jongwonlee.api.bank.datasource.mock

import com.jongwonlee.api.bank.datasource.BankDataSource
import com.jongwonlee.api.bank.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    val banks = listOf(
        Bank("1234", 3.0, 17),
        Bank("1010", 17.3, 0),
        Bank("5678", 0.0, 100))

    override fun retrieveBanks(): Collection<Bank> = banks
}