package com.example.APItite.Service

import com.example.APItite.Model.FoodType
import com.example.APItite.Repo.FoodTypeRepository
import org.springframework.stereotype.Service

@Service
class FoodTypeService (

    private val foodTypeRepo: FoodTypeRepository

) {
    fun getAll(): List<FoodType> {
        var foodTypes = foodTypeRepo.findAll().toList()
        return foodTypes
    }

}