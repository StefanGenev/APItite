package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "order_items")
data class OrderItem(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id", nullable=false)
    var order: Order = Order(),

    @ManyToOne(fetch = FetchType.EAGER)
    var meal: Meal = Meal(),

    @Column(nullable = false)
    var count: Int = 0,

    )