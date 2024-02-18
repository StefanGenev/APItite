package com.example.APItite.Dto

import com.example.APItite.Model.User

data class LoginResponseDto(
        var authToken: String,
        var user: User
)