package com.example.APItite.Repo

import com.example.APItite.Model.Restaurant
import com.example.APItite.Model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository : CrudRepository<Restaurant, Long> {

    fun findByOwnerId(id: Long): Restaurant?
}