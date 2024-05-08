package com.example.APItite.Service


import com.example.APItite.Dto.NoData
import com.example.APItite.Dto.RegisterRestaurantRequestDto
import com.example.APItite.Dto.RegisterRestaurantResponseDto
import com.example.APItite.Dto.SaveRestaurantLocationRequestDto
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Restaurant
import com.example.APItite.Repo.RestaurantRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RestaurantService (
        private val restaurantRepo: RestaurantRepository,
        private val userService: UserService,
        private val foodTypeService: FoodTypeService,
) {
    fun getAll(): List<Restaurant> {
        var restaurants = restaurantRepo.findAll().toList()
        return restaurants
    }

    fun getByOwnerId(id: Long): Restaurant? {
        var restaurant = restaurantRepo.findByOwnerId(id)
        return restaurant
    }

    fun saveRestaurant(dto: RegisterRestaurantRequestDto): RegisterRestaurantResponseDto {

        val owner = userService.findById(dto.ownerId)

        if (owner == null) {
            throw ApiException(400, "Owner doesn't exist")
        }

        val foodType = foodTypeService.findById(dto.foodTypeCode)

        if (foodType == null) {
            throw ApiException(400, "Food type doesn't exist")
        }

        val restaurant = Restaurant(0
            , name = dto.name
            , owner = owner
            , priceRange = dto.priceRange
            , address = dto.address
            , imageUrl = dto.imageUrl
            , foodType = foodType
            , 0.0 )

        val savedRestaurant = restaurantRepo.save(restaurant)

        return RegisterRestaurantResponseDto(savedRestaurant.id)
    }

    fun saveRestaurantLocation(dto: SaveRestaurantLocationRequestDto): NoData {

        val restaurantOptional = restaurantRepo.findById(dto.restaurantId)

        if (restaurantOptional.isEmpty) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        var restaurant = restaurantOptional.get()
        restaurant.address = dto.address

        restaurantRepo.save(restaurant)
        return NoData()
    }

}