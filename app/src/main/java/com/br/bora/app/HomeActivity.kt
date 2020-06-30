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
import com.br.bora.app.model.adapter.EventAdapterPaged
import com.br.bora.app.model.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapterPaged = context?.let { EventAdapterPaged(it) }
        rcv_home_event.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val itemViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        itemViewModel.eventsPagedList.observe(viewLifecycleOwner, Observer {
            adapterPaged?.submitList(it)
        })
        adapterPaged?.notifyDataSetChanged()
        rcv_home_event.adapter = adapterPaged
    }
}
