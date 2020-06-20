package com.br.bora.app.services

import com.br.bora.app.model.User
import com.br.bora.app.request.AuthUser
import com.br.bora.app.request.CreateUser
import com.br.bora.app.response.Token
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @POST("users/")
    fun createUser(@Body request: CreateUser): Call<ResponseBody>

    /*@PUT("users")
    fun userEdit(@Body request: RequestUser): Call<ResponseBody>*/

    @GET("users/{username}")
    fun userByMail(username: String?): Call<User>

    @POST("users/auth")
    fun auth(@Body request: AuthUser): Call<Token>

}