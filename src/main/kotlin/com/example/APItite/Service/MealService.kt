package com.example.APItite.Service


import com.example.APItite.Dto.IdentifierDto
import com.example.APItite.Dto.NoData
import com.example.APItite.Dto.SaveMealRequestDto
import com.example.APItite.Dto.SaveMealResponseDto
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Meal
import com.example.APItite.Repo.MealRepository
import org.springframework.stereotype.Service

@Service
class MealService (
    private val mealRepo: MealRepository,
    private val restaurantService: RestaurantService,
    ) {
    fun getMealsByRestaurant(id: Long) : List<Meal> {
        var meals = mealRepo.findByRestaurantId(id).toList()
        return meals
    }

    fun saveMeal(dto: SaveMealRequestDto) : SaveMealResponseDto {

        val restaurant = restaurantService.findById(dto.restaurantId)

        if (restaurant == null) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        val meal = Meal( id = dto.id
                , name = dto.name
                , description = dto.description
                , price = dto.price
                , imageUrl = dto.imageUrl
                , restaurant = restaurant
                , hasPromotion = dto.hasPromotion
                , promotionType = dto.promotionType
                , promotionPercent = dto.promotionPercent
                , isHidden = dto.isHidden)

        var savedMeal = mealRepo.save(meal)
        return SaveMealResponseDto(meal = savedMeal)
    }

    fun deleteMeal(dto: IdentifierDto) : NoData {

        mealRepo.deleteById(dto.id)
        return NoData()
    }
}