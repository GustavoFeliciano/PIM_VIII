package com.unip.dataBase.Factory

import com.unip.dataBase.Schemas.ConteudosTable
import com.unip.dataBase.Schemas.CriadoresTable
import com.unip.dataBase.Schemas.ItemPlaylistTable
import com.unip.dataBase.Schemas.PlaylistsTable
import com.unip.dataBase.Schemas.UsuariosTable
import io.ktor.server.application.Application
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.dataBaseConfig(){
    Database.connect(
        "jdbc:postgresql://localhost:5430/streaming",
        driver = "org.postgresql.Driver",
        user = "admin",
        password = "admin"
    )

    transaction {
        SchemaUtils.create(
            UsuariosTable,
            CriadoresTable,
            ConteudosTable,
            PlaylistsTable,
            ItemPlaylistTable
        )
    }
}

object DBUtils{
    suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
        newSuspendedTransaction(Dispatchers.IO, statement = block)
}
