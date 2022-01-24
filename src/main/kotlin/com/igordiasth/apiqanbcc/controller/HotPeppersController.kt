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
    fun getHotSauce(@PathVariable id: Long): ResponseEntity<Optional<HotPeppers>> {
        if (hotPeppersRepository.existsById(id)){
            return ResponseEntity(hotPeppersRepository.findById(id), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/hotpeppers")
    fun createHotSauce(@RequestBody hotPepper: HotPeppers): ResponseEntity<HotPeppers> {
        return ResponseEntity(hotPeppersRepository.save(hotPepper), HttpStatus.CREATED)
    }

    @PutMapping("/hotpeppers/{id}")
//    fun updateHotSauce(@PathVariable id: Long, @RequestBody sauceChanges: HotPeppers): ResponseEntity<HotPeppers?> {
//        if (hotPeppersRepository.existsById(id)){
//            val originalSauce = hotPeppersRepository.findById(id).get()
//            val updatedSauce = HotPeppers(
//                id = id,
//                brandName = if (sauceChanges.brandName != "") sauceChanges.brandName else originalSauce.brandName,
//                sauceName = if (sauceChanges.sauceName != "") sauceChanges.sauceName else originalSauce.sauceName,
//                description = if (sauceChanges.description != "") sauceChanges.description else originalSauce.description,
//                url = if (sauceChanges.url != "") sauceChanges.url else originalSauce.url,
//                heat = if (sauceChanges.heat != 0) sauceChanges.heat else originalSauce.heat
//            )
//            return ResponseEntity(hotPeppersRepository.save(updatedSauce), HttpStatus.OK)
//        } else {
//            return ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }

    fun updateGadgetById(@PathVariable("id") id: Long, @RequestBody sauceChanges: HotPeppers): ResponseEntity<HotPeppers> {
        return hotPeppersRepository.findById(id).map {
                gadgetDetails ->
            val updatedGadget: HotPeppers = gadgetDetails.copy(
                brandName = sauceChanges.brandName,
                sauceName = sauceChanges.sauceName,
                description = sauceChanges.description,
                url = sauceChanges.url,
                heat = sauceChanges.heat,

            )
            ResponseEntity(hotPeppersRepository.save(updatedGadget), HttpStatus.OK)
        }.orElse(ResponseEntity<HotPeppers>(HttpStatus.INTERNAL_SERVER_ERROR))
    }
}