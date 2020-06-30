package com.br.bora.app.model

import java.io.Serializable


class Event : Serializable {
    lateinit var id: String
    lateinit var name: String
    lateinit var owner: String
    lateinit var description: String
    lateinit var zipcode: String
    lateinit var address: String
    var streetNumber: Int = 0
    lateinit var startDay: String
    lateinit var startEnd: String
    var rating: Float = 0F
    var isPublic: Boolean = true
    lateinit var photoUrl: String

    data class Create(
        val name: String,
        val owner: String,
        val description: String,
        val zipcode: String,
        val address: String,
        val streetNumber: Int,
        val startDay: String,
        val startEnd: String,
        val startTime: String,
        val endTime: String,
        val password: String,
        val isPublic: Boolean,
        val isFree: Boolean,
        val price: Int
    )

    data class FindAll(
        val id: String,
        val name: String,
        val owner: String,
        val description: String?,
        val zipcode: String?,
        val address: String?,
        val streetNumber: Int?,
        val startDay: String?,
        val startEnd: String?,
        val rating: Float,
        val isPublic: Boolean,
        val photoUrl: String?
    )

    data class participateEvent(
        val username: String
    )

    data class participateFromEvent(
        val id: String,
        val eventFk: String,
        val username: String
    )
}


