package com.br.bora.app.repository

import android.util.Log
import android.view.View
import com.br.bora.app.model.Event
import com.br.bora.app.request.CreateEvent
import com.br.bora.app.services.config.RetrofitInitializer
import com.google.android.material.snackbar.Snackbar
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository {
    fun registerEvent(event: CreateEvent, v: View) {
        RetrofitInitializer.eventService.createEvent(event)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    when (response.code()) {
                        201 -> {
                            Snackbar.make(v, "EVENTO CADASTRADO", Snackbar.LENGTH_LONG)
                                .show()
                        }
                        else -> Snackbar.make(v, "EVENTO NAO CADASTRADO", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("STATE", t.message.toString())
                }
            })

    }

}