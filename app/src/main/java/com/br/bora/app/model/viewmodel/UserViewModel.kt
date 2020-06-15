package com.br.bora.app.model.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.br.bora.app.AuthActivity
import com.br.bora.app.MainActivity
import com.br.bora.app.model.User
import com.br.bora.app.repository.UserRepository
import com.br.bora.app.request.AuthUser
import com.br.bora.app.request.CreateUser

class UserViewModel : ViewModel() {
    private var repository: UserRepository = UserRepository()

    fun createUser(user: CreateUser) {
        return repository.createUser(user)
    }

    fun auth(user: AuthUser, activity: AuthActivity, view: View) {
        return repository.auth(user, activity, view)
    }
}