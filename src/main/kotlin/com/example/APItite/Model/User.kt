package com.example.APItite.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "users")
open class User(

        @Column(nullable = false, length = 32)
        open var name: String = "",

        @Column(nullable = false, unique = true, length = 32)
        open var email: String = "",

        @Column(nullable = false, length = 32)
        open var passWord: String = "",

        @Column(nullable = false)
        open var role: Roles = Roles.CUSTOMER,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinTable(name = "favorite_restaurants", joinColumns = [JoinColumn(name = "restaurant_id")])
        open var favoriteRestaurants: List<Restaurant> = mutableListOf(),

        @OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade = [CascadeType.ALL])
        @JsonIgnore
        var reviews: MutableList<Review> = mutableListOf(),

) {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open var id: Long = 0

        constructor(user: User) : this(user.name, user.email, user.passWord) {
                id = user.id
                name = user.name
                email = user.email
                passWord = user.passWord
                role = user.role
        }
}