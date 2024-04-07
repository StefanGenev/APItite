package com.example.APItite.Service

import com.example.APItite.Model.CustomUserDetails
import com.example.APItite.Repo.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
open class CustomUserDetailsService (
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return CustomUserDetails(userRepository.findByEmail(username)!!)
    }

}