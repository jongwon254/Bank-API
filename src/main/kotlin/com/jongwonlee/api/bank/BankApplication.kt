package com.jongwonlee.api.bank

import com.jongwonlee.api.bank.database.Banks
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BankApplication {

}

fun main(args: Array<String>) {

	// Docker PostgreSQL Database Connection (see also db/dev/*.sql and docker-compose.yml)
	val dbUrl = "jdbc:postgresql://localhost:5432/bank_db"
	val dbUser = "bank_dev_rw"
	val dbPass = "dev_bank_passwd"

	Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPass)

	// Initialize database with mock banks
	// start PostgreSQL with Docker
	transaction {
		addLogger(StdOutSqlLogger)

		SchemaUtils.drop(Banks)
		SchemaUtils.create(Banks)

		Banks.insert {
			it[ip_address] = "10.12.1.1"
			it[account_number] = "da92e7eh"
			it[port] = 80
			it[node_identifier] = "31idwoijd"
			it[version] = "v1.0"
			it[protocol] = "http"
			it[transaction_fee] = 32
			it[trust] = 1
		}

		Banks.insert {
			it[ip_address] = "26.21.23.2"
			it[account_number] = "f00fed98"
			it[port] = 80
			it[node_identifier] = "9jd83g4n"
			it[version] = "v1.6"
			it[protocol] = "http"
			it[transaction_fee] = 10
			it[trust] = 12
		}

		Banks.insert {
			it[ip_address] = "39.30.31.3"
			it[account_number] = "dwd912df"
			it[port] = 80
			it[node_identifier] = "0vue0lxn"
			it[version] = "v3.1"
			it[protocol] = "http"
			it[transaction_fee] = 14
			it[trust] = 5
		}
	}
	runApplication<BankApplication>(*args)
}
