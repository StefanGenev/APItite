package com.example.APItite.Service

import com.example.APItite.Dto.SaveRestaurantInputModel
import com.example.APItite.Model.Restaurant
import com.example.APItite.Repo.RestaurantRepository
import com.example.APItite.Utils.ResponseHandler.generateResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class RestaurantService (
        private val restaurantRepo: RestaurantRepository
) {
    fun getAll(): List<Restaurant> {
        return restaurantRepo.findAll().toList()
    }

    fun saveRestaurant(user: String?, file: MultipartFile?): Restaurant? {
        var inputModelJson: SaveRestaurantInputModel

        try {
            val objectMapper = ObjectMapper()
            inputModelJson = objectMapper.readValue(user, SaveRestaurantInputModel::class.java)
        } catch (e: Exception) {
            return null
        }

        var restaurant = inputModelJson.getRestaurantEntity()

        if (file != null) {
            restaurant.image = file.bytes
        } else {
            return null
        }

        return restaurantRepo.save(restaurant)
    }

}