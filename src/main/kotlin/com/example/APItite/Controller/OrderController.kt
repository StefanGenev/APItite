package com.example.APItite.Controller

import com.example.APItite.Dto.IdentifierDto
import com.example.APItite.Model.Order
import com.example.APItite.Service.OrderService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService,
) {

    @Secured("CUSTOMER", "ADMIN")
    @PostMapping("/confirm")
    fun confirmOrder(@RequestBody dto: Order): ResponseEntity<Any> {
        try {
            val result = orderService.confirmOrder(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("CUSTOMER", "ADMIN")
    @PostMapping("/get_by_user")
    fun getByUserId(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = orderService.getByUserId(dto)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("RESTAURANT", "ADMIN")
    @PostMapping("/get_by_restaurant")
    fun getByRestaurantId(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {
        try {
            val result = orderService.getByRestaurantId(dto.id)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

}