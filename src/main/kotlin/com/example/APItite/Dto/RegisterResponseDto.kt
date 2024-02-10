package com.example.APItite.Dto

import com.example.APItite.Model.User

class RegisterResponseDto (
        var statusCode: Int,
        var authToken: String,
        var user: User
)