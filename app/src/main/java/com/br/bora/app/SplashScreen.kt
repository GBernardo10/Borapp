package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashTimeOut = 2000
        val mainActivity = Intent(this, AuthActivity::class.java)
        Handler().postDelayed({
            startActivity(mainActivity)
            finish()
        }, splashTimeOut.toLong())
    }
}
