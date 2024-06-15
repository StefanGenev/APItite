package com.example.APItite.Service


import com.example.APItite.Dto.*
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Restaurant
import com.example.APItite.Repo.RestaurantRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RestaurantService (
        private val restaurantRepo: RestaurantRepository,
        private val userService: UserService,
        private val foodTypeService: FoodTypeService,
        private val reviewsService: ReviewsService,
        private val orderService: OrderService,
) {
    fun getAll(): List<Restaurant> {
        var restaurants = restaurantRepo.findAll().toList()
        return restaurants
    }

    fun getByOwnerId(id: Long): RestaurantDetailsResponseDto {

        val restaurant = restaurantRepo.findByOwnerId(id)

        if (restaurant == null) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        return getRestaurantDetails(restaurant)
    }

    fun getRestaurantDetails(restaurant: Restaurant): RestaurantDetailsResponseDto {

        val reviews = reviewsService.findByRestaurantId(restaurant.id)

        val totalRating = reviews.sumOf { it.rating }
        val averageRating = (totalRating / reviews.size).toDouble()

        val orders = orderService.getByRestaurantId(restaurant.id)

        return RestaurantDetailsResponseDto(restaurant = restaurant, averageRating = averageRating, totalOrders = orders.size )
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
            , phoneNumber = dto.phoneNumber )

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

    fun changeStatus(dto: ChangeRestaurantStatusRequestDto): NoData {

        val restaurant = restaurantRepo.findById(dto.restaurantId)

        if (restaurant.isEmpty) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        val newRestaurant = restaurant.get()
        newRestaurant.status = dto.status
        newRestaurant.statusNote = dto.note

        val savedRestaurant = restaurantRepo.save(newRestaurant)

        return NoData()
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
    fun findById(id: Long): RestaurantDetailsResponseDto {

        val restaurant = restaurantRepo.findByIdOrNull(id)

        if (restaurant == null) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        return getRestaurantDetails(restaurant)
    }

    fun addRemoveFavoriteRestaurant(dto: AddRemoveFavoriteRestaurantRequestDto): List<Restaurant> {

        var user = userService.findById(dto.userId) ?: throw ApiException(400, "User doesn't exist")

        if (dto.removeFromFavorites) {

            user.favoriteRestaurants = user.favoriteRestaurants.filter { restaurant -> restaurant.id != dto.restaurantId  }

        } else {

            val restaurantDetails = findById(dto.restaurantId) ?: throw ApiException(400, "Restaurant doesn't exist")

            if ( !user.favoriteRestaurants.any { it.id == dto.restaurantId })
                user.favoriteRestaurants.addLast(restaurantDetails.restaurant)
        }

        userService.save(user)

        return user.favoriteRestaurants
    }

    fun delete(id: Long): NoData {

        restaurantRepo.deleteById(id)
        return NoData()
    }
}