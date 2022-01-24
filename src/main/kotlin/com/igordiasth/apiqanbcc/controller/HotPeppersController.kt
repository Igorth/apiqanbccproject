package com.igordiasth.apiqanbcc.controller

import com.igordiasth.apiqanbcc.model.HotPeppers
import com.igordiasth.apiqanbcc.repository.HotPeppersRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hotpeppers")
class HotPeppersController(private val hotPeppersRepository: HotPeppersRepository)  {

    @GetMapping("")
    fun display(): String = "API HOT PEPPERS"

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<HotPeppers>> {
        val hotPeppers = hotPeppersRepository.findAll()
        if (hotPeppers.isEmpty()) {
            return ResponseEntity<List<HotPeppers>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<HotPeppers>>(hotPeppers, HttpStatus.OK)
    }

    @GetMapping("/count")
    fun getCount(): ResponseEntity<Long> = ResponseEntity(hotPeppersRepository.count(), HttpStatus.OK)
}