package com.br.bora.app.model.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.bora.app.DetailsEventoActivity
import com.br.bora.app.R
import com.br.bora.app.model.Event
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_card_evento.view.*

class EventAdapterPaged(private val context: Context) :
    PagedListAdapter<Event.FindAll, EventAdapterPaged.EventViewHolderPaged>(EVENT_COMPARATOR) {
    private lateinit var event: Event

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolderPaged {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_card_evento, parent, false)
        return EventViewHolderPaged(view)
    }

    override fun onBindViewHolder(holder: EventViewHolderPaged, position: Int) {
        event = Event()
        with(event) {
            id = getItem(position)?.id.toString()
            name = getItem(position)?.name.toString()
            owner = getItem(position)?.owner.toString()
            description = getItem(position)?.description.toString()
            address = getItem(position)?.address.toString()
            zipcode = getItem(position)?.zipcode.toString()
            streetNumber = getItem(position)?.streetNumber ?: 0
            startDay = getItem(position)?.startDay.toString()
            startEnd = getItem(position)?.startEnd.toString()
            rating = getItem(position)?.rating ?: 0F
            photoUrl = getItem(position)?.photoUrl.toString()
        }

        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(
                    DetailsEventoActivity.getStartIntent(
                        context,
                        event
                    )
                )
            )
        }
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class EventViewHolderPaged(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event.FindAll) {
            with(event) {
                itemView.card_date.text = name
                Glide.with(itemView.photo_event).load(photoUrl).placeholder(R.drawable.ic_logo)
                    .error(R.drawable.ic_logo)
                    .into(itemView.photo_event)
            }
        }
    }

    companion object {
        private val EVENT_COMPARATOR = object : DiffUtil.ItemCallback<Event.FindAll>() {
            override fun areItemsTheSame(oldItem: Event.FindAll, newItem: Event.FindAll): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Event.FindAll,
                newItem: Event.FindAll
            ): Boolean = newItem == oldItem
        }
    }

}