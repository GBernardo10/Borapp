package com.br.bora.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.br.bora.app.model.Evento
import com.br.bora.app.model.adapter.EventoAdapter
import com.br.bora.app.model.viewmodel.EventoViewModel
import kotlinx.android.synthetic.main.fragment_search_eventos.*
import okhttp3.ResponseBody

class EventSearch : Fragment() {
    private lateinit var viewModel: EventoViewModel

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

        viewModel = ViewModelProviders.of(this).get(EventoViewModel::class.java)
        viewModel.eventosLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { evento ->
                with(rcv_search_eventos) {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = EventoAdapter(evento as MutableList<Evento>) {
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
        viewModel.getEventos()
    }

}
