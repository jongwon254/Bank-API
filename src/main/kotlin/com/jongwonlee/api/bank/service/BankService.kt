package com.jongwonlee.api.bank.service

import com.jongwonlee.api.bank.datasource.BankDataSource
import com.jongwonlee.api.bank.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
}