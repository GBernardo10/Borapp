package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EsqueciMinhaSenha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueci_minha_senha)
    }

    fun irMain(v: View){
        val telaMain = Intent(this,MainActivity::class.java)
        startActivity(telaMain);
    }
}
