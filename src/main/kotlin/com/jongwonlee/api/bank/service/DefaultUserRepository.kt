package com.jongwonlee.api.bank.service

import com.jongwonlee.api.bank.database.Banks
import com.jongwonlee.api.bank.model.BankDB
import com.jongwonlee.api.bank.service.CrudRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

interface UserRepository: CrudRepository<BankDB>

// Implementation of service interface
@Repository
@Service
class DefaultUserRepository: UserRepository {

    // Get all banks
    override fun findAll(): Iterable<BankDB> =
        transaction { Banks.selectAll().map { listBanks(it) } }

    // Get one bank with ID
    override fun findBank(id: Int): BankDB =
        transaction {
            Banks.select { Banks.id eq id }.map { listBanks(it) }.firstOrNull() ?:
            throw NoSuchElementException("Error: No Bank exists with id $id.") }

    // Create one bank if it does not exist
    override fun createBank(bank: BankDB): BankDB {
        transaction {
            if(Banks.select { Banks.id eq bank.id }.map { listBanks(it) }.firstOrNull() == null) {
                Banks.insert(addBank(bank))
            } else {
                throw IllegalArgumentException("Error: Bank with id ${bank.id} already exists.")
            }
        }
        return bank
    }

    // Update the bank if it exists
    override fun updateBank(bank: BankDB): BankDB {
        transaction {
            Banks.select { Banks.id eq bank.id }.map { listBanks(it) }.firstOrNull() ?:
            throw NoSuchElementException("Error: No Bank exists with id ${bank.id}.")
            Banks.update({ Banks.id eq bank.id }) {
                it[ip_address] = bank.ip_address
                it[account_number] = bank.account_number
                it[port] = bank.port
                it[node_identifier] = bank.node_identifier
                it[version] = bank.version
                it[protocol] = bank.protocol
                it[transaction_fee] = bank.transaction_fee
                it[trust] = bank.trust
            }
        }
        return bank
    }

    // Delete the bank if it exists
    override fun deleteBank(id: Int) {
        transaction {
            Banks.select { Banks.id eq id}.map { listBanks(it) }.firstOrNull() ?:
            throw NoSuchElementException("Error: No Bank exists with id $id.")
            Banks.deleteWhere { Banks.id eq id }
        }
    }

    // helper method for iterating bank list
    private fun listBanks(r: ResultRow) =
        BankDB(r[Banks.id],
            r[Banks.ip_address],
            r[Banks.account_number],
            r[Banks.port],
            r[Banks.node_identifier],
            r[Banks.version],
            r[Banks.protocol],
            r[Banks.transaction_fee],
            r[Banks.trust])

    // helper method for creating a bank from the request body
    private fun addBank(bank: BankDB): Banks.(UpdateBuilder<*>) -> Unit = {
        it[id] = bank.id
        it[ip_address] = bank.ip_address
        it[account_number] = bank.account_number
        it[port] = bank.port
        it[node_identifier] = bank.node_identifier
        it[version] = bank.version
        it[protocol] = bank.protocol
        it[transaction_fee] = bank.transaction_fee
        it[trust] = bank.trust
    }
}