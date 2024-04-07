package com.example.APItite.Controller

import com.example.APItite.Dto.JwtResponseDto
import com.example.APItite.Dto.LoginRequestDto
import com.example.APItite.Dto.RefreshTokenRequestDto
import com.example.APItite.Dto.RegisterRequestDto
import com.example.APItite.Model.RefreshToken
import com.example.APItite.Service.RefreshTokenService
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
        private val userService: UserService,
        private val tokenService: TokenService,
        private val refreshTokenService: RefreshTokenService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginRequestDto): ResponseEntity<Any> {
        try {

            val result = userService.login(payload)
            return ResponseHandler.generateResponse("Successful login!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterRequestDto): ResponseEntity<Any> {
        try {

            val result = userService.register(payload)
            return ResponseHandler.generateResponse("Successfully registered!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }

    @PostMapping("/refreshToken")
    fun refreshToken(@RequestBody refreshTokenRequestDTO: RefreshTokenRequestDto): ResponseEntity<Any> {
        try {

            val refreshToken = refreshTokenService.findByToken(refreshTokenRequestDTO.token)
                ?: return ResponseHandler.generateResponse("Refresh token is not in DB!", HttpStatus.MULTI_STATUS, null)

            val verifiedToken = refreshTokenService.verifyExpiration(refreshToken)
            val accessToken = tokenService.generateToken(verifiedToken.userInfo.email)

            val result = JwtResponseDto(accessToken = accessToken,
                                        token = refreshTokenRequestDTO.token)

            return ResponseHandler.generateResponse("RefreshToken sent!", HttpStatus.OK, result)

        } catch (e: Exception) {
            return ResponseHandler.generateResponse(e.message!!, HttpStatus.MULTI_STATUS, null)
        }
    }
}