package com.br.bora.app.repository

import android.widget.Toast
import com.br.bora.app.services.config.RetrofitInitializer
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadRepository {
    fun uploadFile(body: MultipartBody.Part, name: RequestBody) {
        RetrofitInitializer.uploadService.uploadImage(body, name).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
            }
        })
    }
}