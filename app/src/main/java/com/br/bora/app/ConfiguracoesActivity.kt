package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_configuracoes.*

class ConfiguracoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        btEditarPerfil.setOnClickListener {
            startActivity(Intent(this,EditarUsuarioActivity::class.java))
        }
        btEditarSenha.setOnClickListener {
            startActivity(Intent(this,SalvarNovaSenha::class.java))
        }
        btPreferencias.setOnClickListener {
            startActivity(Intent(this,ConfiguracaoPreferenciaActivity::class.java))
        }
        btSair.setOnClickListener {
            //Limpar o preferences com o nome do usuário
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
