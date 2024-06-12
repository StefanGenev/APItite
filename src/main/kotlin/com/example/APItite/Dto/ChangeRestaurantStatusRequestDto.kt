package com.example.APItite.Dto

import com.example.APItite.Model.RestaurantStatuses

data class ChangeRestaurantStatusRequestDto (

    var restaurantId: Long = 0,
    var status: RestaurantStatuses = RestaurantStatuses.REGISTERED,
    var note: String = ""
)