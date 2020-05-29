package com.br.bora.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.setPadding
import kotlinx.android.synthetic.main.activity_configuracao_preferencia.*
import kotlinx.android.synthetic.main.activity_confirmar_evento.view.*

class ConfiguracaoPreferenciaActivity : AppCompatActivity() {

    var preferencias:SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracao_preferencia)

        preferencias = getPreferences(Context.MODE_PRIVATE)

        val futebol = preferencias?.getBoolean("futebol", false)
        val rpg = preferencias?.getBoolean("rpg", false)
        val festas = preferencias?.getBoolean("festas", false)
        val bares = preferencias?.getBoolean("bares", false)
        val ioga = preferencias?.getBoolean("ioga", false)
        val shows = preferencias?.getBoolean("shows", false)

        if (futebol == true) {
            card1.setPadding(3, 3, 3, 3)
        }
        if (rpg == true) {
            card2.setPadding(3, 3, 3, 3)
        }
        if (festas == true) {
            card3.setPadding(3, 3, 3, 3)
        }
        if (bares == true) {
            card4.setPadding(3, 3, 3, 3)
        }
        if (ioga == true) {
            card5.setPadding(3, 3, 3, 3)
        }
        if (shows == true) {
            card6.setPadding(3, 3, 3, 3)
        }
    }

    var futebolSelected = false;
    var rpgSelected = false;
    var festasSelected = false;
    var baresSelected = false;
    var iogaSelected = false;
    var showsSelected = false;

    fun onChangeFutebol(v: View) {
        val editor = preferencias?.edit()
        if (futebolSelected == false) {
            this.futebolSelected = true
            editor?.putBoolean("futebol", true)
            card1.setPadding(3, 3, 3, 3)
        }else {
            this.futebolSelected = false
            editor?.putBoolean("futebol", false)
            card1.setPadding(0, 0,0, 0)
        }
        editor?.commit()
    }

    fun onChangeRpg(v: View) {
        val editor = preferencias?.edit()
        if (rpgSelected == false) {
            this.rpgSelected = true
            editor?.putBoolean("rpg", true)
            card2.setPadding(3, 3, 3, 3)
        }else {
            this.rpgSelected = false
            editor?.putBoolean("rpg", false)
            card2.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }

    fun onChangeFestas(v: View) {
        val editor = preferencias?.edit()
        if (festasSelected == false) {
            this.festasSelected = true
            editor?.putBoolean("festas", true)
            card3.setPadding(3, 3, 3, 3)
        }else {
            this.festasSelected = false
            editor?.putBoolean("festas", false)
            card3.setPadding(0,0,0,0)
        }
        editor?.commit()
    }

    fun onChangeBares(v: View) {
        val editor = preferencias?.edit()
        if (baresSelected == false) {
            this.baresSelected = true
            editor?.putBoolean("bares", true)
            card4.setPadding(3, 3, 3, 3)
        }else {
            this.baresSelected = false
            editor?.putBoolean("bares", false)
            card4.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }

    fun onChangeIoga(v: View) {
        val editor = preferencias?.edit()
        if (iogaSelected == false) {
            this.iogaSelected = true
            editor?.putBoolean("ioga", true)
            card5.setPadding(3, 3, 3, 3)
        }else {
            this.iogaSelected = false
            editor?.putBoolean("ioga", false)
            card5.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }

    fun onChangeShows(v: View) {
        val editor = preferencias?.edit()
        if (showsSelected == false) {
            this.showsSelected = true
            editor?.putBoolean("shows", true)
            card6.setPadding(3, 3, 3, 3)
        }else {
            this.showsSelected = false
            editor?.putBoolean("shows", false)
            card6.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }

    fun irConfiguracao(v: View) {
        val telaConfiguracoes = Intent(this, ConfiguracoesActivity::class.java);
        startActivity(telaConfiguracoes);
    }

    fun salvar(v: View) {
        val editor = preferencias?.edit()
        editor?.commit()
    }
}
