package com.example.APItite.Service

import com.example.APItite.Dto.SaveRestaurantInputModel
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Restaurant
import com.example.APItite.Repo.RestaurantRepository
import com.example.APItite.Utils.ImageUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class RestaurantService (
        private val restaurantRepo: RestaurantRepository
) {
    fun getAll(): List<Restaurant> {
        var restaurants = restaurantRepo.findAll().toList()
        return restaurants
    }

    fun saveRestaurant(restaurant: Restaurant): Restaurant {
        return restaurantRepo.save(restaurant)
    }

}