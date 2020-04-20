package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tab_bar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irHome(v:View){
        val telaHome = Intent(this,TabBarActivity::class.java)
        startActivity(telaHome)
    }

    fun irCadastrar(v:View){
        val telaCadastro = Intent(this,CadastroActivity::class.java)
        startActivity(telaCadastro)
    }
}
