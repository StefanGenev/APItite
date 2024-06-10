package com.example.APItite.Repo

import com.example.APItite.Model.OrderItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderItemRepository : CrudRepository<OrderItem, Long> {

}