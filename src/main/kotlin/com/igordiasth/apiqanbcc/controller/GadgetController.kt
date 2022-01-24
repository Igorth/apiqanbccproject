package com.igordiasth.apiqanbcc.controller

import com.igordiasth.apiqanbcc.repository.GadgetRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GadgetController(private val gadgetRepository: GadgetRepository)  {

    @GetMapping("/")
    fun display(): String = "Spring Boot CRUD operation with Kotlin and MySQL...!"
}