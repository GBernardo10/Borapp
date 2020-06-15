package com.br.bora.app.model

import java.util.*


class Event {
    data class Create(
        val name: String,
        val owner: String,
        val startDay: String,
        val startEnd: String,
        val rating: Double,
        val roles: String?,
        val password: String,
        val isPrivate: Boolean
    )
    data class FindAll(
        val name: String,
        val owner: String,
        val startDay: String?,
        val startEnd: String?,
        val rating: Double,
        val isPrivate: Boolean
    )
}

