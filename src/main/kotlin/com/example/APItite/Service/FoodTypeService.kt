package com.example.APItite.Service

import com.example.APItite.Model.FoodType
import com.example.APItite.Model.User
import com.example.APItite.Repo.FoodTypeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FoodTypeService (

    private val foodTypeRepo: FoodTypeRepository

) {
    fun getAll(): List<FoodType> {
        var foodTypes = foodTypeRepo.findAll().toList()
        return foodTypes
    }

    fun findById(id: Long): FoodType? {
        return foodTypeRepo.findByIdOrNull(id)
    }

}