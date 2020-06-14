package com.br.bora.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.bora.app.model.User
import com.br.bora.app.response.Token
import com.br.bora.app.services.TokenDecode
import com.br.bora.app.services.config.RetrofitInitializer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main.*
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
            Home()
//            val loginInput = findViewById<EditText>(R.id.login).text.toString()
//            val pwdInput = findViewById<EditText>(R.id.pass).text.toString()
//            val auth = User.Auth(loginInput, pwdInput)
//            sign(auth, it)
        }

    }

    private fun restartApplication() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    private fun sign(auth: User.Auth, v: View) {
        val retIn = RetrofitInitializer.userService.auth(auth)

        retIn.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                when (response.code()) {
                    200 -> response.body()?.let { goHome(it) }
                    204 -> Snackbar.make(v, R.string.auth_no_content, Snackbar.LENGTH_LONG).show()
                    401 -> Snackbar.make(v, R.string.auth_no_unauthorized, Snackbar.LENGTH_LONG)
                        .show()
                    else -> Snackbar.make(v, response.message(), Snackbar.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.i("STATE", t.message.toString())
                Snackbar.make(v, t.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        })
    }

    fun irEsqueciSenha(v: View) {
        val telaEsqueciSenha = Intent(this, EsqueciMinhaSenha::class.java)
        startActivity(telaEsqueciSenha);
    }


    fun goHome(token: Token) {
        val decoded = TokenDecode().decodeToken(token)
        Log.d("token", decoded.toString())
        val telaHome = Intent(this, TabBarActivity::class.java)
        startActivity(telaHome)
    }

    fun Home() {
        val telaHome = Intent(this, TesteDrawer::class.java)
        startActivity(telaHome)
    }

    fun irCadastrar(v: View) {
        val telaCadastro = Intent(this, CadastroPfActivity::class.java)
        startActivity(telaCadastro)
    }
}
