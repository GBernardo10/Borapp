package com.br.bora.app.services

import com.br.bora.app.model.Evento
import com.br.bora.app.request.RequestEvento
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface EventoService {

    @Headers("Content-Type:application/json")
    @GET("eventos/{id}")
    fun getEvento(@Path("id") id:Int?): Call<Evento?>

    @Headers("Content-Type:application/json")
    @POST("eventos")
    fun cadastraEvento(@Body request:RequestEvento): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @PUT("eventos")
    fun changeEvento(@Body request:RequestEvento): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("eventos/{id}/{username}")
    fun participarEvento(@Path("id") id: Int?,@Path("username") username: String?): Call<ResponseBody>



}