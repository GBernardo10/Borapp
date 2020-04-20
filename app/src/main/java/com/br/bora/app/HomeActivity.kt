package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun IrPreferencias(v:View){
        val telaEventosPreferenciais = Intent(this, EventosPreferencias::class.java);
        startActivity(telaEventosPreferenciais);
    }

    fun IrRoles(v:View){
        val telaMeusRoles = Intent(this, MeusRoles::class.java);
        startActivity(telaMeusRoles);
    }
}
