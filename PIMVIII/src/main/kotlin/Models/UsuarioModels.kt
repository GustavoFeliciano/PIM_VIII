package com.unip.Models

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioModel(
    val id: Int,
    val nome: String,
    val email: String
)

@Serializable
data class Login(
    val email: String,
    val senha: String
)