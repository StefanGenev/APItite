package com.example.APItite.Repo

import com.example.APItite.Model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(name: String): User
    fun existsByEmail(name: String): Boolean
}