package com.jongwonlee.api.bank.controller

import com.google.gson.Gson
import com.jongwonlee.api.bank.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bank")
class DatabaseController(val repo: UserRepository) {

    //@GetMapping("/")//, produces = [MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE ])
    //@ResponseBody
    //fun helloWorld(): ResponseEntity<Any?> {
    //    val list = Banks.getAll()
    //   return ResponseEntity(Gson().toJson(list), HttpStatus.OK)
    //}

    @GetMapping
    fun list() = repo.findAll()

}