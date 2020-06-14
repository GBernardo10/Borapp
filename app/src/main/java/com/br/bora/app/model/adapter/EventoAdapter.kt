package com.br.bora.app.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.bora.app.R
import com.br.bora.app.model.Event
import kotlinx.android.synthetic.main.fragment_card_evento.view.*

class EventoAdapter(
    private val events: MutableList<Event.FindAll>,
    private val onItemClickListener: ((event: Event.FindAll) -> Unit)
) :
    RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_card_evento, parent, false)
        return EventoViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(events[position])
    }

    inner class EventoViewHolder(
        itemView: View,
        private val onItemClickListener: ((event: Event.FindAll) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event.FindAll) {
            with(event) {
                itemView.card_title.text = name
            }
            itemView.setOnClickListener {
                onItemClickListener.invoke(event)
            }
        }
    }
}



