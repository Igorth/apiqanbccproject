package com.igordiasth.apiqanbcc.controller

import com.igordiasth.apiqanbcc.model.HotPeppers
import com.igordiasth.apiqanbcc.repository.HotPeppersRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class HotPeppersController(private val hotPeppersRepository: HotPeppersRepository)  {

    @GetMapping("/")
    fun display(): String = "API HOT PEPPERS"

    @GetMapping("/hotpeppers")
    fun getAll(): ResponseEntity<List<HotPeppers>> {
        val hotPeppers = hotPeppersRepository.findAll()
        if (hotPeppers.isEmpty()) {
            return ResponseEntity<List<HotPeppers>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<HotPeppers>>(hotPeppers, HttpStatus.OK)
    }

    @GetMapping("/hotpeppers/count")
    fun getCount(): ResponseEntity<Long> = ResponseEntity(hotPeppersRepository.count(), HttpStatus.OK)

    @GetMapping("/hotpeppers/{id}")
    fun getHotPeppers(@PathVariable id: Long): ResponseEntity<Optional<HotPeppers>> {
        if (hotPeppersRepository.existsById(id)){
            return ResponseEntity(hotPeppersRepository.findById(id), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/hotpeppers")
    fun createHotPeppers(@RequestBody hotPepper: HotPeppers): ResponseEntity<HotPeppers> {
        return ResponseEntity(hotPeppersRepository.save(hotPepper), HttpStatus.CREATED)
    }

    @PutMapping("/hotpeppers/{id}")
    fun updateHotPeppersById(@PathVariable("id") id: Long, @RequestBody sauceChanges: HotPeppers): ResponseEntity<HotPeppers> {
        return hotPeppersRepository.findById(id).map {
                hotPeppersDetails ->
            val updatedHotPeppers: HotPeppers = hotPeppersDetails.copy(
                brandName = sauceChanges.brandName,
                sauceName = sauceChanges.sauceName,
                description = sauceChanges.description,
                url = sauceChanges.url,
                heat = sauceChanges.heat,

            )
            ResponseEntity(hotPeppersRepository.save(updatedHotPeppers), HttpStatus.OK)
        }.orElse(ResponseEntity<HotPeppers>(HttpStatus.INTERNAL_SERVER_ERROR))
    }
}