package com.example.APItite.Service


import com.example.APItite.Dto.AddReviewRequestDto
import com.example.APItite.Dto.NoData
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.Review
import com.example.APItite.Repo.RestaurantRepository
import com.example.APItite.Repo.ReviewsRepository
import com.example.APItite.Repo.UserRepository
import org.springframework.stereotype.Service


@Service
class ReviewsService (

    private val reviewsRepo: ReviewsRepository,
    private val userRepository: UserRepository,
    private val restaurantRepository: RestaurantRepository

) {
    fun findByRestaurantId(id: Long) : List<Review> {
        return reviewsRepo.findByRestaurantId(id)
    }

    fun findByUserId(id: Long) : List<Review> {
        return reviewsRepo.findByUserId(id)
    }

    fun addReview(dto: AddReviewRequestDto) : NoData {

        val restaurant = restaurantRepository.findById(dto.restaurantId)

        if (restaurant == null) {
            throw ApiException(400, "Restaurant doesn't exist")
        }

        val user = userRepository.findById(dto.userId)

        if (user == null) {
            throw ApiException(400, "User doesn't exist")
        }

        val review = Review(user = user.get(),
                            restaurant = restaurant.get(),
                            rating = dto.rating,
                            feedback = dto.feedback)

        var savedMeal = reviewsRepo.save(review)

        return NoData()
    }

}