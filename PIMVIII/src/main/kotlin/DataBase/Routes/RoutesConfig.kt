package com.unip.DataBase.Routes

import io.ktor.server.application.Application

fun Application.routesConfig(){
    uploadRoutes()
    authRoutes()
}