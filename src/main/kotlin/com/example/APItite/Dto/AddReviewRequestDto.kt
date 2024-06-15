package com.example.APItite.Dto

data class AddReviewRequestDto (
    var userId: Long,
    var restaurantId: Long,
    var rating: Int,
    var feedback: String
)