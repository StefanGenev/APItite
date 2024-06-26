package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "reviews")
data class Review (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    var restaurant: Restaurant = Restaurant(),

    @ManyToOne(fetch = FetchType.EAGER)
    var user: User = User(),

    @Column(nullable = false)
    var rating: Int = 0,

    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    var feedback: String = "",

    )