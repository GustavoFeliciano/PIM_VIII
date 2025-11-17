package com.unip.Models

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistModel(
    val id: Int,
    val nome: String,
    val usuarioId: Int,
    val itens: List<ItemPlaylistModel>
)