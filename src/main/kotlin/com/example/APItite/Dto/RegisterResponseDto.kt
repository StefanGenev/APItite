package com.example.APItite.Dto

import com.example.APItite.Model.User

class RegisterResponseDto (
        var accessToken: String,
        var token: String,
        var user: User
)