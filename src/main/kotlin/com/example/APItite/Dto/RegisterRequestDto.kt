package com.example.APItite.Dto

import com.example.APItite.Model.Roles

data class RegisterRequestDto(
        var email: String,
        var name: String,
        var password: String,
        var role: Roles
)