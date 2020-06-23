package com.br.bora.app.model

import java.util.*


class Event {
    data class Create(
        val name: String,
        val owner: String,
        val startDay: String,
        val startEnd: String,
        val zipcode: String,
        val address: String? = "",
        val streetNumber: String,
        val privacy: String,
        val password: String,
        val isPublic: Boolean
    )

    data class FindAll(
        val name: String,
        val owner: String,
        val startDay: String?,
        val startEnd: String?,
        val rating: Double,
        val isPublic: Boolean,
        val photoUrl: String,
        val description : String

    )
}

