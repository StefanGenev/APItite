package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "promotions")
data class Promotion(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    var meal: Meal,

    @Column(nullable = false)
    var type: PromotionTypes = PromotionTypes.PERCENT,

    @Column(nullable = false)
    var promotionPercent: Int = 0,

    @Column(nullable = false)
    var additionalMealsCount: Int = 0,
)