package com.unip.dataBase.Schemas

import com.unip.Models.ConteudoModel
import com.unip.Models.CriadorModel
import com.unip.Models.ItemPlaylistModel
import com.unip.Models.PlaylistModel
import com.unip.Models.UsuarioModel


fun UsuarioDAO.toModel() = UsuarioModel(
    id = this.id.value,
    nome = this.nome,
    email = this.email
)

fun CriadorDAO.toModel() = CriadorModel(
    id = this.id.value,
    nome = this.nome
)

fun ConteudoDAO.toModel() = ConteudoModel(
    id = this.id.value,
    titulo = this.titulo,
    descricao = this.descricao,
    criador = this.criador.toModel(),
    videoPath = this.videoPath,
    thumbnailPath = this.thumbnailPath
)

fun ItemPlaylistDAO.toModel() = ItemPlaylistModel(
    id = this.id.value,
    conteudoId = this.conteudo.id.value
)

fun PlaylistDAO.toModel() = PlaylistModel(
    id = this.id.value,
    nome = this.nome,
    usuarioId = this.usuario.id.value,
    itens = this.itens.map { it.toModel() }
)