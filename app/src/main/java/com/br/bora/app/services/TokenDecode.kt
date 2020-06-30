package com.br.bora.app.services

import com.auth0.android.jwt.JWT
import com.br.bora.app.AuthActivity
import com.br.bora.app.model.User
import com.br.bora.app.response.Token
import com.br.bora.app.utils.SaveData

class TokenDecode {
    private lateinit var mShared: SaveData

    fun decodeToken(activity: AuthActivity, token: Token) {
        mShared = SaveData(activity.applicationContext)
        val jwt = JWT(token.token)
        val id = jwt.claims["id"]?.asString()
        val mail = jwt.claims["mail"]?.asString()
        val user = jwt.claims["username"]?.asString()
        mShared.store("auth", user.toString())
        mShared.store("token", jwt.toString())
    }
}