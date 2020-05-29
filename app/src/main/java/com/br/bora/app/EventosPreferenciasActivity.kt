package com.br.bora.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.view.*

class EventosPreferenciasActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD:app/src/main/java/com/br/bora/app/EventosPreferencias.kt
        return inflater.inflate(R.layout.activity_eventos_preferencias,container,false)
    }

//    fun IrDetalhar (v:View){
//        //val telaDetalhar = Intent(this,DetalheEventoActivity::class.java);
////        val telaDetalhar = Intent(this,DetalheEventoActivity::class.java)
//        startActivity(telaDetalhar);
//    }
=======
        val view = inflater.inflate(R.layout.activity_eventos_preferencias, container, false)

        view.settingsIcon.setOnClickListener { view ->
            startActivity(Intent(view.context, ConfiguracoesActivity::class.java))
        }
        return view;
    }
>>>>>>> feature/gesuvs:app/src/main/java/com/br/bora/app/EventosPreferenciasActivity.kt
}
