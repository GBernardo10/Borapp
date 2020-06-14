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
