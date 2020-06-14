package com.br.bora.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.br.bora.app.model.Event
import com.br.bora.app.model.adapter.EventoAdapter
import com.br.bora.app.model.viewmodel.EventoViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : Fragment() {

    private lateinit var viewModel: EventoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*view.add_role.setOnClickListener {
            startActivity(Intent(context, AddEventActivity::class.java))
        }
        view.my_role.setOnClickListener {
            startActivity(Intent(context, MeusRolesActivity::class.java))
        }
        view.settings.setOnClickListener {
            startActivity(Intent(context, CadastroPreferenciasActivity::class.java))
        }
        view.confirm.setOnClickListener {
            startActivity(Intent(context, ScannerActivity::class.java))
        }*/
        return inflater.inflate(R.layout.activity_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventoViewModel::class.java)
        viewModel.eventosLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { event ->
                with(rcv_home_event) {
                    layoutManager =
                        StaggeredGridLayoutManager(
                            2,
                            StaggeredGridLayoutManager.VERTICAL
                        )
                    adapter = EventoAdapter(event as MutableList<Event.FindAll>) {
                        this@HomeActivity.startActivity(
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
