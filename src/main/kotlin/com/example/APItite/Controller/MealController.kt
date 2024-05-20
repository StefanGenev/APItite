package com.example.APItite.Controller

import com.example.APItite.Dto.*
import com.example.APItite.Model.Meal
import com.example.APItite.Model.Promotion
import com.example.APItite.Model.Restaurant
import com.example.APItite.Service.MealService
import com.example.APItite.Service.RestaurantService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RestController
@RequestMapping("/api/meals")
class MealController(
    private val mealService: MealService,
) {

    @PostMapping("/get_meals")
    fun getMeals(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = mealService.getMealsByRestaurant(dto.id)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/save_meal")
    fun saveMeal(@RequestBody dto: SaveMealRequestDto): ResponseEntity<Any> {
        try {
            val result = mealService.saveMeal(dto)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @DeleteMapping("/delete_meal")
    fun deleteMeal(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = mealService.deleteMeal(dto)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}