package com.example.APItite.Model

import jakarta.persistence.*

enum class OrderStatuses {

    INITIALISED,
    ORDERED,
    DELIVERED,
}

enum class PaymentMethods {

    CASH,
    CARD,
}

@Entity
@Table(name = "orders")
data class Order(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    var restaurant: Restaurant = Restaurant(),

    @ManyToOne(fetch = FetchType.EAGER)
    var user: User = User(),

    @Column(nullable = false)
    var status: OrderStatuses = OrderStatuses.INITIALISED,

    @Column(nullable = false)
    var paymentMethod: PaymentMethods = PaymentMethods.CASH,

    @Column(nullable = false)
    var cardNumber: String = "",

    @OneToMany(fetch = FetchType.EAGER, mappedBy="order")
    var orderItems: MutableList<OrderItem> = mutableListOf(),

    )