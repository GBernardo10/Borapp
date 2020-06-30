package com.br.bora.app.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.br.bora.app.AuthActivity
import com.br.bora.app.MainActivity
import com.br.bora.app.R
import com.br.bora.app.model.User
import com.br.bora.app.request.AuthUser
import com.br.bora.app.request.CreateUser
import com.br.bora.app.response.Token
import com.br.bora.app.services.TokenDecode
import com.br.bora.app.services.config.RetrofitInitializer
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    val userLiveData: MutableLiveData<User.UserInfo> = MutableLiveData()

    fun createUser(user: CreateUser, v: View) {
        RetrofitInitializer.userService.createUser(user).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("USUARIO", t.message.toString())
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                when (response.code()) {
                    201 -> {
                        Snackbar.make(v, "Usuario CADASTRADO", Snackbar.LENGTH_LONG)
                            .show()
                    }
                    else -> Snackbar.make(v, "usuario NAO CADASTRADO", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }


    fun auth(auth: AuthUser, activity: AuthActivity, v: View) {
        RetrofitInitializer.userService.auth(auth).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                when (response.code()) {
                    200 -> {
                        response.body()?.let { TokenDecode().decodeToken(activity, it) }
                        Log.d("TOKEN", response.body()?.token.toString())
                        activity.startActivity(
                            Intent(
                                activity.applicationContext,
                                MainActivity::class.java
                            )
                        )
                    }
                    204 -> {
                        Snackbar.make(v, R.string.auth_no_content, Snackbar.LENGTH_LONG).show()
                    }
                    401 -> {
                        Snackbar.make(v, R.string.auth_no_unauthorized, Snackbar.LENGTH_LONG)
                            .show()
                    }
                    else -> Snackbar.make(v, response.message(), Snackbar.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.i("STATE", t.message.toString())
                Snackbar.make(v, t.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        })
    }

    fun findByUsername(username: String, token: String, activity: MainActivity) {
        val myToken = "Bearer $token"
        RetrofitInitializer.userService.findUserByUsername(username, myToken)
            .enqueue(object : Callback<User.UserInfo> {
                override fun onFailure(call: Call<User.UserInfo>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<User.UserInfo>,
                    response: Response<User.UserInfo>
                ) {
                    if (response.isSuccessful) {
                        when (response.code()) {
                            200 -> {
                                response.body()?.let {
                                    val user = User.UserInfo(
                                        name = it.name,
                                        username = it.username,
                                        mail = it.mail,
                                        phone = it.phone
                                    )
                                    activity.findViewById<NavigationView>(R.id.nav_view)
                                        .getHeaderView(0)
                                        .findViewById<TextView>(R.id.username).text =
                                        it.name
                                }

                            }
                        }
                    }

                }

            })
    }
}