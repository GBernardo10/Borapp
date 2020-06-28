package com.br.bora.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.bora.app.model.Event
import kotlinx.android.synthetic.main.activity_detalhe_evento.*
import java.io.Serializable

class DetalheEventoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_evento)

        val event = intent.getSerializableExtra("a") as Event.FindAll;
        owner_details_evento.text = event.owner;
        name_details_evento.text = event.name;
        //adress_detail_event.text = event.
        //date_detail_event.text = event.startDay;
        //value_detail_event.text = event.value
        //description_detail_event.text = event.description;
        //data_create_detail_event.text = event.
        //category_detail_event.text = event
    }

    companion object {
        private const val EXTRA_EVENT = "EXTRA_EVENT"
        fun getStartIntent(context: Context, event: Event.FindAll): Intent {
            return Intent(context, DetalheEventoActivity::class.java).apply {

            }
        }
    }


}


//        val imageBytes = Base64.decode("base64img",Base64.DEFAULT)
//        val decodedImg = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
//        qr_code.setImageBitmap(decodedImg)