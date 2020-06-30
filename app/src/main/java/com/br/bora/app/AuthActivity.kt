package com.br.bora.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.br.bora.app.model.User
import com.br.bora.app.model.viewmodel.UserViewModel
import com.br.bora.app.request.AuthUser
import com.br.bora.app.response.Token
import com.br.bora.app.services.TokenDecode
import com.br.bora.app.services.config.RetrofitInitializer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_auth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        /*switch_theme.setOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }*/

        btnLogin.setOnClickListener {
//            Home()
            val loginInput = findViewById<EditText>(R.id.login).text.toString()
            val pwdInput = findViewById<EditText>(R.id.pass).text.toString()
            val auth = AuthUser(User.Auth(loginInput, pwdInput))
            sign(auth, this, it)
        }
    }

    private fun sign(auth: AuthUser, activity: AuthActivity, view: View) {
        UserViewModel().auth(auth, activity, view)

    }

    fun irEsqueciSenha(v: View) {
        val telaEsqueciSenha = Intent(this, EsqueciMinhaSenha::class.java)
        startActivity(telaEsqueciSenha);
    }

    fun Home() {
        val telaHome = Intent(this, MainActivity::class.java)
        startActivity(telaHome)
    }

    fun irCadastrar(v: View) {
        val telaCadastro = Intent(this, RegisterUserActivity::class.java)
        startActivity(telaCadastro)
    }
}
