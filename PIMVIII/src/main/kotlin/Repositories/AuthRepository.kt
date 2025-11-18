package com.unip.Repositories

import com.unip.Models.loginRequest
import com.unip.dataBase.Factory.DBUtils.suspendTransaction
import com.unip.dataBase.Schemas.UsuarioDAO
import com.unip.dataBase.Schemas.UsuariosTable
import org.jetbrains.exposed.sql.and

class AuthRepository {

    suspend fun login(body: loginRequest) : Boolean = suspendTransaction{

        val user = UsuarioDAO.find { (UsuariosTable.email eq body.email) and (UsuariosTable.senha eq body.senha) }
            .limit(1)
            .firstOrNull()

        if(user == null) return@suspendTransaction false
        true
    }
}