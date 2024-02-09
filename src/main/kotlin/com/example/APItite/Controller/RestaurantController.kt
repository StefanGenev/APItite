package com.example.APItite.Controller

import com.example.APItite.Model.Restaurant
import com.example.APItite.Service.RestaurantService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(
        private val restaurantService: RestaurantService,
) {
    @PostMapping("/get-all")
    fun getAll(): List<Restaurant> {
        return restaurantService.getAll()
    }
}