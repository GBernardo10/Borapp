package com.br.bora.app.model.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.bora.app.model.Event
import com.br.bora.app.services.config.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventoViewModel : ViewModel() {

    val eventosLiveData: MutableLiveData<List<Event.FindAll>> = MutableLiveData()

    fun getEventos() {
        RetrofitInitializer.eventService.getEvents()
            .enqueue(object : Callback<List<Event.FindAll>> {
                override fun onFailure(call: Call<List<Event.FindAll>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<Event.FindAll>>,
                    response: Response<List<Event.FindAll>>
                ) {
                    val eventos: MutableList<Event.FindAll> = mutableListOf()
                    response.body()?.let {
                        for (result in it) {
                            val evento = Event.FindAll(
                                owner = result.owner,
                                name = result.name,
                                startDay = result.startDay,
                                startEnd = result.startEnd,
                                rating = result.rating,
                                isPrivate = result.isPrivate
                            )
                            eventos.add(evento)
                        }
                    }
                    eventosLiveData.value = eventos
                }
            })
    }
}