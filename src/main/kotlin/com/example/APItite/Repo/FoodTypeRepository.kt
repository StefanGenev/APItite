package com.example.APItite.Repo

import com.example.APItite.Model.FoodType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodTypeRepository : CrudRepository<FoodType, Long> {
}