package com.unip.DataBase.Routes

import com.unip.Models.loginRequest
import com.unip.Repositories.AuthRepository
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.authRoutes(repository: AuthRepository){
    routing {
        route("/auth"){
            post("/login") {
                val body = call.receive<loginRequest>()

                if (!repository.login(body)) {
                    call.respondText("Credenciais inválidas")
                } else {
                    call.respond(mapOf("message" to "logado com sucesso"))
                }
            }
        }
    }
}