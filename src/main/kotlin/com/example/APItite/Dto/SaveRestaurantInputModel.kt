package com.example.APItite.Dto

import com.example.APItite.Model.PriceRanges
import com.example.APItite.Model.Restaurant

data class SaveRestaurantInputModel (
    var name: String = "",

    var ownerId: Int = 0,

    var priceRange: PriceRanges = PriceRanges.CHEAP,

    var address: String = "",

    var rating: Double = 0.0
) {

}