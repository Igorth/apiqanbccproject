package com.igordiasth.apiqanbcc.controller

import com.igordiasth.apiqanbcc.repository.HotPeppersRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HotPeppersController(private val hotPeppersRepository: HotPeppersRepository)  {

    @GetMapping("/")
    fun display(): String = "API HOT PEPPERS"
}