package com.jongwonlee.api.bank.service

import com.jongwonlee.api.bank.database.Banks
import com.jongwonlee.api.bank.model.BankDB
import com.jongwonlee.api.bank.service.CrudRepository
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

interface UserRepository: CrudRepository<BankDB>

@Repository
@Service
class DefaultUserRepository: UserRepository {
    override fun findAll(): Iterable<BankDB> =
        transaction { Banks.selectAll().map { fromRow(it) } }

    private fun fromRow(r: ResultRow) =
        BankDB(r[Banks.id],
            r[Banks.account_number],
            r[Banks.ip_address],
            r[Banks.port],
            r[Banks.node_identifier],
            r[Banks.version],
            r[Banks.protocol],
            r[Banks.transaction_fee],
            r[Banks.trust])
}