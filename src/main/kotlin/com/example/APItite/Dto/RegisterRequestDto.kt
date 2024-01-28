package com.example.APItite.Dto

import com.example.APItite.Model.Roles

data class RegisterRequestDto(
        val email: String,
        val password: String,
        val role: Roles
)