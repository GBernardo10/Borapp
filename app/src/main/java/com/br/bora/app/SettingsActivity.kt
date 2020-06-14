package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.br.bora.app.utils.SaveData
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        saveData = SaveData(this)
        switch_theme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveData.setDarkModeState(true)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                saveData.setDarkModeState(false)
            }
        }

        btn_edit_password_settings.setOnClickListener {
            startActivity(Intent(this, SalvarNovaSenha::class.java))
        }
        btPreferencias.setOnClickListener {
            startActivity(Intent(this, ConfiguracaoPreferenciaActivity::class.java))
        }
    }
}