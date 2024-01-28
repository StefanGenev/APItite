package com.example.APItite.Service

import com.example.APItite.Model.User
import com.example.APItite.Repo.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserService (
    private val userRepo: UserRepository
) {
    fun findById(id: Long): User? {
        return userRepo.findByIdOrNull(id)
    }

    fun findByEmail(name: String): User? {
        return userRepo.findByEmail(name)
    }

    fun existsByEmail(name: String): Boolean {
        return userRepo.existsByEmail(name)
    }

    fun save(user: User): User {
        return userRepo.save(user)
    }
}