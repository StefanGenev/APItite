package com.example.APItite.Service

import com.example.APItite.Model.Restaurant
import com.example.APItite.Repo.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantService (
        private val restaurantRepo: RestaurantRepository
) {
    fun getAll(): List<Restaurant> {
        return restaurantRepo.findAll().toList()
    }

}