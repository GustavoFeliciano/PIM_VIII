package com.unip

import com.unip.DataBase.Routes.routesConfig
import com.unip.Repositories.AuthRepository
import com.unip.dataBase.Factory.dataBaseConfig
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

private val authRepository = AuthRepository()

fun Application.module() {
    install(ContentNegotiation){
        json()
    }
    install(CORS) {
        anyHost()
    }
    dataBaseConfig()
    routesConfig(authRepository)
}
