package com.jongwonlee.api.bank.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Demo: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)

    fun getAll(): List<Any> = transaction { Demo.selectAll().map { it[name] } }
}