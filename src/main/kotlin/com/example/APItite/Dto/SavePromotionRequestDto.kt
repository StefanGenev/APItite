package com.example.APItite.Dto

import com.example.APItite.Model.PromotionTypes

data class SavePromotionRequestDto (

    var id: Long = 0,
    var mealId: Long = 0,
    var type: PromotionTypes = PromotionTypes.PERCENT,
    var promotionPercent: Int = 0,
    var additionalMealsCount: Int = 0,
)