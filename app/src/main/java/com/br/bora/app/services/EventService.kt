package com.br.bora.app.services

import com.br.bora.app.model.Event
import com.br.bora.app.request.RequestEvento
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface EventService {

    @GET("eventos")
    fun getEvents(): Call<List<Event.FindAll>>

    @GET("eventos/{id}")
    fun getEvent(@Path("id") id:Int?): Call<Event?>

    @POST("eventos")
    fun createEvent(@Body request: Event.Create): Call<ResponseBody>

    @PUT("eventos")
    fun changeEvento(@Body request: Event): Call<ResponseBody>

    @POST("eventos/{id}/{username}")
    fun participarEvento(@Path("id") id: Int?, @Path("username") username: String?): Call<ResponseBody>
}