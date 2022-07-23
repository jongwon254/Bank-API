package com.jongwonlee.api.bank.service

import com.jongwonlee.api.bank.database.Banks
import com.jongwonlee.api.bank.model.BankDB
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

interface CrudRepository<T> {
    fun findAll(): Iterable<T>
}

