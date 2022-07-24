package com.jongwonlee.api.bank.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

// TDD for old domain
internal class MockBankDataSourceTest {

    private val mockDataSource = MockBankDataSource()

    @Test
    fun provideCollectionOfBanks() {
        // given
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        Assertions.assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun provideMockData() {
        // given
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        Assertions.assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        Assertions.assertThat(banks).anyMatch { it.trust != 0.0 }
        Assertions.assertThat(banks).anyMatch { it.transactionFee != 0 }
    }
}
