package com.example.APItite.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*


enum class PriceRanges {
    CHEAP, MIDRANGE, EXPENSIVE
}

enum class RestaurantStatuses {
        REGISTERED, APPROVED, HIDDEN
}

@Entity
@Table(name = "restaurants")
data class Restaurant(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(nullable = false, length = 64)
        var name: String = "",

        @OneToOne(fetch = FetchType.LAZY)
        @JsonIgnore
        var owner: User = User(),

        @Enumerated(EnumType.STRING)
        var priceRange: PriceRanges = PriceRanges.CHEAP,

        @Column(nullable = false)
        var address: String = "",

        @Column(nullable = false)
        var imageUrl: String = "",

        @ManyToOne(fetch = FetchType.EAGER)
        var foodType: FoodType = FoodType(),

        @Column(nullable = false, length = 16)
        var phoneNumber: String = "",

        @Column(nullable = false)
        var status: RestaurantStatuses = RestaurantStatuses.REGISTERED,

        @Column(nullable = false)
        var statusNote: String = "",

        @OneToMany(fetch = FetchType.LAZY, mappedBy="restaurant", cascade = [CascadeType.ALL])
        @JsonIgnore
        var orders: MutableList<Order> = mutableListOf(),

        @OneToMany(fetch = FetchType.LAZY, mappedBy="restaurant", cascade = [CascadeType.ALL])
        @JsonIgnore
        var meals: MutableList<Meal> = mutableListOf(),
        )