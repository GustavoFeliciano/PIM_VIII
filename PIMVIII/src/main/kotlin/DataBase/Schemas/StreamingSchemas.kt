package com.unip.dataBase.Schemas

import org.jetbrains.exposed.dao.id.IntIdTable

object UsuariosTable : IntIdTable() {
    val nome = varchar("nome", 200)
    val email = varchar("email", 200)
    val senha = varchar("senha", 200) // simples
}

object CriadoresTable : IntIdTable() {
    val nome = varchar("nome", 200)
}

object ConteudosTable : IntIdTable() {
    val titulo = varchar("titulo", 200)
    val descricao = varchar("descricao", 500)
    val criadorId = integer("criador_id").references(CriadoresTable.id)
    val videoPath = varchar("video_path", 400)
    val thumbnailPath = varchar("thumb_path", 400)
}

object PlaylistsTable : IntIdTable() {
    val nome = varchar("nome", 200)
    val usuarioId = integer("usuario_id").references(UsuariosTable.id)
}

object ItemPlaylistTable : IntIdTable() {
    val playlistId = integer("playlist_id").references(PlaylistsTable.id)
    val conteudoId = integer("conteudo_id").references(ConteudosTable.id)
}


