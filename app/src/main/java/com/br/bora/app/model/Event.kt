package com.br.bora.app.model

import java.io.Serializable
import kotlin.properties.Delegates


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
    var rating: Double = 0.0
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
        val password: String,
        val isPublic: Boolean,
        val isFree: Boolean,
        val price: String
    )

    data class FindAll(
        val name: String,
        val owner: String,
        val description: String?,
        val zipcode: String?,
        val address: String?,
        val streetNumber: Int?,
        val startDay: String?,
        val startEnd: String?,
        val rating: Double,
        val isPublic: Boolean,
        val photoUrl: String?
    )
}


