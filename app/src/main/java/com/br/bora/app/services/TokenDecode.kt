package com.br.bora.app.services

import com.auth0.android.jwt.JWT
import com.br.bora.app.model.User
import com.br.bora.app.response.Token

class TokenDecode {

    fun decodeToken(token: Token):User.UserAuthenticated {
        val jwt = JWT(token.token)
        val id = jwt.claims["id"]?.asString()
        val mail = jwt.claims["mail"]?.asString()
        return User.UserAuthenticated(id!!,mail!!)
    }
}