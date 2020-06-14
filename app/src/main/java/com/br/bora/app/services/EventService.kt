package com.br.bora.app.services

import com.br.bora.app.model.Evento
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface EventService {

    @GET("eventos")
    fun getEventos(): Call<List<Evento>>
}