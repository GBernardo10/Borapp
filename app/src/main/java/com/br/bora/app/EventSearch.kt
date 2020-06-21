package com.br.bora.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.br.bora.app.model.Event
import com.br.bora.app.model.adapter.EventoAdapter
import com.br.bora.app.model.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_search_eventos.*

class EventSearch : Fragment() {
    private lateinit var viewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        refresh_layout.setOnRefreshListener {
//            fetchRepos()
//        }
//        fetchRepos()


        return inflater.inflate(R.layout.fragment_search_eventos, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        viewModel.eventsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { event ->
                with(rcv_search_eventos) {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = EventoAdapter(event as MutableList<Event.FindAll>) {
                        this@EventSearch.startActivity(
                            DetalheEventoActivity.getStartIntent(
                                context,
                                it.owner,
                                it.name
                            )
                        )
                    }
                }
            }
        })
        viewModel.getEvents()
    }
}
