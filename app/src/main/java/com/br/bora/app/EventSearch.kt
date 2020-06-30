package com.br.bora.app

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.br.bora.app.model.Event
import com.br.bora.app.model.adapter.EventAdapterPaged
import com.br.bora.app.model.adapter.EventoAdapter
import com.br.bora.app.model.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_search_eventos.*


class EventSearch : Fragment() {
    private lateinit var searchView: SearchView
    private lateinit var viewModel: EventViewModel
    private lateinit var evento: Event


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_search_eventos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadEvents("")
    }

    private fun loadEvents(keys: String) {
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        evento = Event()
        viewModel.eventsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { event ->
                with(rcv_search_eventos) {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = EventoAdapter(event as MutableList<Event.FindAll>) {
                        with(evento) {
                            id = it.id
                            name = it.name
                            owner = it.owner
                            description = it.description.toString()
                            address = it.address.toString()
                        }
                        this@EventSearch.startActivity(
                            DetailsEventoActivity.getStartIntent(
                                context,
                                evento
                            )
                        )
                    }
                }
            }
        })
        viewModel.getEvents(keys)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
        val item: MenuItem? = menu.findItem(R.id.action_search)
        if (item != null) {
            searchView =
                SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
            (context as MainActivity).supportActionBar?.title = "Pesquise eventos em alta"
            menu.findItem(R.id.action_search).apply {
                setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
                actionView = searchView
            }
            val searchPlate =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
            val closeBtn = searchView.findViewById(androidx.appcompat.R.id.search_close_btn) as ImageView
            with(searchPlate) {
                hint = "Pesquise por eventos..."
                setHintTextColor(resources.getColor(R.color.colorText))
                setTextColor(resources.getColor(R.color.colorText))
            }
            closeBtn.setImageResource(R.drawable.ic_close_light)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null)
                        loadEvents(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null)
                        loadEvents(newText)
                    return false
                }
            })
            val searchManager =
                this.activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }
    }
}
