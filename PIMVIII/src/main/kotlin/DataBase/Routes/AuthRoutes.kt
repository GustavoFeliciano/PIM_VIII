package com.unip.DataBase.Routes

import com.unip.Models.Login
import com.unip.dataBase.Factory.DBUtils.suspendTransaction
import com.unip.dataBase.Schemas.UsuariosTable
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere

fun Application.authRoutes(){
    routing {
        route("/auth"){
            post("/login") {
                val body = call.receive<Login>()

                val user = suspendTransaction {
                    UsuariosTable.select { UsuariosTable.email eq body.email }
                        .andWhere { UsuariosTable.senha eq body.senha }
                        .limit(1)
                        .firstOrNull()
                }

                if (user == null) {
                    call.respondText("Credenciais inválidas")
                } else {
                    call.respond(mapOf("message" to "logado com sucesso"))
                }
            }
        }
    }
}