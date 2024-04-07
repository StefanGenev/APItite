package com.example.APItite.Dto

import com.example.APItite.Model.UserRole

data class RegisterRequestDto(
        var email: String,
        var name: String,
        var password: String,
        var roles: Set<UserRole> = HashSet<UserRole>()
)