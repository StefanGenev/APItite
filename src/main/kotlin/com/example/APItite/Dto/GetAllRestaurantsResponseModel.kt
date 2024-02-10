package com.example.APItite.Dto

import com.example.APItite.Model.Restaurant

data class GetAllRestaurantsResponseModel (
        var statusCode: Int,
        var message: String,
        var restaurants: List<Restaurant>
)