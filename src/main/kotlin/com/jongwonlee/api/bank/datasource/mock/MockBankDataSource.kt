package com.jongwonlee.api.bank.datasource.mock

import com.jongwonlee.api.bank.datasource.BankDataSource
import com.jongwonlee.api.bank.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        Bank("1234", 3.0, 17),
        Bank("1010", 17.3, 0),
        Bank("5678", 0.0, 100))

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Error: No Bank exists with account number $accountNumber.")

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Error: Bank with account number ${bank.accountNumber} already exists.")
        }
        banks.add(bank)
        return bank
    }
}