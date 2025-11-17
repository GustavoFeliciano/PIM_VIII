package com.unip.Models


import kotlinx.serialization.Serializable

@Serializable
data class CriadorModel(
    val id: Int,
    val nome: String
)