package com.example.APItite.Dto

import com.example.APItite.Model.User

data class LoginResponseDto (
        var accessToken: String,
        var token: String,
        var user: User
)