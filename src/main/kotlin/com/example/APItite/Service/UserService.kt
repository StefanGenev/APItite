package com.example.APItite.Service

import com.example.APItite.Dto.*
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.RefreshToken
import com.example.APItite.Model.RestaurantStatuses
import com.example.APItite.Model.User
import com.example.APItite.Repo.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserService (
    private val userRepo: UserRepository,
    private val tokenService: TokenService,
    private val refreshTokenService: RefreshTokenService,
    private val authenticationManager: AuthenticationManager,
    ) {

    fun login(payload: LoginRequestDto): LoginResponseDto {

        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                payload.email,
                payload.password
            )
        )

        if (authentication.isAuthenticated()) {

            val user = findByEmail(payload.email) ?: throw ApiException(400, "Login failed")
            user.favoriteRestaurants= user.favoriteRestaurants.filter { it -> it.status == RestaurantStatuses.APPROVED }

            val refreshToken: RefreshToken = refreshTokenService.createRefreshToken(payload.email)

            return LoginResponseDto(
                accessToken = tokenService.generateToken(user.email),
                token = refreshToken.token,
                user = user
            )

        } else {
            throw UsernameNotFoundException("Invalid user request")
        }
    }

    fun checkIfEmailExists(payload: CheckIfEmailExistsRequestDto): CheckIfEmailExistsResponseDto {
        val userExists = existsByEmail(payload.email)
        return CheckIfEmailExistsResponseDto(exists = userExists)

    }

    fun register(payload: RegisterRequestDto): RegisterResponseDto {

        if (existsByEmail(payload.email)) {
            throw ApiException(400, "Email already exists")
        }

        val encoder = BCryptPasswordEncoder()

        val user = User(
            email = payload.email,
            name = payload.name,
            passWord = encoder.encode(payload.password),
            role = payload.role
        )

        save(user)

        val refreshToken: RefreshToken = refreshTokenService.createRefreshToken(payload.email)

        val result = RegisterResponseDto(
            accessToken = tokenService.generateToken(user.email),
            token = refreshToken.token,
            user = user
        )

        return result
    }

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

    fun getAll(): List<User> {
        return userRepo.findAll().toList()
    }
    @Transactional
    fun delete(id: Long): NoData {

        refreshTokenService.deleteByUserId(id)
        userRepo.deleteById(id)

        return NoData()
    }
}