package com.br.bora.app.model.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.br.bora.app.EventSearch
import com.br.bora.app.R
import com.br.bora.app.data_source.EventDataSource
import com.br.bora.app.data_source.EventDataSourceFactory
import com.br.bora.app.model.Event
import com.br.bora.app.model.Participante
import com.br.bora.app.model.User
import com.br.bora.app.repository.EventRepository
import com.br.bora.app.request.CreateEvent
import com.br.bora.app.request.ParticipateEvent
import com.br.bora.app.services.config.RetrofitInitializer
import kotlinx.android.synthetic.main.fragment_search_eventos.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventViewModel : ViewModel() {
    private var repository: EventRepository = EventRepository()

    val eventsPagedList: LiveData<PagedList<Event.FindAll>>
    private val liveDataSource: LiveData<EventDataSource>
    val eventsLiveData: MutableLiveData<List<Event.FindAll>> = MutableLiveData()
    val eventsFromOwnerLiveData: MutableLiveData<List<Event.FindAll>> = MutableLiveData()
    val usersLiveData: MutableLiveData<List<Event.participateFromEvent>> = MutableLiveData()

    init {
        val itemDataSourceFactory = EventDataSourceFactory()
        liveDataSource = itemDataSourceFactory.eventLiveDataSource
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setPageSize(EventDataSource.PAGE).build()
        eventsPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    fun getEventsFromOwner(owner: String) {
        RetrofitInitializer.eventService.getEventsFromOwner(owner)
            .enqueue(object : Callback<List<Event.FindAll>> {
                override fun onFailure(call: Call<List<Event.FindAll>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<Event.FindAll>>,
                    response: Response<List<Event.FindAll>>
                ) {
                    val events: MutableList<Event.FindAll> = mutableListOf()
                    response.body()?.let {
                        for (result in it) {
                            val event = Event.FindAll(
                                id = result.id,
                                owner = result.owner,
                                name = result.name,
                                startDay = result.startDay,
                                startEnd = result.startEnd,
                                rating = result.rating,
                                isPublic = result.isPublic,
                                photoUrl = result.photoUrl,
                                description = result.description,
                                address = result.address,
                                zipcode = result.zipcode,
                                streetNumber = result.streetNumber
                            )
                            events.add(event)
                        }
                    }
                    eventsFromOwnerLiveData.value = events
                }
            })
    }

    fun getPartipantes(id: String) {
        RetrofitInitializer.eventService.getAllParticipanteFromEvent(id)
            .enqueue(object : Callback<List<Event.participateFromEvent>> {
                override fun onFailure(call: Call<List<Event.participateFromEvent>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<Event.participateFromEvent>>,
                    response: Response<List<Event.participateFromEvent>>
                ) {
                    val users: MutableList<Event.participateFromEvent> = mutableListOf()
                    response.body()?.let {
                        for (result in it) {
                            val user = Event.participateFromEvent(
                                id = result.id,
                                eventFk = result.eventFk,
                                username = result.username
                            )
                            users.add(user)
                        }
                    }
                    usersLiveData.value = users
                }
            })
    }

    fun getEvents(keys: String) {
        RetrofitInitializer.eventService.getEventsSearch(keys)
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
                                id = result.id,
                                owner = result.owner,
                                name = result.name,
                                startDay = result.startDay,
                                startEnd = result.startEnd,
                                rating = result.rating,
                                isPublic = result.isPublic,
                                photoUrl = result.photoUrl,
                                description = result.description,
                                address = result.address,
                                zipcode = result.zipcode,
                                streetNumber = result.streetNumber
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

    fun participateEvent(id: String, guest: ParticipateEvent, v: View) {
        return repository.participateEvent(id, guest, v)
    }
}
