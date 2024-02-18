package com.example.APItite.Controller

import com.example.APItite.Dto.LoginRequestDto
import com.example.APItite.Dto.RegisterRequestDto
import com.example.APItite.Dto.LoginResponseDto
import com.example.APItite.Dto.RegisterResponseDto
import com.example.APItite.Exceptions.ApiException
import com.example.APItite.Model.User
import com.example.APItite.Service.HashService
import com.example.APItite.Service.TokenService
import com.example.APItite.Service.UserService
import com.example.APItite.Utils.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(
        private val hashService: HashService,
        private val tokenService: TokenService,
        private val userService: UserService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginRequestDto): ResponseEntity<Any> {
        try {
            val user = userService.findByEmail(payload.email) ?: throw ApiException(400, "Login failed")

            if (!hashService.checkBcrypt(payload.password, user.password)) {
                throw ApiException(400, "Login failed")
            }

            val result = LoginResponseDto(
                authToken = tokenService.createToken(user),
                user = user
            )

            return ResponseHandler.generateResponse("Successfull login!", HttpStatus.OK, result)
        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterRequestDto): ResponseEntity<Any> {
        try {
            if (userService.existsByEmail(payload.email)) {
                throw ApiException(400, "Email already exists")
            }

            val user = User(
                email = payload.email,
                name = payload.name,
                password = hashService.hashBcrypt(payload.password),
                role = payload.role
            )

            val savedUser = userService.save(user)
            val result = RegisterResponseDto(
                authToken = tokenService.createToken(savedUser),
                user = user
            )

            return ResponseHandler.generateResponse("Successfully registered!", HttpStatus.OK, result)
        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}