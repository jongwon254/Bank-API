package com.jongwonlee.api.bank.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseURL = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun returnAllBanks() {
            // given
            // when/then
            mockMvc.get(baseURL)
                .andDo { print() }
                .andExpect {
                    status { isOk()}
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun returnBankWithAccountNumber() {
            // given
            val accountNumber = 1234

            // when/then
            mockMvc.get("$baseURL/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("3.0") }
                    jsonPath("$.transactionFee") { value("17") }
                }
        }

        @Test
        fun returnNotFoundAccountNumber() {
            //given
            val invalidAccountNumber = "does_not_exist"

            // when/then
            mockMvc.get("$baseURL/$invalidAccountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostBank {

        @Test
        fun addNewBank() {
            // given
            val newBank = Bank("acc123", 21.42, 2)

            // when
            val performPost = mockMvc.post(baseURL) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newBank))
                    }
                }

            mockMvc.get("$baseURL/${newBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(newBank)) } }
        }

        @Test
        fun returnBadRequestForDuplicateAccountNumber() {
            // given
            val invalidBank = Bank("1234", 1.0, 1)

            // when
            val performPost = mockMvc.post(baseURL) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchBank {

        @Test
        fun updateExistingBank() {
            // given
            val updatedBank = Bank("1234", 1.0, 1)

            // when
            val performPatch = mockMvc.patch(baseURL) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }

            // then
            performPatch
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }

            mockMvc.get("$baseURL/${updatedBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedBank)) } }
        }

        @Test
        fun returnNotExistingAccountNumber() {
            // given
            val invalidBank = Bank("does_not_exist", 1.0, 1)

            // when
            val performPatch = mockMvc.patch(baseURL) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            performPatch
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteBank {

        @Test
        @DirtiesContext
        fun deleteExistingBank() {
            // given
            val accountNumber = 1234

            // when
            val performDelete = mockMvc.delete("$baseURL/$accountNumber")

            // then
            performDelete
                .andDo { print() }
                .andExpect { content { status { isNoContent() } } }

            mockMvc.get("$baseURL/$accountNumber")
                .andExpect { content { status { isNotFound() } } }
        }

        @Test
        fun returnNotFoundAccountNumber() {
            //given
            val invalidAccountNumber = "does_not_exist"

            // when/then
            mockMvc.get("$baseURL/$invalidAccountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}