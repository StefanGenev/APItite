package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "promotions")
data class Promotion(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    var meal: Meal,
)