package com.example.APItite.Model

import jakarta.persistence.*

@Entity
@Table(name = "USER_ROLES")
data class UserRole (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    val id: Long = 0,

    val name: String = ""
)