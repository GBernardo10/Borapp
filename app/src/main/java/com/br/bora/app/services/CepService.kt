package com.br.bora.app.services

import com.br.bora.app.model.Cep
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CepService {
    @Headers("Content-Type:application/json")
    @GET("https://viacep.com.br/ws/{cep}/json/")
    fun getEndereco(@Path("cep") cep: String): Call<Cep>
}