package com.unip.dataBase.Schemas

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UsuarioDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UsuarioDAO>(UsuariosTable)

    var nome by UsuariosTable.nome
    var email by UsuariosTable.email
    var senha by UsuariosTable.senha
    val playlists by PlaylistDAO referrersOn PlaylistsTable.usuarioId
}

class CriadorDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CriadorDAO>(CriadoresTable)

    var nome by CriadoresTable.nome

    val conteudos by ConteudoDAO referrersOn ConteudosTable.criadorId
}

class ConteudoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ConteudoDAO>(ConteudosTable)

    var titulo by ConteudosTable.titulo
    var descricao by ConteudosTable.descricao
    var criador by CriadorDAO referencedOn ConteudosTable.criadorId
    var videoPath by ConteudosTable.videoPath
    var thumbnailPath by ConteudosTable.thumbnailPath

    val playlists by ItemPlaylistDAO referrersOn ItemPlaylistTable.conteudoId
}

class PlaylistDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlaylistDAO>(PlaylistsTable)

    var nome by PlaylistsTable.nome

    var usuario by UsuarioDAO referencedOn PlaylistsTable.usuarioId

    val itens by ItemPlaylistDAO referrersOn ItemPlaylistTable.playlistId
}

class ItemPlaylistDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ItemPlaylistDAO>(ItemPlaylistTable)

    var playlist by PlaylistDAO referencedOn ItemPlaylistTable.playlistId
    var conteudo by ConteudoDAO referencedOn ItemPlaylistTable.conteudoId
}
