package com.example.APItite.Repo

import com.example.APItite.Model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(name: String): User?
    fun existsByEmail(name: String): Boolean
}