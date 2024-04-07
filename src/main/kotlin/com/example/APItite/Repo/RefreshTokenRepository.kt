package com.example.APItite.Repo

import com.example.APItite.Model.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface RefreshTokenRepository: CrudRepository<RefreshToken, Int> {
    fun findByToken(token: String): RefreshToken?

}