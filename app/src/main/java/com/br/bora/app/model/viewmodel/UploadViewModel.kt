package com.br.bora.app.model.viewmodel

import android.view.View
import com.br.bora.app.repository.UploadRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadViewModel {
    private val repository: UploadRepository = UploadRepository()

    fun uploadFile(body: MultipartBody.Part, name: RequestBody) {
        return repository.uploadFile(body, name)
    }
}