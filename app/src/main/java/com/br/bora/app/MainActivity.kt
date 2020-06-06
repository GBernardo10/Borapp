package com.br.bora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.br.bora.app.request.RequestUserLogin
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
        val signInInfo =
            RequestUserLogin(username, password)
        retIn.auth(signInInfo).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.code() == 201){
                    irHome()
                }else{
                    main_messageError.text = getString(R.string.loginSenhaError)
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
    fun validaCampos(): Boolean{
        if(login.text.isEmpty()){
            login.requestFocus()
            login.error = getString(R.string.loginError)
            return false;
        }
        if(pass.text.isEmpty()){
            pass.requestFocus()
            pass.error = getString(R.string.senhaError)
        }
        return true;
    }

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
