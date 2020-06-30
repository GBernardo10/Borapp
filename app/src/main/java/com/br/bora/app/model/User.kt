package com.br.bora.app.model

import com.google.gson.Gson
import java.io.Serializable

class User : Serializable {

    var name: String? = ""
    var mail: String? = ""
    var phone: String? = ""
    var username: String? = ""


    data class Create(
        val name: String?,
        val mail: String?,
        val phone: String?,
        var username: String?,
        var password: String?
    )

    data class UserInfo(
        var name: String?,
        var mail: String?,
        var phone: String?,
        var username: String?
    )

    data class Participantes(
        val username: String?
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
