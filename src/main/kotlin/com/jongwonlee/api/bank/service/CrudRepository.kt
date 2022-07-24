package com.jongwonlee.api.bank.service

import com.jongwonlee.api.bank.model.BankDB

interface CrudRepository<T> {
    fun findAll(): Iterable<T>

    fun findBank(id: Int): BankDB

    fun createBank(bank: BankDB): BankDB

    fun updateBank(bank: BankDB): BankDB

    fun deleteBank(id: Int)
}

