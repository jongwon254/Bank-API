package com.jongwonlee.api.bank.datasource.network

import com.jongwonlee.api.bank.datasource.BankDataSource
import com.jongwonlee.api.bank.datasource.network.dto.BankList
import com.jongwonlee.api.bank.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("network")
class NetworkDataSource(
    @Autowired private val restTemplate: RestTemplate
) : BankDataSource {

    override fun retrieveBanks(): Collection<Bank> {
        val response: ResponseEntity<BankList> =
            restTemplate.getForEntity("http://143.110.137.54/banks")

        return response.body?.results
            ?: throw IOException("Error: Could not fetch banks from the network.")
    }

    override fun retrieveBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun createBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }
}