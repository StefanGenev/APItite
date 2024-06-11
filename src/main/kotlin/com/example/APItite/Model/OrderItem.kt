package com.example.APItite.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "order_items")
data class OrderItem(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    @JsonIgnore // Ignore during JSON serialization
    var order: Order? = Order(),

    @ManyToOne(fetch = FetchType.EAGER)
    var meal: Meal = Meal(),

    @Column(nullable = false)
    var count: Int = 0,

    )