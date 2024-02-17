package com.example.APItite.Dto

import com.example.APItite.Model.PriceRanges
import com.example.APItite.Model.Restaurant

data class SaveRestaurantInputModel (
    var name: String = "",

    var priceRange: PriceRanges = PriceRanges.CHEAP,

    var address: String = "",

    var rating: Double = 0.0
) {
    fun getRestaurantEntity(): Restaurant {
        return Restaurant(name = this.name,
                          priceRange = this.priceRange,
                          address = this.address,
                          rating = this.rating)
    }
}