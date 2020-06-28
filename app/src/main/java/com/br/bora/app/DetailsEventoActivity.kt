package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.bora.app.databinding.ActivityDetailsEventoBinding
import com.br.bora.app.model.Event
import kotlinx.android.synthetic.main.activity_detalhe_evento.*

class DetailsEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val event: Event = intent.getSerializableExtra(EXTRA_EVENT) as Event

        with(binding) {
            //ownerEvent.text = event.owner
            //titleEvent.text = event.name
            owner_details_evento.text = event.owner;
            name_details_evento.text = event.name;
            adress_detail_event.text = java.lang.String.format("%s %s %s",event.address + event.streetNumber + event.zipcode)
            date_detail_event.text = event.startDay;
            //value_detail_event.text = event.startDay
            description_detail_event.text = event.description;
            data_create_detail_event.text = event
            //category_detail_event.text = event
        }

        btn_bora_detail_event.setOnClickListener{

        }

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