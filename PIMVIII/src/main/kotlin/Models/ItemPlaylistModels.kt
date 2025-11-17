package com.unip.Models

import kotlinx.serialization.Serializable

@Serializable
data class ItemPlaylistModel(
    val id: Int,
    val conteudoId: Int
)