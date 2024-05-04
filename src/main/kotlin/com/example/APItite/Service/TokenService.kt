package com.example.APItite.Service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


/**
 * Creates and parses JWT tokens.
 */
@Service
class TokenService(
        @Value("\${security.key}")
        private val jwtKey: String,
        @Value("\${refresh.token.seconds.before.expiration}")
        private val refreshTokenSecondsBeforeExpiration: Long
) {
    fun extractEmail(token: String): String {
        return extractClaim<String>(token, Claims::getSubject)
    }

    fun extractExpiration(token: String): Date {
        val expiration = extractClaim<Date>(token, Claims::getExpiration)
        return expiration
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return (email == userDetails.username && !isTokenExpired(token))
    }

    fun generateToken(username: String): String {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, username)
    }

    private fun createToken(claims: Map<String, Any?>, username: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + (1000 * refreshTokenSecondsBeforeExpiration)))
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact()
    }

    private fun getSignKey(): Key {
        val keyBytes = Decoders.BASE64.decode(jwtKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}