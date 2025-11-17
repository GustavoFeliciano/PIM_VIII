package com.unip

import com.unip.DataBase.Routes.routesConfig
import com.unip.dataBase.Factory.dataBaseConfig
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    dataBaseConfig()
    routesConfig()
}
