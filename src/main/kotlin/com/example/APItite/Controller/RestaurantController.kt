package com.example.APItite.Controller

import com.example.APItite.Dto.GetAllRestaurantsResponseModel
import com.example.APItite.Model.Restaurant
import com.example.APItite.Service.RestaurantService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(
        private val restaurantService: RestaurantService,
) {
    @GetMapping("/get-all")
    fun getAll(): GetAllRestaurantsResponseModel {
        var restaurants = restaurantService.getAll()

        print("get-all result " + restaurants.toString())
        return GetAllRestaurantsResponseModel(
                200, "", restaurants
        )
    }
}