package com.example.APItite.Controller

import com.example.APItite.Dto.IdentifierDto
import com.example.APItite.Dto.SaveFoodTypeRequestDto
import com.example.APItite.Dto.SaveMealRequestDto
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
    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<Any> {
        try {
            val result: List<FoodType> = foodTypeService.getAll()
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("ADMIN")
    @PostMapping("/save")
    fun saveFoodType(@RequestBody dto: SaveFoodTypeRequestDto): ResponseEntity<Any> {
        try {
            val result = foodTypeService.saveFoodType(dto)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("ADMIN")
    @DeleteMapping("/delete")
    fun deleteFoodType(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = foodTypeService.deleteFoodType(dto)
            return ResponseHandler.generateResponse("Successfully deleted data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}