package com.example.APItite.Repo

import com.example.APItite.Model.Review
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewsRepository : CrudRepository<Review, Long> {

    fun findByRestaurantId(id: Long): ArrayList<Review>
    fun findByUserId(id: Long): ArrayList<Review>

    fun findByUserIdAndRestaurantId(userId: Long, restaurantId: Long): ArrayList<Review>

}