package com.example.APItite.Repo

import com.example.APItite.Model.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderRepository : CrudRepository<Order, Long> {

}