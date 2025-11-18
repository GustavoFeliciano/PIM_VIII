package com.unip.DataBase.Routes

import com.unip.Repositories.AuthRepository
import io.ktor.server.application.Application

fun Application.routesConfig(authRepo: AuthRepository){
    uploadRoutes()
    authRoutes(authRepo)
}