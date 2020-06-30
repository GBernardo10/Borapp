package com.br.bora.app.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.bora.app.R
import com.br.bora.app.model.Event
import kotlinx.android.synthetic.main.item_guest_event.view.*

class ParticipanteAdapter(private val participantes: MutableList<Event.participateFromEvent>) :
    RecyclerView.Adapter<ParticipanteAdapter.ParticipanteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipanteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_guest_event, parent, false)
        return ParticipanteViewHolder(view)
    }

    override fun getItemCount(): Int = participantes.size

    override fun onBindViewHolder(holder: ParticipanteViewHolder, position: Int) {
        holder.bind(participantes[position])
    }

    inner class ParticipanteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event.participateFromEvent) {
            with(event) {
                itemView.username_guest.text = username
            }
        }
    }
}