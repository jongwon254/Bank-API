package com.jongwonlee.api.bank.datasource.mock

import com.jongwonlee.api.bank.datasource.BankDataSource
import com.jongwonlee.api.bank.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    override fun getBanks(): Collection<Bank> {
        TODO("Not yet implemented")
    }
}