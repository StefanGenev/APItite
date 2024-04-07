package com.example.APItite.Service

import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.RefreshToken
import com.example.APItite.Repo.RefreshTokenRepository
import com.example.APItite.Repo.RestaurantRepository
import com.example.APItite.Repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*


@Service
class RefreshTokenService(

    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository

)
{

    fun createRefreshToken(email: String): RefreshToken {

        val userInfo = userRepository.findByEmail(email)

        if (userInfo != null) {
            val refreshToken = RefreshToken(userInfo = userInfo,
                                token = UUID.randomUUID().toString(),
                                expiryDate = Instant.now().plusMillis(600000))

            return refreshTokenRepository.save(refreshToken)
        } else {
            throw UsernameNotFoundException("Email not found")
        }
    }

    fun findByToken(token: String): RefreshToken? {
        return refreshTokenRepository.findByToken(token)
    }

    fun verifyExpiration(token: RefreshToken): RefreshToken {

        if (token.expiryDate.compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token)
            throw RuntimeException(token.token + " Refresh token is expired. Please make a new login..!")
        }
        return token
    }
}