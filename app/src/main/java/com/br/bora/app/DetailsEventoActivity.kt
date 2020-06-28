package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.bora.app.databinding.ActivityDetailsEventoBinding
import com.br.bora.app.model.Event

class DetailsEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val event: Event = intent.getSerializableExtra(EXTRA_EVENT) as Event

        with(binding) {
            ownerEvent.text = event.owner
            titleEvent.text = event.name
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