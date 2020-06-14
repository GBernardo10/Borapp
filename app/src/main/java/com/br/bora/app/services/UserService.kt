package com.br.bora.app.services


import com.br.bora.app.model.User
import com.br.bora.app.request.AuthUser
import com.br.bora.app.response.Token
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type:application/json")
    @POST("users/auth")
    fun auth(@Body request:User.Auth):Call<Token>
}