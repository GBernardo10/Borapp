package com.br.bora.app.model.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.br.bora.app.AddEventActivity
import com.br.bora.app.model.ZipCode
import com.br.bora.app.repository.ZipCodeRepository

class ZipCodeViewModel : ViewModel() {
    private val repository: ZipCodeRepository = ZipCodeRepository()

    fun findZipCode(zipCode: String, activity: AddEventActivity, v: View) {
        return repository.findZipCode(zipCode, activity, v)
    }
}