package com.example.APItite.Dto

import com.example.APItite.Model.Restaurant

data class RestaurantDetailsResponseDto (
    var restaurant: Restaurant,
    var averageRating: Double,
    var totalOrders: Int = 0
)