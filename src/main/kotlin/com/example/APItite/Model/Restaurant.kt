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

        @Enumerated(EnumType.STRING)
        var priceRange: PriceRanges = PriceRanges.CHEAP,

        @Column(nullable = false)
        var address: String = "",

        @Lob
        @Column(nullable = false)
        var image: ByteArray,

        @Column(nullable = false)
        var rating: Double
)