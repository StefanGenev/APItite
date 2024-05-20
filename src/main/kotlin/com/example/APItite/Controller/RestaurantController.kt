package com.example.APItite.Controller

import com.example.APItite.Dto.*
import com.example.APItite.Model.Promotion
import com.example.APItite.Model.Restaurant
import com.example.APItite.Service.MealService
import com.example.APItite.Service.RestaurantService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(
        private val restaurantService: RestaurantService,
        private val mealService: MealService,
) {
    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<Any> {
        try {
            val result = restaurantService.getAll()
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/get_by_owner_id")
    fun getByOwnerId(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = restaurantService.getByOwnerId(dto.id)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody dto: RegisterRestaurantRequestDto): ResponseEntity<Any> {
        try {

            val result = restaurantService.registerRestaurant(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/save")
    fun saveRequestBody(@RequestBody dto: SaveRestaurantRequestDto): ResponseEntity<Any> {
        try {

            val result = restaurantService.saveRestaurant(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/save_location")
    fun saveLocation(@RequestBody dto: SaveRestaurantLocationRequestDto): ResponseEntity<Any> {
        try {

            val result = restaurantService.saveRestaurantLocation(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}