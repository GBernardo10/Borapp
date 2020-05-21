package com.br.bora.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class EventosPreferencias : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_eventos_preferencias,container,false)
    }

//    fun IrDetalhar (v:View){
//        //val telaDetalhar = Intent(this,DetalheEventoActivity::class.java);
////        val telaDetalhar = Intent(this,DetalheEventoActivity::class.java)
//        startActivity(telaDetalhar);
//    }
}
