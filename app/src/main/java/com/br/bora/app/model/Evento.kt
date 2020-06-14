package com.br.bora.app.model

import java.util.*

class Evento (
    val name:String,
    val owner:String,
    val startDay:Date,
    val startEnd:Date,
    val roles:String,
    val password:String,
    val isPublic:Boolean
)
data class Evento(val owner: String, val name: String, val rating: Int, val isPrivate: Boolean)
