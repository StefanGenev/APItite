package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "meals")
data class Meal(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, columnDefinition = "nvarchar(64)", length = 64)
    var name: String = "",

    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    var description: String = "",

    @Column(nullable = false)
    var price: Double = 0.0,

    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    var imageUrl: String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    var restaurant: Restaurant = Restaurant(),

    @Column(nullable = false)
    var hasPromotion: Boolean = false,

    @Column(nullable = false)
    var promotionType: PromotionTypes = PromotionTypes.PERCENT,

    @Column(nullable = false)
    var promotionPercent: Int = 0,

    @Column(nullable = false)
    var isHidden: Boolean = false,
)