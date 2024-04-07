package com.example.APItite.Model

import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


open class CustomUserDetails(user: User) : User(user), UserDetails {

    private val log = LoggerFactory.getLogger(CustomUserDetails::class.java)

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles
            .stream()
            .map { role ->
                log.debug("Granting Authority to user with role: " + role.toString())
                SimpleGrantedAuthority(role.toString())
            }
            .collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return super.passWord
    }

    override fun getUsername(): String {
        return super.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}