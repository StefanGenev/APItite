package com.example.APItite.Service


import com.example.APItite.Dto.*
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Promotion
import com.example.APItite.Model.Restaurant
import com.example.APItite.Repo.MealRepository
import com.example.APItite.Repo.PromotionsRepository
import com.example.APItite.Repo.RestaurantRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

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

    fun registerRestaurant(dto: RegisterRestaurantRequestDto): RegisterRestaurantResponseDto {

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

    fun saveRestaurant(dto: SaveRestaurantRequestDto): Restaurant {

        val restaurant = restaurantRepo.findById(dto.id)

        if (restaurant.isEmpty) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        val foodType = foodTypeService.findById(dto.foodTypeCode)

        if (foodType == null) {
            throw ApiException(400, "Food type doesn't exist")
        }

        val newRestaurant = restaurant.get()
        newRestaurant.name = dto.name
        newRestaurant.priceRange = dto.priceRange
        newRestaurant.address = dto.address
        newRestaurant.imageUrl = dto.imageUrl
        newRestaurant.foodType = foodType

        val savedRestaurant = restaurantRepo.save(newRestaurant)

        return savedRestaurant
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
    fun findById(id: Long): Restaurant? {
        return restaurantRepo.findByIdOrNull(id)
    }

    fun addRemoveFavoriteRestaurant(dto: AddRemoveFavoriteRestaurantRequestDto): List<Restaurant> {

        var user = userService.findById(dto.userId) ?: throw ApiException(400, "User doesn't exist")

        if (dto.removeFromFavorites) {

            user.favoriteRestaurants = user.favoriteRestaurants.filter { restaurant -> restaurant.id != dto.restaurantId  }

        } else {

            val restaurant = findById(dto.restaurantId) ?: throw ApiException(400, "Restaurant doesn't exist")

            if ( !user.favoriteRestaurants.any { it.id == dto.restaurantId })
                user.favoriteRestaurants.addLast(restaurant)
        }

        userService.save(user)

        return user.favoriteRestaurants
    }
}