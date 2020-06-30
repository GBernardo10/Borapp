package com.br.bora.app.model.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.bora.app.model.Event
import com.br.bora.app.repository.EventRepository
import com.br.bora.app.request.CreateEvent
import com.br.bora.app.services.config.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventViewModel : ViewModel() {
    private var repository: EventRepository = EventRepository()

    val eventsLiveData: MutableLiveData<List<Event.FindAll>> = MutableLiveData()

    fun getEvents() {
        RetrofitInitializer.eventService.getEvents()
            .enqueue(object : Callback<List<Event.FindAll>> {
                override fun onFailure(call: Call<List<Event.FindAll>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<Event.FindAll>>,
                    response: Response<List<Event.FindAll>>
                ) {
                    val events: MutableList<Event.FindAll> = mutableListOf()
                    response.body()?.let {
                        for (result in it) {
                            val event = Event.FindAll(
                                owner = result.owner,
                                name = result.name,
                                startDay = result.startDay,
                                startEnd = result.startEnd,
                                rating = result.rating,
                                isPublic = result.isPublic,
                                photoUrl = result.photoUrl,
                                address= result.address,
                                zipcode =  result.zipcode,
                                streetNumber = result.streetNumber,
                                description = result.description
                            )
                            events.add(event)
                        }
                    }
                    eventsLiveData.value = events
                }
            })
    }

    fun createEvent(event: CreateEvent, v: View) {
        return repository.registerEvent(event, v)
    }

    fun participarEvento(id: Int?, username: String?, v: View){
        return repository.participarEvento(id,username,v);
    }
}
