package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.bora.app.databinding.ActivityDetailsEventoBinding
import com.br.bora.app.model.Event
import com.br.bora.app.model.Participante
import com.br.bora.app.model.User
import com.br.bora.app.model.adapter.ParticipanteAdapter
import com.br.bora.app.model.viewmodel.EventViewModel
import com.br.bora.app.request.ParticipateEvent
import com.br.bora.app.utils.SaveData
import java.util.*

class DetailsEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsEventoBinding
    private lateinit var mSaveData: SaveData
    private lateinit var viewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val event: Event = intent.getSerializableExtra(EXTRA_EVENT) as Event
        mSaveData = SaveData(applicationContext)
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        viewModel.usersLiveData.observe(this, Observer {
            it?.let { user ->
                with(binding.rcListGuest) {
                    layoutManager = LinearLayoutManager(
                        this@DetailsEventoActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    adapter = ParticipanteAdapter(user as MutableList<Event.participateFromEvent>)
                }
            }
        })
        viewModel.getPartipantes(event.id)
        val username = mSaveData.getStore("auth")!!

//        if (username.toUpperCase(Locale.ROOT) == event.owner.toUpperCase(Locale.ROOT)) {
//            binding.btnBora.isEnabled = false
//            binding.btnBora.setBackgroundColor(resources.getColor(R.color.colorBtnBackgroundDisabled))
//        }

        with(binding) {
            ownerEvent.text = event.owner
            titleEvent.text = event.name
            ratingEvent.rating = event.rating
            addressEvent.text = event.address
            descriptionEvent.text = event.description
        }

        val participate = ParticipateEvent(
            Event.participateEvent(
                username = username
            )
        )



        binding.btnBora.setOnClickListener {
            confirmed(event.id, participate, it)
        }
    }

    private fun confirmed(id: String, guest: ParticipateEvent, v: View) {
        EventViewModel().participateEvent(id, guest, v)
    }

    companion object {
        private const val EXTRA_EVENT = "EXTRA_EVENT"
        fun getStartIntent(context: Context, event: Event): Intent {
            return Intent(context, DetailsEventoActivity::class.java).apply {
                putExtra(EXTRA_EVENT, event)
            }
        }
    }
}


//        val imageBytes = Base64.decode("base64img",Base64.DEFAULT)
//        val decodedImg = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
//        qr_code.setImageBitmap(decodedImg)