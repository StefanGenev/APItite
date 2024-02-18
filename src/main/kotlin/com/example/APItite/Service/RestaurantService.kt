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

        for (restaurant in restaurants) {
            val decompressedImage = ImageUtils.decompress(restaurant.image)
            restaurant.image = decompressedImage
        }

        return restaurants
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
            restaurant.image = ImageUtils.compress(file.bytes)
        } else {
            throw ApiException(400, "No image passed")
        }

        return restaurantRepo.save(restaurant)
    }

}