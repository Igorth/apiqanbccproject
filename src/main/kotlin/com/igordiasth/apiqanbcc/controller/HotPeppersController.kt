package com.igordiasth.apiqanbcc.controller

import com.igordiasth.apiqanbcc.model.HotPeppers
import com.igordiasth.apiqanbcc.repository.HotPeppersRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @GetMapping("/{id}")
    fun getHotSauce(@PathVariable id: Long): ResponseEntity<Optional<HotPeppers>> {
        if (hotPeppersRepository.existsById(id)){
            return ResponseEntity(hotPeppersRepository.findById(id), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}