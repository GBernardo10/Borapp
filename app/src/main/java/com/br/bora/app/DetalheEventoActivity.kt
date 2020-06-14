package com.br.bora.app

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import androidx.annotation.RequiresApi
import com.br.bora.app.model.Evento
import kotlinx.android.synthetic.main.activity_detalhe_evento.*

class DetalheEventoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_evento)

        iniciaTela()
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
