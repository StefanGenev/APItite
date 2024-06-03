package com.example.APItite.Dto

data class AddRemoveFavoriteRestaurantRequestDto (

    var userId: Long,
    var restaurantId: Long,
    var removeFromFavorites: Boolean
)