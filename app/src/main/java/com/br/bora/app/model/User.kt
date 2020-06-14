package com.br.bora.app.model

import com.google.gson.Gson

class User {
    data class Create(
        val name: String?,
        val mail: String?,
        val phone: String?,
        var username: String?,
        var password: String?
    )

    data class Auth(
        var username: String?,
        var password: String?
    )

    class UserAuthenticated(
        val mailUser: String,
        val idUser: String
    ) {
        override fun toString(): String {
            return Gson().toJson(this)
        }
    }
}
