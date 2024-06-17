package com.example.APItite.Dto

import com.example.APItite.Model.OrderItem
import com.example.APItite.Model.PaymentMethods

data class ConfirmOrderRequestDto (
    var restaurantId: Long,
    var userId: Long,
    var paymentMethod: PaymentMethods = PaymentMethods.CASH,
    var cardNumber: String = "",
    var address: String = "",
    var deliveryPrice: Double = 0.0,
    var orderItems: MutableList<OrderItem> = mutableListOf(),
)