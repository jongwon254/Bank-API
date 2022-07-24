package com.jongwonlee.api.bank.controller

import com.google.gson.Gson
import com.jongwonlee.api.bank.model.BankDB
import com.jongwonlee.api.bank.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/banks")
class DatabaseController(val repo: UserRepository) {

    //@GetMapping("/")//, produces = [MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE ])
    //@ResponseBody
    //fun helloWorld(): ResponseEntity<Any?> {
    //    val list = Banks.getAll()
    //   return ResponseEntity(Gson().toJson(list), HttpStatus.OK)
    //}

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getBanks() = repo.findAll()

    @GetMapping("/{id}")
    fun getBank(@PathVariable id: Int) = repo.findBank(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: BankDB) = repo.createBank(bank)

    @PatchMapping
    fun updateBank(@RequestBody bank: BankDB) = repo.updateBank(bank)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable id: Int) = repo.deleteBank(id)
}