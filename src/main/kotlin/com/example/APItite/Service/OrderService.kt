package com.example.APItite.Service

import com.example.APItite.Dto.ConfirmOrderRequestDto
import com.example.APItite.Dto.ConfirmOrderResponseDto
import com.example.APItite.Dto.IdentifierDto
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Order
import com.example.APItite.Model.OrderStatuses
import com.example.APItite.Repo.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val restaurantRepo: RestaurantRepository,
    private val usersRepo: UserRepository,
    private val reviewsRepo: ReviewsRepository,
) {

    fun confirmOrder(dto: ConfirmOrderRequestDto) : ConfirmOrderResponseDto {

        val restaurant = restaurantRepo.findById(dto.restaurantId)

        if (restaurant == null) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        val user = usersRepo.findById(dto.userId)

        if (user == null) {
            throw ApiException(400, "User doesn't exist")
        }

        val order = Order()
        order.restaurant = restaurant.get()
        order.user = user.get()
        order.address = dto.address
        //TODO: order.cardNumber = dto.cardNumber
        order.paymentMethod = dto.paymentMethod
        order.status = OrderStatuses.ORDERED
        order.orderedOn = LocalDateTime.now()
        order.deliveryPrice = dto.deliveryPrice

        var savedOrder = orderRepository.save(order)
        savedOrder.orderItems = dto.orderItems

        for (orderItem in savedOrder.orderItems) {
            orderItem.order = savedOrder
        }

        orderItemRepository.saveAll(dto.orderItems)

        val canWriteReviews = reviewsRepo.findByUserIdAndRestaurantId(userId = dto.userId, restaurantId = dto.restaurantId).isEmpty()

        return ConfirmOrderResponseDto(canWriteReview = canWriteReviews)
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