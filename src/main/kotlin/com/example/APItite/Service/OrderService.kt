package com.example.APItite.Service

import com.example.APItite.Dto.NoData
import com.example.APItite.Model.Order
import com.example.APItite.Model.OrderStatuses
import com.example.APItite.Repo.OrderItemRepository
import com.example.APItite.Repo.OrderRepository
import org.springframework.stereotype.Service


@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
) {

    fun confirmOrder(dto: Order) : NoData {

        dto.status = OrderStatuses.ORDERED
        var savedOrder = orderRepository.save(dto)

        for (orderItem in dto.orderItems) {
            orderItem.order = savedOrder
        }

        orderItemRepository.saveAll(dto.orderItems)

        return NoData()
    }

}