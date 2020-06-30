package com.br.bora.app.services

import com.br.bora.app.model.Event
import com.br.bora.app.model.Participante
import com.br.bora.app.model.User
import com.br.bora.app.request.CreateEvent
import com.br.bora.app.request.ParticipateEvent
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface EventService {

    @GET("eventos/")
    fun getEvents(@Query("page") page: Int): Call<List<Event.FindAll>>

    @GET("eventos/")
    fun getEventsSearch(@Query("keys") keys: String): Call<List<Event.FindAll>>

    @GET("eventos/{id}")
    fun getEvent(@Path("id") id: Int?): Call<Event?>

    @POST("eventos/")
    fun createEvent(@Body request: CreateEvent): Call<ResponseBody>

    @PUT("eventos/")
    fun changeEvento(@Body request: Event): Call<ResponseBody>

    @POST("eventos/{id}/participate")
    fun participarEvento(
        @Path("id") id: String, @Body guest: ParticipateEvent
    ): Call<ResponseBody>

    @GET("eventos/{id}/guest")
    fun getAllParticipanteFromEvent(@Path("id") id: String): Call<List<Event.participateFromEvent>>

    @GET("eventos/{owner}")
    fun getEventsFromOwner(@Path("owner") owner: String): Call<List<Event.FindAll>>

}