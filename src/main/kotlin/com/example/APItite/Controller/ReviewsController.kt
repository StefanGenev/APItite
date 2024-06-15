package com.example.APItite.Controller

import com.example.APItite.Dto.AddReviewRequestDto
import com.example.APItite.Dto.IdentifierDto
import com.example.APItite.Service.ReviewsService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reviews")
class ReviewsController(
    private val reviewsService: ReviewsService,
) {

    @Secured("RESTAURANT", "ADMIN")
    @PostMapping("/get_by_restaurant")
    fun getReviewsByRestaurantId(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {

        try {

            var result = reviewsService.findByRestaurantId(dto.id)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("CUSTOMER", "ADMIN")
    @PostMapping("/get_by_user")
    fun getReviewsByUserId(@RequestBody dto: IdentifierDto): ResponseEntity<Any> {

        try {

            var result = reviewsService.findByUserId(dto.id)
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @Secured("CUSTOMER", "ADMIN")
    @PostMapping("/add")
    fun addReview(@RequestBody dto: AddReviewRequestDto): ResponseEntity<Any> {

        try {

            var result = reviewsService.addReview(dto)
            return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}