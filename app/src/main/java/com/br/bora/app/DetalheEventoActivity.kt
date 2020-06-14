package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalhe_evento.*

class DetalheEventoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_evento)

        val owner = intent.getStringExtra(EXTRA_OWNER)
        val name = intent.getStringExtra(EXTRA_NAME)
        owner_details_evento.text = owner
        name_details_evento.text = name
    }

    companion object {
        private const val EXTRA_OWNER = "EXTRA_OWNER"
        private const val EXTRA_NAME = "EXTRA_NAME"
        fun getStartIntent(context: Context, owner: String, name: String): Intent {
            return Intent(context, DetalheEventoActivity::class.java).apply {
                putExtra(EXTRA_OWNER, owner)
                putExtra(EXTRA_NAME, name)
            }
        }
    }


}


//        val imageBytes = Base64.decode("base64img",Base64.DEFAULT)
//        val decodedImg = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
//        qr_code.setImageBitmap(decodedImg)