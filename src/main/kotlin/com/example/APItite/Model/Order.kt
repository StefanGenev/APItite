package com.example.APItite.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import jakarta.persistence.*
import java.time.LocalDateTime

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
data class Order (

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

    @Column(nullable = false)
    var address: String = "",

    @OneToMany(fetch = FetchType.EAGER,
                mappedBy="order")
    var orderItems: MutableList<OrderItem> = mutableListOf(),

    @Column(nullable = false)
    var orderedOn: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var deliveredOn: LocalDateTime = LocalDateTime.now(),

    ) {
    override fun toString(): String {
        return ""
    }
}