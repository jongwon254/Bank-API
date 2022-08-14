package com.jongwonlee.api.bank.controller

import com.google.gson.Gson
import com.jongwonlee.api.bank.model.BankDB
import com.jongwonlee.api.bank.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/banks")
@CrossOrigin()
class DatabaseController(val repo: UserRepository) {

    // Exceptions for 404 and 400 code
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    // Getting all banks
    @GetMapping
    fun getBanks() = repo.findAll()

    // Getting a bank with ID
    @GetMapping("/{id}")
    fun getBank(@PathVariable id: Int) = repo.findBank(id)

    // Creating a bank
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: BankDB) = repo.createBank(bank)

    // Updating a bank
    @PatchMapping
    fun updateBank(@RequestBody bank: BankDB) = repo.updateBank(bank)

    // Deleting a bank
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable id: Int) = repo.deleteBank(id)
}