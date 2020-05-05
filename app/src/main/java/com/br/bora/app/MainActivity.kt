package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.br.bora.app.model.RequestUserLogin
import com.br.bora.app.services.UserService
import com.br.bora.app.services.config.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            irHome()
//            val loginInput = findViewById<EditText>(R.id.login).text.toString()
//            val pwdInput = findViewById<EditText>(R.id.pass).text.toString()
//            signin(loginInput,pwdInput)
        }
    }
    private fun signin(username:String,password:String){
        val retIn = RetrofitInitializer.getRetrofitInstance().create(UserService::class.java)
        val signInInfo = RequestUserLogin(username,password)
        retIn.auth(signInInfo).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.code() == 200){
                    irHome()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("STATE", t.message.toString())
            }
        })
    }

/*
	"username":"Gesuvs",
	"password":"#futebol1996"

 */
    fun irEsqueciSenha(v:View){
        val telaEsqueciSenha = Intent(this,EsqueciMinhaSenha::class.java)
        startActivity(telaEsqueciSenha);
    }
    fun irHome(){
        val telaHome = Intent(this,TabBarActivity::class.java)
        startActivity(telaHome)
    }
    fun irCadastrar(v: View){
        val telaCadastro = Intent(this,CadastroPfActivity::class.java)
        startActivity(telaCadastro)
    }
}
