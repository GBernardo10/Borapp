package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    fun IrCadastrarPf(v:View){
        val telaPf = Intent(this,CadastroPfActivity::class.java);
        startActivity(telaPf);
    }

    fun IrCadastroPj(v:View){
        val telaPj = Intent(this,CadastroPjActivity::class.java);
        startActivity(telaPj);
    }
}
