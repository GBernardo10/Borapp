package com.br.bora.app.utils

import android.content.Context
import android.content.SharedPreferences

class SaveData(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("file", Context.MODE_PRIVATE)

    fun setDarkModeState(state: Boolean?) {
        val editor: SharedPreferences.Editor? = sharedPreferences.edit()
        editor?.putBoolean("dark", state!!)
        editor?.apply()
    }

    fun loadDarkModeState(): Boolean? {
        return sharedPreferences.getBoolean("dark", false)
    }
}