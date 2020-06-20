package com.br.bora.app.repository

import android.view.View
import android.widget.EditText
import com.br.bora.app.AddEventActivity
import com.br.bora.app.R
import com.br.bora.app.model.ZipCode
import com.br.bora.app.services.config.RetrofitInitializer
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZipCodeRepository {

    fun findZipCode(zipCode: String, activity: AddEventActivity, v: View) {
        RetrofitInitializer.zipCodeService.findAddress(zipCode).enqueue(object : Callback<ZipCode> {
            override fun onFailure(call: Call<ZipCode>, t: Throwable) {

            }

            override fun onResponse(call: Call<ZipCode>, response: Response<ZipCode>) {
                if (!response.isSuccessful || response.body()?.erro == "true") Snackbar.make(
                    v,
                    R.string.zip_code_not_found,
                    Snackbar.LENGTH_LONG
                ).show()
                else if (response.body()?.logradouro.toString().isNotEmpty()) {
                    activity.findViewById<EditText>(R.id.address_event)
                        .setText(response.body()?.logradouro)
                } else {
                    Snackbar.make(
                        v,
                        R.string.zip_code_not_found,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}