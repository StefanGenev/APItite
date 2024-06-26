package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "food_types")
open class FoodType (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0,

    @Column(nullable = false, columnDefinition = "nvarchar(64)", length = 64)
    open var name: String = "",

    @Column(nullable = false, columnDefinition = "nvarchar(64)", length = 64)
    open var nameEnglish: String = "",

)
