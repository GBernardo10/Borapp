package com.br.bora.app.data_source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.br.bora.app.model.Event

class EventDataSourceFactory : DataSource.Factory<Int, Event.FindAll>() {
    val eventLiveDataSource = MutableLiveData<EventDataSource>()

    override fun create(): DataSource<Int, Event.FindAll> {
        val eventDataSource = EventDataSource()
        eventLiveDataSource.postValue(eventDataSource)
        return eventDataSource
    }

}