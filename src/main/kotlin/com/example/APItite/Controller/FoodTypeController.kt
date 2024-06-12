package com.example.APItite.Controller

import com.example.APItite.Model.FoodType
import com.example.APItite.Service.FoodTypeService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/food_types")
class FoodTypeController(
    private val foodTypeService: FoodTypeService,
) {
    @Secured("RESTAURANT", "ADMIN")
    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<Any> {
        try {
            val result: List<FoodType> = foodTypeService.getAll()
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}