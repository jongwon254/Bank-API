package com.jongwonlee.api.bank.controller

import com.jongwonlee.api.bank.datasource.BankDataSource
import com.jongwonlee.api.bank.datasource.mock.MockBankDataSource
import com.jongwonlee.api.bank.model.Bank
import com.jongwonlee.api.bank.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()

}