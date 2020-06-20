package com.br.bora.app.services

import com.br.bora.app.model.ZipCode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ZipCodeService {
    @GET("https://viacep.com.br/ws/{cep}/json/")
    fun findAddress(@Path("cep") zipCode: String): Call<ZipCode>
}