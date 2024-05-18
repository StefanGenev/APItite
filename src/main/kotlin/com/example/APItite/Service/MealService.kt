package com.example.APItite.Service


import com.example.APItite.Dto.NoData
import com.example.APItite.Dto.SaveMealRequestDto
import com.example.APItite.Dto.SaveMealResponseDto
import com.example.APItite.Dto.SavePromotionRequestDto
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Meal
import com.example.APItite.Model.Promotion
import com.example.APItite.Repo.MealRepository
import com.example.APItite.Repo.PromotionsRepository
import org.springframework.stereotype.Service

@Service
class MealService (
    private val mealRepo: MealRepository,
    private val restaurantService: RestaurantService,
    private val promotionRepo: PromotionsRepository,
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
                        , restaurant = restaurant)

        var savedMeal = mealRepo.save(meal)
        return SaveMealResponseDto(meal = savedMeal)
    }

    fun savePromotion(dto: SavePromotionRequestDto): NoData {

        var promotion: Promotion?

        if (dto.id > 0) {

            promotion = promotionRepo.findById(dto.id).get()
            promotion.promotionPercent = dto.promotionPercent

        } else {

            val meal = mealRepo.findById(dto.mealId)

            if (meal.isEmpty) {
                throw ApiException(400, "Meal doesn't exist")
            }

            promotion = Promotion(meal = meal.get(), promotionPercent = dto.promotionPercent)
        }

        promotionRepo.save(promotion)

        return NoData()
    }

    fun getPromotions(id: Long): List<Promotion> {
        var promotions = promotionRepo.findByMealRestaurantId(id)
        return promotions
    }
}