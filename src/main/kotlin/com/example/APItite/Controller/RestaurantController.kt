package com.example.APItite.Controller

import com.example.APItite.Dto.*
import com.example.APItite.Model.RestaurantStatuses
import com.example.APItite.Service.RestaurantService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(
        private val restaurantService: RestaurantService
) {
    @Secured("ADMIN")
    @GetMapping("/get_all")
    fun getAll(): ResponseEntity<Any> {
        try {
            val result = restaurantService.getAll()
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @GetMapping("/get_all_visible")
    fun getAllVisibleRestaurants(): ResponseEntity<Any> {
        try {

            var result = restaurantService.getAll()
            result = result.filter { item -> item.status == RestaurantStatuses.APPROVED }

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

    @PostMapping("/get_by_id")
    fun getById(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = restaurantService.findById(dto.id)
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
    fun save(@RequestBody dto: SaveRestaurantRequestDto): ResponseEntity<Any> {
        try {

            val result = restaurantService.saveRestaurant(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("ADMIN")
    @PostMapping("/changeStatus")
    fun register(@RequestBody dto: ChangeRestaurantStatusRequestDto): ResponseEntity<Any> {
        try {

            val result = restaurantService.changeStatus(dto)
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

    @Secured("CUSTOMER", "ADMIN")
    @PostMapping("/addRemoveFavoriteRestaurant")
    fun addRemoveFavorite(@RequestBody dto: AddRemoveFavoriteRestaurantRequestDto): ResponseEntity<Any> {
        try {
            val result = restaurantService.addRemoveFavoriteRestaurant(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("ADMIN")
    @DeleteMapping("/delete")
    fun delete(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = restaurantService.delete(dto.id)
            return ResponseHandler.generateResponse("Successfully deleted data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}