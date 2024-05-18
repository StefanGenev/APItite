package com.example.APItite.Repo

import com.example.APItite.Model.Promotion
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PromotionsRepository : CrudRepository<Promotion, Long> {

    fun findByMealRestaurantId(id: Long): ArrayList<Promotion>
}