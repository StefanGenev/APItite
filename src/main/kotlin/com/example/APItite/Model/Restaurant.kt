package com.example.APItite.Model

import jakarta.persistence.*
import java.sql.Blob


enum class PriceRanges {
    CHEAP, MIDRANGE, EXPENSIVE
}
@Entity
@Table(name = "restaurants")
data class Restaurant(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(nullable = false)
        var name: String = "",

        @ManyToOne(fetch = FetchType.EAGER)
        var owner: User,

        @Enumerated(EnumType.STRING)
        var priceRange: PriceRanges = PriceRanges.CHEAP,

        @Column(nullable = false)
        var address: String = "",

        @Column(nullable = false)
        var imageUrl: String = "",

        var rating: Double = 0.0
)