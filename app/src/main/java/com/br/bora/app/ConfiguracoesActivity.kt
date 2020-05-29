package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ConfiguracoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)
    }

    fun irEditarPerfil(v: View) {
        val telaEditarPerfil = Intent(this,EditarUsuarioActivity::class.java)
        startActivity(telaEditarPerfil)
    }

    fun irConfiguracoesPreferenciais(v: View) {
        val telaConfiguracoesPreferenciais = Intent(this,ConfiguracaoPreferenciaActivity::class.java)
        startActivity(telaConfiguracoesPreferenciais)
    }

    fun logout(v: View) {
        val telaLogin = Intent(this,MainActivity::class.java)
        startActivity(telaLogin)
    }
}
