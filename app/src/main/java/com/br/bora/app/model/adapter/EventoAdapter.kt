package com.br.bora.app.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.bora.app.R
import com.br.bora.app.model.Evento
import kotlinx.android.synthetic.main.fragment_card_evento.view.*

class EventoAdapter(
    private val eventos: MutableList<Evento>,
    private val onItemClickListener: ((evento: Evento) -> Unit)
) :
    RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_card_evento, parent, false)
        return EventoViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(eventos[position])
    }

    inner class EventoViewHolder(
        itemView: View,
        private val onItemClickListener: ((evento: Evento) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(evento: Evento) {
            with(evento) {
                itemView.card_title.text = name
            }
            itemView.setOnClickListener {
                onItemClickListener.invoke(evento)
            }
        }
    }
}



