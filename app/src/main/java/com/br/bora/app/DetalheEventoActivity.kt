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

    fun iniciaTela(){
        dEvento_btBora.setOnClickListener {

        }
    }

    fun preencherCampos(evento:Evento){
        dEvento_vTituloEvento.text = evento.name;
        //dEvento_tvOrganizador.text =
//        if(evento.isPago){
//            dEvento_tvValor.text = evento.valor
//        }
        //dEvento_tvDiaSemana.text = evento.startDay.toString();
        //dEvento_tvData.text = evento.startDay.toString();
        //dEvento_tvDetalhe.text = evento.descricao;
        //dEvento_tvCriacao.text = evento.dataCriacao;
        //dEvento_tvCategoria.text = evento.categoria;
        //dEvento_tvSaldo

    }



}


//        val imageBytes = Base64.decode("base64img",Base64.DEFAULT)
//        val decodedImg = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
//        qr_code.setImageBitmap(decodedImg)