package com.unip.Models

import kotlinx.serialization.Serializable

@Serializable
data class ConteudoModel(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val criador: CriadorModel,
    val videoPath: String,
    val thumbnailPath: String
)