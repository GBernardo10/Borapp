package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MeusRolesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meus_roles)
    }

    fun irAlterarEventos(v:View){
        startActivity(Intent(this,EditarEventoActivity::class.java))
    }

    fun irVisualizarEventos(v:View){
        startActivity(Intent(this,DetalheEventoActivity::class.java))
    }

}
