package com.br.bora.app.services

import com.br.bora.app.model.User
import com.br.bora.app.request.RequestUser
import com.br.bora.app.request.RequestUserLogin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type:application/json")
    @POST("auth")
    fun auth(@Body request: RequestUserLogin):Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("users")
    fun user(@Body request: RequestUser):Call<ResponseBody>

    @Headers
    @GET("users/{username}")
    fun userByMail(username: String?): Call<User>
}