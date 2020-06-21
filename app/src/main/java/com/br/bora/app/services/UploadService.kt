package com.br.bora.app.services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface UploadService {
    @Multipart
    @POST("upload/save")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("file") name: RequestBody
    ): Call<ResponseBody>
}