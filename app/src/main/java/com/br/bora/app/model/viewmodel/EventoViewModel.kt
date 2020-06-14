package com.br.bora.app.model.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.bora.app.model.Evento
import com.br.bora.app.services.config.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventoViewModel : ViewModel() {

    val eventosLiveData: MutableLiveData<List<Evento>> = MutableLiveData()

    fun getEventos() {
        RetrofitInitializer.eventService.getEventos().enqueue(object : Callback<List<Evento>> {
            override fun onFailure(call: Call<List<Evento>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Evento>>, response: Response<List<Evento>>) {
                val eventos: MutableList<Evento> = mutableListOf()
                response.body()?.let {
                    for (result in it) {
                        val evento = Evento(
                            owner = result.owner,
                            name = result.name,
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