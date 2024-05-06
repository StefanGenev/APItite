package com.example.APItite.Controller

import com.example.APItite.Dto.RegisterRestaurantRequestDto
import com.example.APItite.Dto.SaveRestaurantLocationRequestDto
import com.example.APItite.Model.Restaurant
import com.example.APItite.Service.RestaurantService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(
        private val restaurantService: RestaurantService,
) {
    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<Any> {
        try {
            val result: List<Restaurant> = restaurantService.getAll()
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/save")
    fun save(@RequestBody dto: RegisterRestaurantRequestDto): ResponseEntity<Any> {
        try {

            val result = restaurantService.saveRestaurant(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/save_location")
    fun save(@RequestBody dto: SaveRestaurantLocationRequestDto): ResponseEntity<Any> {
        try {

            val result = restaurantService.saveRestaurantLocation(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}