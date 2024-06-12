package com.example.APItite.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*


@Entity
@Table(name = "users")
open class User(
        @Column(nullable = false)
        open var name: String = "",

        @Column(nullable = false, unique = true)
        open var email: String = "",

        @Column(nullable = false)
        open var passWord: String = "",

        @Column(nullable = false)
        open var role: Roles = Roles.CUSTOMER,

        @ManyToMany(fetch = FetchType.EAGER)
        var favoriteRestaurants: List<Restaurant> = mutableListOf()
) {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        open var id: Long = 0

        constructor(user: User) : this(user.name, user.email, user.passWord) {
                id = user.id
                name = user.name
                email = user.email
                passWord = user.passWord
                role = user.role
        }
}