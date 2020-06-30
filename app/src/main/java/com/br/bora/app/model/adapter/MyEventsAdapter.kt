package com.br.bora.app.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.bora.app.R
import com.br.bora.app.model.Event
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_card_evento.view.*

class MyEventsAdapter(
    private val events: MutableList<Event.FindAll>,
    private val onItemClickListener: ((event: Event.FindAll) -> Unit)
) :
    RecyclerView.Adapter<MyEventsAdapter.EventoViewHolder>() {

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
                itemView.card_date.text = name
                Glide.with(itemView.photo_event).load(photoUrl).placeholder(R.drawable.ic_logo)
                    .error(R.drawable.ic_logo)
                    .into(itemView.photo_event)
            }
            itemView.setOnClickListener {
                onItemClickListener.invoke(event)
            }
        }
    }
}



