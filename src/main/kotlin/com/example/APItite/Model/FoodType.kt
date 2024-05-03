package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "food_types")
open class FoodType (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0,

    @Column(nullable = false)
    open var name: String = "",

)
