package com.br.bora.app.data_source

import androidx.paging.PageKeyedDataSource
import com.br.bora.app.model.Event
import com.br.bora.app.services.config.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDataSource : PageKeyedDataSource<Int, Event.FindAll>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Event.FindAll>
    ) {
        val service = RetrofitInitializer.eventService
        val call = service.getEvents(PAGE)

        call.enqueue(object : Callback<List<Event.FindAll>> {
            override fun onFailure(call: Call<List<Event.FindAll>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<Event.FindAll>>,
                response: Response<List<Event.FindAll>>
            ) {
                when (response.code()) {
                    200 -> {
                        response.body()?.let { callback.onResult(it, null, PAGE + 1) }
                    }
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event.FindAll>) {
        val service = RetrofitInitializer.eventService
        val call = service.getEvents(params.key)
        call.enqueue(object : Callback<List<Event.FindAll>> {
            override fun onFailure(call: Call<List<Event.FindAll>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<Event.FindAll>>,
                response: Response<List<Event.FindAll>>
            ) {
                when (response.code()) {
                    200 -> {
                        response.body()
                        val key = params.key + 1
                        response.body()?.let { callback.onResult(it, key) }
                    }
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event.FindAll>) {
        val service = RetrofitInitializer.eventService
        val call = service.getEvents(params.key)
        call.enqueue(object : Callback<List<Event.FindAll>> {
            override fun onFailure(call: Call<List<Event.FindAll>>, t: Throwable) {}
            override fun onResponse(
                call: Call<List<Event.FindAll>>,
                response: Response<List<Event.FindAll>>
            ) {
                when (response.code()) {
                    200 -> {
                        response.body()
                        val key = if (params.key > 1) params.key - 1 else 0
                        response.body()?.let { callback.onResult(it, key) }
                    }
                }
            }
        })
    }

    companion object {
        const val PAGE = 1
    }
}