package com.example.APItite.Repo

import com.example.APItite.Model.Meal
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MealRepository : CrudRepository<Meal, Long> {

    fun findByRestaurantId(id: Long): ArrayList<Meal>
}