package com.br.bora.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.br.bora.app.model.Event
import com.br.bora.app.model.adapter.MyEventsAdapter
import com.br.bora.app.model.viewmodel.EventViewModel
import com.br.bora.app.utils.SaveData
import kotlinx.android.synthetic.main.fragment_my_events.*

/**
 * A simple [Fragment] subclass.
 */
class MyEventsFragment : Fragment() {
    private lateinit var viewModel: EventViewModel
    private lateinit var evento: Event
    private lateinit var saveData: SaveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        saveData = context?.let { SaveData(it) }!!
        val mOwner = saveData.getStore("auth")
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        evento = Event()
        viewModel.eventsFromOwnerLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { event ->
                with(rcv_my_eventos) {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = MyEventsAdapter(event as MutableList<Event.FindAll>) {
                        with(evento) {
                            id = it.id
                            name = it.name
                            owner = it.owner
                            description = it.description.toString()
                            address = it.address.toString()
                        }
                        this@MyEventsFragment.startActivity(
                            DetailsEventoActivity.getStartIntent(
                                context,
                                evento
                            )
                        )
                    }
                }
            }
        })
        if (mOwner != null) {
            viewModel.getEventsFromOwner(mOwner)
        }
    }

}
