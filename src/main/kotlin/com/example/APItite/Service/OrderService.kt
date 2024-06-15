package com.example.APItite.Service

import com.example.APItite.Dto.IdentifierDto
import com.example.APItite.Dto.NoData
import com.example.APItite.Model.Order
import com.example.APItite.Model.OrderItem
import com.example.APItite.Model.OrderStatuses
import com.example.APItite.Repo.OrderItemRepository
import com.example.APItite.Repo.OrderRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
) {

    fun confirmOrder(dto: Order) : NoData {

        dto.status = OrderStatuses.ORDERED
        dto.orderedOn = LocalDateTime.now()

        var newOrderItems: MutableList<OrderItem> = dto.orderItems.map { it }.toMutableList()
        dto.orderItems.clear()

        var savedOrder = orderRepository.save(dto)
        savedOrder.orderItems = newOrderItems

        for (orderItem in savedOrder.orderItems) {
            orderItem.order = savedOrder
        }

        orderItemRepository.saveAll(dto.orderItems)

        return NoData()
    }

    fun getByUserId(dto: IdentifierDto) : List<Order> {

        val result = orderRepository.findByUserId(dto.id)
        return result
    }

    fun getByRestaurantId(id: Long) : List<Order> {

        val result = orderRepository.findByRestaurantId(id)
        return result
    }

}