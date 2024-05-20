package com.example.APItite.Dto

import com.example.APItite.Model.PromotionTypes

data class SaveMealRequestDto(

    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var imageUrl: String = "",
    var restaurantId: Long = 0,
    var hasPromotion: Boolean = false,
    var promotionType: PromotionTypes = PromotionTypes.PERCENT,
    var additionalMealsCount: Int = 0,
    var promotionPercent: Int = 0,
    var isHidden: Boolean = false,
    )