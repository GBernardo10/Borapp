package com.br.bora.app

import android.content.Context
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

    var futebolSelected = false;
    var rpgSelected = false;
    var festasSelected = false;
    var baresSelected = false;
    var iogaSelected = false;
    var showsSelected = false;
    var preferencias:SharedPreferences? = null
    val editor = preferencias?.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracao_preferencia)

        preferencias = getPreferences(Context.MODE_PRIVATE)

        val futebol = preferencias?.getBoolean("futebol", this.futebolSelected)
        val rpg = preferencias?.getBoolean("rpg", this.rpgSelected)
        val festas = preferencias?.getBoolean("festas", this.festasSelected)
        val bares = preferencias?.getBoolean("bares", this.baresSelected)
        val ioga = preferencias?.getBoolean("ioga", this.iogaSelected)
        val shows = preferencias?.getBoolean("shows", this.showsSelected)
    }

    fun onChangeFutebol(v: View) {
        if (futebolSelected == false) {
            this.futebolSelected = true
            editor?.putBoolean("futebol", this.futebolSelected)
            card1.setPadding(3, 3, 3, 3)
        }else {
            this.futebolSelected = false
            editor?.putBoolean("futebol", this.futebolSelected)
            card1.setPadding(0, 0,0, 0)
        }
        editor?.commit()
    }

    fun onChangeRpg(v: View) {
        if (rpgSelected == false) {
            this.rpgSelected = true
            editor?.putBoolean("rpg", this.rpgSelected)
            card2.setPadding(3, 3, 3, 3)
        }else {
            this.rpgSelected = false
            editor?.putBoolean("rpg", this.rpgSelected)
            card2.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }

    fun onChangeFestas(v: View) {
        if (festasSelected == false) {
            this.festasSelected = true
            editor?.putBoolean("festas", this.festasSelected)
            card3.setPadding(3, 3, 3, 3)
        }else {
            this.festasSelected = false
            editor?.putBoolean("festas", this.festasSelected)
            card3.setPadding(0,0,0,0)
        }
        editor?.commit()
    }

    fun onChangeBares(v: View) {
        if (baresSelected == false) {
            this.baresSelected = true
            editor?.putBoolean("bares", this.baresSelected)
            card4.setPadding(3, 3, 3, 3)
        }else {
            this.baresSelected = false
            editor?.putBoolean("bares", this.baresSelected)
            card4.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }

    fun onChangeIoga(v: View) {
        if (iogaSelected == false) {
            this.iogaSelected = true
            editor?.putBoolean("ioga", this.iogaSelected)
            card5.setPadding(3, 3, 3, 3)
        }else {
            this.iogaSelected = false
            editor?.putBoolean("ioga", this.iogaSelected)
            card5.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }

    fun onChangeShows(v: View) {
        if (showsSelected == false) {
            this.showsSelected = true
            editor?.putBoolean("shows", this.iogaSelected)
            card6.setPadding(3, 3, 3, 3)
        }else {
            this.showsSelected = false
            editor?.putBoolean("shows", this.iogaSelected)
            card6.setPadding(0, 0, 0, 0)
        }
        editor?.commit()
    }
}
