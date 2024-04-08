package com.example.APItite.Model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "REFRESH_TOKENS")
data class RefreshToken (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var token: String = "",

    var expiryDate: Instant = Instant.now(),

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User
)