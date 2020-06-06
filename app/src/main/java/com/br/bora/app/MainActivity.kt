package com.br.bora.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.br.bora.app.model.User
import com.br.bora.app.request.AuthUser
import com.br.bora.app.response.Token
import com.br.bora.app.services.TokenDecode
import com.br.bora.app.services.UserService
import com.br.bora.app.services.config.RetrofitInitializer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            goHome()

//            val loginInput = findViewById<EditText>(R.id.login).text.toString()
//            val pwdInput = findViewById<EditText>(R.id.pass).text.toString()
//            val auth = User.Auth(loginInput,pwdInput)
//            sign(auth,it)
        }
    }

    private fun sign(auth: User.Auth, v: View) {
        val retIn = RetrofitInitializer.getRetrofitInstance().create(UserService::class.java)
        val signInInfo = AuthUser(auth)

        retIn.auth(signInInfo).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                when (response.code()) {
//                    200 -> response.body()?.let { goHome(it) }
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
    fun goHome() {
        val telaHome = Intent(this, TabBarActivity::class.java)
        startActivity(telaHome)
    }


//    fun goHome(token:Token) {
//        val decoded = TokenDecode().decodeToken(token)
//        Log.d("token", decoded.toString())
//        val telaHome = Intent(this, TabBarActivity::class.java)
//        startActivity(telaHome)
//    }

    fun irCadastrar(v: View) {
        val telaCadastro = Intent(this, CadastroPfActivity::class.java)
        startActivity(telaCadastro)
    }
}
